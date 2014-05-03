import play.*;
import utils.DataRequester;
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

				ServicesFactory.getObservationService().addObservation(new Observation("ESPAÑA","HumanDevelopmentIndex",2.3));
				ServicesFactory.getObservationService().addObservation(new Observation("FRANCIA","HumanDevelopmentIndex",3.4));
				ServicesFactory.getObservationService().addObservation(new Observation("ITALIA","HumanDevelopmentIndex",3.0));

				ServicesFactory.getObservationService().addObservation(new Observation("ESPAÑA","WebIndex",2.3));
				ServicesFactory.getObservationService().addObservation(new Observation("FRANCIA","WebIndex",1.4));
				ServicesFactory.getObservationService().addObservation(new Observation("ITALIA","WebIndex",2.0));


				
				//url  repo 
				ServicesFactory.getUrlRepositoryService().addURL(new UrlRepository("https://data.undp.org/api/views/wxub-qc5k/rows.csv?accessType=DOWNLOAD"));
				
				//an admin
				ServicesFactory.getAdminService().createAdmin(new Admin("roque", "Alberto Roque", "Carrizo Fernandez", "e10adc3949ba59abbe56e057f20f883e", "roque@roque.roque"));
				ServicesFactory.getMiembroService().createMiembro(new Miembro("sergio", "sergio", "Sergio Jimenez", "e10adc3949ba59abbe56e057f20f883e", "sergio@sergio.sergio"));

				DataRequester.request();
			}
		}
	}
}
