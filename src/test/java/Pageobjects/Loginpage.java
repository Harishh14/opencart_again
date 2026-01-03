package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends Basepage {

    public Loginpage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//input[@id='input-email']")
     WebElement usernameTxt;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordTxt;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginBtn;
    
   
    
    // Actions
    public void enterUsername(String username) {
 		usernameTxt.sendKeys(username);	
    }

    public void enterPassword(String password) {
 		passwordTxt.sendKeys(password);		
    }

    public void clickLogin() {
    	loginBtn.click();
    }
    
   
//    // Business method
//    public void login(String username, String password) {
//        enterUsername(username);
//        enterPassword(password);
//        clickLogin();
//    }
}
