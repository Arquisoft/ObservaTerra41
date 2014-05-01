package controllers;



import conf.ServicesFactory;
import models.*;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.observation;
import play.mvc.*;

@Security.Authenticated(SecuredAdmin.class)
public class Admin extends Controller {

    public static Result newCountry() {
      Form<Country> form = countryForm.bindFromRequest();
  	  if(form.hasErrors()) {
  	    return badRequest(
  	      views.html.country.render(ServicesFactory.getCountryService().all(),countryForm)
  	    );
  	  } else {
        Country countryToAdd = form.get();
        ServicesFactory.getCountryService().create(countryToAdd);
  	    return redirect(routes.Application.showCountries());
  	  }    
    }
    
    public static Result deleteCountry(String code) {
    	ServicesFactory.getCountryService().remove(code);
        return redirect(routes.Application.showCountries());
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
  	    return redirect(routes.Application.showIndicators());  
  	  }    
    }
    
    public static Result deleteIndicator(String code) {
        ServicesFactory.getIndicatorService().remove(code);
        return redirect(routes.Application.showIndicators());
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
      ServicesFactory.getObservationService().create(countryId, indicatorId, value);

  	  return redirect(routes.Application.showObservations());  
    }

    /**
     * No va funcionar aun
     * @param id
     * @return
     */
    public static Result deleteObservation(Long id) {
        ServicesFactory.getObservationService().delete(id);
        return redirect(routes.Application.showObservations());
    }
    
    static Form<Country>  	  countryForm     = Form.form(Country.class);
    static Form<Indicator>    indicatorForm   = Form.form(Indicator.class);
    static Form<Observation>  observationForm = Form.form(Observation.class);

}
