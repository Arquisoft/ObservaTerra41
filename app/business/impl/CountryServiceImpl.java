package business.impl;

import java.util.List;

import business.CountryService;
import models.Country;
import play.db.ebean.Model.Finder;

public class CountryServiceImpl implements CountryService{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private  Finder<String, Country> find = new Finder(String.class,
			Country.class);

	public  List<Country> all() {
		return find.all();
	}

	public  void create(Country country) {
		if (findByName(country.getName()) == null && country.getName().length()>1) {
			country.save();
		}
	}

	public  void remove(String code) {
		find.ref(code).delete();
	}

	public  void deleteAll() {
		for (Country c : all())
			c.delete();
	}

	public  Country findByName(String name) {
		return find.where().eq("name", name).findUnique();
	}

	public  Country findByCode(String code) {
		return find.byId(code);
	}

}
