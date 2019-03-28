package pl.b2b.net.tf.demo.testfactory;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.b2b.net.tf.demo.driver.DriverSetup;
import pl.b2b.testfactory.TestFactoryUtils;
import pl.b2b.testfactory.annotations.TestFactoryMethod;

public class BaseTF {
    @Test
    @TestFactoryMethod(value = "Start", description = "Open browser. For FireFox enter FF", group = "Configuration")
    @Parameters({"driver"})
    public static void setUp(String driver) throws Throwable {
        DriverSetup.setDriver(driver);
    }

    @Test
    @TestFactoryMethod(value = "End", description = "Close browser", group = "Configuration")
    public static void tearDown() {
        DriverSetup.close(DriverSetup.getDriver());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            TestFactoryUtils.addScreenShotFromSeleniumDriver(DriverSetup.getDriver());
        }
    }
}
