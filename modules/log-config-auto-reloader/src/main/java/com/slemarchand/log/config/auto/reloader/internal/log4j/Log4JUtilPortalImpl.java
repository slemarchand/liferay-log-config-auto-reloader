package com.slemarchand.log.config.auto.reloader.internal.log4j;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.URL;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * Log4JUtil implementation for Liferay Portal 7.4 GA 41 and above.
 *
 * @See https://github.com/liferay/liferay-portal/blob/7.4.3.41-ga41/modules/apps/portal/portal-log4j/bnd.bnd
 *
 * @author Sébastien Le Marchand
 *
 */
@Component(enabled = false, service = Log4JUtil.class)
public class Log4JUtilPortalImpl implements Log4JUtil {

	@Activate
	public void activate() {
		_log.info("activate()");
	}

	public void configureLog4J(URL url) {
		com.liferay.portal.log4j.Log4JUtil.configureLog4J(url);
	}

	public void setLevel(String name, String priority, boolean custom) {
		com.liferay.portal.log4j.Log4JUtil.setLevel(name, priority, custom);
	}

	private Log _log = LogFactoryUtil.getLog(Log4JUtilPortalImpl.class);

}