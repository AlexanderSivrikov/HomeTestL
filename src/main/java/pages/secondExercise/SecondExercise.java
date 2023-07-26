package pages.secondExercise;

import core.BasePageDriver;
import core.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SecondExercise extends BasePageDriver {

    @FindBy(xpath = "//table[@id='customers']/tbody")
    private WebElement table;

    public SecondExercise() {
        driver.get("http://www.w3schools.com/html/html_tables.asp");
        PageFactory.initElements(driver, this);
    }

    public WebElement getTable() {
        return table;
    }

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

    public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String cellText = table.findElement(By.xpath("./tr/td[text()='" + searchText + "']")).getText();
        return cellText.equals(expectedText);
    }

    public String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText) throws Exception {
        return table.findElement(By.xpath("./tr/td[text()='" + searchText + "']")).getText();
    }

    public String fromFile(int searchColumn, String searchText, int returnColumnText) throws Exception {
        searchColumn = searchColumn-1;
        returnColumnText = returnColumnText-1;
        String path = "src/main/resources/HW.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        excelReader.getSheetData();
        for (String[] el : excelReader.getSheetData()) {
            if(el[searchColumn].equals(searchText)) {
                return el[returnColumnText];
            }
        }
        return "";
    }

}

