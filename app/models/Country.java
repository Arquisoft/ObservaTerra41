package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class Country extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	private String code;
	private String name;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static Finder<String, Country> find = new Finder(String.class,
			Country.class);

	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public static JsonNode toJson(Country country) {
		return Json.toJson(country);
	}
}
