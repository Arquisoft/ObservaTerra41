package controllers;

import java.util.List;

import conf.ServicesFactory;
import models.*;
import views.html.*;

import play.data.Form;

import play.mvc.Controller;

import play.mvc.Result;

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
			System.out.println("USUARIO "+Form.form().bindFromRequest("yhugbu"));
			return redirect(routes.Application.index());
		}
	}

	public static String getSessionUser() {
		return session("user");
	}

	public static Result cerrarSesion() {
		session().clear();
		return redirect(routes.Application.index());
	}

	public static Result seleccionPais() {

		String mensaje="";
		return ok(SeleccionDeComparacion.render(Country.all(), SelectedCountry,mensaje));

	}

	public static Result graficas() {
		String a = "a";
		String b = "b";
		return ok(GraficasPaises.render(a, b));

	}

	public static Result comparar() {
		Form<CountryTemp> loginForm = Form.form(CountryTemp.class)
				.bindFromRequest();
		String pais1 = Form.form().bindFromRequest().get("primero");
		String pais2 = Form.form().bindFromRequest().get("segundo");
		
		List<Country> paises= Country.all();
		
		boolean encontrado1=false;
		boolean encontrado2=false;
		
		for(Country c : paises){
			if(c.getCode().compareToIgnoreCase(pais1)==0){
				encontrado1=true;
				
			}
			if(c.getCode().compareToIgnoreCase(pais2)==0){
				encontrado2=true;
			}
		}
		if(encontrado1==false || encontrado2 ==false){
			return ok(SeleccionDeComparacion.render(Country.all(), SelectedCountry,"Pais Invalido"));
		}else{
			System.out.println("EL pais numero 1 +" + Form.form().bindFromRequest("yhugbu"));
			return ok(MostrarComparacion.render(pais1, pais2));
		}
			
		
	}

	static Form<Country> countryForm = Form.form(Country.class);
	static Form<Indicator> indicatorForm = Form.form(Indicator.class);
	static Form<Observation> observationForm = Form.form(Observation.class);
	static Form<User> userForm = Form.form(User.class);
	static Form<CountryTemp> SelectedCountry = Form.form(CountryTemp.class);

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

	public static class CountryTemp {

		private String primerPais;
		private String segundoPais;

		public String toString() {
			return primerPais;
		}

		public String getPrimerPais() {
			return primerPais;
		}

		public void setPrimerPais(String primerPais) {
			this.primerPais = primerPais;
		}

		public String getSegundoPais() {
			return segundoPais;
		}

		public void setSegundoPais(String segundoPais) {
			this.segundoPais = segundoPais;
		}

	}

}
