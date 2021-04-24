package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterationPage {

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
	private By subscribeNo = By.xpath("(//input[@name='newsletter'])[2]");
	private By agreePolicy = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue']");
	private By registerLink = By.xpath("//div/a[text()='Register']");
	private By logoutLink = By.xpath("//div/a[text()='Logout']");
	private By successMsg = By.cssSelector("div#content>h1");
	private ElementUtil elementUtil;

	public RegisterationPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);

	}

	public boolean registerAccount(String firstName, String lastName, String email, String telephone, String password,
			String subscribeVal) {
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPassword, password);
		if (subscribeVal.equals("Yes"))
			elementUtil.doClick(subscribeYes);
		else
			elementUtil.doClick(subscribeNo);
		elementUtil.doClick(agreePolicy);
		elementUtil.doClick(continueButton);

		String msg = elementUtil.doGetText(successMsg);
		System.out.println("The account creation message is: "+msg);

		if (msg.equals(Constants.REGISTER_ACCOUNT_MSG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		} else
			return false;

	}

}
