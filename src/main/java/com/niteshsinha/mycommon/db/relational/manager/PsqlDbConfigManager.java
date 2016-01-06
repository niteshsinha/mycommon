package com.niteshsinha.mycommon.db.relational.manager;

import java.util.Properties;

import com.niteshsinha.mycommon.config.ConfigManager;
import com.niteshsinha.mycommon.config.IConfigFactory;
import com.niteshsinha.mycommon.db.relational.config.PsqlDbConfig;

public final class PsqlDbConfigManager {

	private static final String DEFAULT_MYSQL_DB_CONFIG_FILENAME = "psql.db.properties";
	public static final String KEY_MYSQLDB_CONF_FILENAME = "PsqlDb.ConfigFileName";
	
	private static final PsqlDbConfigManager instance = new PsqlDbConfigManager();
	private ConfigManager<PsqlDbConfig> configManager = null;
	
	private PsqlDbConfigManager(){
		//enforce singleton
	}
	
	private String getConfFileName() {
		Object confFileName = System.getProperty(KEY_MYSQLDB_CONF_FILENAME);
		if (confFileName instanceof String) {
			return (String) confFileName;
		}
		return DEFAULT_MYSQL_DB_CONFIG_FILENAME;
	}
	
	public static PsqlDbConfigManager getInstance() {
		return instance;
	}
	
	public ConfigManager<PsqlDbConfig> getConfigManager() {
		return this.configManager;
	}
	
	public void loadDbConfig() {
		if(this.configManager == null){
			String configFileName = getConfFileName();
			this.configManager = new ConfigManager<PsqlDbConfig>(configFileName, new IConfigFactory<PsqlDbConfig>() {
				public PsqlDbConfig createConfig(Properties properties) {
					return new PsqlDbConfig(properties);
				}
			});
		}
	}
}


