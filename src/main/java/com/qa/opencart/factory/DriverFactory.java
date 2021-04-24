package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This method is for initializing the Web driver instance and return the same
 * 
 * @author yazharasi prabhakaran
 *
 */
public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String highlight = null;
	public static ThreadLocal<WebDriver> tlLocal = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlLocal.get();
	}

	public WebDriver init_driver() {

		highlight = prop.getProperty("highlight").trim();
		OptionsManager op = new OptionsManager(prop);

		String browsername = prop.getProperty("browser").trim();
		System.out.println("The browser name is: " + browsername);
		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlLocal.set(new ChromeDriver(op.getChromeOptions()));
		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlLocal.set(new FirefoxDriver(op.getFirefoxOptions()));
		} else if (browsername.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		else {
			System.out.println("Please pass the correct browser: " + browsername);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	/**
	 * This method is for initializing the Properties object and return the same
	 * 
	 * @return Properties Object
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");

		if (env == null) {
			System.out.println("The Environment is --> PROD");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			System.out.println("The Environment is :" + env);
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException is found...");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException is found...");
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
