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
import pl.b2b.testfactory.TestFactoryUtils;
import pl.b2b.testfactory.annotations.TestFactoryMethod;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFactorySteps extends BaseTF {
    private TravelOptionActions travelOptionMethods;
//    List<List<String>> logList = new ArrayList<>();
    List<String> resultList = new ArrayList<>();

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
    @TestFactoryMethod(value = "Demo - weryfikacja stawek ubezpieczenia", description = "Wykonanie zadania próbnego PoC", group = "demo")
//    @Parameters({"direction", "destination", "dateOfDeparture", "dateOfReturn", "numberOfAdults", "numberOfChildren", "standardProtection", "fullComfort", "prestigiousJourney"})
    public void yourJourneyTest(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
                                String numberOfChildren, String standardProtection, String fullComfort, String prestigiousJourney) throws AWTException {
        travelOptionMethods.navigateToUrl();
        travelOptionMethods.selectDirection(direction);
        travelOptionMethods.selectPurpose(destination);
        travelOptionMethods.typeDates(dateOfDeparture, dateOfReturn);
        travelOptionMethods.numberOfTravelers(numberOfAdults, numberOfChildren);
        travelOptionMethods.clickNextButton();

        String line = "\r\n"+direction+","+destination+","+dateOfDeparture+","+dateOfReturn+","+numberOfAdults+","+numberOfChildren+","+standardProtection+","+fullComfort+","+prestigiousJourney;

//        resultList.add(direction);
//        resultList.add(destination);
//        resultList.add(dateOfDeparture);
//        resultList.add(dateOfReturn);
//        resultList.add(numberOfAdults);
//        resultList.add(numberOfChildren);
//        resultList.add(standardProtection);
//        resultList.add(fullComfort);
//        resultList.add(prestigiousJourney);

        Boolean status = travelOptionMethods.checkAllPrices(standardProtection, fullComfort, prestigiousJourney );
        Assert.assertTrue(status,"Podane kwoty nie zgadzają się");

        if(status){
            line = line+",Pozytywny";
        }else{
            line = line+",Negatywny";
        }
        resultList.add(line);
  //      logList.add(resultList);
        TestFactoryUtils.log(""+resultList);
//        resultList = new ArrayList<>();



//        Assert.assertEquals(standardProtection,travelOptionMethods.getPriceInStandardProtection(),"Kwota dla wariantu: 'Standardowa ochrona' nie zgadza się.");
//        Assert.assertEquals(fullComfort,travelOptionMethods.getPriceInFullComfort(),"Kwota dla wariantu: 'Pełny komfort' nie zgadza się.");
//        Assert.assertEquals(prestigiousJourney,travelOptionMethods.getPriceInPrestigiusJourney(),"Kwota dla wariantu: 'Prestiżowa podróż' nie zgadza się.");

//        WebDriverCfg.getWebDriverInstance().get(CfgTest.websiteAddress);
//        Assert.assertNotNull(WebDriverCfg.getWebDriverInstance().getCurrentUrl());
    }




//    @DataProvider(name = "TestData2")
//    public static Object[][] inputData() {
//        return new Object[][] { { "Europa", "Wypoczynek", "20190405", "20190411", "1", "0", "31,36", "53,90", "91,14" },
//                { "Europa", "Narciarstwo", "20190405", "20190411", "1", "0", "82,96", "142,35", "246,20" }};
//    }
//
//    @Test(dataProvider = "TestData2")
//    @TestFactoryMethod(value = "Demo - weryfikacja stawek ubezpieczenia", description = "Wykonanie zadania próbnego PoC. DataProvider", group = "demo")
////    @Parameters({"direction", "destination", "dateOfDeparture", "dateOfReturn", "numberOfAdults", "numberOfChildren", "standardProtection", "fullComfort", "prestigiousJourney"})
//    public void yourJourneyTestDP(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
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
}
