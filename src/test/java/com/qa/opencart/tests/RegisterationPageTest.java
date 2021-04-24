package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class RegisterationPageTest extends BaseTest {

	@BeforeClass
	public void setupRegisterationPage() {
		registerationPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getTestData() {
		return ExcelUtil.getExcelData(Constants.TESTDATA_SHEET_NAME);

	}
	
	public String getRandomNumber() {
		Random random = new Random();
		return "user"+random.nextInt(10000)+"999@gmail.com";
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider="getTestData")
	public void registerAccountTest(String firstName, String lastName, String telephone, String password, String subscribe) {

		Assert.assertTrue(registerationPage.registerAccount(firstName, lastName, getRandomNumber(), telephone, password, subscribe));
	}

}
