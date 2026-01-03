package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import Pageobjects.Homepage;
import Pageobjects.Loginpage;
import Pageobjects.myacpage;
import Utilities.Dataprovider;
import Utilities.exu;

public class TC_003_datalogin extends BaseClass {

	@Test(groups={"Master","DataDriven"},dataProvider = "Logindata", dataProviderClass = Dataprovider.class)
	public void verfiyLogin(String username, String password, String exp) {

		try {
			Homepage hp = new Homepage(getDriver());
			hp.clickmyacc();
			hp.clicklog();

			Loginpage lp = new Loginpage(getDriver());
			lp.enterUsername(username);
			lp.enterPassword(password);
			lp.clickLogin();

			myacpage myac = new myacpage(getDriver());
			
			boolean actual = myac.validation();
			if (exp.equalsIgnoreCase("valid")) {
				if (actual == true) {
					Assert.assertTrue(actual);
					myac.eclick();
					
				} 
				else {
					Assert.assertTrue(false);
				}
			} 
			
			else if (exp.equalsIgnoreCase("invalid")) {
				
				if (actual == true) {
					myac.eclick();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}

	}
}
