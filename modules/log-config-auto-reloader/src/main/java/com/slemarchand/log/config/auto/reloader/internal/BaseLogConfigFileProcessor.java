package com.slemarchand.log.config.auto.reloader.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.slemarchand.log.config.auto.reloader.internal.configuration.LogConfigAutoReloaderConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

public abstract class BaseLogConfigFileProcessor {
	
	@Modified
	@Activate
	public void activate(Map<String, Object> properties) throws IOException {
		
		_configuration = ConfigurableUtil.createConfigurable(
				 LogConfigAutoReloaderConfiguration.class, properties);
	
		if(isWatchFileEnabled(_configuration)) {
			
			_checkConfigFile();
			
			_processLogConfigFile();
			
			_fileWatcher = new FileWatcher(_getLogConfigFile()) {
				@Override
				public void doOnChange() {
					try {
						_processLogConfigFile();
					} catch (IOException e) {
						_log.error(e);
					}
				}	
			};		
			
			_fileWatcher.start();
		
		} else if(_fileWatcher != null) {
			_fileWatcher.stopThread();
		}		
	}

	@Deactivate
	public void deactivate() {
		
		_log.debug("deactivate()");
		
		if(_fileWatcher != null) {
			_fileWatcher.stopThread();
		}
	}
	
	private void _checkConfigFile() throws IOException {
		
		File file = _getLogConfigFile();
		
		if(!file.exists()) {
			if(isCreateIfNotExistEnabled(_configuration)) {
				
				String sampleContent = _toString(this.getClass().getClassLoader().getResourceAsStream(getSampleLogConfigFileResourcePath()));

				FileUtil.write(file, sampleContent);
				
			} else {
				throw new FileNotFoundException("File not found: " + file.toString());
			}
		}	
	}
	
	private String _toString(InputStream inputStream) {
		
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		
		String str = scanner.useDelimiter("\\A").next();
		
		scanner.close();
		
		return str;
	}
	
	protected abstract boolean isWatchFileEnabled(LogConfigAutoReloaderConfiguration configuration);

	protected abstract boolean isCreateIfNotExistEnabled(LogConfigAutoReloaderConfiguration configuration);
	
	protected abstract String getLogConfigFilePath(LogConfigAutoReloaderConfiguration configuration);
	
	protected abstract String getSampleLogConfigFileResourcePath();
	
	protected abstract void processLogConfigFile(File logConfigFile) throws IOException;
	
	private void _processLogConfigFile() throws IOException {
		processLogConfigFile(_getLogConfigFile());
	}

	private File _getLogConfigFile() {
		return new File(_resolveVariables(_configuration.loglevelsPropertiesFilePath()));
	}

	private String _resolveVariables(String content) {
		
		String newContent = content;
		
		Pattern varPattern = Pattern.compile("\\$\\{([^}]*)\\}");
		
		Matcher matcher = varPattern.matcher(content);
			
		while(matcher.find()) {
			
			String varName = matcher.group(1);
			
			String varValue = System.getProperty(varName);
			
			if(varValue == null || varValue.trim().isEmpty()) {
				varValue = System.getenv(varName);
			}
			
			newContent = newContent.replaceAll(Pattern.quote("${" + varName + "}"), varValue);
		}
		
		return newContent;
	}

	private volatile LogConfigAutoReloaderConfiguration _configuration;
	
	private FileWatcher _fileWatcher;
	
	private final static Log _log = LogFactoryUtil.getLog(BaseLogConfigFileProcessor.class);
}