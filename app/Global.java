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
			//Pagina principal: http://hdr.undp.org/en/data
			//table1: https://data.undp.org/api/views/wxub-qc5k/rows.csv?accessType=DOWNLOAD
			//table 2: diferente estructura -- no parseable
			//table3: https://data.undp.org/api/views/9jnv-7hyp/rows.csv?accessType=DOWNLOAD
			//table 4: https://data.undp.org/api/views/pq34-nwq7/rows.csv?accessType=DOWNLOAD
			//table 5: no compaible
			//table 6: https://data.undp.org/dataset/Table-6-Command-over-resources/ti85-2nvi
			//table 7: https://data.undp.org/api/views/iv8b-7gbj/rows.csv?accessType=DOWNLOAD
			//table 8: no compaible
			//table 9: no compaible
			//table 10:https://data.undp.org/api/views/itri-v7qr/rows.csv?accessType=DOWNLOAD
			// url repo
			ServicesFactory
					.getUrlRepositoryService()
					.addURL(new UrlRepository(
							"https://data.undp.org/api/views/wxub-qc5k/rows.csv?accessType=DOWNLOAD","Naciones Unidas"));
			ServicesFactory
			.getUrlRepositoryService()
			.addURL(new UrlRepository(
					"https://data.undp.org/api/views/9jnv-7hyp/rows.csv?accessType=DOWNLOAD","Naciones Unidas"));
			
			ServicesFactory
			.getUrlRepositoryService()
			.addURL(new UrlRepository(
					"https://data.undp.org/api/views/pq34-nwq7/rows.csv?accessType=DOWNLOAD","Naciones Unidas"));
			
			ServicesFactory
			.getUrlRepositoryService()
			.addURL(new UrlRepository(
					"https://data.undp.org/dataset/Table-6-Command-over-resources/ti85-2nvi","Naciones Unidas"));
			
			ServicesFactory
			.getUrlRepositoryService()
			.addURL(new UrlRepository(
					"https://data.undp.org/api/views/iv8b-7gbj/rows.csv?accessType=DOWNLOAD","Naciones Unidas"));
			
			ServicesFactory
			.getUrlRepositoryService()
			.addURL(new UrlRepository(
					"https://data.undp.org/api/views/itri-v7qr/rows.csv?accessType=DOWNLOAD","Naciones Unidas"));
			
			
			

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
			 //DataRequester.persistsCsvData("data/1");
		}

	}
}
