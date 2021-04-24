package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private ElementUtil elementUtil;

	private By productheader = By.cssSelector("div#content h1");
	private By productImageCount = By.cssSelector("a.thumbnail img");
	private By productMetaData = By.cssSelector("div.col-sm-4 ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div.col-sm-4 ul.list-unstyled:nth-of-type(2) li");
	private By quantityField = By.cssSelector("input#input-quantity");
	private By addToCartButton = By.cssSelector("button#button-cart");
	private By successMessage = By.cssSelector("div.alert-success");

	public ProductInfoPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return elementUtil.doGetText(productheader);
	}

	public int getProductImageCount() {
		return elementUtil.getElements(productImageCount).size();
	}

	public Map<String, String> getProductDetails() {
		Map<String, String> productInfo = new HashMap<String, String>();
		productInfo.put("Product Name", getProductHeader());
		List<WebElement> metadata = elementUtil.getElements(productMetaData);
		for (WebElement e : metadata) {
			String str[] = e.getText().split(":");
			String key = str[0].trim();
			String value = str[1].trim();
			productInfo.put(key, value);
		}

		List<WebElement> pricedata = elementUtil.getElements(productPriceData);
		String price = pricedata.get(0).getText().trim();
		productInfo.put("Price", price);
		String tax[] = pricedata.get(1).getText().split(":");
		productInfo.put(tax[0].trim(), tax[1].trim());

		return productInfo;

	}

	public void selectQuantity(String quantity) {
		elementUtil.doSendKeys(quantityField, quantity);

	}

	public void addToCart() {
		elementUtil.doClick(addToCartButton);
	}

	public String getSuccessMessage() {
		return elementUtil.waitForElementVisibility(successMessage, 5).getText();
	}

}
