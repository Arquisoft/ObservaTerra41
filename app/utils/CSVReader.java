package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import business.CountryService;
import business.IndicatorSercive;
import business.ObservationService;
import business.impl.CountryServiceImpl;
import business.impl.IndicatorServiceImpl;
import business.impl.ObservationServiceImpl;

import models.Country;
import models.Indicator;
import models.Observation;

public class CSVReader {

	public List<Observation> read(InputStream file) throws IOException {

		List<Observation> obsList = new ArrayList<Observation>();
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				file, "UTF-8"));
		
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		
		
		
		String cabeceras="";
		
		cabeceras=streamReader.readLine();
		
		String [] aux;
		aux= cabeceras.split(",");
		
		
		for(int i =4;i<aux.length;i++){
			String st=aux[i].substring(5,aux[i].length());
			aux[i]=st;
		}
		
		for(int i =0;i<aux.length;i++){
			System.out.println(aux[i]);
		}
		
		
		IndicatorSercive is = new IndicatorServiceImpl();
		//List<Indicator>lst= is.all();
		
		for(int i=4;i<aux.length;i++){
			if(is.findByName(aux[i])==null){
				String pais=aux[i];
				//campo string vacio queda a espensas
				Indicator in= new Indicator(pais);
				is.create(in);
			}
		}
		
		
		CountryService cs= new CountryServiceImpl();
		ObservationService os= new ObservationServiceImpl();
		
		while ((inputStr = streamReader.readLine()) != null) {
			String []linea= inputStr.split(",");
			String pais=linea[1];
			String codepais=linea[3];
			
			
			
			if(cs.findByCode(codepais)==null){
				Country c= new Country(pais);
				cs.create(c);
			}
			
			for(int i =4;i<13;i++){
				if(isNumeric(linea[i])==true){
				Observation o= new Observation(cs.findByName(pais),is.findByName(aux[i]),Double.parseDouble(linea[i]));
				os.addObservation(o);
				}
				
				
			}
			
			
		}


		return obsList;
	}
	
	public static boolean isNumeric( String s ){
	    try{
	        double y = Double.parseDouble( s );
	        return true;
	    }
	    catch( NumberFormatException err ){
	        return false;
	    }
	}

}
