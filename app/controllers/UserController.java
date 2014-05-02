package controllers;

import java.util.HashMap;
import conf.ServicesFactory;
import models.Miembro;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class UserController extends Controller {
	static Form<UserForm> userForm = Form.form(UserForm.class);

	public static Result newUser(boolean update) {

		userForm = Form.form(UserForm.class).bindFromRequest();
		HashMap<String, String> datos = (HashMap<String, String>) userForm
				.data();
		String usuario = datos.get("userName");
		String nombre = datos.get("name");
		String apellidos = datos.get("surname");
		String email = datos.get("email");
		String pass = datos.get("pass");
		String pass2 = datos.get("pass2");
		
		if (userForm.hasErrors()) {
			return badRequest(register.render(userForm, update));
		}

		if (update) {

			User user = ServicesFactory.getUsersService().findByUserName(
					usuario);

			user.setName(nombre);
			user.setSurname(apellidos);
			user.setEmail(email);
			user.setPassword(pass2);

			ServicesFactory.getUsersService().update(user);
			return redirect(routes.UserController.showUsers());

		} else {
			ServicesFactory.getMiembroService().createMiembro(
					new Miembro(usuario, nombre, apellidos, pass2, email));
			return redirect(routes.UserController.showUsers());

		}
	}

	public static Result showUsers() {
		return ok(user.render(ServicesFactory.getMiembroService()
				.findAllMiembros()));
	}

	public static String failPassword() {
		return session("error");
	}

	public static class UserForm {

		String userName;
		String name;
		String surname;
		String email;
		String pass;
		String pass2;
		String pass3;

		public UserForm() {

		}

		public UserForm(User user) {
			this.userName = user.getUserName();
			name = user.getName();
			surname = user.getSurname();
			email = user.getEmail();
			pass = user.getPassword();

		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String usuario) {
			this.userName = usuario;
		}

		public String getName() {
			return name;
		}

		public void setName(String nombre) {
			this.name = nombre;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String apellidos) {
			this.surname = apellidos;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public String getPass2() {
			return pass2;
		}

		public void setPass2(String pass2) {
			this.pass2 = pass2;
		}

		public String getPass3() {
			return pass3;
		}

		public void setPass3(String pass3) {
			this.pass3 = pass3;
		}

		public String validate() {

			if(!pass.equals("")){
				User user = ServicesFactory.getUsersService().findByUserName(
						session("user"));
	
				if (!pass.equals(user.getPassword())) {
					return "Tu password no coincide";
				}
			}
			if (!pass2.equals(pass3)) {

				return "La nueva password no coincide en ambos campos";
			}
			return null;
		}

	}
}
