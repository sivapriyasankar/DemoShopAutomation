package com.testscriptdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testsscriptdemo.utils.WebdriverManager;

public class BasePage {
	
    WebDriver driver;
    
    public BasePage(WebdriverManager driverManager){
        this.driver = driverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
    
    public WebDriver getDriver() {
    	return this.driver;
    }

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void moveToProductImage(WebElement element){
    Actions actions = new Actions(driver);
	WebElement productImage = element;
	actions.moveToElement(productImage).build().perform();
    }
}
