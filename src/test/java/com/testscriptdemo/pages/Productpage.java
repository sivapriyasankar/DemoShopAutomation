package com.testscriptdemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testsscriptdemo.utils.WebdriverManager;

public class Productpage extends BasePage {

	ArrayList<String> productlist = new ArrayList<String>();
	ArrayList<Double> productPrice = new ArrayList<Double>();
	double lowestPriceProduct=0;
	String lowestPriceProductName = null;

	public Productpage(WebdriverManager driverManager) {
		super(driverManager);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//*[text()='Add to wishlist']")
	public List<WebElement> btnAddToWishlist;

	@FindBy(xpath="//*[@class='product-compare-wishlist d-compare-wishlist']/..//h2")
	public List<WebElement> lblProductName;

	@FindBy(xpath="(//*[@class='lar la-heart'])[1]")
	public WebElement btnWishList;

	@FindBy(xpath="//td[@class='product-name']/a")
	public List<WebElement> tblProductwishlist;

	@FindBy(xpath="//td[@class='product-price']//bdi")
	public List<WebElement> tblPriceList;
		
	@FindBy(xpath="(//*[@class='la la-shopping-bag'])[1]")
	public WebElement btnClickCart;
	
	@FindBy(xpath="//*[@class='product-name']//a")
	public WebElement lblAddedProduct;
	
	@FindBy(xpath="//*[@class='cc-btn cc-accept-all cc-btn-no-href']")
	public WebElement btnAcceptAll;
	
	@FindBy(xpath="//*[text()='Product added to cart successfully']")
	public WebElement lblProductAdded;
	
	
	public void addProductToWishList(String productValue) {
		int totalCount = Integer.parseInt(productValue);
		btnAcceptAll.click();
		if (btnAddToWishlist.size() > 0) {
			for (int productCount = 0; productCount < totalCount; productCount++) {
				moveToProductImage(btnAddToWishlist.get(productCount));
				waitForElementToBeVisible(btnAddToWishlist.get(0));
				productlist.add(lblProductName.get(productCount).getText());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btnAddToWishlist.get(0).click();
			}
		}
	}

	public void clickOnWishlist() {
		moveToProductImage(btnWishList);
		btnWishList.click();
	}


	public void getwishListTable() {
		int wishSize = tblProductwishlist.size();
		for(int i=0;i<wishSize;i++) {
			String act = tblProductwishlist.get(i).getText();
			String exp = productlist.get(wishSize-(i+1));	
			Assert.assertTrue(act.equals(exp));
		}
	}

	public void getLowestPriceProduct() {
		for(int i=0;i<tblPriceList.size();i++) {
			String act = tblPriceList.get(i).getText().replace("£", "");
			Double val = Double.parseDouble(act);
			productPrice.add(val);
		}
		int amountElement=0;
		
			for(int amountValue=1;amountValue<productPrice.size();amountValue++) {
				if(productPrice.get(amountElement)<productPrice.get(amountValue)) {
					lowestPriceProduct = productPrice.get(amountElement);
				} else {
					amountElement=amountValue;
				}
				if(amountValue==productPrice.size())
					break;
			}
			
		
	}
	
	public void addLowestPriceProductTomyCard() {		
		lowestPriceProductName = driver.findElement(By.xpath("//bdi[contains(text(),'"+lowestPriceProduct+"')]/../../../..//*[@class='product-name']/a")).getText();
		driver.findElement(By.xpath("//bdi[contains(text(),'"+lowestPriceProduct+"')]/../../../..//*[@class='product-add-to-cart']/a")).click();
		Assert.assertTrue(lblProductAdded.isDisplayed());
	}
	
	public void verifyItemInmyCart() {
		btnClickCart.click();
		Assert.assertTrue(lowestPriceProductName.equals(lblAddedProduct.getText()));
	}


}
