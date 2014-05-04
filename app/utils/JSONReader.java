package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import models.Country;
import models.Indicator;
import models.Observation;
import org.json.*;

import business.CountryService;
import business.IndicatorSercive;
import business.ObservationService;
import business.impl.CountryServiceImpl;
import business.impl.IndicatorServiceImpl;
import business.impl.ObservationServiceImpl;

public class JSONReader {

	public List<Observation> read(InputStream file) throws IOException {

		List<Observation> obsList = new ArrayList<Observation>();
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				file, "UTF-8"));
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		while ((inputStr = streamReader.readLine()) != null) {
			responseStrBuilder.append(inputStr);
		}
		
		try {
			JSONObject jsonObj= new JSONObject (responseStrBuilder.toString());
			
			JSONObject aux= (JSONObject) jsonObj.get("lectura");
			JSONArray aux2= (JSONArray) aux.get("valores");
			
			String pais;
			String indicador;
			double value;
			
			CountryService cs = new CountryServiceImpl();
			IndicatorSercive is= new IndicatorServiceImpl();
			ObservationService os= new ObservationServiceImpl();
			
			
	        for(int i = 0; i < aux2.length(); i++){
	            pais = aux2.getJSONObject(i).getString("pais");    
	            indicador = aux2.getJSONObject(i).getString("Indicador");
	            String valueaux = aux2.getJSONObject(i).getString("valor");
	            
	            
	            if(cs.findByName(pais)==null){
		        	   Country c= new Country(pais);
		        	   cs.create(c);
		         }
		        if(is.findByName(indicador)==null){
		            	Indicator in = new Indicator(indicador);
		            	is.create(in);
		        }
		        
	            value= Double.parseDouble(valueaux);
	            Observation ob= new Observation(cs.findByName(pais),is.findByName(indicador),value);
	            os.addObservation(ob);
	        }


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

		// .....................
		// List<Observation> obsList = new ArrayList<Observation>();
		//
		// JSONParser parser = new JSONParser();
		//
		// String line;
		//
		// try {
		// int i=file.read();
		// String aux= file+" ";
		//
		// System.out.println(aux);
		//
		// while((line = bf.readLine()) != null){
		//
		// System.out.println(line);
		// Object obj = parser.parse(line);
		//
		// JSONObject jsonObject = (JSONObject) obj;
		//
		// String country = (String) jsonObject.get("Country");
		//
		// String indicator = (String) jsonObject.get("Indicator");
		//
		// Double value = (Double) jsonObject.get("Value");
		// }
		//
		//
		//
		//
		//
		//
		//
		//
		// } catch (FileNotFoundException e) {
		// // manejo de error
		// } catch (IOException e) {
		// // manejo de error
		// } catch (ParseException e) {
		// // manejo de error
		// }
		return obsList;
	}
}
