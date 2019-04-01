package pl.b2b.tf.demo;

import org.testng.annotations.*;
import pl.b2b.net.tf.demo.GlobalDefinitions;
import pl.b2b.net.tf.demo.testfactory.BaseTF;
import pl.b2b.net.tf.demo.testfactory.TestFactorySteps;
import pl.b2b.net.tf.demo.utils.DataLoader;

import java.io.IOException;

public class TransactionTest {

//    @Test
//    public void transactionTest1() throws Throwable {
//        BaseTF.setUp("FF");
//        TestFactorySteps testFactorySteps = new TestFactorySteps();
//        testFactorySteps.setUp();
//        testFactorySteps.yourJourneyTest("Europa","Wypoczynek","20190405","20190411",
//                "1","0","31,36","53,90","91,14");
//        BaseTF.tearDown();
//    }


//    @DataProvider(name = "TestData")
//    public Object[][] getData() throws IOException {
//        DataLoader dataLoader = new DataLoader();
//        Object[][] tab = dataLoader.read(GlobalDefinitions.EXCEL_FILE_PATH);
//        return tab;
//    }
//
//    @BeforeClass
//    public void transactionTestDataProviderStart() throws Throwable {
//        BaseTF.setUp("FF");
//    }
//    @Test(dataProvider = "TestData")
//    @Parameters({"direction", "destination", "dateOfDeparture", "dateOfReturn", "numberOfAdults", "numberOfChildren", "standardProtection", "fullComfort", "prestigiousJourney"})
//    public void transactionTestDataProvider(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
//                                            String numberOfChildren, String standardProtection, String fullComfort, String prestigiousJourney) throws Throwable {
//
//        TestFactorySteps testFactorySteps = new TestFactorySteps();
//        testFactorySteps.setUp();
//        testFactorySteps.yourJourneyTestDP(direction,destination,dateOfDeparture,dateOfReturn,
//                numberOfAdults,numberOfChildren,standardProtection,fullComfort,prestigiousJourney);
//
//    }
//
//        @AfterClass
//    public void transactionTestDataProviderEnd() {
//            BaseTF.tearDown();
//        }
}
