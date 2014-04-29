package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import models.Observation;

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

		System.out.println(responseStrBuilder);

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
