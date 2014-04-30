package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import models.Observation;

public class CSVReader {

	public List<Observation> read(InputStream file) throws IOException {

		List<Observation> obsList = new ArrayList<Observation>();
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				file, "UTF-8"));
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		while ((inputStr = streamReader.readLine()) != null) {
			inputStr = "--" + inputStr;
			responseStrBuilder.append(inputStr);
		}

		String texto = responseStrBuilder.toString();
		String[] cadenas = texto.split("--");
		int i = 1;
		while (i < cadenas.length) {

			String[] filas = cadenas[i].split(";");
			for (int k = 0; k < filas.length; k += 3) {
				System.out.println(filas[k] + filas[k + 1] + filas[k + 2]);
				String country = filas[k];
				String indicator = filas[k + 1];
				String valueString = filas[k + 2];
				Double value = Double.valueOf(valueString);
				Observation observation = new Observation(country, indicator,
						value);
				obsList.add(observation);
				System.out.println(observation);
			}
			i++;

		}

		return obsList;
	}

}
