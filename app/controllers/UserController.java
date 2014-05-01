package controllers;

import conf.ServicesFactory;
import controllers.Application.Login;
import models.User;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class UserController extends Controller {
	static Form<User> userForm = Form.form(User.class);

	public static String name = "nadie";
	
	public static Result newUser() {
		String usuario = Form.form().bindFromRequest().get("user");
		String nombre = Form.form().bindFromRequest().get("name");
		String apellidos = Form.form().bindFromRequest().get("surname");
		String email = Form.form().bindFromRequest().get("email");
		String pass = Form.form().bindFromRequest().get("pass");
		String pass2 = Form.form().bindFromRequest().get("pass2");

		name = "User: " + usuario + " name: " + nombre + " apellidos: "
				+ apellidos + " email: " + email + " pass: " + pass
				+ " pass2: " + pass2;
		ServicesFactory.getUsersService().createUser(new User(usuario, nombre, apellidos, pass, email));
		return redirect(routes.Application.showRegister());
	}
	
	public static Result showUsers(){
		return ok(user.render(ServicesFactory.getUsersService().findAllUsers()));
	}
}
