package utils;

import java.util.ArrayList;
import java.util.List;

import models.Indicator;
import models.Observation;

import org.junit.Assert;
import org.junit.Test;
import org.json.*;

public class JSONTest {

	@Test
	public void testTamaño() {

		
		try {
				List<Observation>ls=precarga();
		        Assert.assertTrue(ls.size() == 3);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testPaises() {

		
		try {
				List<Observation>ls=precarga();
		        Assert.assertTrue(ls.get(1).getCountry().getName().compareToIgnoreCase("España") == 0);
		        Assert.assertTrue(ls.get(2).getCountry().getName().compareToIgnoreCase("Francia") == 0);
		        Assert.assertTrue(ls.get(3).getCountry().getName().compareToIgnoreCase("Italia") == 0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testMediavalores() {

		
		try {
				double acum=0;
				List<Observation>ls=precarga();
		       for(Observation o :ls){
		    	   
		    	   acum+=o.getObsValue();
		       }
		        Assert.assertTrue(acum==36.9);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<Observation> precarga() throws JSONException{
		JSONObject jsonObj;
		jsonObj = new JSONObject("data/1");

		JSONObject aux = (JSONObject) jsonObj.get("lectura");
		JSONArray aux2 = (JSONArray) aux.get("valores");

		List<Observation> ls= new ArrayList<Observation>();
		for(int i = 0; i < aux2.length(); i++){
			String pais = aux2.getJSONObject(i).getString("pais");    
	        String indicador = aux2.getJSONObject(i).getString("Indicador");
	        String valueaux = aux2.getJSONObject(i).getString("valor");
	        Observation o = new Observation(pais,indicador,Double.parseDouble(valueaux));
	        
	        ls.add(o);
	}
		return ls;

}
	}
