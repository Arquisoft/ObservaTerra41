package models;

import play.db.ebean.*;
import play.data.validation.Constraints.Required;
import play.libs.Json;

import javax.persistence.*;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class Indicator extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7091678348041541147L;


	@Id
	private String code;
	
	private  String year;

	@Required
	private String name;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static Finder<String, Indicator> find = new Finder(String.class,
			Indicator.class);

	public String getName() {
		return name;
	}
	

	@Override
	public String toString() {
		return "Indicator [code=" + code + ", year=" + year + ", name=" + name
				+ "]";
	}




	public void setName(String name) {
		this.name = name;
	}

	public void setName(String name,String year) {
		this.name = name;
		this.year = year;
	}


	public String getCode() {
		return code;
	}

	public Indicator(String name) {
		this.name = name;
		this.code = generatedId(name);
		this.year = "-";
	}
	
	public Indicator(String name, String year) {
		setYear(year);
		this.name = name;
		this.code = generatedId(name);
	}

	private String generatedId(String name){
		char[] code = name.toCharArray();
		String co ="";
		for(char c : code){
			if(c != ' '){
				co+=c;
			}
		}
		return year+co;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		if(year.equals("") || year == null)
			this.year = "-";
		else
			this.year = year;
	}

	
	public JsonNode toJson() {
		return Json.toJson(this);
	}
	

}