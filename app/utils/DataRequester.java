package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashSet;
import java.util.Set;

import play.Logger;
import conf.ServicesFactory;
import models.UrlRepository;

/**
 * Ejemplo de tata request
 * 
 * @author Sergio
 * 
 */
public class DataRequester {

	private static Set<String> paths = new HashSet<String>();

	public static void request() {
		try {
			for (UrlRepository repo : ServicesFactory.getUrlRepositoryService()
					.all()) { 
				URL url = new URL(repo.getUrl());
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream("data/"
						+ repo.getId());
				paths.add("data/" + repo.getId());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
			}
		} catch (Exception e) {
			Logger.error("Fallo al intentar recuperar datos: " + e);
		}
		
		if(paths.size()>0){
			autopersists();
		}
		
		//No hace falta guardarlas
		for(String path : paths){
			File file = new File(path);
			file.delete();
		}
	}
	private static void autopersists(){
		for(String st:paths){
			
			persistsCsvData(st);
		}
	}

	private static void persistsCsvData(String filepath) {
		CSV.persistCSV(filepath);
	}

}
