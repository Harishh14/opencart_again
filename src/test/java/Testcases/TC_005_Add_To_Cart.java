package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Baseclass.BaseClass;
import Pageobjects.Homepage;
import Pageobjects.Searchpage;

public class TC_005_Add_To_Cart extends BaseClass {

	@Test(groups = {"Master"})
	public void Addtocart() {
		try {
			Homepage hp = new Homepage(getDriver());
			hp.searchitem("hp");

			hp.searchbtn();

			Searchpage sc = new Searchpage(getDriver());
			if (sc.isProductAvailable("HP LP3065")) {

				sc.selectProduct("HP LP3065");
				sc.Enterquantity("1");
				sc.Addtocart();
			}
			Assert.assertEquals(sc.cmessage(), true);
			

		} catch (Exception e) {
			System.out.println(e);
			Assert.fail();
		}

	}

	
}
