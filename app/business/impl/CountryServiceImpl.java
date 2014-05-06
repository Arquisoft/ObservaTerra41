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
		if (find.byId(country.getCode())==null) {
			country.save();
		}
	}

	public  void remove(String code) {
		try {
			find.ref(code).delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public  void deleteAll() {
		try {
			for (Country c : all())
				c.delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public  Country findByName(String name) {
		return find.where().eq("name", name).findUnique();
	}

	public  Country findByCode(String code) {
		return find.byId(code);
	}

}
