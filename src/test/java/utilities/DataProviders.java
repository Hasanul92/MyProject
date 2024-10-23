package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {
        String path = "C:\\Users\\Me\\IdeaProjects\\OpenCartV1\\testData\\TestData.xlsx";
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);

        // Create a two-dimensional array to store the data
        Object[][] loginData = new Object[totalRows][totalCols];

        // Read data from Excel and store it in the two-dimensional array
        for (int r = 1; r <= totalRows; r++) {
            for (int c = 0; c < totalCols; c++) {
                loginData[r - 1][c] = xlutil.getCellData("Sheet1", r, c);
            }
        }

        // Return the two-dimensional array
        return loginData;
    }
}
