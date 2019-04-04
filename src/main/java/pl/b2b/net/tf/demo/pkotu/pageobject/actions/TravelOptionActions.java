package pl.b2b.net.tf.demo.pkotu.pageobject.actions;

import org.openqa.selenium.*;
import org.testng.Assert;
import pl.b2b.net.tf.demo.pkotu.pageobject.pages.TravelOptionObject;
import pl.b2b.testfactory.TestFactoryUtils;

import static pl.b2b.net.tf.demo.GlobalDefinitions.PKO_TU_URL;
import java.awt.*;
import java.util.List;

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
        checkIfSuccess(1,"Kierunek podróży nie został wybrany.");
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
        checkIfSuccess(2,"Cel podróży nie został wybrany");
    }

    public void typeDates(String dateOfDeparture, String dateOfReturn) throws AWTException {
        typeDateRobot(startDate, dateOfDeparture);
        typeDateRobot(endDate, dateOfReturn);
        checkIfSuccess(3,"Termin wyjazdu nie został wprowadzony ");
    }

    public void clickNextButton() {
        click(nextButton);
    }

    public void numberOfTravelers(String numberOfAdults, String numberOfChildren) {

        if (numberOfAdults.equals("1") && numberOfChildren.equals("0")) {
            click(travelers1person);
            checkIfSuccess(5,"Liczba podróżujących nie została wybrana");
        } else {
            click(travelMorePeople);
            type(numberOfAdultField, Keys.BACK_SPACE);
            type(numberOfAdultField, numberOfAdults);
            type(numberOfChildrenField, Keys.BACK_SPACE);
            type(numberOfChildrenField, numberOfChildren);
            checkIfSuccess(6,"Liczba podróżujących nie została wybrana");
        }
    }

    private void checkIfSuccess(int expectedNumber, String komunikat){
        int locatorElementSize = driver.findElements(By.cssSelector(TravelOptionObject.SUCCESS_CSS)).size();
        Assert.assertEquals(locatorElementSize,expectedNumber,komunikat);
    }

    public String getPriceInStandardProtection() {
        waitForWebElement(standardProtection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", standardScroll);
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

    public String checkAllPrices(String standardProtection, String fullComfort, String prestigiousJourney) {
        String standard = getPriceInStandardProtection().trim().replace(".", ",");
        String comfort = getPriceInFullComfort().trim().replace(".", ",");
        String prestigius = getPriceInPrestigiusJourney().trim().replace(".", ",");

        standardProtection = standardProtection.trim().replace(".", ",");
        fullComfort = fullComfort.trim().replace(".", ",");
        prestigiousJourney = prestigiousJourney.trim().replace(".", ",");

        String message = "";

        if (!standard.equals(standardProtection)) {
            message = "Kwota za pakiet standard wynosi: "+standard+", zamiast: "+standardProtection+"\r\n";
        }

        if (!comfort.equals(fullComfort)) {
            message = message + "Kwota za pakiet comfort wynosi: "+comfort+", zamiast: "+fullComfort+"\r\n";
        }

        if (!prestigius.equals(prestigiousJourney)) {
            message = message + "Kwota za pakiet prestiż wynosi: "+prestigius+", zamiast: "+prestigiousJourney+"\r\n";
        }

        return message;

    }

}
