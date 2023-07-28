package pages;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TablePage extends BasePage {

    @FindBy(xpath = "//table[@id='customers']/tbody")
    private WebElement table;

    public TablePage() {
        driver.get("http://www.w3schools.com/html/html_tables.asp");
        PageFactory.initElements(driver, this);
    }

    public WebElement getTable() {
        return table;
    }
}

