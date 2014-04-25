

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import utils.URLLoader;
/**
 * Ejemplo de tata request
 * @author Sergio
 *
 */
public class DataRequester {

	private static String HumanDevelopmentIndexAndItsComponents =URLLoader.getUrl("HumanDevelopmentIndexAndItsComponents");
	

	public static void request(){
		try{
			URL url=new URL(HumanDevelopmentIndexAndItsComponents);
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream("data/HumanDevelopmentIndexAndItsComponents.txt");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
