package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void getLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void getLoginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_PARTIAL_URL));
	}

	@Test(priority = 3)
	public void isForgotPasswordLinkPresentTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkPresent());
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void doLoginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getInvalidLoginData(){
		return new Object[][] {{"  ","  "},{"test","  "},{"  ","test"}};
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 0, dataProvider = "getInvalidLoginData")
	public void doLoginWithInvalidDataTest(String un, String pwd) {
		String msg = loginPage.doLoginwithInvalidData(un, pwd);
		Assert.assertTrue(msg.contains("Warning: No match"));
	}
}
