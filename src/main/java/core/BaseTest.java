package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
public class BaseTest {
    protected WebDriver driver;
    public static ExtentReports extents = new ExtentReports();
    private static final ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReportResults.html");
    public static ExtentTest extentTest;
    @BeforeAll
    public static void beforeAll(){
        extents.attachReporter(reporter);
        reporter.config().setOfflineMode(true);
        reporter.config().setDocumentTitle("Home Work Leumit");
        reporter.config().setReportName("Test Report");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '(zzz)'");
        reporter.config().setEncoding("UTF-8");
    }
    @BeforeEach
    public void SetUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @AfterAll
    public static void afterClassCore(){
        extents.flush();
    }
}
