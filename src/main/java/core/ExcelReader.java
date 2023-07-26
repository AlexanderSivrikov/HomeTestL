package core;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    private final String excelFilePath;
    private XSSFSheet sheet;
    private XSSFWorkbook book;

    public ExcelReader(String excelFilePath) throws IOException {
        this.excelFilePath = excelFilePath;
        File file = new File(excelFilePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet("Sheet1");
        } catch (IOException e) {
            throw new IOException("The unsupported format exception");
        }
    }

    public ExcelReader(String excelFilePath, String sheetName) throws IOException {
        this.excelFilePath = excelFilePath;
        File file = new File(excelFilePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet(sheetName);
        } catch (IOException e) {
            throw new IOException("The unsupported format exception");
        }
    }
    private String cellToString(XSSFCell cell) throws Exception {
        Object result = null;
        CellType cellType = cell.getCellType();
        switch (cellType){
            case NUMERIC:
                result = cell.getNumericCellValue();
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BLANK:
                result = "";
                break;
            default:
                throw new Exception("Error with reading a cell");

        }
        return result.toString();
    }

    private int xlsxCountColumn(){
        return sheet.getRow(0).getLastCellNum();
    }

    private int xlsxCountRow(){
        return sheet.getLastRowNum() + 1;
    }

    public String[][] getSheetData() throws Exception {
        File file = new File(excelFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet("Sheet1");
        int numOfColumns = xlsxCountColumn();
        int numOfRows = xlsxCountRow();
        String[][] data = new String[numOfRows-1][numOfColumns];
        for (int i = 1; i < numOfRows; i++){
            for (int j = 0; j< numOfColumns; j++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i-1][j] = value;
                if (value == null) {
                    System.out.println("The cell is empty");
                }
            }
        }
        return data;
    }

    public String[][] getSheetData(String sheetName) throws Exception {
        File file = new File(excelFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        int numOfColumns = xlsxCountColumn();
        int numOfRows = xlsxCountRow();
        String[][] data = new String[numOfRows-1][numOfColumns];
        for (int i = 1; i < numOfRows; i++){
            for (int j = 0; j< numOfColumns; j++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i-1][j] = value;
                if (value == null) {
                    System.out.println("The cell is empty");
                }
            }
        }
        return data;
    }

}
