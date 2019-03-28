package pl.b2b.net.tf.demo.pkotu.pageobject.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pl.b2b.net.tf.demo.GlobalDefinitions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static pl.b2b.net.tf.demo.pkotu.pageobject.pages.TravelOptionObject.LOGO_XPATH;

public abstract class AbstractPageObject {
    private WebDriverWait wait;
    protected WebDriver driver;

    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, GlobalDefinitions.DRIVER_EXPLICIT_TIMEOUT);
    }

    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }
    protected void waitForPageLoadComplete(String urlRegex) {
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));

        waitForElementToBeVisible(getLogo(LOGO_XPATH));

        wait.until(ExpectedConditions.urlMatches(urlRegex));
    }
    protected WebElement getLogo(String logo) {
        Assert.assertNotNull(logo, "Logo CSS selector should be specified");
        return driver.findElement(By.xpath(logo));
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected void click(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    protected void waitForWebElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void type(WebElement webElement, String text) {
        waitForWebElement(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected void type(WebElement webElement, Keys keys) {
        waitForWebElement(webElement);
        webElement.clear();
        webElement.sendKeys(keys);
    }

    protected void typeDateRobot(WebElement webElement, String text) throws AWTException {
        waitForWebElement(webElement);
        webElement.click();
        webElement.clear();
        pasteRobot(webElement, text);
        webElement.sendKeys(Keys.ENTER);
    }

    protected void pasteRobot(WebElement element, String text) throws AWTException {
        Robot robot = new Robot();

        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

//        if (verifyEnteredData)
//            Assert.assertEquals(getElementsValue(element), text, "Text wasn't printed");
    }


}
