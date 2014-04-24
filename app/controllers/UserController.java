package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {
	static Form<User> userForm = Form.form(User.class);

	public static Result newUser() {
		return redirect(routes.Application.showRegister());

	}
}
