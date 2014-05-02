package controllers;

import java.util.HashMap;
import conf.ServicesFactory;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class UserController extends Controller {
	static Form<User> userForm = Form.form(User.class);

	public static Result newUser() {

		userForm = Form.form(User.class).bindFromRequest();
		HashMap<String, String> datos = (HashMap<String, String>) userForm
				.data();
		String usuario = datos.get("user");
		String nombre = datos.get("name");
		String apellidos = datos.get("surname");
		String email = datos.get("email");
		String pass = datos.get("pass");
		String pass2 = datos.get("pass2");

		User user = ServicesFactory.getUsersService().findByUserName(usuario);
		if (user != null) {
			ServicesFactory.getUsersService().update(user);
			return redirect(routes.Application.index());
		} else {
			ServicesFactory.getUsersService().createUser(
					new User(usuario, nombre, apellidos, pass, email));
			return redirect(routes.Application.login());

		}
	}

	public static Result showUsers() {
		return ok(user.render(ServicesFactory.getMiembroService().findAllMiembros()));
	}

	public static String failPassword() {
		return session("error");
	}
}
