package pl.b2b.net.tf.demo.testfactory;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.b2b.net.tf.demo.GlobalDefinitions;
import pl.b2b.net.tf.demo.driver.DriverSetup;
import pl.b2b.net.tf.demo.pkotu.pageobject.actions.TravelOptionActions;
import pl.b2b.net.tf.demo.utils.DataLoader;
import pl.b2b.testfactory.annotations.TestFactoryMethod;

import java.awt.*;
import java.io.IOException;

public class TestFactorySteps extends BaseTF {
    private TravelOptionActions travelOptionMethods;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverSetup.getDriver();
        travelOptionMethods = new TravelOptionActions(driver);
    }

//    @DataProvider(name = "TestData")
//    public Object[][] getData() throws IOException {
//        DataLoader dataLoader = new DataLoader();
//        Object[][] tab = dataLoader.read(GlobalDefinitions.EXCEL_FILE_PATH);
//        return tab;
//    }
//
//    @Test(dataProvider = "TestData")
//    @TestFactoryMethod(value = "Demo test - porównanie parametrów", description = "Wykonanie zadania próbnego PoC", group = "demo")
////    @Parameters({"direction", "destination", "dateOfDeparture", "dateOfReturn", "numberOfAdults", "numberOfChildren", "standardProtection", "fullComfort", "prestigiousJourney"})
//    public void yourJourneyTest(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
//                                String numberOfChildren, String standardProtection, String fullComfort, String prestigiousJourney) throws AWTException {
//
//        travelOptionMethods.navigateToUrl();
//        travelOptionMethods.selectDirection(direction);
//        travelOptionMethods.selectPurpose(destination);
//        travelOptionMethods.typeDates(dateOfDeparture, dateOfReturn);
//        travelOptionMethods.numberOfTravelers(numberOfAdults, numberOfChildren);
//        travelOptionMethods.clickNextButton();
//        Assert.assertEquals(standardProtection,travelOptionMethods.getPriceInStandardProtection());
//        Assert.assertEquals(fullComfort,travelOptionMethods.getPriceInFullComfort());
//        Assert.assertEquals(prestigiousJourney,travelOptionMethods.getPriceInPrestigiusJourney());
//
////        WebDriverCfg.getWebDriverInstance().get(CfgTest.websiteAddress);
////        Assert.assertNotNull(WebDriverCfg.getWebDriverInstance().getCurrentUrl());
//    }




    @DataProvider(name = "TestData2")
    public static Object[][] inputData() {
        return new Object[][] { { "Europa", "Wypoczynek", "20190405", "20190411", "1", "0", "31,36", "53,90", "91,14" },
                { "Europa", "Narciarstwo", "20190405", "20190411", "1", "0", "82,96", "142,35", "246,20" }};
    }

    @Test(dataProvider = "TestData2")
    @TestFactoryMethod(value = "Demo - weryfikacja stawek ubezpieczenia", description = "Wykonanie zadania próbnego PoC. DataProvider", group = "demo")
//    @Parameters({"direction", "destination", "dateOfDeparture", "dateOfReturn", "numberOfAdults", "numberOfChildren", "standardProtection", "fullComfort", "prestigiousJourney"})
    public void yourJourneyTestDP(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
                                String numberOfChildren, String standardProtection, String fullComfort, String prestigiousJourney) throws AWTException {

        travelOptionMethods.navigateToUrl();
        travelOptionMethods.selectDirection(direction);
        travelOptionMethods.selectPurpose(destination);
        travelOptionMethods.typeDates(dateOfDeparture, dateOfReturn);
        travelOptionMethods.numberOfTravelers(numberOfAdults, numberOfChildren);
        travelOptionMethods.clickNextButton();
        Assert.assertEquals(standardProtection,travelOptionMethods.getPriceInStandardProtection());
        Assert.assertEquals(fullComfort,travelOptionMethods.getPriceInFullComfort());
        Assert.assertEquals(prestigiousJourney,travelOptionMethods.getPriceInPrestigiusJourney());

//        WebDriverCfg.getWebDriverInstance().get(CfgTest.websiteAddress);
//        Assert.assertNotNull(WebDriverCfg.getWebDriverInstance().getCurrentUrl());
    }
}
