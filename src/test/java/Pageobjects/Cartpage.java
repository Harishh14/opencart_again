package Pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cartpage extends Basepage {

	public Cartpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[@id='cart']")
	WebElement btncart;

	@FindBy(xpath = "//div[@id='cart']//p/a[1]")
	WebElement vcart;

	@FindBy(xpath = "//*[@id='content']/div[2]/div/table//strong[text()='Total:']//following::td")
	WebElement tprice;

	@FindBy(xpath = "//div[@id='accordion']/following::a[text()='Checkout']")
	WebElement btncheckout;

	public void cartbtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(btncart));
		btncart.click();
	}

	public void vcartbtn() {
		vcart.click();
	}

	public String totalprice() {
		return tprice.getText();
	}

	public void checkbtn() {
		btncheckout.click();
	}

}
