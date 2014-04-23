package controllers;


import model.User;
import model.exceptions.BusinessException;
import conf.ServicesFactory;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	User a = null;
    	try {
    		//llamada al service de prueva solo para ver que funciona 
    		//añadir un usuario de user = admin y pass = admin para que lo encuentre
			a = ServicesFactory.getUsuariosService().findUserByUsernameAndPass("admin", "admin");
		} catch (BusinessException e) {
			
			 return ok(index.render("e.getMessage().toString()"));
			
		}
    	String user="";
    	if(a!=null){
    		user=a.getUser();
    	}
        return ok(index.render("Your new application is ready."+user));
    }

}
