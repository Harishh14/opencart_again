package Pageobjects;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registration extends Basepage {

	// Constructor
	public Registration(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// ========= Your Personal Details =========

	@FindBy(id = "input-firstname")
	public WebElement firstName;

	@FindBy(id = "input-lastname")
	public WebElement lastName;

	@FindBy(id = "input-email")
	public WebElement email;

	@FindBy(id = "input-telephone")
	public WebElement telephone;

	// ========= Your Password =========

	@FindBy(id = "input-password")
	public WebElement password;

	@FindBy(id = "input-confirm")
	public WebElement confirmPassword;

	// ========= Newsletter =========

	@FindBy(xpath = "//input[@name='newsletter' and @value='1']")
	public WebElement newsletterYes;

	@FindBy(xpath = "//input[@name='newsletter' and @value='0']")
	public WebElement newsletterNo;

	// ========= Privacy Policy & Continue =========

	@FindBy(name = "agree")
	public WebElement privacyPolicyCheckbox;

	@FindBy(xpath = "//input[@value='Continue']")
	public WebElement continueButton;

	// ========= Success Message =========

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	public WebElement successMessage;

	// ========= Page Actions =========

	public void enterFirstName(String fname) {
		firstName.sendKeys(fname);
	}

	public void enterLastName(String lname) {
		lastName.sendKeys(lname);
	}

	public void enterEmail(String mail) {
		email.sendKeys(mail);
	}

	public void enterTelephone(String phone) {
		telephone.sendKeys(phone);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
		confirmPassword.sendKeys(pwd);
	}

	public void subscribeNewsletter(boolean yesOrNo) {
		if (yesOrNo) {
			newsletterYes.click();
		} else {
			newsletterNo.click();
		}
	}

	public void acceptPrivacyPolicy() {
		privacyPolicyCheckbox.click();
	}

	public void clickContinue() {
		continueButton.click();
	}

	public boolean isRegistrationSuccessful() {
		return successMessage.isDisplayed();
	}
	
	
}
