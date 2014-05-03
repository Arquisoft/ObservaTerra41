package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Observation;
import models.User;
import conf.ServicesFactory;
import play.api.i18n.Lang;
import play.mvc.Controller;

public class Util extends Controller{
	
	public static String getSessionUser() {
		return session("user");
	}
	
	public static boolean isAdmin(){
		User user = ServicesFactory.getUsersService().findByUserName(getSessionUser());
		if(user != null)
			return user.isAdmin();
		return false;
	}
	
	public static Observation findObs(String country, String indicator) {
		return ServicesFactory.getObservationService().findByCountryIndicator(
				country, indicator);
	}

	public static String getColor(int index) {
		switch (index) {
		case 0:
			return "#FF7070";
		case 1:
			return "#7081FF";
		case 2:
			return "#70FF70";
		case 3:
			return "#DEF200";
		default:
			return "#000000";
		}
	}
	
	public static boolean updateByAdmin(String name){
		if(isAdmin() && !name.equals(getSessionUser())){
			return true;
		}
		return false;
	}
	
	public static boolean validateEmail(String email){
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	public static String language(){
		return lang().code();
	}
}
