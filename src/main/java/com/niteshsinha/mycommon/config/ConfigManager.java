package com.niteshsinha.mycommon.config;

import java.util.Properties;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class ConfigManager <C extends IConfig >{
	
	private static Logger logger = BaseLoggerProvider.getLogger(ConfigManager.class);
	private final String configFileName;
	
	// Store specific service related config properties used by respective services 
	private Properties configProperties = new Properties();
	
	protected boolean isVendorAware = false;
	
	private C config;
	private final IConfigFactory<C> configFactory;
	
	private String vendor = null;

	public ConfigManager(String configFileName, IConfigFactory<C> configFactory) {
		if (logger.isDebugEnabled()) {
			logger.debug("Entering constructor: "); 
			logger.debug("ConfigFileName : " + configFileName); 
		}
		
		this.configFileName = configFileName;
		this.configFactory = configFactory;
		
		try{
			this.configProperties = ConfigUtil.loadPropertiesFile(configFileName);
		}catch (Exception e) {
			logger.error("Could not find the config file name: " + configFileName, e);
			//e.printStackTrace();
			/**
			 * TODO Logger
			 */
		}
		this.config = configFactory.createConfig((Properties)configProperties.clone());
    }
	
	public C getConfig() {
        return config;
    }
}
