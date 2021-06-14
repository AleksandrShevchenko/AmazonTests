package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingOptionsPage extends BasePage {

    public final String PAGE_TITLE = "Select a Payment Method - Amazon.com Checkout";
    public ShippingOptionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class=\"a-row\"]//input[@class=\"a-button-text\"]")
    private WebElement continueButton;

    @FindBy(xpath = "//span[contains(text(),\"Add a credit or debit card\")]")
    private WebElement addCreditCardButton;

    @FindBy(xpath = "//a[contains(text(),\"Learn more\")][contains(@href,\"cobrandcard\")]")
    private WebElement learnMoreAboutAmazonStoreCardLink;

    @FindBy(xpath = "//span[contains(text(),\"Enter a gift card, voucher or promotional code\")]")
    private WebElement enterAGiftCardVoucherOrPromotionalCodeLink;

    @FindBy(xpath = "//span[contains(text(),\"Add a personal checking account\")]")
    private WebElement addAPersonalCheckingAccountButton;

    @FindBy(xpath = "//div[contains(text(), \"Please enter a name\")]")
    private WebElement emptyNameFieldErrorMessage;

    @FindBy(xpath = "//div[contains(text(), \"Please enter an address\")]")
    private WebElement emptyStreetAddressFieldErrorMessage;

    @FindBy(xpath = "//div[@class=\"a-box a-alert-inline a-alert-inline-error\"]//div[contains(text(), \"Please enter a city name\")]")
    private WebElement emptyCityFieldErrorMessage;

    @FindBy(xpath = "//div[contains(text(), \"Please enter a ZIP\")]")
    private WebElement emptyZipCodeFieldErrorMessage;

    @FindBy(xpath = "//div[contains(text(), \"Please enter a phone number\")]")
    private WebElement emptyPhoneNumberFieldErrorMessage;

    @FindBy(xpath = "//div[@class=\"a-box a-alert-inline a-alert-inline-warning\"]//div[contains(text(), \"Please enter a valid ZIP\")]")
    private WebElement invalidZipCodeErrorMessage;

    @FindBy(xpath = "//div[contains(text(), \"valid phone number\")]")
    private WebElement invalidPhoneNumberErrorMessage;

    @FindBy(xpath = "//h1[contains(text(),\"Select a payment method\")]")
    private WebElement pageHeader;
    public WebElement getPageHeader() {return pageHeader;}

    public void clickOnTheContinueButton()
    {
        try {continueButton.click();}
        catch (NoSuchElementException ignored) {}
    }

    public WebElement getAddCreditCardButton() {return addCreditCardButton;}

    public Boolean isAddCreditCardButtonVisible()
    {
        return addCreditCardButton.isDisplayed();
    }
    public Boolean isLearnMoreAboutAmazonStoreCardButtonVisible() { return learnMoreAboutAmazonStoreCardLink.isDisplayed(); }
    public Boolean isAddAPersonalCheckingAccountButtonVisible() { return addAPersonalCheckingAccountButton.isDisplayed(); }
    public Boolean isEnterAGiftCardVoucherOrPromotionalCodeButtonVisible() { return enterAGiftCardVoucherOrPromotionalCodeLink.isDisplayed(); }
    public Boolean isPageTitleCorrect() { return driver.getTitle().equals(PAGE_TITLE); }

    /*
        public WebElement getEnterAGiftCardVoucherOrPromotionalCodeLink() {return enterAGiftCardVoucherOrPromotionalCodeLink;}
    public WebElement getAddAPersonalCheckingAccountButton(){return addAPersonalCheckingAccountButton;}
    public WebElement getLearnMoreAboutAmazonStoreCardLink() {return learnMoreAboutAmazonStoreCardLink;}
     */
}
