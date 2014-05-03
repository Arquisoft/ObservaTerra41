package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class Country extends Model {

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + "]";
	}

	private static final long serialVersionUID = 1L;
	@Id
	private String code;
	
	private String name;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static Finder<String, Country> find = new Finder(String.class,
			Country.class);

	public Country(String name) {
		this.code = generateId(name);
		this.name = name;
	}

	public Country() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public String getCode() {
		return code;
	}
	
	private String generateId(String nombre){

		char[] code = nombre.toCharArray();
		String co ="";
		for(char c : code){
			if(c != ' '){
				co+=c;
			}
		}
		return co.toUpperCase();

	}

	public static JsonNode toJson(Country country) {
		return Json.toJson(country);
	}
}
