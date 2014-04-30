package conf;

import business.CountryService;
import business.IndicatorSercive;
import business.ObservationService;
import business.UsersService;
import business.impl.CountryServiceImpl;
import business.impl.IndicatorServiceImpl;
import business.impl.ObservationServiceImpl;
import business.impl.UsersServiceImpl;

public class ServicesFactory {
	public static UsersService getUsersService(){
		return new UsersServiceImpl();
	}
	
	public static IndicatorSercive getIndicatorService() {
		return new IndicatorServiceImpl();
	}
	
	public static CountryService getCountryService(){
		return new CountryServiceImpl();
	}
	
	public static ObservationService getObservationService(){
		return new ObservationServiceImpl();
	}
}
