package com.niteshsinha.mycommon.db.relational.manager;

import java.util.Properties;

import com.niteshsinha.mycommon.config.ConfigManager;
import com.niteshsinha.mycommon.config.IConfigFactory;
import com.niteshsinha.mycommon.db.relational.config.MysqlDbConfig;

public final class MysqlDbConfigManager {

	private static final String DEFAULT_MYSQL_DB_CONFIG_FILENAME = "mysql.db.properties";
	public static final String KEY_MYSQLDB_CONF_FILENAME = "MysqlDb.ConfigFileName";
	
	private static final MysqlDbConfigManager instance = new MysqlDbConfigManager();
	private ConfigManager<MysqlDbConfig> configManager = null;
	
	private MysqlDbConfigManager(){
		//enforce singleton
	}
	
	private String getConfFileName() {
		Object confFileName = System.getProperty(KEY_MYSQLDB_CONF_FILENAME);
		if (confFileName instanceof String) {
			return (String) confFileName;
		}
		return DEFAULT_MYSQL_DB_CONFIG_FILENAME;
	}
	
	public static MysqlDbConfigManager getInstance() {
		return instance;
	}
	
	public ConfigManager<MysqlDbConfig> getConfigManager() {
		return this.configManager;
	}
	
	public void loadDbConfig() {
		if(this.configManager == null){
			String configFileName = getConfFileName();
			this.configManager = new ConfigManager<MysqlDbConfig>(configFileName, new IConfigFactory<MysqlDbConfig>() {
				public MysqlDbConfig createConfig(Properties properties) {
					return new MysqlDbConfig(properties);
				}
			});
		}
	}
}
