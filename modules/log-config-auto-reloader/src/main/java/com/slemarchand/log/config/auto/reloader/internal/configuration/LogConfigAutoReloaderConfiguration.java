package com.slemarchand.log.config.auto.reloader.internal.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
		category = "infrastructure",
		scope = ExtendedObjectClassDefinition.Scope.SYSTEM
	)
	@Meta.OCD(
		id = "com.slemarchand.log.config.auto.reloader.internal.configuration.LogConfigAutoReloaderConfiguration",
		localization = "content/Language",
		name = "log-config-auto-reloader-configuration-name",
		description = "Watch log config file and automatically apply log config each time the file is modified, without the requirement of any restart of anything"
	)
public interface LogConfigAutoReloaderConfiguration {

	@Meta.AD(
			name = "log-levels-properties-file-watch", deflt = "false", required = false 
		)
	public boolean loglevelsPropertiesFileWatch();
	
	@Meta.AD(
			name = "log-levels-properties-file-create-if-not-exist", deflt = "false", required = false
		)
	public boolean loglevelsPropertiesFileCreateIfNotExist();
	
	@Meta.AD(
			name = "log-levels-properties-file-path", deflt = "${liferay.home}/log-levels.properties", required = false
		)
	public String loglevelsPropertiesFilePath();
	
	@Meta.AD(
			name = "log4j-xml-file-watch", deflt = "false", required = false 
		)
	public boolean log4jXmlFileWatch();
	
	@Meta.AD(
			name = "log4j-xml-file-create-if-not-exist", deflt = "false", required = false
		)
	public boolean log4jXmlFileCreateIfNotExist();
	
	@Meta.AD(
			name = "log4j-xml-file-path", deflt = "${liferay.home}/log4j.xml", required = false
		)
	public String log4jXmlFilePath();
	

}
