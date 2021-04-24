package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ErrorList;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("HL-4100 Diabetes Screening")
@Story("HL-4101 - Test all the actions applicable for Accounts Page")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountsPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("This test case verifies the Accounts page tile")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void getAccountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE, ErrorList.ACC_PAGE_TITLE_ERROR);
	}

	@Description("This test case verifies the Accounts page Url")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void getAccountsPageUrlTest() {
		String url = accountsPage.getAccountsPageUrl();
		Assert.assertTrue(url.contains(Constants.ACCOUNTS_PAGE_URL), ErrorList.ACC_PAGE_URL_ERROR);
	}
	
	@Description("This test case verifies the Accounts page Header")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void getAccountsPageHeaderTest() {
		String url = accountsPage.getAccountsPageUrl();
		Assert.assertTrue(url.contains(Constants.ACCOUNTS_PAGE_URL));
	}

	@Description("This test case verifies the Accounts page Section Headers")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void getAccountsPageSectionHeadersTest() {
		List<String> secHeaderList = accountsPage.getAccountsPageSectionHeaders();
		secHeaderList.stream().forEach(text -> System.out.println(text));
		Assert.assertEquals(secHeaderList, Constants.accountsPageSecHeaders);
	}

	@Description("This test case verifies if Logout link is present")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 5)
	public void isLogoutLinkDisplayedTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkDisplayed());
	}

}
