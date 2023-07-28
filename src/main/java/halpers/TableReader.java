package halpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableReader {
    private ExcelReader excelReader = null;
    private final String path = "src/main/resources/HW.xlsx";

    public String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        int rowIndex = 2;
        List<WebElement> companies = table.findElements(By.xpath("./tr/td[" + searchColumn + "]"));
        for (WebElement el : companies) {
            if (el.getText().equals(searchText)) {
                break;
            }
            rowIndex++;
        }
        return table.findElement(By.xpath("./tr[" + rowIndex + "]/td[" + returnColumnText + "]")).getText();
    }

    public String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText) throws Exception {
        return table.findElement(By.xpath("./tr/td["+searchColumn+"][text()='"+searchText+"']/../td["+returnColumnText+"]")).getText();
    }

    public String getCellTexFromFile(int searchColumn, String searchText, int returnColumnText) {
        searchColumn = searchColumn-1;
        returnColumnText = returnColumnText-1;
        try {
            excelReader = new ExcelReader(path);
            excelReader.getSheetData();
            for (String[] el : excelReader.getSheetData()) {
                if(el[searchColumn].equals(searchText)) {
                    return el[returnColumnText];
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public boolean verifyTableCellTextFromFile(int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String actualText = getCellTexFromFile(searchColumn, searchText, returnColumnText);
        return actualText.contentEquals(expectedText);
    }

    public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String actualText = getTableCellText(table, searchColumn, searchText, returnColumnText);
        return actualText.contentEquals(expectedText);
    }

    public boolean verifyTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String actualText = getTableCellText(table, searchColumn, searchText, returnColumnText);
        return actualText.contentEquals(expectedText);
    }
}
