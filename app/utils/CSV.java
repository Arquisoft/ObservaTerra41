package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import conf.ServicesFactory;
import models.Country;
import models.Indicator;
import models.Observation;

public class CSV {

	private static boolean[] hasObservations;
	protected static Indicator[] indicadores;
	protected static Set<Country> paises = new HashSet<Country>();
	protected static Set<Observation> observaciones = new HashSet<Observation>();

	/**
	 * Lee el fichero y lo parsea linea a linea
	 * 
	 * @param filepath
	 */
	protected static void read(String filepath) {
		FileReader filereader = null;
		BufferedReader bufferedReader = null;
		try {
			filereader = new FileReader(filepath);
			bufferedReader = new BufferedReader(filereader);

			// Lectura y procesado del fichero
			String linea;
			int numeroLinea = 1;
			while ((linea = bufferedReader.readLine()) != null) {
				procesador(linea, numeroLinea);
				numeroLinea++;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != filereader) {
					filereader.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void persistCSV(String filepath){
		read(filepath);
		for(Indicator ind: indicadores){
			if(ind != null){
			ServicesFactory.getIndicatorService().create(ind);}
		}
		for(Country co : paises){
			ServicesFactory.getCountryService().create(co);
		}
		for(Observation ob:observaciones){
			ServicesFactory.getObservationService().addObservation(ob);
		}
		
	}
	private static void procesador(String linea, int numeroLinea) {
		String[] columnas = linea.split(",");
		if (numeroLinea == 1) {
			procesarCabecera(columnas);
		} else {
			procesarCuerpo(columnas);
		}

	}

	private static void procesarCuerpo(String[] columnas) {

		Country paisEvaluado = null;

		// buscamos si realmente evaluamos un pais (puede ser otra cosa 'world')
		for (int i = 0; i < columnas.length; i++) {
			if (!hasObservations[i]) {
				paisEvaluado = addCountry(columnas[i], columnas[i + 1],
						columnas[i + 2]);
				break;
			}
		}

		// de ser asi creamos las observaciones pertinentes
		if (paisEvaluado != null) {
			for (int i = 0; i < columnas.length; i++) {
				if (hasObservations[i]) {
					observaciones.add(new Observation(paisEvaluado,
							indicadores[i], getValor(columnas[i])));

				}
			}
		}

	}

	/**
	 * procesamos la cabecera de donde obtenemos los indicadores e informacion
	 * para luego saber de donde extraer los datos pra las observaciones
	 * 
	 * @param columnas
	 */
	private static void procesarCabecera(String[] columnas) {
		hasObservations = new boolean[columnas.length];
		indicadores = new Indicator[columnas.length];
		for (int i = 0; i < columnas.length; i++) {
			hasObservations[i] = isIndicator(columnas[i], i);
		}
	}

	/**
	 * Crearemos un pais si el tipo del mismo es un ranked country realmente la
	 * abreviacion del apis la despreciamos
	 * 
	 * @param dato
	 */
	private static Country addCountry(String name, String type,
			String Abbreviation) {
		if (StripString.Strip(type).equals("Ranked Country")) {
			paises.add(new Country(name));
			return new Country(name);
		}
		return null;

	}

	/**
	 * sabemos que es un indicador porque delante tiene el año del mismo si es
	 * indicador lo añade al set
	 * 
	 * @param dato
	 */
	private static boolean isIndicator(String dato, int index) {
		char[] valor = dato.toCharArray();
		String fecha = "";
		String indicador = "";
		for (char c : valor) {
			if (Character.isDigit(c)) {
				fecha += c;
			} else {
				if (fecha.equals("")) {
					// no es un indicador, no continues
					break;
				} else {
					indicador += c;
				}
			}
		}
		indicador = StripString.Strip(indicador);
		if (!indicador.equals("")) {
			indicadores[index] = new Indicator(indicador,
					Integer.parseInt(fecha));
			return true;
		}
		return false;
	}

	/**
	 * Obtiene el valor del String en formato double
	 * 
	 * @param data
	 * @return
	 */
	private static Double getValor(String data) {
		try {

			return Double.parseDouble(data);
		} catch (Exception e) {
			try {
				return Integer.parseInt(data) + 0.0;
			} catch (Exception e1) {
				return 0.0;
			}
		}
	}

}
