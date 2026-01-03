package Testcases;

import java.io.IOException;
import org.testng.annotations.Test;
import Utilities.exu;

public class demo {

    @Test
    public void nuhi() throws IOException {

        String epath = System.getProperty("user.dir")
                + "\\Test_data\\opencart.xlsx";

        exu excel = new exu(epath);

        int totalrows = excel.getRowCount("Sheet1");
        int totalcol = excel.getCellCount("Sheet1", 0);

        System.out.println("Rows = " + totalrows);
        System.out.println("Columns = " + totalcol);
    }
}
