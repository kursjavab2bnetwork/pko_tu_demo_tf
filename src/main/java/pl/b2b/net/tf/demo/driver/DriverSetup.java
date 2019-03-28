package pl.b2b.net.tf.demo.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static pl.b2b.net.tf.demo.GlobalDefinitions.DRIVER_EXPLICIT_TIMEOUT;
import static pl.b2b.net.tf.demo.GlobalDefinitions.DRIVER_IMPLICIT_TIMEOUT;

public class DriverSetup {
    public static final String WEBDRIVER_RESOURCES = "c:\\tf\\tmp\\webdriver-resources\\";
    private static WebDriver driver;

    public static WebDriver createDriver(String browser) throws Throwable {
        try {
            if (driver == null) {
                BrowserEnum browserEnum = BrowserEnum.valueOf(browser);

                System.setProperty(browserEnum.getSystemProperty(), WEBDRIVER_RESOURCES + browserEnum.getExecutableFile());
                switch (browserEnum) {
                    case CHROME:
                        System.out.println("Launching Google Chrome ...");
                        driver = new ChromeDriver();
                        System.out.println("Browser opened");
                        break;

                    case FF:
                        System.out.println("Launching FireFox ...");

                        DesiredCapabilities capabilities = new DesiredCapabilities();
                        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

                        driver = new FirefoxDriver(capabilities);
                        System.out.println("Browser opened");
                        break;

                    default:
                        System.out.println("Invalid browser getSelectorType specified.");
                        throw new IllegalArgumentException("NO BROWSER DEFINED");
                }

                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(DRIVER_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(DRIVER_EXPLICIT_TIMEOUT, TimeUnit.SECONDS);
            }

            return driver;
        } catch (Throwable e) {
            if (driver != null) {
                DriverSetup.close(driver);
            }
            throw e;
        }
    }

    public static void close(WebDriver driverInstance) {
        System.out.println("Closing browser ...");
        driverInstance.quit();
        System.out.println("Driver quit complete");
        driver = null;
        System.out.println("Driver instance set as null");
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(String browser) throws Throwable {
        System.out.println("Driver was set to = " + browser);

        createDriver(browser);
    }
}