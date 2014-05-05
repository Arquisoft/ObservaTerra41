package controllers;

import conf.ServicesFactory;
import controllers.security.SecuredAdmin;
import models.*;
import play.data.*;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.DataRequester;
import views.html.*;

@Security.Authenticated(SecuredAdmin.class)
public class Admin extends Controller {

	// Muestra la vista de CRUD de usuarios
	public static Result showUsers() {
		return ok(user.render(ServicesFactory.getMiembroService()
				.findAllMiembros()));
	}

	// Muestra la vista de CRUD de países
	public static Result showCountries() {
		return ok(country.render(ServicesFactory.getCountryService().all(),
				countryForm));
	}

	// Muestra la vista de CRUD de indicadores
	public static Result showIndicators() {
		return ok(indicator.render(ServicesFactory.getIndicatorService().all(),
				indicatorForm));
	}

	// Muestra la vista de CRUD de observaciones
	public static Result showObservations() {
		String mensaje = "";
		return ok(observation.render(ServicesFactory.getObservationService()
				.all(), ServicesFactory.getCountryService().all(),
				ServicesFactory.getIndicatorService().all(), observationForm,
				mensaje));
	}

	// Persiste los datos de las urls indicadas previamente
	public static Result collectData() {
		/*DataRequester.request();// descargamos los datos
		for (String st : DataRequester.filePaths()) {
			DataRequester.persistsCsvData(st);// parseamos e insertamos a bd
		}
		if (DataRequester.filePaths().length <= 0) {
			ObservationService ob = ServicesFactory.getObservationService();
			CountryService cs = ServicesFactory.getCountryService();
			IndicatorSercive is = ServicesFactory.getIndicatorService();
			
			return badRequest(data.render(ob.all(),
					cs.all(), is.all()));
		}*/
		DataRequester.request();
		return Application.comparar();
	}

	public static Result newCountry() {
		Form<Country> form = countryForm.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(views.html.country.render(ServicesFactory
					.getCountryService().all(), countryForm));
		} else {
			Country countryToAdd = form.get();
			ServicesFactory.getCountryService().create(countryToAdd);
			return redirect(routes.Admin.showCountries());
		}
	}

	public static Result deleteCountry(String code) {
		ServicesFactory.getCountryService().remove(code);
		return redirect(routes.Admin.showCountries());
	}

	public static Result newIndicator() {
		Form<Indicator> form = indicatorForm.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(views.html.indicator.render(ServicesFactory
					.getIndicatorService().all(), indicatorForm));
		} else {
			Indicator ind = form.get();
			ServicesFactory.getIndicatorService().create(ind);
			return redirect(routes.Admin.showIndicators());
		}
	}

	public static Result deleteIndicator(String code) {
		ServicesFactory.getIndicatorService().remove(code);
		return redirect(routes.Admin.showIndicators());
	}

	public static Result newObservation() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String countryId = requestData.get("countryId");
		String indicatorId = requestData.get("indicatorId");
		Double value;
		try {
			value = Double.parseDouble(requestData.get("value"));
		} catch (Exception e) {
			String mensaje = Messages.get("obs.form.err");
			return ok(observation.render(ServicesFactory
					.getObservationService().all(), ServicesFactory
					.getCountryService().all(), ServicesFactory
					.getIndicatorService().all(), observationForm, mensaje));
		}

		// Observation obs = new Observation(countryId,indicatorId,value);
		ServicesFactory.getObservationService().addObservation(
				new Observation(countryId, indicatorId, value));

		return redirect(routes.Admin.showObservations());
	}

	public static Result deleteObservation(Long id) {
		ServicesFactory.getObservationService().delete(id);
		return redirect(routes.Admin.showObservations());
	}

	public static Result deleteUser(Long id) {
		ServicesFactory.getUsersService().removeUser(id);
		return redirect(routes.Admin.showUsers());
	}

	public static Result updateUser(String name) {
		User user = ServicesFactory.getUsersService().findByUserName(name);
		ServicesFactory.getUsersService().update(user);
		return redirect(routes.Admin.showUsers());
	}

	public static Result showUrls() {
		return ok(url.render(ServicesFactory.getUrlRepositoryService().all()));
	}

	public static Result deleteUrl(Long id) {
		ServicesFactory.getUrlRepositoryService().deleteUrl(id);
		return redirect(routes.Admin.showUrls());
	}

	public static Result addUrl() {
		newUrlForm = Form.form(UrlRepository.class).bindFromRequest();
		UrlRepository url = newUrlForm.get();
		ServicesFactory.getUrlRepositoryService().addURL(url);
		return redirect(routes.Admin.showUrls());
	}

	static Form<UrlRepository> newUrlForm = Form.form(UrlRepository.class);
	static Form<UrlRepository> urlForm = Form.form(UrlRepository.class);
	static Form<Country> countryForm = Form.form(Country.class);
	static Form<Indicator> indicatorForm = Form.form(Indicator.class);
	static Form<Observation> observationForm = Form.form(Observation.class);

}
