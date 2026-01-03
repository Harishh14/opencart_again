package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import Pageobjects.Homepage;
import Pageobjects.Registration;

public class TC_001_Registration extends BaseClass {

	@Test(groups= {"Reggression","Master"})
	public void verify_reg() {
		
		

		logger.info("********** TC_001_Registration Started **********");

		Homepage hp = new Homepage(getDriver());
		logger.info("Homepage object created");

		hp.clickmyacc();
		logger.info("Clicked on My Account");

		hp.clickreg();
		logger.info("Clicked on Register option");

		Registration rg = new Registration(getDriver());
		logger.info("Registration page object created");

		rg.firstName.sendKeys("Himanshu");
		logger.info("Entered First Name");

		rg.lastName.sendKeys("Soni");
		logger.info("Entered Last Name");

		String email = Randomemail();
		rg.email.sendKeys(email);
		logger.info("Entered Email: {}", email);

		String phone = Randomnum();
		rg.telephone.sendKeys(phone);
		logger.info("Entered Telephone: {}", phone);

		String pass = Randompass();
		rg.password.sendKeys(pass);
		rg.confirmPassword.sendKeys(pass);
		logger.info("Entered Password and Confirm Password");

		rg.newsletterNo.click();
		logger.info("Selected Newsletter: NO");

		rg.privacyPolicyCheckbox.click();
		logger.info("Accepted Privacy Policy");

		rg.continueButton.click();
		logger.info("Clicked Continue button");

		boolean res = rg.isRegistrationSuccessful();

		if (res==true) {
			logger.info("Registration successful – Test Passed");
			Assert.assertTrue(true);
		} else {
			logger.error("Registration failed – Test Failed");
			Assert.fail();
		}

		logger.info("********** TC_001_Registration Finished **********");
	}
}
