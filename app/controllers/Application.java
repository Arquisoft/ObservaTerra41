package controllers;

import java.util.ArrayList;
import java.util.List;

import business.CountryService;
import business.IndicatorSercive;
import business.ObservationService;
import conf.ServicesFactory;
import models.*;
import views.html.*;
import play.data.Form;
import play.mvc.*;

public class Application extends Controller {

	public static Result index() {
		ObservationService ob = ServicesFactory.getObservationService();
		CountryService cs = ServicesFactory.getCountryService();
		IndicatorSercive is = ServicesFactory.getIndicatorService();
		return ok(index.render(ob.all(), cs.all(), is.all()));
	}

	@Security.Authenticated(SecuredAdmin.class)
	public static Result showCountries() {
		return ok(country.render(ServicesFactory.getCountryService().all(),
				countryForm));
	}

	@Security.Authenticated(SecuredAdmin.class)
	public static Result showIndicators() {
		return ok(indicator.render(ServicesFactory.getIndicatorService().all(),
				indicatorForm));
	}

	@Security.Authenticated(SecuredAdmin.class)
	public static Result showObservations() {
		String mensaje = "";
		return ok(observation.render(ServicesFactory.getObservationService()
				.all(), ServicesFactory.getCountryService().all(),
				ServicesFactory.getIndicatorService().all(), observationForm,
				mensaje));
	}
	
	public static Result bars(String indicator) {
		return ok(bars.render(ServicesFactory.getIndicatorService().findByCode(
				indicator)));
	}

	public static Result showRegister() {
		return ok(register.render(userForm));
	}
	
	public static Result fillRegister(String name){
		User user = ServicesFactory.getUsersService().findByUserName(name);
		user = new User("prueba","prueba","prueba","prueba","prueba");
		if(user!=null)
		userForm.fill(user);
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
			/*
			 * String name = loginForm.get().user;
			 * User user = ServicesFactory.getUsersService().findUserName(user);
			 * if(user.isAdmin())
			 * session("admin", "true");
			 */
			// QUITAR ESTA LINEA Y DESCOMENTAR LO DE ARRIBA CUANDO ESTE EL VER
			// SI ES ADMIN!!!!
			session("admin", "true");
			return redirect(routes.Application.index());
		}
	}

	public static String getSessionUser() {
		return session("user");
	}
	
	public static String isAdmin(){
		return session("admin");
	}

	public static Result cerrarSesion() {
		session().clear();
		return redirect(routes.Application.index());
	}

	public static Result comparar() {
		
		Form<ComparationForm> comparationForm = Form.form(ComparationForm.class).bindFromRequest();
		
		List<Country> selected = new ArrayList<Country>();
		Indicator indicator = null;
		
		if(comparationForm.get().indicator != null){
			if(comparationForm.get().country1!=null && !comparationForm.get().country1.equals("none"))
				selected.add(ServicesFactory.getCountryService().findByCode(comparationForm.get().country1));
			if(comparationForm.get().country2!=null && !comparationForm.get().country2.equals("none"))
				selected.add(ServicesFactory.getCountryService().findByCode(comparationForm.get().country2));
			if(comparationForm.get().country3!=null && !comparationForm.get().country3.equals("none"))
				selected.add(ServicesFactory.getCountryService().findByCode(comparationForm.get().country3));
			if(comparationForm.get().country4!=null && !comparationForm.get().country4.equals("none"))
				selected.add(ServicesFactory.getCountryService().findByCode(comparationForm.get().country4));
			
			indicator = ServicesFactory.getIndicatorService().findByCode(comparationForm.get().indicator);
		}
		
		return ok(compare.render(ServicesFactory.getCountryService().all(), ServicesFactory.getIndicatorService().all(), indicator, selected, comparationForm));
	}
	
	public static Observation findObs(String country, String indicator){
		return ServicesFactory.getObservationService().findByCountryIndicator(country, indicator);
	}
	
	public static String getColor(int index){
		switch(index){
			case 0: return "#FF7070";
			case 1: return "#7081FF";
			case 2: return "#70FF70";
			case 3: return "#DEF200";
			default: return "#000000";
		}
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
			User usuario = ServicesFactory.getUsersService().findByUserName(
					user);

			if (usuario == null)
				return "Invalid user or password";
			if (!usuario.getPassword().equals(password))
				return "Invalid user or password";

			return null;
		}

	}

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

}
