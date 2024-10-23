package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    private String excelpath;

    // Constructor to initialize the file path
    public ExcelUtility(String excelpath) {
        this.excelpath = excelpath;
    }

    // Get row count from the specified sheet
    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(excelpath);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = wb.getSheet(sheetName);
            int totalRows=sheet.getPhysicalNumberOfRows();
            return totalRows;
        }
    }

    // Get cell count from a specific row in the specified sheet
    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(excelpath);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = wb.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            return row != null ? row.getPhysicalNumberOfCells() : 0; // Handling null row
        }
    }

    // Get data from a specific cell in the sheet
    public String getCellData(String sheetName, int rownum, int column) throws IOException {
        try (FileInputStream fi = new FileInputStream(excelpath);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = wb.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) return ""; // Return empty if row is null
            XSSFCell cell = row.getCell(column);
            return cell != null ? cell.toString() : ""; // Return empty if cell is null
        }
    }

    // Write data to a specific cell in the sheet
    public void writeCellData(String sheetName, int rownum, int column, String data) throws IOException {
        File xlfile = new File(excelpath);

        // Create new file if it doesn't exist
        if (!xlfile.exists()) {
            try (XSSFWorkbook wb = new XSSFWorkbook();
                 FileOutputStream fo = new FileOutputStream(excelpath)) {
                wb.createSheet(sheetName); // Create new sheet
                wb.write(fo); // Save the workbook
            }
        }

        try (FileInputStream fi = new FileInputStream(excelpath);
             XSSFWorkbook wb = new XSSFWorkbook(fi);
             FileOutputStream fo = new FileOutputStream(excelpath)) {

            XSSFSheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                sheet = wb.createSheet(sheetName); // Create sheet if it doesn't exist
            }

            XSSFRow row = sheet.getRow(rownum);
            if (row == null) {
                row = sheet.createRow(rownum); // Create new row if null
            }

            XSSFCell cell = row.createCell(column);
            cell.setCellValue(data); // Set cell data
            wb.write(fo); // Save changes to the file
        }
    }
}
