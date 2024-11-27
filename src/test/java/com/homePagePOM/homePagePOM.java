package com.homePagePOM;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class homePagePOM {
    private WebDriver driver;
    private WebDriverWait wait;
    

    // Constructor
    public homePagePOM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait after assigning the driver
        PageFactory.initElements(driver, this); // Initialize PageFactory
    }

    // Locate the Revenue Calculator tab
    @FindBy(xpath = "//div[contains(text(), 'Revenue Calculator')]") 
    private WebElement revenueCalculatorTab;
    
    @FindBy(xpath="//span[@class='MuiSlider-track css-10opxo5']")
    private WebElement slider;
    
    @FindBy(xpath="//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']")
    private WebElement textField;
    
    @FindBy(xpath="//*[contains(text(),'Total Recurring Reimbursement for all Patients Per Month:')]/p")
    private WebElement totalRecurringReimbursement;
    
  
    // Method to navigate to the Revenue Calculator
    public void goToRevenueCalculator() {
        wait.until(ExpectedConditions.elementToBeClickable(revenueCalculatorTab)); // Wait for the element to be clickable
        revenueCalculatorTab.click(); // Click on the tab
    }
    
    public void Slide(int l)
    {
    	try {
			Actions a = new Actions(driver);
			// a.moveToElement(x).build().perform();
			a.dragAndDropBy(slider, l, 0).build().perform();
			Thread.sleep(8000);
		} catch (Exception e) {
			System.out.println(e);

		}	
    }
    
    public void updateTextFieldTo(String val)
    {
    	int l = textField.getText().length();
		textField.click();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		textField.sendKeys(selectAll);
		textField.sendKeys(Keys.BACK_SPACE);
		textField.sendKeys(val);
		
    }
    public String ReturntextFieldValue()
    {  	
		return textField.getAttribute("value");   	
    }
    
    public String ReturnTotalRecurringReimbursement()
    {
		return totalRecurringReimbursement.getText();
    	
    }
   
    
}
