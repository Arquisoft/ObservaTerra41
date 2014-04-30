import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import conf.ServicesFactory;
import models.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
        InitialData.insert(app);
    }
    
	static class InitialData {
		public static void insert(Application app) {
			if (ServicesFactory.getCountryService().all().isEmpty()) {
				
				new Indicator("hdi", "Human Development Index").save();
				new Indicator("wi", "WebIndex").save();
				new Country("es", "España").save();
				new Country("fr", "Francia").save();
				new Country("it", "Italia").save();
				
				
				
				// Some observations
				new Observation("es","hdi",2.3).save();
				new Observation("fr","hdi",3.4).save(); 
				new Observation("it","hdi",3.0).save();
				
				new User("roque", "Alberto Roque", "Carrizo Fernandez", "123456", "roque@roque.roque").save();
				DataRequester.request();
			}
		}
	}
}
