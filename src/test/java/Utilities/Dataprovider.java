package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataprovider {
	@DataProvider(name="Logindata")
	public  String[][] getdata() throws IOException{
		String epath = System.getProperty("user.dir")
                + "\\Test_data\\opencart.xlsx";

		exu excel = new exu(epath);
		
		int totalrows = excel.getRowCount("Sheet1");
		int totalcol = excel.getCellCount("Sheet1", 0);
		
		String logindata[][]=new String[totalrows][totalcol];
		for(int r=1;r<=totalrows;r++) {
			for(int c=0;c<totalcol;c++) {
				
				logindata[r-1][c]= excel.getCellData("Sheet1", r, c);
				
			}
		}
		return logindata;
		
		
	}
	
	
	
}
