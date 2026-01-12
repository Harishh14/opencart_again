package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage {

	public Homepage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
@FindBy(xpath="//span[@class='caret']/preceding-sibling::span")
WebElement macc;
	
@FindBy(xpath="//a[text()='Register']")	
WebElement Reg;
	
@FindBy(xpath="//a[text()='Login']")	
WebElement Logg;

@FindBy(xpath="//input[@placeholder='Search']")
WebElement srcbox;

@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
WebElement srcbtn;

public void clickmyacc() {
	macc.click();
}

public void clickreg() {
	Reg.click();
}
	
public void clicklog() {
	Logg.click();
}

public void searchitem(String pName) {
	srcbox.clear();
	srcbox.sendKeys(pName);

}

public void searchbtn() {
	srcbtn.click();
}
	
	
}
