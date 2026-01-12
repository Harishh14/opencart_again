package Pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Checkoutpage extends Basepage {

	public Checkoutpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// ---------- STEP 1 – Checkout Options ----------
	@FindBy(xpath = "//input[@value='guest']")
	WebElement guestCheckoutRadio;

	@FindBy(xpath = "//input[@value='register']")
	WebElement registerAccountRadio;

	@FindBy(xpath = "//input[@id='button-account']")
	WebElement continueCheckoutOptionsBtn;

	// ---------- STEP 2 – Billing Details ----------
	@FindBy(xpath = "//input[@id='input-payment-firstname']")
	WebElement billingFirstName;

	@FindBy(xpath = "//input[@id='input-payment-lastname']")
	WebElement billingLastName;

	@FindBy(xpath = "//input[@id='input-payment-address-1']")
	WebElement billingAddress1;

	@FindBy(xpath = "//input[@id='input-payment-city']")
	WebElement billingCity;

	@FindBy(xpath = "//input[@id='input-payment-postcode']")
	WebElement billingPostcode;

	@FindBy(xpath = "//select[@id='input-payment-country']")
	WebElement billingCountry;

	@FindBy(xpath = "//select[@id='input-payment-zone']")
	WebElement billingState;

	@FindBy(xpath = "//input[@id='button-payment-address']")
	WebElement continueBillingBtn;

	// ---------- STEP 3 – Delivery Details ----------
	@FindBy(xpath = "//input[@id='button-shipping-address']")
	WebElement continueDeliveryDetailsBtn;

	// ---------- STEP 4 – Delivery Method ----------
	@FindBy(xpath = "//textarea[@name='comment']")
	WebElement deliveryCommentBox;

	@FindBy(xpath = "//input[@id='button-shipping-method']")
	WebElement continueDeliveryMethodBtn;

	// ---------- STEP 5 – Payment Method ----------
	@FindBy(xpath = "//input[@name='agree']")
	WebElement termsCheckbox;

	@FindBy(xpath = "//input[@id='button-payment-method']")
	WebElement continuePaymentMethodBtn;

	// ---------- STEP 6 – Confirm Order ----------
	@FindBy(xpath = "//input[@id='button-confirm']")
	WebElement confirmOrderBtn;

	//subtotal
	@FindBy(xpath="//strong[text()='Sub-Total:']//following::td[1]")
	WebElement sbtotal;

	@FindBy(xpath="//strong[text()='Flat Shipping Rate:']//following::td[1]")
	WebElement fsr;

	//totalprice
	@FindBy(xpath="//strong[text()='Total:']//following::td")
	WebElement pbco;

	public void selectGuestCheckout() {
		guestCheckoutRadio.click();
		continueCheckoutOptionsBtn.click();
	}

	public void enterBillingDetails(String fn, String ln, String addr, String city, String postcode) {

		billingFirstName.sendKeys(fn);
		billingLastName.sendKeys(ln);
		billingAddress1.sendKeys(addr);
		billingCity.sendKeys(city);
		billingPostcode.sendKeys(postcode);
	}

	public void selectCountryIgnoreCase(String countryName) {

		Select country = new Select(billingCountry);
		List<WebElement> options = country.getOptions();

		for (WebElement opt : options) {
			if (opt.getText().equalsIgnoreCase(countryName)) {
				opt.click();
				break;
			}
		}
	}

	public void selectStateIgnoreCase(String stateName) {

		Select state = new Select(billingState);
		List<WebElement> options = state.getOptions();

		for (WebElement opt : options) {
			if (opt.getText().equalsIgnoreCase(stateName)) {
				opt.click();
				break;
			}
		}
	}

	public void clickContinueBilling() {
		continueBillingBtn.click();
	}

	public void continueDeliveryDetails() {
		continueDeliveryDetailsBtn.click();
	}

	public void enterDeliveryComment(String comment) {
		deliveryCommentBox.sendKeys(comment);
	}

	public void continueDeliveryMethod() {
		continueDeliveryMethodBtn.click();
	}

	public void acceptTermsAndContinuePayment() {
		termsCheckbox.click();
		continuePaymentMethodBtn.click();
	}

	public void confirmOrder() {
		confirmOrderBtn.click();
	}
	
	public String subtotalp() {
		return sbtotal.getText();
	}
	public String fsrate() {
		return fsr.getText();
	}
	public String beforepriceorder() {
		return pbco.getText();
	}
}
