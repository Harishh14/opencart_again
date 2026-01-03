package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myacpage extends Basepage {

	public myacpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	 @FindBy(xpath = "//h2[normalize-space()='My Account']")
	    WebElement head;
	 
	 @FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	 WebElement exit;
	 
	 public boolean validation() {
	    	return head.isDisplayed();
	    }
	 
	 public void eclick() {
		 exit.click();
	 }

}
