package pl.b2b.tf.demo;

import org.testng.annotations.Test;
import pl.b2b.net.tf.demo.testfactory.BaseTF;
import pl.b2b.net.tf.demo.testfactory.TestFactorySteps;

public class TransactionTest {

    @Test
    public void transactionTest1() throws Throwable {
        BaseTF.setUp("FF");
        TestFactorySteps testFactorySteps = new TestFactorySteps();
        testFactorySteps.setUp();
        testFactorySteps.yourJourneyTest("Europa","Wypoczynek","20190405","20190411",
                "1","0","31,36","53,90","91,14");
        BaseTF.tearDown();
    }

}
