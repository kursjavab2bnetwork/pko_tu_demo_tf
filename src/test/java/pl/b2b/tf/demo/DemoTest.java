package pl.b2b.tf.demo;

import org.testng.annotations.*;
import pl.b2b.net.tf.demo.GlobalDefinitions;
import pl.b2b.net.tf.demo.testfactory.BaseTF;
import pl.b2b.net.tf.demo.testfactory.TestFactorySteps;
import pl.b2b.net.tf.demo.utils.DataLoader;

import java.io.IOException;

public class DemoTest {
//
//    @DataProvider(name = "TestData")
//    public Object[][] getData() throws IOException {
//        DataLoader dataLoader = new DataLoader();
//        Object[][] tab = dataLoader.read(GlobalDefinitions.EXCEL_FILE_PATH);
//        return tab;
//    }
//
//
//    @BeforeClass
//    public void demoTestStart() throws Throwable {
//        BaseTF.setUp("FF");
//    }
//
//    @Test(dataProvider = "TestData")
//    @Parameters({"direction", "destination", "dateOfDeparture", "dateOfReturn", "numberOfAdults", "numberOfChildren", "standardProtection", "fullComfort", "prestigiousJourney"})
//    public void demoTest(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
//                                            String numberOfChildren, String standardProtection, String fullComfort, String prestigiousJourney) throws Throwable {
//
//        TestFactorySteps testFactorySteps = new TestFactorySteps();
//        testFactorySteps.setUp();
//        testFactorySteps.yourJourneyTestParams(direction,destination,dateOfDeparture,dateOfReturn,
//                numberOfAdults,numberOfChildren,standardProtection,fullComfort,prestigiousJourney);
//
//    }
//
//        @AfterClass
//    public void demoTestEnd() {
//            BaseTF.tearDown();
//        }


}
