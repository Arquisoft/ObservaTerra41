package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import models.Observation;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

	public List<Observation> read(InputStream input) throws IOException {
		List<Observation> obsList = new ArrayList<Observation>();

		JSONParser parser = new JSONParser();
	
		
		try {
			int i=input.read();
			
			while(i!=-1){
				
			}
			Object obj = parser.parse(new FileReader("prueba.json"));

			JSONObject jsonObject = (JSONObject) obj;

			String country = (String) jsonObject.get("Country");

			String indicator = (String) jsonObject.get("Indicator");

			Double value = (Double) jsonObject.get("Value");

		} catch (FileNotFoundException e) {
			// manejo de error
		} catch (IOException e) {
			// manejo de error
		} catch (ParseException e) {
			// manejo de error
		}
		return obsList;
	}
}
