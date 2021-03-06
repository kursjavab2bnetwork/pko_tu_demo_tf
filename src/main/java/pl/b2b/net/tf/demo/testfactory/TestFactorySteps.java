package pl.b2b.net.tf.demo.testfactory;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pl.b2b.net.tf.demo.GlobalDefinitions;
import pl.b2b.net.tf.demo.driver.DriverSetup;
import pl.b2b.net.tf.demo.pkotu.pageobject.actions.TravelOptionActions;
import pl.b2b.net.tf.demo.utils.DataLoader;
import pl.b2b.testfactory.TestFactoryUtils;
import pl.b2b.testfactory.annotations.TestFactoryMethod;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFactorySteps extends BaseTF {
    private TravelOptionActions travelOptionMethods;
    List<String> resultList = new ArrayList<>();
    Boolean firstIteration = true;
    Boolean scenarioStatus = true;
    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverSetup.getDriver();
        travelOptionMethods = new TravelOptionActions(driver);
    }

    @DataProvider(name = "TestData")
    public Object[][] getData() throws IOException {
        DataLoader dataLoader = new DataLoader();
        Object[][] tab = dataLoader.read(GlobalDefinitions.EXCEL_FILE_PATH);
        return tab;
    }


    @Test(dataProvider = "TestData")
    @TestFactoryMethod(value = "Demo - weryfikacja stawek ubezpieczenia", description = "Wykonanie zadania próbnego PoC. Zaczytanie parametrów z pliku", group = "Demo")
    public void yourJourneyTest(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
                                String numberOfChildren, String standardProtection, String fullComfort, String prestigiousJourney) throws AWTException {
        travelOptionMethods.navigateToUrl();
        travelOptionMethods.selectDirection(direction);
        travelOptionMethods.selectPurpose(destination);
        travelOptionMethods.typeDates(dateOfDeparture, dateOfReturn);
        travelOptionMethods.numberOfTravelers(numberOfAdults, numberOfChildren);
        travelOptionMethods.clickNextButton();

        String standard = travelOptionMethods.getPriceInStandardProtection().trim().replace(".", ",");
        String comfort = travelOptionMethods.getPriceInFullComfort().trim().replace(".", ",");
        String prestigius = travelOptionMethods.getPriceInPrestigiusJourney().trim().replace(".", ",");

        standardProtection = standardProtection.replace(".", ",");
        fullComfort = fullComfort.replace(".", ",");
        prestigiousJourney = prestigiousJourney.replace(".", ",");

        String line;
        if(firstIteration){
            line = "\r\nKierunek;Cel wyjzadu;Data wyjazdu;Data powrotu;Liczba dorosłych;Liczba dzieci;Cena standard;Cena standard oczekiwana;Cena comfort;Cena komfort oczekiwana;Cena presitż;Cena prestiż oczekiwana;Rezultat"
            +"\r\n" + direction + ";" + destination + ";" + dateOfDeparture + ";" + dateOfReturn + ";" + numberOfAdults + ";" + numberOfChildren + ";" + standardProtection + ";" + standard + ";" + fullComfort + ";" + comfort + ";" + prestigiousJourney + ";" + prestigius;
            firstIteration = false;
        }else{
             line = "\r\n" + direction + ";" + destination + ";" + dateOfDeparture + ";" + dateOfReturn + ";" + numberOfAdults + ";" + numberOfChildren + ";" + standardProtection + ";" + standard + ";" + fullComfort + ";" + comfort + ";" + prestigiousJourney + ";" + prestigius;
            firstIteration = false;
        }

        Boolean status;
        String message = travelOptionMethods.checkAllPrices(standardProtection, fullComfort, prestigiousJourney);
        if (message.equals("")) {
            status = true;
            line = line + ";Pozytywny";
        } else {
            status = false;
            scenarioStatus = false;
            line = line + ";Negatywny";
        }

        resultList.add(line);
        TestFactoryUtils.log("" + resultList +"\r\n");
        Assert.assertTrue(status, "Podane kwoty nie zgadzają się. Zobacz log, aby sprawdzić szczegóły.");
        Assert.assertTrue(scenarioStatus, "Podane kwoty nie zgadzają się. Zobacz log, aby sprawdzić szczegóły.");


    }


    @Test
    @TestFactoryMethod(value = "Demo - weryfikacja stawek ubezpieczenia.", description = "Wykonanie zadania próbnego PoC. Samodzielnie wprowadź parametry.", group = "Demo")
    @Parameters({"direction", "destination", "dateOfDeparture", "dateOfReturn", "numberOfAdults", "numberOfChildren", "standardProtection", "fullComfort", "prestigiousJourney"})
    public void yourJourneyTestParams(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
                                      String numberOfChildren, String standardProtection, String fullComfort, String prestigiousJourney) throws AWTException {

        travelOptionMethods.navigateToUrl();
        travelOptionMethods.selectDirection(direction);
        travelOptionMethods.selectPurpose(destination);
        travelOptionMethods.typeDates(dateOfDeparture, dateOfReturn);
        travelOptionMethods.numberOfTravelers(numberOfAdults, numberOfChildren);
        travelOptionMethods.clickNextButton();
        String message = travelOptionMethods.checkAllPrices(standardProtection, fullComfort, prestigiousJourney);
        Boolean status = false;
        if (message.equals("")) {
            status = true;
        }
        Assert.assertTrue(status, "Podane kwoty nie zgadzają się.\r\n" + message);
    }
}
