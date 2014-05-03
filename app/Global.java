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

			// url repo
			ServicesFactory
					.getUrlRepositoryService()
					.addURL(new UrlRepository(
							"https://data.undp.org/api/views/wxub-qc5k/rows.csv?accessType=DOWNLOAD"));

			// an admin
			ServicesFactory.getAdminService().createAdmin(
					new Admin("roque", "Alberto Roque", "Carrizo Fernandez",
							"e10adc3949ba59abbe56e057f20f883e",
							"roque@roque.roque"));
			//a user
			ServicesFactory.getMiembroService().createMiembro(
					new Miembro("sergio", "sergio", "Sergio Jimenez",
							"e10adc3949ba59abbe56e057f20f883e",
							"sergio@sergio.sergio"));

			//download the data,parse it and load into bd
			DataRequester.request();
			DataRequester.persistsCsvData("data/1");
		}

	}
}
