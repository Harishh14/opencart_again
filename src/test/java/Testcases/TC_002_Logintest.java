package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import Pageobjects.Homepage;
import Pageobjects.Loginpage;
import Pageobjects.myacpage;

public class TC_002_Logintest extends BaseClass {
	@Test(groups={"Master","Sanity"})
	public void verfiyLogin() {
		try {

			Homepage hp = new Homepage(getDriver());
			hp.clickmyacc();
			hp.clicklog();

			Loginpage lp = new Loginpage(getDriver());
			lp.enterUsername(p.getProperty("userr"));
			lp.enterPassword(p.getProperty("passs"));
			lp.clickLogin();

			myacpage myac = new myacpage(getDriver());
			boolean res = myac.validation();
			Assert.assertTrue(res);
		}

		catch (Exception e) {

			Assert.fail();
		}
	}

}
