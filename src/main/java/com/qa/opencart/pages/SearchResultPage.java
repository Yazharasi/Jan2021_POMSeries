package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By searchItemresults = By.cssSelector("div.product-layout");
	private By productNameList = By.cssSelector("div.caption a");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public int getProductResultsCount() {
		return elementUtil.getElements(searchItemresults).size();

	}

	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> productResults = elementUtil.getElements(productNameList);
		for (WebElement e : productResults) {
			if (e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);

	}

}
