package com.qa.opencart.pages;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private By accountsPageHeader = By.cssSelector("div#logo a");
	private By accountsPageSectionHeaders = By.cssSelector("div#account-account h2");
	private By logout = By.cssSelector("aside#column-right a:nth-of-type(13)");
	private ElementUtil elementUtil;
	private WebDriver driver;
	private By searchTextbox = By.name("search");
	private By searchButton = By.xpath("(//button[@type='button'])[4]");

	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	@Step("This step is retrieving the Account Page title")
	public String getAccountsPageTitle() {
		return elementUtil.getPageTitle();
	}

	@Step("This step is retrieving the Account Page Url")
	public String getAccountsPageUrl() {
		return elementUtil.getPageUrl();
	}

	@Step("This step is retrieving the Account Page Header")
	public String getAccountsPageHeader() {
		return elementUtil.doGetText(accountsPageHeader);

	}

	@Step("This step is retrieving the Account Page Section Headers")
	public List<String> getAccountsPageSectionHeaders() {
		List<String> secHeaderList = elementUtil.waitForElementsAndReturnTextList(accountsPageSectionHeaders, 5);
		Collections.sort(secHeaderList);
		return secHeaderList;

	}

	@Step("This step is to check if Logout link is displayed")
	public boolean isLogoutLinkDisplayed() {
		return elementUtil.doIsDisplayed(logout);
	}
	
	public SearchResultPage doSearchProduct(String productName) {
		elementUtil.doSendKeys(searchTextbox, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}
}
