package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import models.*;
import play.Logger;
import play.data.*;
import play.i18n.Messages;
import play.mvc.*;
import play.libs.Json;
import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import utils.*;

import com.fasterxml.jackson.databind.node.*;

import conf.ServicesFactory;

public class API extends Controller {

    public static Result countries() {
    	return ok(Json.toJson(ServicesFactory.getCountryService().all()));
    }

    public static Result country(String code) {
    	
    	return ok(Json.toJson(ServicesFactory.getCountryService().findByCode(code)));
    }

    /** Generates JSON with links to other resources 
     *  following HATEOAS principles */
    public static Result countriesHATEOAS() {
     JsonNodeFactory factory = JsonNodeFactory.instance;
     ArrayNode result = new ArrayNode(factory);
     for (Country country : ServicesFactory.getCountryService().all()) {
       ObjectNode countryJson = Json.newObject();
       countryJson.put("code", country.getCode());
       countryJson.put("name", country.getName());
       ArrayNode links = new ArrayNode(factory);
       ObjectNode self = Json.newObject();
       self.put("rel", "self");
       self.put("href", routes.API.countryHATEOAS(country.getCode()).absoluteURL(request()));
       
       links.add(self);
       countryJson.put("links", links);
       result.add(countryJson);
     }
     return ok(result);
    }
    
    public static Result countryHATEOAS(String code) {
    	JsonNodeFactory factory = JsonNodeFactory.instance;
    	
    	Country country = ServicesFactory.getCountryService().findByCode(code);
        ObjectNode countryJson = Json.newObject();
        countryJson.put("code", country.getCode());
        countryJson.put("name", country.getName());
        ArrayNode links = new ArrayNode(factory);
        ObjectNode self = Json.newObject();
        self.put("rel", "self");
        self.put("href", routes.API.countryHATEOAS(country.getCode()).absoluteURL(request()));
        links.add(self);
         
        ObjectNode parent = Json.newObject();
        parent.put("rel", "parent");
        parent.put("href", routes.API.countriesHATEOAS().absoluteURL(request()));
        links.add(parent);

        countryJson.put("links", links);
    	return ok(countryJson);
    }
    
    public static Result updateCountry(String code) {
    	
    	Country previous 	= ServicesFactory.getCountryService().findByCode(code);
    	Country newCountry 	= countryForm.bindFromRequest().get();
    	previous.setName(newCountry.getName());
    	previous.save();
    	return redirect(routes.API.countries());
    }

    public static Result addCountry() {
    	Country country = countryForm.bindFromRequest().get();
    	country.save();
    	return redirect(routes.API.countries());
    }

    public static Result delCountry(String code) {
    	ServicesFactory.getCountryService().remove(code);
    	return redirect(routes.API.countries());
    }

    public static Result observations() {
    	
    	List<Observation> obsList = ServicesFactory.getObservationService().all();
    	return ok(Json.toJson(obsList));
    }

    public static Result observationsByIndicator(String indicator) {
    	
    	List<Observation> obsList = ServicesFactory.getObservationService().findByIndicatorName(indicator);
    	return ok(Json.toJson(obsList));
    }

    public static Result observationsByCountry(String country) {
    	
    	List<Observation> obsList = ServicesFactory.getObservationService().findByCountryCode(country);
    	return ok(Json.toJson(obsList));
    }

    public static Result addObservation(String country, String indicator) {
    	return TODO;
    }
    
    public static Result delObservation(String country, String indicator) {
    	return TODO;
    }
    
    public static Result uploadExcel() {
    try {
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart excel = body.getFile("excel");
      if (excel != null) {
    	    File file = excel.getFile();
    	    ExcelReader reader = new ExcelReader();
    	    List<Observation> obsList = reader.read(new FileInputStream(file));
    	    for (Observation obs: obsList) {
    	    	obs.save();
    	    }
    	    Logger.info("Excel file uploaded with " + obsList.size() + " observations");
    	    return redirect(routes.Application.index());
       } else {
   	    	Logger.error("Missing file to upload ");
    	    return redirect(routes.Application.index());    
       } 
      }
    catch (IOException e) {
      return(badRequest(Messages.get("read.excel.error") + "." + e.getLocalizedMessage()));	
    }
    }
    public static Result  uploadJSON(){
    	MultipartFormData body = request().body().asMultipartFormData();
        FilePart JSON = body.getFile("JSON");
        
        JSONReader reader = new JSONReader();
        File file = JSON.getFile();
        try {
			List<Observation> aux= reader.read(new FileInputStream(file));
			  for (Observation obs: aux) {
	    	    	obs.save();
	    	    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return redirect(routes.Application.index()); 
         
    }
    
    public static Result  uploadCSV(){
    	MultipartFormData body = request().body().asMultipartFormData();
        FilePart JSON = body.getFile("JSON");
        
        CSVReader reader = new CSVReader();
        File file = JSON.getFile();
        try {
			List<Observation> aux= reader.read(new FileInputStream(file));
			  for (Observation obs: aux) {
	    	    	obs.save();
	    	    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return redirect(routes.Application.index()); 
         
    }
    static Form<Country>  	  countryForm     = Form.form(Country.class);
    static Form<Indicator>    indicatorForm   = Form.form(Indicator.class);
    static Form<Observation>  observationForm = Form.form(Observation.class);

}
