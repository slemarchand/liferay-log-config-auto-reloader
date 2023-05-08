package com.slemarchand.log.config.auto.reloader.internal.log4j;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(service = Log4JUtilRegistrar.class, immediate = true)
public class Log4JUtilRegistrar {
	
	public static final String VERSION_7_4_3_41 = "7.4.3.41";
	
	@Activate
	public void activate(BundleContext context) throws InvalidSyntaxException {
		
		_log.debug("activate()");
		
		// https://github.com/liferay/liferay-portal/blob/7.0.6-ga7/portal-kernel/src/com/liferay/portal/kernel/util/ReleaseInfo.java	
		
		String currentVersion = ReleaseInfo.getVersion(); // for example 7.4.3.75
		
		boolean versionGreaterThanOrEqualTo74GA41 = new Version(currentVersion)
				.greaterThanOrEqualTo(VERSION_7_4_3_41);
	
		Log4JUtil service;
		
		if(versionGreaterThanOrEqualTo74GA41) {
			
			_log.info("Version dected is greater or equal to 7.4.3.41");
			
			service = new Log4JUtilPortalImpl();
			
		} else {
			
			_log.info("Version dected is lesser or equal to 7.4.3.40");
			
			service = new Log4JUtilPetraImpl();
		}
		
		context.registerService(Log4JUtil.class, service, null);
		
		_log.debug("Service registred: " +  service.toString());
	}
	
	private Log _log = LogFactoryUtil.getLog(Log4JUtilRegistrar.class);
	


}

