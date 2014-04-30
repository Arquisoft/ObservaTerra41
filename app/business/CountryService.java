package business;

import java.util.List;

import models.Country;

public interface CountryService {

	public  List<Country> all();

	public  void create(Country country);

	public  void remove(String code);

	public  void deleteAll();

	public  Country findByName(String name);

	public  Country findByCode(String code);

}
