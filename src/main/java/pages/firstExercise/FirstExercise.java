package pages.firstExercise;

import com.relevantcodes.extentreports.LogStatus;
import core.BasePageDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FirstExercise extends BasePageDriver {

    @FindBy(xpath = "//div[contains(@class, 'lang-bttn')]/span[@class = 'current-language']")
    private WebElement languagesBtn;

    @FindAll(@FindBy (xpath = "//span[@class='current-language']//following-sibling::div/ul[@class='languagesList']/li[@class='languageLink']/a"))
    private List<WebElement> languages;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesBtn;

    private String regexMask = "(https?:\\/\\/)|(w{3}\\.)|.888?.com\\/?|\\?lang=";
    public FirstExercise() {
        driver.get("https://888.com/");
        PageFactory.initElements(driver, this);
    }
    public void openTheLanguageDropDown(){
        waitUntilElementVisible(10, acceptCookiesBtn);
        acceptCookiesBtn.click();
        waitUntilElementInvisible(10, acceptCookiesBtn);
    }
    public void checkLanguages() {
        ArrayList<String> len = new ArrayList<>();
        for (WebElement el : languages) {
            if (languagesBtn.getText().toLowerCase().contains("en")) {
                languagesBtn.click();
                String languageCode = el.getAttribute("href").replaceAll(regexMask, "");
                el.click();
                len.add(languagesBtn.getText().toLowerCase());
                if (languageCode.equals(languagesBtn.getText().toLowerCase())) {
                    test.log(LogStatus.PASS, "The site open with the right  " + languagesBtn.getText() + " language");
                } else {
                    test.log(LogStatus.ERROR, "The site open with the wrong " + languagesBtn.getText() + " language");
                }

            }
            for (WebElement el1 : languages) {
                waitUntilElementWillBeClickable(5, languagesBtn);
                waitUntilElementVisible(5, languagesBtn);
                languagesBtn.click();
                String languageCode = el1.getAttribute("href").replaceAll(regexMask, "");
                if (len.contains(languageCode)) {
                    continue;
                }
                el1.click();
                if (languageCode.equals(languagesBtn.getText().toLowerCase())) {
                    test.log(LogStatus.PASS, "The site open with the right  " + languagesBtn.getText() + " language");
                } else {
                    test.log(LogStatus.ERROR, "The site open with the wrong " + languagesBtn.getText() + " language");
                }
                len.add(languagesBtn.getText().toLowerCase());
                break;
            }
            report.endTest(test);
            report.flush();
        }
    }
}
