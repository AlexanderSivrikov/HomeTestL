package first;

import com.aventstack.extentreports.Status;
import core.BaseTest;
import halpers.TableReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.TablePage;

public class TestHomeWork extends BaseTest {
    private final TableReader tableReader = new TableReader();

    @Test
    public void checkTableCellWithExtendReport() {
        extentTest = extents.createTest("Verify Table Cell Text");
        extentTest.log(Status.INFO, "Start test...");
        if (tableReader.verifyTableCellText(new TablePage().getTable(), 1, "Ernst Handel", 3, "Austria")) {
            extentTest.pass("Test PASSED");
        } else {
            extentTest.fail("Test FAILED");
        }
        Assertions.assertTrue(tableReader.verifyTableCellText(new TablePage().getTable(), 1, "Ernst Handel", 3, "Austria"), "The actual result doesn't equal expected result");

    }

    @Test
    public void checkTableCellByXpathWithExtendReport() {
        extentTest = extents.createTest("Verify Table Cell Text By Xpath");
        extentTest.log(Status.INFO, "Start test...");
        if (tableReader.verifyTableCellTextByXpath(new TablePage().getTable(), 1, "Ernst Handel", 3, "Austria")) {
            extentTest.pass("Test PASSED");
        } else {
            extentTest.fail("Test FAILED");
        }
        Assertions.assertTrue(tableReader.verifyTableCellTextByXpath(new TablePage().getTable(), 1, "Ernst Handel", 3, "Austria"), "The actual result doesn't equal expected result");
    }

    @Test
    public void checkTableCellFromFileWithExtendReport(){
        extentTest = extents.createTest("Verify Table Cell Text From Excel File");
        extentTest.log(Status.INFO, "Start test...");
        if (tableReader.verifyTableCellTextFromFile(1, "Ernst Handel", 3, "Austria")) {
            extentTest.pass("Test PASSED");
        } else {
            extentTest.fail("Test FAILED");
        }
        Assertions.assertTrue(tableReader.verifyTableCellTextFromFile(1, "Ernst Handel", 3, "Austria"), "The actual result doesn't equal expected result");
    }
}
