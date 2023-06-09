# Log Config Auto Reloader for Liferay 🔥

![license](https://img.shields.io/github/license/slemarchand/lfr-log-config-auto-reloader?style=flat-square)

Watch any log config file and automatically apply log config each time the file is modified, without the requirement of any restart of anything.

## Compatibility

Liferay versions `7.1`, `7.2`, `7.3` and `7.4` are supported (both for `CE` and `DXP`).

## Installation

Get the `log-config-auto-reloader.jar` file from the [release page](https://github.com/slemarchand/lfr-log-config-auto-reloader/releases) and just drop it into your `$LIFERAY_HOME/deploy` directory.

## Configuration

Go to Control `Panel > System Settings > Infrastructure > Log Config Auto Reloader` and edit the configuration.

![Control Panel Configuration Form](doc/control_panel_screenshot.png) 

Configuration parameters are:
* **Watch log levels properties file** (yes/no)
* **Log levels properties file path** (file system path)
* **Create log levels properties file if not exist** (yes/no)
* **Watch Log4j XML file** (yes/no)
* **Log4j XML file path** (file system path)
* **Create Log4j XML file if not exist** (yes/no)

Don't forget to submit the configuration form.

Alternatively, you can just the drop [this configuration file](https://github.com/slemarchand/lfr-log-config-auto-reloader/blob/main/configs/dev/osgi/configs/com.slemarchand.log.config.auto.reloader.internal.configuration.LogConfigAutoReloaderConfiguration.config) (usual config for local developement) into your `$LIFERAY_HOME/osgi/deploy` directory.

## License

[MIT](LICENSE)