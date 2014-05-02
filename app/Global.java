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
				ServicesFactory.getIndicatorService().create(new Indicator("hdi", "Human Development Index"));
				ServicesFactory.getIndicatorService().create(new Indicator("wi", "WebIndex"));

				//some countrys
				ServicesFactory.getCountryService().create(new Country("es", "España"));
				ServicesFactory.getCountryService().create(new Country("fr", "Francia"));
				ServicesFactory.getCountryService().create(new Country("it", "Italia"));				
				
				
				// Some observations
				ServicesFactory.getObservationService().addObservation(new Observation("es","hdi",2.3));
				ServicesFactory.getObservationService().addObservation(new Observation("fr","hdi",3.4));
				ServicesFactory.getObservationService().addObservation(new Observation("it","hdi",3.0));

				
				//url  repo 
				ServicesFactory.getUrlRepositoryService().addURL(new UrlRepository("https://data.undp.org/api/views/wxub-qc5k/rows.csv?accessType=DOWNLOAD"));
				
				//an admin
				ServicesFactory.getAdminService().createAdmin(new Admin("roque", "Alberto Roque", "Carrizo Fernandez", "123456", "roque@roque.roque"));

				DataRequester.request();
			}
		}
	}
}
