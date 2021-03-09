package common;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {
	static String osName = System.getProperty("os.name");
	private static AppiumDriverLocalService service;

	public static Log getLog() {
		return LogFactory.getLog(AppiumServer.class);
	}

	public static void setInstance() {
		getLog().info("OS name = " + osName);

		if (osName.contains("Windows")) {
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withLogFile(new File("./ServerLogs/appium.txt")));

		} else if (osName.contains("Mac")) {
			HashMap<String, String> environment = new HashMap<String, String>();
			environment.put("ANDROID_HOME", "/Users/mv962/Library/Android/sdk");
			environment.put("JAVA_HOME", "/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home");
			environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));

			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
					.usingPort(Constants.PORT_APPIUM).withIPAddress(Constants.HOST_APPIUM).withArgument(GeneralServerFlag.SESSION_OVERRIDE)
					.withEnvironment(environment).withLogFile(new File("./ServerLogs/appium.log")));

		} else {
			getLog().info("AppiumServer - Unspecified OS found, Appium can't run");
		}

	}

	static AppiumDriverLocalService getInstance() {
		if (service == null) {
			setInstance();
			service.clearOutPutStreams();
		}
		return service;
	}

	public static void startServer() {
		if (!checkIfServerIsRunnning(Constants.PORT_APPIUM)) {
			getInstance().start();
			getLog().info("=====Appium " + osName + ": server started=====");
		} else {
			getLog().info("=====Appium " + osName + ": server already running=====");
		}
	}

	public static void stopServer() throws IOException {

		Runtime runtime = Runtime.getRuntime();
		if (osName.contains("Windows")) {
			try {
				runtime.exec("taskkill /F /IM node.exe");
				runtime.exec("taskkill /F /IM cmd.exe");
				getLog().info("Appium " + osName + ": server stopped");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (osName.contains("Mac")) {
			try {
				runtime.exec("killall node");
				getLog().info("=======Appium " + osName + ": server stopped==========");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

}
