package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
		
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	/************************ Wait utilities ***************************/

	public WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement waitForElementVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public List<WebElement> waitForElementsPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public void waitForElementsAndPrintText(By locator, int timeout) {
		waitForElementsPresent(locator, timeout).stream().forEach(ele -> System.out.println(ele.getText()));

	}

	public List<String> waitForElementsAndReturnTextList(By locator, int timeout) {
		return waitForElementsPresent(locator, timeout).stream().map(ele -> ele.getText()).collect(Collectors.toList());

	}

	public List<WebElement> waitForElementsVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public void clickWhenReady(By locator, int timeout) {
		waitForElementToBeClickable(locator, timeout).click();

	}

	public void waitForTitle(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(title));
	}

	public void waitForTitleContains(int timeout, String title) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void waitForUrl(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlToBe(url));

	}

	public void waitForUrlContains(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(url));
	}

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String waitForAlertAndGetText(int timeout) {
		return waitForAlert(timeout).getText();

	}

	public void waitForAlertGetTextAndAcceptIt(int timeout) {
		waitForAlert(timeout).accept();

	}

	public void waitForAlertAndDismissIt(int timeout) {
		waitForAlert(timeout).dismiss();

	}

	public void waitForFrameWithIndex(int index, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrameWithNameOrId(String idorname, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idorname));
	}

	public void waitForFrameWithLocator(String locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrameWithWebElement(String element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public WebElement fluentwaitforElementPresence(By locator, int timeout, int interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public List<WebElement> fluentwaitforElementsPresence(By locator, int timeout, int interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public Alert fluentwaitforAlertPresence(int timeout, int interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoAlertPresentException.class);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public void fluentwaitforFramePresence(String idorname, int timeout, int interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchFrameException.class);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idorname));

	}

	public void fluentWaitForTitle(int timeout, int interval, String value) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(interval));
		wait.until(ExpectedConditions.titleContains(value));

	}

	public void fluentWaitForUrl(int timeout, int interval, String value) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(interval));
		wait.until(ExpectedConditions.urlContains(value));

	}

	/*********************** List Utilities *****************************/

	public ArrayList<String> getTextofAllLinks(By locator) {
		ArrayList<String> linksTextList = new ArrayList<String>();
		List<WebElement> linksList = getElements(locator);
		for (WebElement e : linksList) {
			linksTextList.add(e.getText());
		}
		return linksTextList;
	}

	public ArrayList<String> getAttrvalueforAllLinks(By locator, String attrname) {
		List<WebElement> linksList = getElements(locator);
		ArrayList<String> linksAttrList = new ArrayList<String>();
		for (WebElement e : linksList) {
			linksAttrList.add(e.getAttribute(attrname));
		}
		return linksAttrList;
	}

	public void doSelectfromDropdownbyIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectfromDropdownbyValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectfromDropdownbyVisText(By locator, String visibletext) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibletext);
	}

	/**
	 * This method can select a value from dropdown without using the Select Class
	 * 
	 * @param locator
	 * @param option
	 */
	public void doSelectDropdownValueWithoutSelectClass(By locator, String option) {

		List<WebElement> alloptions = getElements(locator);
		for (WebElement e : alloptions) {
			if (e.getText().equals(option)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * This method can select a single/multiple/All choices from the combo tree
	 * style dropdown
	 * 
	 * @param locator
	 * @param choices
	 */

	public void doSelectMultiOptionsfromComboDropdown(By locator, String... choices) {
		List<WebElement> choiceList = getElements(locator);

		if (!choices[0].equalsIgnoreCase("all")) {

			for (WebElement e : choiceList) {
				String choicetext = e.getText();
				for (int i = 0; i < choices.length; i++) {
					if (choicetext.equals(choices[i])) {
						e.click();
						break;
					}

				}
			}

		}

		else {
			try {
				for (WebElement e : choiceList) {
					e.click();
				}
			} catch (Exception e) {
			}
		}

	}

	/**
	 * This method will get a text as input, enter it in the search text field and
	 * pick a specific text from Suggestion list
	 * 
	 * @param locator1
	 * @param locator2
	 * @param source
	 * @param target
	 * @throws InterruptedException
	 */
	public void doSelectTextfromSuggList(By locator1, By locator2, String source, String target)
			throws InterruptedException {
		getElement(locator1).sendKeys(source);
		Thread.sleep(2000);
		List<WebElement> suggList = getElements(locator2);
		for (WebElement e : suggList) {
			String sugg = e.getText();
			if (sugg.equals(target)) {
				e.click();
				break;
			}
		}

	}

	/**
	 * This method will click on an element, switch to child window, get Title and
	 * return back to Parent window
	 * 
	 * @param locator
	 */

	public void goToChildWindowGetTitleandClose(By locator) {
		getElement(locator).click();
		Set<String> handles = driver.getWindowHandles();
		ArrayList<String> handleList = new ArrayList<String>(handles);
		String parWindow = handleList.get(0);
		String childWindow = handleList.get(1);

		driver.switchTo().window(childWindow);
		System.out.println("The child window title is: " + driver.getTitle());
		driver.close();
		driver.switchTo().window(parWindow);

	}

	/**
	 * This test case will mouse over on Main Menu, click on a sub menu and print
	 * the page title
	 * 
	 * @param locator1
	 * @param locator2
	 * @param submenu
	 */

	public void doMouseOverandSelectSubmenu(By locator1, By locator2, String submenu) {

		Actions action = new Actions(driver);
		WebElement ele = getElement(locator1);

		action.moveToElement(ele).perform();

		List<WebElement> sublist = getElements(locator2);
		for (WebElement e : sublist) {
			if (e.getText().equals(submenu)) {
				e.click();
				System.out.println(driver.getTitle());
				break;
			}
		}

	}

	/**
	 * This method will move over on an element
	 * 
	 * @param locator
	 */
	public void doMouseOver(By locator) {
		Actions action = new Actions(driver);
		WebElement ele = getElement(locator);
		action.moveToElement(ele).perform();
	}

	/**
	 * This method will drag the source element and drop it in the target location
	 * 
	 * @param sourcelocator
	 * @param targetlocator
	 */

	public void doDragandDrop(By sourcelocator, By targetlocator) {
		Actions action = new Actions(driver);
		WebElement source = getElement(sourcelocator);
		WebElement target = getElement(targetlocator);
		action.dragAndDrop(source, target).perform();
	}

}
