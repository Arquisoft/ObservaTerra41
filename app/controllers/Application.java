package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import business.CountryService;
import business.IndicatorSercive;
import business.ObservationService;
import business.impl.ObservationServiceImpl;
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

	public static Result seleccionPais() {

		String mensaje = "";
		return ok(SeleccionDeComparacion.render(ServicesFactory
				.getCountryService().all(), SelectedCountry, mensaje));

	}

	public static Result graficas() {
		
			String a="a";
			String b="b";
		
		return ok(GraficasPaises.render(a, b));

	}

	public static Result comparar() {
		Form<CountryTemp> loginForm = Form.form(CountryTemp.class)
				.bindFromRequest();
		String pais1 = Form.form().bindFromRequest().get("primero");
		String pais2 = Form.form().bindFromRequest().get("segundo");

		List<Country> paises = ServicesFactory.getCountryService().all();

		boolean encontrado1 = false;
		boolean encontrado2 = false;
		String nombrepais1="";
		String nombrepais2="";

		for (Country c : paises) {
			if (c.getCode().compareToIgnoreCase(pais1) == 0) {
				encontrado1 = true;
				nombrepais1=c.getName();
			}
			if (c.getCode().compareToIgnoreCase(pais2) == 0) {
				encontrado2 = true;
				nombrepais2=c.getName();
			}
		}
		if (encontrado1 == false || encontrado2 == false) {
			return ok(SeleccionDeComparacion.render(ServicesFactory
					.getCountryService().all(), SelectedCountry,
					"Pais Invalido"));
		} else {
			ObservationService os = new ObservationServiceImpl();
			
			List<Observation>observaciones1= new ArrayList<Observation>();
			List<Observation>observaciones2= new ArrayList<Observation>();
			observaciones1=os.findByCountryCode(pais1);
			observaciones2=os.findByCountryCode(pais2);
			
			Map<String,Double>medicionpais1= new HashMap<String,Double>();
			Map<String,Double>medicionpais2= new HashMap<String,Double>();
			
			//calculamos los valores medios de cada indicador que tenga el pais
			for(Observation o:observaciones1){
				String observacion=o.getIndicator().getName();
				
				if(medicionpais1.containsKey(observacion)){
					double valorant=medicionpais1.get(observacion);
					medicionpais1.put(observacion, valorant+o.getObsValue()/2);
				}else{
					medicionpais1.put(observacion, o.getObsValue());
				}
			}
			
			for(Observation o:observaciones2){
				String observacion=o.getIndicator().getName();
				
				if(medicionpais2.containsKey(observacion)){
					double valorant=medicionpais2.get(observacion);
					medicionpais2.put(observacion, valorant+o.getObsValue()/2);
				}else{
					
					medicionpais2.put(observacion,o.getObsValue());
				}
			}
			
			List<valoresMedios>lm1= new ArrayList<valoresMedios>();
			List<valoresMedios>lm2= new ArrayList<valoresMedios>();
			//creamos dos listas las cuales pasaremos a las vistas
			Iterator it = medicionpais1.entrySet().iterator();
			while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			
			String indicador=(String) e.getKey();
			Double valor=(Double) e.getValue();
			valoresMedios vm= new valoresMedios(indicador,valor);
			lm1.add(vm);
			}
			
			//para el segundo pais
			
			Iterator it2 = medicionpais2.entrySet().iterator();
			while (it2.hasNext()) {
			Map.Entry e = (Map.Entry)it2.next();
			
			String indicador=(String) e.getKey();
			Double valor=(Double) e.getValue();
			valoresMedios vm= new valoresMedios(indicador,valor);
			lm2.add(vm);
			}
			
			return ok(MostrarComparacion.render(lm1, lm2,nombrepais1,nombrepais2));
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
