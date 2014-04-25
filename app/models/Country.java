package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class Country extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	public String code;
	public String name;

	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Finder<String, Country> find = new Finder(String.class,
			Country.class);

	public static List<Country> all() {
		return find.all();
	}

	public static void create(Country country) {
		if (Country.findByName(country.name) == null) {
			country.save();
		}
	}

	public static void remove(String code) {
		find.ref(code).delete();
	}

	public static void deleteAll() {
		for (Country c : all())
			c.delete();
	}

	public static Country findByName(String name) {
		return find.where().eq("name", name).findUnique();
	}

	public static Country findByCode(String code) {
		return find.byId(code);
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
