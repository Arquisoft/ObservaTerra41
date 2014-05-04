package utils;

import models.Country;
import models.Indicator;
import models.Observation;

import org.junit.Assert;
import org.junit.Test;

public class CSVTest {

	

	@Test
	public void testIndicadores() {
		CSV.read("data/1");
		int totalIndicadores = 0;
		for (Indicator ind : CSV.indicadores) {
			if (ind != null) {
				// System.out.println(ind.toString());
				totalIndicadores++;
			}
		}
		Assert.assertTrue(totalIndicadores == 11);
	}

	
	/**
	 * Nos aseguramos de que no tenemos paises repes
	 */
	@Test
	public void testPaises() {
		CSV.read("data/3");
		Country[] paises = CSV.paises.toArray(new Country[0]);
		Country evaluated = null;
		for (int i = 0; i < paises.length; i++) {
			evaluated = paises[i];
			paises[i] = null;
			for (int j = 0; j < paises.length; j++) {
				if (paises[j] != null) {
					Assert.assertFalse(evaluated.equals(paises[j]));
				}
			}
		}
	}
	
	
	/**
	 * Nos aseguramos de que no tenemos paises repes
	 */
	@Test
	public void testObservaciones() {
		CSV.read("data/1");
		for(Observation ob : CSV.observaciones){
			System.out.println(ob.toString());
		}
	}
}
