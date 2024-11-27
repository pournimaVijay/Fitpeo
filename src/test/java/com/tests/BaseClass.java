package com.tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    public WebDriver driver;

  
    public void initializeDriver() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        driver.manage().deleteAllCookies(); 
        driver.manage().window().maximize(); 
    }

    @BeforeClass
    public void launchUrl() {
        initializeDriver(); 
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); 
        }
    }
}
