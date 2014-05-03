package models;



import org.junit.Assert;
import org.junit.Test;

public class ModelTest {
	@Test
	public void testIndicadores(){
		Indicator uno = new Indicator("HDI rank",2012);
		Indicator dos = new Indicator("HDI rank",2012);
		Indicator tres = new Indicator("HDI rank",2013);

		Assert.assertTrue(uno.equals(dos));
		Assert.assertFalse(uno.equals(tres));
	}
	
	
	@Test
	public void testPaises(){
		Country uno = new Country("Côte d'Ivoire");
		Country dos = new Country("Côte d'Ivoire");//no impor que el pais este mal escrito pues es el mismo pais
		Country tres = new Country("Italy");
		Assert.assertTrue(uno.equals(dos));
		Assert.assertFalse(uno.equals(tres));
	}



}
