package com.niteshsinha.mycommon.db.relational.manager;

import java.util.Properties;

import com.niteshsinha.mycommon.config.ConfigManager;
import com.niteshsinha.mycommon.config.IConfigFactory;
import com.niteshsinha.mycommon.db.relational.config.DBConfig;

public class DBConfigManager {

	private static final String DEFAULT_LOCAL_DB_CONFIG_FILENAME = "/mobius/pooldbconfig.properties";
	private static final String DEFAULT_GLOBAL_DB_CONFIG_FILENAME = "/mobius/globaldbconfig.properties";
	public static final String KEY_GLOBAL_DB_CONF_FILENAME = "mobius.global.dbconfig.ConfigFileName";
	public static final String KEY_LOCAL_DB_CONF_FILENAME = "mobius.local.dbconfig.ConfigFileName";
	
	private static final DBConfigManager instance = new DBConfigManager();
	private ConfigManager<DBConfig> globalConfigManager = null;
	private ConfigManager<DBConfig> localConfigManager = null;
	
	private DBConfigManager(){
		
	}
	
	private String getGlobalDBConfFileName() {
		Object confFileName = System.getProperties().get(KEY_GLOBAL_DB_CONF_FILENAME);

		if (confFileName instanceof String) {
			return (String) confFileName;
		}

		return DEFAULT_GLOBAL_DB_CONFIG_FILENAME;
	}
	
	private String getLocalDBConfFileName() {
		Object confFileName = System.getProperties().get(KEY_LOCAL_DB_CONF_FILENAME);

		if (confFileName instanceof String) {
			return (String) confFileName;
		}

		return DEFAULT_LOCAL_DB_CONFIG_FILENAME;
	}
	
	public static DBConfigManager getInstance() {
		return instance;
	}
	
	public ConfigManager<DBConfig> getGlobalConfigManager(){
		return this.globalConfigManager;
	}
	
	public ConfigManager<DBConfig> getLocalConfigManager(){
		return this.localConfigManager;
	}
	
	public void loadGlobalDBConfig(){
		if (this.globalConfigManager == null){
			String configFileName = getGlobalDBConfFileName();
			this.globalConfigManager = new ConfigManager<DBConfig>(configFileName, new IConfigFactory<DBConfig>(){
				public DBConfig createConfig(Properties dbConfigProperties){
					return new DBConfig(dbConfigProperties);
				}
			});
		}
	}
	
	public void loadLocalDBConfig(){
		if (this.localConfigManager == null){
			String configFileName = getLocalDBConfFileName();
			this.localConfigManager = new ConfigManager<DBConfig>(configFileName, new IConfigFactory<DBConfig>(){
				public DBConfig createConfig(Properties dbConfigProperties){
					return new DBConfig(dbConfigProperties);
				}
			});
		}
	}
}
