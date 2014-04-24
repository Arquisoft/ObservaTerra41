package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import models.*;
import views.html.*;

import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import utils.ExcelReader;
import play.data.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render(Observation.all(), Country.all(),
				Indicator.all()));
	}

	public static Result showCountries() {
		return ok(country.render(Country.all(), countryForm));
	}

	public static Result showIndicators() {
		return ok(indicator.render(Indicator.all(), indicatorForm));
	}

	public static Result showObservations() {
		return ok(observation.render(Observation.find.all(), Country.all(),
				Indicator.all(), observationForm));
	}

	public static Result bars(String indicator) {
		return ok(bars.render(Indicator.findByCode(indicator)));
	}

	public static Result showRegister() {
		return ok(register.render(userForm));
	}

	public static Result login() {
		return ok(login.render(Form.form(Login.class)));
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

	public static String getSessionUser(){
		return session("user");
	}
	
	public static Result cerrarSesion(){
		session().clear();
		return redirect(routes.Application.index());
	}
	
	static Form<Country> countryForm = Form.form(Country.class);
	static Form<Indicator> indicatorForm = Form.form(Indicator.class);
	static Form<Observation> observationForm = Form.form(Observation.class);
	static Form<User> userForm = Form.form(User.class);

	public static class Login {

		public String user;
		public String password;

		public String validate() {
			// Llamada a método que checkee si existen
			User usuario = User.findByUserName(user);
			
			if (usuario == null) return "Invalid user or password";
			if(!usuario.password.equals(password)) return "Invalid user or password";
				
			return null;
		}

	}

}
