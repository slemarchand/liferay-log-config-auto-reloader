package com.slemarchand.log.config.auto.reloader.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.slemarchand.log.config.auto.reloader.internal.configuration.LogConfigAutoReloaderConfiguration;
import com.slemarchand.log.config.auto.reloader.internal.log4j.Log4JUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
		service = Log4jXmlFileProcessor.class, 
		immediate = true, 
		configurationPid = "com.slemarchand.log.config.auto.reloader.internal.configuration.LogConfigAutoReloaderConfiguration")
public class Log4jXmlFileProcessor extends BaseLogConfigFileProcessor {
	
	@Override
	protected boolean isWatchFileEnabled(LogConfigAutoReloaderConfiguration configuration) {
		return configuration.log4jXmlFileWatch();
	}
	
	@Override
	protected String getLogConfigFilePath(LogConfigAutoReloaderConfiguration configuration) {
		return configuration.log4jXmlFilePath();
	}
	
	@Override
	protected boolean isCreateIfNotExistEnabled(LogConfigAutoReloaderConfiguration configuration) {
		return configuration.log4jXmlFileCreateIfNotExist();
	}
	
	@Override
	protected String getSampleLogConfigFileResourcePath() {
		return "/META-INF/samples/log4j.xml";
	}
	
	@Override
	protected void processLogConfigFile(File log4jXmlFile) throws IOException {
		
		_log.info("Setting log configuration from " + log4jXmlFile);
		
		_log4JUtil.configureLog4J(log4jXmlFile.toURI().toURL());
	}
	
	@Reference
	private Log4JUtil _log4JUtil;

	private final static Log _log = LogFactoryUtil.getLog(Log4jXmlFileProcessor.class);


}