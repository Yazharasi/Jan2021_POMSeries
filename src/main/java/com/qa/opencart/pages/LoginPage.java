package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// 1.Private Members
	private WebDriver driver;
	private ElementUtil element;
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPasswordLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By registerLink = By.xpath("//div/a[text()='Register']");
	private By loginFailedMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	// 2. Class Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		element = new ElementUtil(driver);

	}

	// 3. Page Actions

	public String getLoginPageTitle() {
		return element.getPageTitle();
	}

	public String getLoginPageUrl() {
		return element.getPageUrl();
	}

	public boolean isForgotPasswordLinkPresent() {
		return element.doIsDisplayed(forgotPasswordLink);
	}

	@Step("logging with username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		element.doSendKeys(username, un);
		element.doSendKeys(password, pwd);
		element.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	@Step("logging with incorrect username: {0} and password: {1}")
	public String doLoginwithInvalidData(String un, String pwd) {
		element.doSendKeys(username, un);
		element.doSendKeys(password, pwd);
		element.doClick(loginButton);
		return element.doGetText(loginFailedMsg);
	}
	
	public RegisterationPage navigateToRegisterPage() {
		element.doClick(registerLink);
		return new RegisterationPage(driver);
	}

}
