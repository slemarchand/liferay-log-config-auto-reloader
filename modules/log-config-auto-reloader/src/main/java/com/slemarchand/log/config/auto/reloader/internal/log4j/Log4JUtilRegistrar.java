package com.slemarchand.log.config.auto.reloader.internal.log4j;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(service = Log4JUtilRegistrar.class, immediate = true)
public class Log4JUtilRegistrar {
	
	@Activate
	public void activate(BundleContext context) throws InvalidSyntaxException {
		
		_log.debug("activate()");
		
		Log4JUtil service;
		
		if(_isPortalImpl()) {
			service = new Log4JUtilPortalImpl();
		} else {
			service = new Log4JUtilPetraImpl();
		}
		
		context.registerService(Log4JUtil.class, service, null);
		
		_log.debug("Service registred: " +  service.toString());
	}
	
	private boolean _isPortalImpl() {
		try {
			this.getClass().getClassLoader().loadClass("com.liferay.portal.log4j.Log4JUtil");	
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private Log _log = LogFactoryUtil.getLog(Log4JUtilRegistrar.class);
	


}

