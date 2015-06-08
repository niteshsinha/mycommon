package com.niteshsinha.mycommon.config;

import java.util.Properties;

public interface IConfigFactory<C extends IConfig> {

	public C createConfig(Properties properties);
}
