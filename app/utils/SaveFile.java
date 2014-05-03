package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class SaveFile {

	public void save(InputStream file) throws IOException {
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(
				file, "UTF-8"));
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		while ((inputStr = streamReader.readLine()) != null) {
			responseStrBuilder.append(inputStr + "\n");
		}
		String contenido = responseStrBuilder.toString();
		String salidaTxt = "data/Prueba.txt";
		PrintStream salida = new PrintStream(""
				+ salidaTxt + "");
		String[] linea = contenido.split("\n");
		for (int i = 0; i < linea.length; i++) {
			String bloque = linea[i];
			salida.println(bloque);
		}
		salida.close();

	}
}
