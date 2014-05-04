package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import conf.ServicesFactory;
import models.*;
import utils.MD5Hash;
import views.html.*;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.*;

public class Application extends Controller {
	
	public static Result download(Long id){
		UsersResources ur = ServicesFactory.getUsersResourcesService().findResourceByCode(id);
		response().setContentType("application/x-download");  
		response().setHeader("Content-disposition","attachment; filename=" + ur.getFilename()); 
		return ok(new File("public/" + ur.getFilePath()));
	}

	// Muesta la vista de la página principal
	public static Result index() {
		return ok(index.render());
	}
	
	// Muesta la vista de la página principal
	public static Result authors() {
		return ok(developer.render());
	}
	
	public static Result data(){
		return ok(data.render(ServicesFactory.getUsersResourcesService().all()));
	}

	public static Result login() {
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result changeLanguage() {
		String url = request().getHeader("referer");
		String code = Form.form().bindFromRequest().get("language");
		changeLang(code);
		return redirect(url);
	}

	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("user", loginForm.get().user);
			return redirect(routes.Application.index());
		}
	}

	public static Result newUser() {
		Form<UserForm> userForm = Form.form(UserForm.class).bindFromRequest();

		if (userForm.hasErrors()) {
			return badRequest(register.render(userForm));
		}

		ServicesFactory.getMiembroService().createMiembro(
				new Miembro(userForm.get().getUserName(), userForm.get()
						.getName(), userForm.get().getSurname(), userForm.get()
						.getPass(), userForm.get().getEmail()));

		return redirect(routes.Application.index());
	}

	public static Result showRegister() {
		Form<UserForm> userForm = Form.form(UserForm.class);
		return ok(register.render(userForm));
	}

	// Muesta la vista de comparar países
	public static Result comparar() {

		Form<ComparationForm> comparationForm = Form
				.form(ComparationForm.class).bindFromRequest();

		List<Country> selected = new ArrayList<Country>();
		Indicator indicator = null;

		if (comparationForm.get().indicator != null) {
			if (comparationForm.get().country1 != null
					&& !comparationForm.get().country1.equals("none"))
				selected.add(ServicesFactory.getCountryService().findByCode(
						comparationForm.get().country1));
			if (comparationForm.get().country2 != null
					&& !comparationForm.get().country2.equals("none"))
				selected.add(ServicesFactory.getCountryService().findByCode(
						comparationForm.get().country2));
			if (comparationForm.get().country3 != null
					&& !comparationForm.get().country3.equals("none"))
				selected.add(ServicesFactory.getCountryService().findByCode(
						comparationForm.get().country3));
			if (comparationForm.get().country4 != null
					&& !comparationForm.get().country4.equals("none"))
				selected.add(ServicesFactory.getCountryService().findByCode(
						comparationForm.get().country4));

			indicator = ServicesFactory.getIndicatorService().findByCode(
					comparationForm.get().indicator);
		}

		return ok(compare.render(ServicesFactory.getCountryService().all(),
				ServicesFactory.getIndicatorService().all(), indicator,
				selected, comparationForm));
	}

	// Formulario para el login
	public static class Login {

		private String user;
		private String password;

		public String validate() {
			// Llamada a método que checkee si existen
			User usuario = ServicesFactory.getUsersService().findByUserName(
					user);

			if (usuario == null)
				return Messages.get("user.form.err1");
			if (!usuario.getPassword().equals(password))
				return Messages.get("user.form.err1");

			return null;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = MD5Hash.codeToMD5(password);
		}

	}

	// Formulario para la vista de comparacion de paises
	public static class ComparationForm {

		private String indicator;
		private String country1;
		private String country2;
		private String country3;
		private String country4;

		public String getIndicator() {
			return indicator;
		}

		public void setIndicator(String indicator) {
			this.indicator = indicator;
		}

		public String getCountry1() {
			return country1;
		}

		public void setCountry1(String country1) {
			this.country1 = country1;
		}

		public String getCountry2() {
			return country2;
		}

		public void setCountry2(String country2) {
			this.country2 = country2;
		}

		public String getCountry3() {
			return country3;
		}

		public void setCountry3(String country3) {
			this.country3 = country3;
		}

		public String getCountry4() {
			return country4;
		}

		public void setCountry4(String country4) {
			this.country4 = country4;
		}

	}

	// Formulario para el registro de usuarios
	public static class UserForm {

		private String userName;
		private String name;
		private String surname;
		private String email;
		private String pass;
		private String pass2;

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
			this.pass = MD5Hash.codeToMD5(pass);
		}

		public String getPass2() {
			return pass2;
		}

		public void setPass2(String pass2) {
			this.pass2 = MD5Hash.codeToMD5(pass2);
		}

		public String validate() {

			if (!pass.equals(pass2))
				return Messages.get("user.form.err2");

			if (!Util.validateEmail(email))
				return Messages.get("user.form.err3");

			return null;
		}

	}
}
