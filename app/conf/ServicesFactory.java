package conf;

import business.AdminService;
import business.CountryService;
import business.IndicatorSercive;
import business.MiembroService;
import business.ObservationService;
import business.UrlRepositoryService;
import business.UsersService;
import business.impl.AdminServiceImpl;
import business.impl.CountryServiceImpl;
import business.impl.IndicatorServiceImpl;
import business.impl.MiembroServiceImpl;
import business.impl.ObservationServiceImpl;
import business.impl.UrlRepositoryServiceImpl;
import business.impl.UsersServiceImpl;

public class ServicesFactory {
	public static UsersService getUsersService() {
		return new UsersServiceImpl();
	}

	public static IndicatorSercive getIndicatorService() {
		return new IndicatorServiceImpl();
	}

	public static CountryService getCountryService() {
		return new CountryServiceImpl();
	}

	public static ObservationService getObservationService() {
		return new ObservationServiceImpl();
	}

	public static UrlRepositoryService getUrlRepositoryService() {
		return new UrlRepositoryServiceImpl();
	}

	public static MiembroService getMiembroService() {
		return new MiembroServiceImpl();
	}

	public static AdminService getAdminService() {
		return new AdminServiceImpl();
	}
}
