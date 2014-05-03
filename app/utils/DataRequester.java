package utils;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import conf.ServicesFactory;
import models.UrlRepository;

/**
 * Ejemplo de tata request
 * 
 * @author Sergio
 * 
 */
public class DataRequester {

	public static void request() {
		try {
			for (UrlRepository repo : ServicesFactory.getUrlRepositoryService()
					.all()) {
				URL url = new URL(repo.getUrl());
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream("data/"
						+ repo.getId());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void persistsCsvData(String filepath){
		CSV.persistCSV(filepath);
		
	}
	

}
