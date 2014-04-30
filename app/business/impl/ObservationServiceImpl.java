package business.impl;

import java.util.ArrayList;
import java.util.List;

import business.ObservationService;
import conf.ServicesFactory;
import models.Country;
import models.Indicator;
import models.Observation;
import play.db.ebean.Model.Finder;

public class ObservationServiceImpl implements ObservationService{
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private  Finder<Long, Observation> find = new Finder(Long.class,
			Observation.class);
	
	public  List<Observation> all() {
		return find.all();
	}

	
	public  void create(String countrycode, String indicatorkey, Double value) {
		Indicator indicator = ServicesFactory.getIndicatorService().findByCode(indicatorkey);
		Country country = ServicesFactory.getCountryService().findByCode(countrycode);
		Observation observation = new Observation(country, indicator, value);
		observation.save();
	
	}

	public  void delete(Long id) {
		find.ref(id).delete();
	}

	public  void deleteAll() {
		for (Observation obs : all()) {
			obs.delete();
		}
	}
	
	
	public  List<Observation> filterByIndicatorName(String indicatorName,
			List<Observation> observations) {
		List<Observation> result = new ArrayList<Observation>();
		for (Observation obs : observations) {
			if (obs.getIndicator().getName() == indicatorName)
				result.add(obs);
		}
		return result;
	}

	public  List<Observation> findByIndicatorName(String indicatorCode) {
		Indicator ind = ServicesFactory.getIndicatorService().findByCode(indicatorCode);
		List<Observation> result = find.where().eq("indicator", ind).findList();
		return result;
	}

	public  List<Observation> findByCountryCode(String countryCode) {
		Country c = ServicesFactory.getCountryService().findByCode(countryCode);
		List<Observation> result = find.where().eq("country", c).findList();
		return result;
	}
	
	public Observation findById(Long id){
		return find.byId(id);
	}




}
