package business;

import java.util.List;

import models.Indicator;


public interface IndicatorSercive {
	public void create(Indicator indicator);

	public List<Indicator> all();

	public void remove(String id);

	public Indicator findByName(String name);

	public Indicator findByCode(String code) ;

	public void deleteAll() ;
}
