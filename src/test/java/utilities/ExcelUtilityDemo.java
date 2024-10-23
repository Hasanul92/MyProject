package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtilityDemo {
    public static FileInputStream fi;
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static FileOutputStream fo;
    public String excelpath;

    public ExcelUtilityDemo(String excelpath){
        this.excelpath=excelpath;
    }

    public int getRowCount(String SheetName) throws IOException {
        fi=new FileInputStream(excelpath);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(SheetName);
        int rowCount=sheet.getPhysicalNumberOfRows();
        wb.close();
        fi.close();
        return rowCount;
    }
    public int getCellCount(String SheetName, int rownum) throws IOException{
        fi=new FileInputStream(excelpath);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(SheetName);
        int cellCount=sheet.getRow(rownum).getPhysicalNumberOfCells();
        wb.close();
        fi.close();
        return cellCount;
    }
    public String getCellData(String SheetName, int rownum, int column) throws IOException{
        fi=new FileInputStream(excelpath);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(SheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(column);
        String cellCount=cell.toString();
        wb.close();
        fi.close();
        return cellCount;
    }
    public void writeCelldata(String SheetName, int rownum, int column, String data) throws IOException{
        File xlfile=new File(excelpath);
        if(!xlfile.exists()){     //If file not exist then create new file
            wb=new XSSFWorkbook();
            fo=new FileOutputStream(excelpath);

        }
        fi=new FileInputStream(excelpath);
        wb=new XSSFWorkbook(fi);
        if(wb.getSheetIndex(SheetName)==-1)  //if sheet not exists then create new sheet
            wb.createSheet(SheetName);
        sheet=wb.getSheet(SheetName);
        row=sheet.getRow(rownum);
        if(row==null)      //if row not exist create new row
            sheet.createRow(rownum);

        cell=row.createCell(column);
        cell.setCellValue(data);
        fo=new FileOutputStream(excelpath);

//        File=new FileOutputStream(filePath);
//        wb=new XSSFWorkbook();
//        sheet=wb.createSheet(SheetName);
//
//        row=sheet.createRow(rownum);
//        cell=row.createCell(column);
//        cell.setCellValue(data);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();

    }
}

