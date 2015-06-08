package com.niteshsinha.mycommon.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class ConfigUtil {

	private static Logger logger = BaseLoggerProvider.getLogger(ConfigUtil.class);
	
	public static Properties loadPropertiesFile(String fileName) throws FileNotFoundException, IOException{
		logger.info("whts the file name: " + fileName);
		Properties configProperties = new Properties();
		InputStream configFileStream = null;
		try{
		    configFileStream = getInputStreamFromFile(fileName);
		    // Store specific service related config properties used by respective services 
			configProperties.load(configFileStream);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Retrying with the full absolute path");
			//configProperties = ConfigUtil.loadPropertiesFile(fileName, true);
		}finally{
		    if(configFileStream != null) {
		        configFileStream.close();
		    }
		}
		logger.info("Done with processing the config file:" + fileName);
		return configProperties;
	}
	
	private static InputStream getInputStreamFromFile(String path) throws FileNotFoundException {
    	String START_PREFIX_1="./";
    	String START_PREFIX_2="/";
    	InputStream stream = null;
    	if(path != null){
    		if(path.startsWith(START_PREFIX_1)){
    			path = path.substring(2);
    		}
    		if(path.startsWith(START_PREFIX_2)){
    			path = path.substring(1);
    		}   		
    		stream = ConfigUtil.class.getClassLoader().getResourceAsStream(path);
    		
    		if (stream == null){
    			throw new FileNotFoundException(path + " : Not found!!!!!");
    		}
    	}
    	return stream;
	}
	
	public static String getString(Properties properties, String propertyKey, String defaultPropertyValue) {
        if (properties == null) {
            return defaultPropertyValue;
        }
        String returnValue = properties.getProperty(propertyKey, defaultPropertyValue);
        //return StringUtil.isNullOrEmpty(returnValue) ? returnValue : returnValue.trim();
        return returnValue;
    }
	
	public static int getInt(Properties properties, String propertyKey, int defaultPropertyValue) {
        if (properties == null) {
            return defaultPropertyValue;
        }
        String value = (String) properties.get(propertyKey);
        int returnValue = defaultPropertyValue;

        if (value != null) {
            try {
                returnValue = Integer.parseInt(value.trim());
            } catch (NumberFormatException exception) {
                returnValue = defaultPropertyValue;
            }
        }

        return returnValue;
    }
	
	public static double getDouble(Properties properties, String propertyKey, double defaultPropertyValue) {
		if(properties == null) {
			return defaultPropertyValue;
		}
		String value = (String)properties.get(propertyKey);
		double returnValue = defaultPropertyValue;
		
		if(value != null) {
			try {
				returnValue = Double.parseDouble(value.trim());
			} catch (NumberFormatException exception) {
                returnValue = defaultPropertyValue;
            }
		}
		return returnValue;
	}

    public static long getLong(Properties properties, String propertyKey, long defaultPropertyValue) {
        if (properties == null) {
            return defaultPropertyValue;
        }
        String value = (String) properties.get(propertyKey);
        long returnValue = defaultPropertyValue;

        if (value != null) {
            try {
                returnValue = Long.parseLong(value.trim());
            } catch (NumberFormatException exception) {
                returnValue = defaultPropertyValue;
            }
        }

        return returnValue;
    }

    public static boolean getBoolean(Properties properties, String propertyKey, boolean defaultPropertyValue) {
        if (properties == null) {
            return defaultPropertyValue;
        }
        String value = (String) properties.get(propertyKey);
        boolean returnValue = defaultPropertyValue;

        if (value != null) {
            try {
                returnValue = Boolean.parseBoolean(value.trim());
            } catch (NumberFormatException exception) {
                returnValue = defaultPropertyValue;
            }
        }

        return returnValue;
    }
    
}
