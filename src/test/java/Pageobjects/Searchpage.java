package Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Searchpage extends Basepage {

	public Searchpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

//	@FindBy(xpath = "//div[@id='content']/div//img")
//	List<WebElement> allprod;

	@FindBy(xpath = "//input[@name='quantity']")
	WebElement quant;

	@FindBy(xpath = "//button[@id='button-cart']")
	WebElement btncart;

	@FindBy(xpath = "//div[contains(text(),'Success: You have added ')]")
	WebElement cnfmsg;

	public boolean isProductAvailable(String productName) {

	    List<WebElement> products = driver.findElements(By.xpath("//div[@id='content']/div//img"));

	    for (WebElement prod : products) {

	        if (prod.getAttribute("title").equalsIgnoreCase(productName)) {
	            return true;
	        }
	    }
	    return false;
	}


	public void selectProduct(String productName) {

	    List<WebElement> products = driver.findElements(By.xpath("//div[@id='content']/div//img"));

	    for (WebElement prod : products) {

	        if (prod.getAttribute("title").equalsIgnoreCase(productName)) {
	            prod.click();
	            break;
	        }
	    }
	}

	public void Enterquantity(String quantity) {
		quant.clear();
		quant.sendKeys(quantity);
	}
	
	public void Addtocart() {
		btncart.click();
	}
	public boolean cmessage() {
		return cnfmsg.isDisplayed();
		
	}

}
