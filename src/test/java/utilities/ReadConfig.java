package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	String path = "Config.properties";

	// constructor
	public ReadConfig() {
		try {
			prop = new Properties();

			// to open Config.properties file in input mode and load the file
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBrowser() {
		String value = prop.getProperty("browser");

		if (value != null)
			return value;
		else
			throw new RuntimeException("url not specified in config file.");

	}

}
