package Testcases;

import org.testng.annotations.Test;

import org.testng.Assert;

import Baseclass.BaseClass;
import Pageobjects.Homepage;
import Pageobjects.Searchpage;

public class TC_004_SearchProduct extends BaseClass {
	@Test(groups = {"Master"})
	public void Search() {
		try {
			Homepage hp = new Homepage(getDriver());
			hp.searchitem("mac");

			hp.searchbtn();

			Searchpage sc = new Searchpage(getDriver());
			sc.isProductAvailable("macbook");

			
			Assert.assertEquals(sc.isProductAvailable("macbook"), true);
			
			

		} catch (Exception e) {
			System.out.println(e);
			Assert.fail();
		}

	}

}
