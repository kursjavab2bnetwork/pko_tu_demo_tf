package pl.b2b.net.tf.demo.pkotu.pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TravelOptionObject extends AbstractPageObject{
    public TravelOptionObject(WebDriver driver){
        super(driver);
    }

    protected static String LOGO_XPATH = "//img[@src='/kupuje/resources/k2ut/img/layout/logo.png']";
    protected static final String TITLE_XPATH = "//header[contains(@class, 'site-header') and text() = 'Tak Podróżuję']";
    protected static String SUCCESS_CSS = "div.form-row-field.success";

    protected static final String DESTINATION_WORLD_TXT = "//span[contains(@class, 'custom-radio-label') and text() = 'Świat']";
    protected static final String DESTINATION_EUROPE_TXT = "//span[contains(@class, 'visible-not-mobile-inline') and text() = 'i kraje basenu Morza Śródziemnego']";

    protected static final String REST_XPATH =  "//span[contains(@class, 'custom-radio-label') and text() = 'Wypoczynek']";
    protected static final String SKIING_XPATH =  "//span[contains(@class, 'custom-radio-label') and text() = 'Narciarstwo']";
    protected static final String HIGH_RISK_SPORTS_XPATH=  "//span[contains(@class, 'custom-radio-label') and text() = 'Sporty wysokiego ryzyka']";
    protected static final String WORK_XPATH =  "//span[contains(@class, 'custom-radio-label') and text() = 'Praca umysłowa']";
    protected static final String PHYSICAL_WORK_XPATH =  "//span[contains(@class, 'custom-radio-label') and text() = 'Praca fizyczna']";

    protected static final String DATE_START_ID = "id-tripCover-startDate-0-1";
    protected static final String DATE_END_ID = "id-tripCover-endDate-0-1";

    protected static final String TRAVELLERS_ONE_PERSON_XPATH = "//span[contains(@class, 'custom-radio-label') and text() = '1 osoba']";

    protected static final String TRAVELLERS_MORE_PEOPLE_XPATH = "//span[contains(@class, 'custom-radio-label') and text() = 'więcej osób']";
    protected static final String NUMBER_OF_ADULT_ID = "id-tripPerson-adult-18";
    protected static final String NUMBER_CHILDREN_ID = "id-tripPerson-child-18";

    protected static final String NEXT_BUTTON_ID = "idNavNext";

    protected static final String STANDARD_PROTECTION = "//div[@data-variant=\"standard\"]/div[2]/ul/li[5]/span/span";
    protected static final String FULL_COMFORT = "//div[@data-variant=\"optimum\"]/div[2]/ul/li[5]/span/span[1]";
    protected static final String PRESTIGIOUS_JOURNEY = "//div[@data-variant=\"lux\"]/div[2]/ul/li[5]/span/span[1]";

    @FindBy(xpath = TravelOptionObject.DESTINATION_EUROPE_TXT)
    protected WebElement destinationEurope;

    @FindBy(xpath = TravelOptionObject.DESTINATION_WORLD_TXT)
    protected WebElement destinationWorld;

    @FindBy(xpath = TravelOptionObject.REST_XPATH)
    protected WebElement purposeRest;

    @FindBy(xpath = TravelOptionObject.SKIING_XPATH)
    protected WebElement purposeSkiing;

    @FindBy(xpath = TravelOptionObject.HIGH_RISK_SPORTS_XPATH)
    protected WebElement purposeHighRisk;

    @FindBy(xpath = TravelOptionObject.WORK_XPATH)
    protected WebElement purposeWork;

    @FindBy(xpath = TravelOptionObject.PHYSICAL_WORK_XPATH)
    protected WebElement purposePhysicalWork;

    @FindBy(id = TravelOptionObject.DATE_START_ID)
    protected WebElement startDate;

    @FindBy(id = TravelOptionObject.DATE_END_ID)
    protected WebElement endDate;

    @FindBy(xpath = TravelOptionObject.TRAVELLERS_ONE_PERSON_XPATH)
    protected WebElement travelers1person;

    @FindBy(xpath = TravelOptionObject.TRAVELLERS_MORE_PEOPLE_XPATH)
    protected WebElement travelMorePeople;

    @FindBy(id = TravelOptionObject.NUMBER_OF_ADULT_ID)
    protected WebElement numberOfAdultField;

    @FindBy(id = TravelOptionObject.NUMBER_CHILDREN_ID)
    protected WebElement numberOfChildrenField;

    @FindBy(id = TravelOptionObject.NEXT_BUTTON_ID)
    protected WebElement nextButton;

    @FindBy(xpath = TravelOptionObject.STANDARD_PROTECTION)
    protected WebElement standardProtection;

    @FindBy(xpath = TravelOptionObject.FULL_COMFORT)
    protected WebElement fullComfort;

    @FindBy(xpath = TravelOptionObject.PRESTIGIOUS_JOURNEY)
    protected WebElement prestigiousJourney;
}
