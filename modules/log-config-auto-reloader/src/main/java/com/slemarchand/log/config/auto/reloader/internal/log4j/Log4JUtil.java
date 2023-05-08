package com.slemarchand.log.config.auto.reloader.internal.log4j;

import aQute.bnd.annotation.ProviderType;

import java.net.URL;

@ProviderType
public interface Log4JUtil {

	public void configureLog4J(URL url);

	public void setLevel(String name, String priority, boolean custom);

}