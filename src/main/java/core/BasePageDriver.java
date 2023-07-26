package core;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sun.media.sound.SoftAbstractResampler;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageDriver {

    protected static WebDriver driver;

    public static void setDriver(WebDriver driver_p){
        driver = driver_p;
    }

    public SoftAssertions assertions = new SoftAssertions();
    public ExtentReports report =  new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html");

    public ExtentTest test = report.startTest("Test Home Work");

    public static void checkUrl(int waitTime, String url){
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        driverWait.until(ExpectedConditions.urlContains(url));
    }

    public static void waitUntilElementVisible (int waitTime, WebElement el){
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        driverWait.until(ExpectedConditions.visibilityOf(el));
    }

    public static void waitUntilElementInvisible (int waitTime, WebElement el){
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        driverWait.until(ExpectedConditions.invisibilityOf(el));
    }

    public static void waitUntilElementWillBeClickable (int waitTime, WebElement el){
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        driverWait.until(ExpectedConditions.elementToBeClickable(el));
    }

}
