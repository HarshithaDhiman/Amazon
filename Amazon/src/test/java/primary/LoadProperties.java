package primary;

import java.io.FileInputStream;
import java.util.Properties;


public class LoadProperties {
	Properties prop;
	String dataPropFilePath;
	public LoadProperties() {
		prop = new Properties();
		dataPropFilePath = System.getProperty("user.dir")+"\\Properties\\user.properties";
	}

	public String getProp(String key) {
		try {
			FileInputStream fis = new FileInputStream(dataPropFilePath);
			prop.load(fis);
		}
		catch(Exception e){
			ColorLog.i("From ReadProperties Class", "Properties file does not exist, please check!");
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
