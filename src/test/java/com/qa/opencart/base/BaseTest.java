package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	public DriverFactory df;
	private WebDriver driver;
	public LoginPage loginPage;
	public Properties prop;
	public AccountsPage accountsPage;
	public SearchResultPage searchResultPage;
	public ProductInfoPage productInfoPage;
	public RegisterationPage registerationPage;

	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String browsername) {
		df = new DriverFactory();
		prop = df.init_prop();
		prop.setProperty("browser", browsername);
		driver = df.init_driver();
		loginPage = new LoginPage(driver);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
