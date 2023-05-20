package com.slemarchand.log.config.auto.reloader.internal.log4j;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.URL;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(service = Log4JUtil.class, immediate=true)
public class Log4JUtilImpl implements Log4JUtil {

	@Activate
	public void activate() {
		
		_log.debug("activate()");
		
		_petraImpl = _isPetraImpl();
	}

	public void configureLog4J(URL url) {
		if(_petraImpl) {
			com.liferay.petra.log4j.Log4JUtil.configureLog4J(url);
		} else {
			com.liferay.portal.log4j.Log4JUtil.configureLog4J(url);
		}
	}

	public void setLevel(String name, String priority, boolean custom) {
		if(_petraImpl) {
			com.liferay.petra.log4j.Log4JUtil.setLevel(name, priority, custom);
		} else {
			com.liferay.portal.log4j.Log4JUtil.setLevel(name, priority, custom);
		}
	}
	
	private boolean _isPetraImpl() {
		try {
			this.getClass().getClassLoader().loadClass("com.liferay.petra.log4j.Log4JUtil");	
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private boolean _petraImpl = false;
	
	private Log _log = LogFactoryUtil.getLog(Log4JUtilImpl.class);
}