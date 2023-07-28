package core;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected static WebDriver driver;
    public ExtentTest extentTest;
    public static void setDriver(WebDriver driver_p){
        driver = driver_p;
    }

}
