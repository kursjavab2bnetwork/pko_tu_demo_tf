package pl.b2b.net.tf.demo.testfactory;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
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

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverSetup.getDriver();
        travelOptionMethods = new TravelOptionActions(driver);
 //       TestFactoryUtils.log("\r\nKierunek;Cel wyjzadu;Data wyjazdu;Data powrotu;Liczba dorosłych;Liczba dzieci;Cena standard;Cena standard oczekiwana;Cena comfort;Cena komfort oczekiwana;Cena presitż;Cena prestiż oczekiwana");

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

        String line = "\r\n" + direction + ";" + destination + ";" + dateOfDeparture + ";" + dateOfReturn + ";" + numberOfAdults + ";" + numberOfChildren + ";" + standardProtection + ";" + standard + ";" + fullComfort + ";" + comfort + ";" + prestigiousJourney + ";" + prestigius;

        Boolean status = false;
        String message = travelOptionMethods.checkAllPrices(standardProtection, fullComfort, prestigiousJourney);
        if (message.equals("")) {
            status = true;
        }

        Assert.assertTrue(status, "Podane kwoty nie zgadzają się. Zobacz log, aby sprawdzić szczegóły.");

        if (status) {
            line = line + ";Pozytywny";
        } else {
            line = line + ";Negatywny";
        }
        resultList.add(line);

        TestFactoryUtils.log("" + resultList);

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
