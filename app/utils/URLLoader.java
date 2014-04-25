package utils;



import java.io.IOException;
import java.util.Properties;


public class URLLoader {

	private static final String CONF_FILE = "organizations.properties";
	
	private static URLLoader instance;
	private Properties properties;

	private URLLoader() {
		properties = new Properties();
		try {
			properties.load(URLLoader.class.getClassLoader().getResourceAsStream(CONF_FILE));
		} catch (IOException e) {
			throw new RuntimeException("Propeties file can not be loaded", e);
		}
	}
	
	public static String getUrl(String key) {
		return getInstance().getURL( key );
	}

	private String getURL(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in config file");
		}
		return value;
	}

	private static URLLoader getInstance() {
		if (instance == null) {
			instance = new URLLoader();
		}
		return instance;
	}

}
