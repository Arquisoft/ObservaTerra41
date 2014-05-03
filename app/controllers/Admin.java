package controllers;

import conf.ServicesFactory;
import controllers.security.SecuredAdmin;
import models.*;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.country;
import views.html.indicator;
import views.html.observation;
import views.html.user;

@Security.Authenticated(SecuredAdmin.class)
public class Admin extends Controller {
	
	//Muestra la vista de CRUD de usuarios
	public static Result showUsers() {
		return ok(user.render(ServicesFactory.getMiembroService()
				.findAllMiembros()));
	}
	
	//Muestra la vista de CRUD de países
	public static Result showCountries() {
		return ok(country.render(ServicesFactory.getCountryService().all(),
				countryForm));
	}

	//Muestra la vista de CRUD de indicadores
	public static Result showIndicators() {
		return ok(indicator.render(ServicesFactory.getIndicatorService().all(),
				indicatorForm));
	}

	//Muestra la vista de CRUD de observaciones
	public static Result showObservations() {
		String mensaje = "";
		return ok(observation.render(ServicesFactory.getObservationService()
				.all(), ServicesFactory.getCountryService().all(),
				ServicesFactory.getIndicatorService().all(), observationForm,
				mensaje));
	}

	
    public static Result newCountry() {
      Form<Country> form = countryForm.bindFromRequest();
  	  if(form.hasErrors()) {
  	    return badRequest(
  	      views.html.country.render(ServicesFactory.getCountryService().all(),countryForm)
  	    );
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
  	  if(form.hasErrors()) {
  	    return badRequest(
  	      views.html.indicator.render(ServicesFactory.getIndicatorService().all(),indicatorForm)
  	    );
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
      try{
      value = Double.parseDouble(requestData.get("value"));
      }catch(Exception e){
    	  String mensaje="Campo no valido";
    	  return ok(observation.render(ServicesFactory.getObservationService().all(),
  				ServicesFactory.getCountryService().all(),
  				ServicesFactory.getIndicatorService().all(), observationForm,mensaje));
      }
      
      
     // Observation obs = new Observation(countryId,indicatorId,value);
      ServicesFactory.getObservationService().addObservation(new Observation(countryId, indicatorId, value));

  	  return redirect(routes.Admin.showObservations());  
    }

    /**
     * No va funcionar aun
     * @param id
     * @return
     */
    public static Result deleteObservation(Long id) {
        ServicesFactory.getObservationService().delete(id);
        return redirect(routes.Admin.showObservations());
    }
    
    public static Result deleteUser(Long id){
    	ServicesFactory.getUsersService().removeUser(id);
    	return redirect(routes.Admin.showUsers());
    }
    
    public static Result updateUser(String name){
    	User user = ServicesFactory.getUsersService().findByUserName(name);
    	ServicesFactory.getUsersService().update(user);
    	return redirect(routes.Admin.showUsers());	
    }
    
    
    //FORMULARIOS
    static Form<Country>  	  countryForm     = Form.form(Country.class);
    static Form<Indicator>    indicatorForm   = Form.form(Indicator.class);
    static Form<Observation>  observationForm = Form.form(Observation.class);

}
