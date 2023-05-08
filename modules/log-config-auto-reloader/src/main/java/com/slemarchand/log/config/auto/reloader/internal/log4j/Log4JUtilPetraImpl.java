package com.slemarchand.log.config.auto.reloader.internal.log4j;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.URL;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * Log4JUtil implementation for Liferay Portal 7.0, 7.1, 7.2, 7.3 and 7.4 until GA 40 included.
 *
 * @See https://github.com/liferay/liferay-portal/blob/7.4.3.40-ga40/modules/apps/petra/petra-log4j/bnd.bnd
 *
 * @author Sébastien Le Marchand
 *
 */
@Component(enabled = false, service = Log4JUtil.class)
public class Log4JUtilPetraImpl implements Log4JUtil {

	@Activate
	public void activate() {
		_log.debug("activate()");
	}

	public void configureLog4J(URL url) {
		com.liferay.petra.log4j.Log4JUtil.configureLog4J(url);
	}

	public void setLevel(String name, String priority, boolean custom) {
		com.liferay.petra.log4j.Log4JUtil.setLevel(name, priority, custom);
	}

	private Log _log = LogFactoryUtil.getLog(Log4JUtilPortalImpl.class);

}