package com.slemarchand.log.config.auto.reloader.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.slemarchand.log.config.auto.reloader.internal.configuration.LogConfigAutoReloaderConfiguration;
import com.slemarchand.log.config.auto.reloader.internal.log4j.Log4JUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		service = LogLevelsPropertiesFileProcessor.class, 
		immediate = true, 
		configurationPid = "com.slemarchand.log.config.auto.reloader.internal.configuration.LogConfigAutoReloaderConfiguration")
public class LogLevelsPropertiesFileProcessor extends BaseLogConfigFileProcessor {
	
	@Override
	protected boolean isWatchFileEnabled(LogConfigAutoReloaderConfiguration configuration) {
		return configuration.loglevelsPropertiesFileWatch();
	}
	
	@Override
	protected String getLogConfigFilePath(LogConfigAutoReloaderConfiguration configuration) {
		return configuration.loglevelsPropertiesFilePath();
	}
	
	@Override
	protected boolean isCreateIfNotExistEnabled(LogConfigAutoReloaderConfiguration configuration) {
		return configuration.loglevelsPropertiesFileCreateIfNotExist();
	}
	
	@Override
	protected String getSampleLogConfigFileResourcePath() {
		return "/META-INF/samples/log-levels.properties";
	}
	
	@Override
	protected void processLogConfigFile(File logLevelsFile) throws IOException {
		
		_log.info("Setting log levels from " + logLevelsFile);
		
		Properties properties = new Properties();
		
		properties.load(new FileInputStream(logLevelsFile));
		
		Set<Entry<Object, Object>> entries = properties.entrySet();
		
		for (Entry<Object, Object> entry : entries) {
			
			String logger = entry.getKey().toString().trim();
			
			logger = logger.replaceAll("^log4j\\.", "");
			
			String level = entry.getValue().toString().trim().toUpperCase();
			
			_applyLogLevel(logger, level);
		}
	}

	private void _applyLogLevel(String logger, String level) {
		
		_log.info(String.format("Set log level %s = %s",  logger, level));
		
		_log4JUtil.setLevel(logger, level, true);
	}

	@Reference
	private Log4JUtil _log4JUtil;
	
	private final static Log _log = LogFactoryUtil.getLog(LogLevelsPropertiesFileProcessor.class);


}