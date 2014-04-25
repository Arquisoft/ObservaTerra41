

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
/**
 * Ejemplo de tata request
 * @author Sergio
 *
 */
public class DataRequester {

	private static String HumanDevelopmentIndexAndItsComponents ="https://data.undp.org/api/views/wxub-qc5k/rows.csv?accessType=DOWNLOAD";
	

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
