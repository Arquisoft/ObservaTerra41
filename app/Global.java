import play.*;
import conf.ServicesFactory;
import models.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
        InitialData.insert(app);
    }
    
	static class InitialData {
		public static void insert(Application app) {
			if (ServicesFactory.getCountryService().all().isEmpty()) {
				
				//some indicators
				ServicesFactory.getIndicatorService().create(new Indicator("Human Development Index"));
				ServicesFactory.getIndicatorService().create(new Indicator("Web Index"));

				//some countrys
				ServicesFactory.getCountryService().create(new Country("España"));
				ServicesFactory.getCountryService().create(new Country("Francia"));
				ServicesFactory.getCountryService().create(new Country("Italia"));				
				
				
				// Some observations
				ServicesFactory.getObservationService().addObservation(new Observation("ESP","HumanDevelopmentIndex",2.3));
				ServicesFactory.getObservationService().addObservation(new Observation("FRA","HumanDevelopmentIndex",3.4));
				ServicesFactory.getObservationService().addObservation(new Observation("ITA","HumanDevelopmentIndex",3.0));

				
				//url  repo 
				ServicesFactory.getUrlRepositoryService().addURL(new UrlRepository("https://data.undp.org/api/views/wxub-qc5k/rows.csv?accessType=DOWNLOAD"));
				
				//an admin
				ServicesFactory.getAdminService().createAdmin(new Admin("roque", "Alberto Roque", "Carrizo Fernandez", "123456", "roque@roque.roque"));
				ServicesFactory.getMiembroService().createMiembro(new Miembro("sergio", "sergio", "Sergio Jimenez", "123456", "sergio@sergio.sergio"));

				DataRequester.request();
			}
		}
	}
}
