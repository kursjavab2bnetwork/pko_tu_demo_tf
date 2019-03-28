package pl.b2b.net.tf.demo.pkotu.pageobject.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pl.b2b.net.tf.demo.pkotu.pageobject.pages.TravelOptionObject;
import static pl.b2b.net.tf.demo.GlobalDefinitions.PKO_TU_URL;
import java.awt.*;

public class TravelOptionActions extends TravelOptionObject {
    public TravelOptionActions(WebDriver driver){
        super(driver);
    }

    public void navigateToUrl(){
        navigateToUrl(PKO_TU_URL);
        waitForPageLoadComplete(PKO_TU_URL);
        checkPageTitleAndLogo();
    }

    private void checkPageTitleAndLogo(){
        Assert.assertNotNull(TravelOptionObject.LOGO_XPATH, "Logo ID should be specified");
        Assert.assertNotNull(TravelOptionObject.TITLE_XPATH, "The title does not match");
    }


    public void selectDirection(String direction) {
        switch (direction) {
            case "Europa":
                click(destinationEurope);
                break;
            case "Świat":
                click(destinationWorld);
                break;
        }
        checkIfSuccess(1);
    }

    public void selectPurpose(String purpose) {
        switch (purpose) {
            case "Wypoczynek":
                click(purposeRest);
                break;
            case "Narciarstwo":
                click(purposeSkiing);
                break;
            case "Sporty wysokiego ryzyka":
                click(purposeHighRisk);
                break;
            case "Praca":
                click(purposeWork);
                break;
            case "Praca fizyczna":
                click(purposePhysicalWork);
                break;
        }
        checkIfSuccess(2);
    }

    public void typeDates(String dateOfDeparture, String dateOfReturn) throws AWTException {
        typeDateRobot(startDate, dateOfDeparture);
        typeDateRobot(endDate, dateOfReturn);
        checkIfSuccess(3);
    }

    public void clickNextButton() {
        click(nextButton);
    }

    public void numberOfTravelers(String numberOfAdults, String numberOfChildren) {

        if (numberOfAdults.equals("1") && numberOfChildren.equals("0")) {
            click(travelers1person);
            checkIfSuccess(5);
        } else {
            click(travelMorePeople);
            type(numberOfAdultField, Keys.BACK_SPACE);
            type(numberOfAdultField, numberOfAdults);
            type(numberOfChildrenField, Keys.BACK_SPACE);
            type(numberOfChildrenField, numberOfChildren);
            checkIfSuccess(6);
        }
    }

    private void checkIfSuccess(int expectedNumber){
        int locatorElementSize = driver.findElements(By.cssSelector(TravelOptionObject.SUCCESS_CSS)).size();
        Assert.assertEquals(locatorElementSize,expectedNumber);
    }

    public String getPriceInStandardProtection() {
        waitForWebElement(standardProtection);
        return standardProtection.getText();
    }

    public String getPriceInFullComfort() {
        waitForWebElement(fullComfort);
        return fullComfort.getText();
    }

    public String getPriceInPrestigiusJourney() {
        waitForWebElement(standardProtection);
        return prestigiousJourney.getText();
    }
}
