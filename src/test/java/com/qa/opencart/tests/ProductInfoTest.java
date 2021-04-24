package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {

	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ProductInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
				
	}
	
	@DataProvider
	public Object[][] getProductName(){
		return new Object[][] {{"imac", 1},{"macbook", 3}};
	}

	@Test(priority = 1, dataProvider="getProductName")
	public void getProductResultsCountTest(String productName, int count) {
		searchResultPage = accountsPage.doSearchProduct(productName);
		Assert.assertTrue(searchResultPage.getProductResultsCount() == count);

	}

	@Test(priority = 2)
	public void getProductHeaderTest() {
		productInfoPage = searchResultPage.selectProductFromResults("MacBook Air");
		String productname = productInfoPage.getProductHeader();
		Assert.assertEquals(productname, "MacBook Air");
	}

	@Test(priority = 3)
	public void getProductImageCountTest() {
		int count = productInfoPage.getProductImageCount();
		System.out.println("The product image count is: " + count);
		Assert.assertTrue(count > 0);
	}

	@Test(priority = 4)
	public void getProductDetailsTest() {
		Map<String, String> productdata = productInfoPage.getProductDetails();
		productdata.forEach((k, v) -> System.out.println(k + " : " + v));
		softAssert.assertEquals(productdata.get("Brand"),"Apple");
		softAssert.assertEquals(productdata.get("Product Name"),"MacBook Air");
		softAssert.assertAll();
	}

	@Test(priority = 5)
	public void addToCartTest() {
		productInfoPage.selectQuantity("1");
		productInfoPage.addToCart();
		String message = productInfoPage.getSuccessMessage();
		Assert.assertTrue(message.contains(Constants.ADD_TO_CART_MESSAGE));
	}


}

