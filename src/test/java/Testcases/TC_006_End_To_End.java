package Testcases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import Pageobjects.Cartpage;
import Pageobjects.Checkoutpage;
import Pageobjects.Homepage;
import Pageobjects.Loginpage;
import Pageobjects.Registration;
import Pageobjects.Searchpage;
import Pageobjects.myacpage;

public class TC_006_End_To_End extends BaseClass {

	@Test(groups = {"Master"})
	public void EndToEnd() {

		logger.info("********** TC_006_End_To_End Started **********");

		try {

			Homepage hp = new Homepage(getDriver());
			logger.info("Homepage object created");
			Thread.sleep(1000);

			hp.clickmyacc();
			logger.info("Clicked on My Account");
			Thread.sleep(1000);

			hp.clickreg();
			logger.info("Clicked on Register option");
			Thread.sleep(1500);

			Registration rg = new Registration(getDriver());
			logger.info("Registration page object created");
			Thread.sleep(1000);

			rg.firstName.sendKeys("Himanshu");
			logger.info("Entered First Name");
			Thread.sleep(500);

			rg.lastName.sendKeys("Soni");
			logger.info("Entered Last Name");
			Thread.sleep(500);

			String email = Randomemail();
			rg.email.sendKeys(email);
			logger.info("Entered Email: " + email);
			Thread.sleep(500);

			String phone = Randomnum();
			rg.telephone.sendKeys(phone);
			logger.info("Entered Telephone: " + phone);
			Thread.sleep(500);

			String pass = Randompass();
			rg.password.sendKeys(pass);
			rg.confirmPassword.sendKeys(pass);
			logger.info("Entered Password and Confirm Password");
			Thread.sleep(800);

			rg.newsletterNo.click();
			logger.info("Selected Newsletter: NO");
			Thread.sleep(500);

			rg.privacyPolicyCheckbox.click();
			logger.info("Accepted Privacy Policy");
			Thread.sleep(500);

			rg.continueButton.click();
			logger.info("Clicked Continue button");
			Thread.sleep(2000);

			boolean res = rg.isRegistrationSuccessful();

			if (res) {
				logger.info("Registration successful – Test Passed");
				Assert.assertTrue(true);
			} else {
				logger.error("Registration failed – Test Failed");
				Assert.fail();
			}

			logger.info("********** Registration Completed **********");

			myacpage mc = new myacpage(getDriver());
			mc.eclick();
			logger.info("Clicked on Logout / Continue to Login");
			Thread.sleep(1500);
			
			hp.clickmyacc();
			hp.clicklog();
			Thread.sleep(1500);

			Loginpage lp = new Loginpage(getDriver());
			lp.enterUsername(email);
			lp.enterPassword(pass);
			lp.clickLogin();
			logger.info("Logged in successfully with registered credentials");
			Thread.sleep(2000);

			hp.searchitem("hp");
			logger.info("Entered product in search box");
			Thread.sleep(500);

			hp.searchbtn();
			logger.info("Clicked search button");
			Thread.sleep(1500);

			Searchpage sc = new Searchpage(getDriver());

			if (sc.isProductAvailable("HP LP3065")) {
				logger.info("Product HP LP3065 is available");
				sc.selectProduct("HP LP3065");
				Thread.sleep(1500);

				sc.Enterquantity("1");
				logger.info("Entered quantity 1");

				sc.Addtocart();
				logger.info("Clicked Add to Cart");
			} else {
				logger.error("Product HP LP3065 NOT available");
				Assert.fail("Product not available");
			}

			Assert.assertEquals(sc.cmessage(), true);
			logger.info("Add to cart success message verified");
			Thread.sleep(1500);

			Cartpage cp = new Cartpage(getDriver());
			cp.cartbtn();
			logger.info("Opened Shopping-cart");
			Thread.sleep(2000);
			
			cp.vcartbtn();
			Thread.sleep(1000);
			
			System.out.println(cp.totalprice());
			Assert.assertEquals(cp.totalprice(),"$122.00" );
			Thread.sleep(1000);
			logger.info("validation of the amount");

			cp.checkbtn();
			logger.info("Clicked Checkout button");
			Thread.sleep(2000);

			Checkoutpage ch = new Checkoutpage(getDriver());

			ch.enterBillingDetails("Himanshu", "Soni", "Surat Nagar", "Gurgaon", "122001");
			logger.info("Entered Billing details");
			Thread.sleep(1000);

			ch.selectCountryIgnoreCase("india");
			logger.info("Selected Country India (ignore case)");
			Thread.sleep(1000);

			ch.selectStateIgnoreCase("haryana");
			logger.info("Selected State Haryana (ignore case)");
			Thread.sleep(1000);

			ch.clickContinueBilling();
			logger.info("Clicked Continue Billing");
			Thread.sleep(1500);

			ch.continueDeliveryDetails();
			logger.info("Continued Delivery Details");
			Thread.sleep(1500);

			ch.enterDeliveryComment("okay");
			logger.info("Entered delivery comment");
			Thread.sleep(800);

			ch.continueDeliveryMethod();
			logger.info("Continued Delivery Method");
			Thread.sleep(1500);

			ch.acceptTermsAndContinuePayment();
			logger.info("Accepted Terms & Continued Payment");
			Thread.sleep(1500);
			
			String lastp  =ch.beforepriceorder();
			System.out.println(lastp);
			Assert.assertEquals(lastp, "$105.00");
			Thread.sleep(1000);
						
			ch.confirmOrder();
			logger.info("Clicked Confirm Order");
			Thread.sleep(3000);

			logger.info("********** Order Placed Successfully **********");
			logger.info("********** TC_006_End_To_End Finished **********");

		} catch (Exception e) {

			logger.error("Exception occurred in End To End test: " + e.getMessage());
			Assert.fail("Test Failed due to exception: " + e.getMessage());
		}
	}
}
