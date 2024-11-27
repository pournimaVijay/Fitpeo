package com.tests;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.homePagePOM.homePagePOM;

public class Tests extends BaseClass {
	private homePagePOM homePage;

	@BeforeClass
	public void setup() {

		homePage = new homePagePOM(driver);
	}

	@Test(priority = 1)
	public void homePage() throws InterruptedException { // 1
		System.out.println("Execution started.............");
		driver.get("https://www.fitpeo.com/");
		driver.manage().window().maximize();
		System.out.println("TC 1: Navigated to Home Page");
	}

	@Test(priority = 2)
	public void revenueCalculatorPage() throws InterruptedException { // 2
		Thread.sleep(2000);
		homePage.goToRevenueCalculator();
		Thread.sleep(3000);
		System.out.println("TC 2: Navigated to Revenue Calculator Page");
	}

	@Test(priority = 3) //
	public void scrollToSliderSection() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,450)");
		Thread.sleep(1000);
		System.out.println("TC 3: Scrolled Down to the Slider section");
	}

	@Test(priority = 4) // Unable to reach the value by slider
	public void adjustSlider() throws InterruptedException {
		homePage.Slide(108);
		System.out.println("TC 4: Slider adjusted");

	}

	@Test(priority = 5)
	public void updateTextField() {
		homePage.updateTextFieldTo("560");
		System.out.println("TC 5: Text fiels updated to 560");

	}

	@Test(priority = 6)
	public void validateSliderValue() {
		homePage.updateTextFieldTo("560");
		String ExpectedValue = "560";
		String ActualValue = homePage.ReturntextFieldValue();
		SoftAssert a = new SoftAssert();
		a.assertEquals(ActualValue, ExpectedValue);
		a.assertAll();
		System.out.println("TC 6: Expected and actual value is validated");

	}

	@Test(priority = 7)
	public void selectCPTCodes() throws InterruptedException {
		List<WebElement> codes = driver
				.findElements(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 inter css-1s3unkt']"));
		List<WebElement> checkBox = driver.findElements(By.xpath(
				"//span[@class='MuiTypography-root MuiTypography-body1 MuiFormControlLabel-label css-1s3unkt']"));
		List<String> check = new ArrayList<String>();
		check.add("CPT-99453");
		check.add("CPT-99454");
		check.add("CPT-99091");
		check.add("CPT-99474");

		for (int i = 0; i < codes.size(); i++) {
			for (int j = 0; j < check.size(); j++) {
				if (check.get(j).equals(codes.get(i).getText())) {
					checkBox.get(i).click();
					Thread.sleep(2000);
				}
			}
		}
		System.out.println("TC 7: Checkboxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474 are selected");
	}

	@Test(priority = 8)
	public void validateSelectedTotalRecurringReimbursement() {
		String TotalRecurringReimbursement = homePage.ReturnTotalRecurringReimbursement();
		System.out.println("TC 8: Total Recurring Reimbursement for selected codes is " + TotalRecurringReimbursement);
	}

	@Test(priority = 9)
	public void validateAllTotalRecurringReimbursement() throws InterruptedException {
		driver.navigate().refresh();
		List<WebElement> l = driver.findElements(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3']"));
		for (WebElement w : l) {
			w.click();
		}
		List<WebElement> allCgeckbox = driver
				.findElements(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3']"));

		for (WebElement a : allCgeckbox) {

			if (a.isSelected() == false) {
				a.click();
			}
		}
		SoftAssert a = new SoftAssert();
		System.out.println("TC 9: Value displayed in header of total Recurring Reimbursement is" + homePage.ReturnTotalRecurringReimbursement());

		a.assertEquals("$110800", homePage.ReturnTotalRecurringReimbursement());
		a.assertAll();

	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("Execution ended.............");
		}

	}

}
