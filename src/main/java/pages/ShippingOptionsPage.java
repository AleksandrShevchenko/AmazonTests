package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingOptionsPage extends BasePage {

    public ShippingOptionsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class=\"a-row\"]//input[@class=\"a-button-text\"]")
    private WebElement continueButton;

    public void clickOnTheContinueButton()
    {
        try {continueButton.click();}
        catch (NoSuchElementException noContinueButton) {}

    }

    @FindBy(xpath = "//span[contains(text(),\"Add a credit or debit card\")]")
    private WebElement addCreditCardButton;
    public WebElement getAddCreditCardButton() {return addCreditCardButton;}

    public Boolean isAddCreditCardButtonVisible()
    {
        return addCreditCardButton.isDisplayed();
    }

    @FindBy(xpath = "//span[contains(text(),\"Enter a gift card, voucher or promotional code\")]")
    private WebElement enterAGiftCardVoucherOrPromotionalCodeLink;
    public WebElement getEnterAGiftCardVoucherOrPromotionalCodeLink() {return enterAGiftCardVoucherOrPromotionalCodeLink;}

    public Boolean isEnterAGiftCardVoucherOrPromotionalCodeButtonVisible()
    {
        return enterAGiftCardVoucherOrPromotionalCodeLink.isDisplayed();
    }

    @FindBy(xpath = "//a[contains(text(),\"Learn more\")][contains(@href,\"cobrandcard\")]")
    private WebElement learnMoreAboutAmazonStoreCardLink;
    public WebElement getLearnMoreAboutAmazonStoreCardLink() {return learnMoreAboutAmazonStoreCardLink;}

    public Boolean isLearnMoreAboutAmazonStoreCardButtonVisible()
    {
        return learnMoreAboutAmazonStoreCardLink.isDisplayed();
    }

    @FindBy(xpath = "//span[contains(text(),\"Add a personal checking account\")]")
    private WebElement addAPersonalCheckingAccountButton;
    public WebElement getAddAPersonalCheckingAccountButton(){return addAPersonalCheckingAccountButton;}

    public Boolean isAddAPersonalCheckingAccountButtonVisible()
    {
        return addAPersonalCheckingAccountButton.isDisplayed();
    }
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

    public final String PAGE_TITLE = "Select a Payment Method - Amazon.com Checkout";
    public Boolean isPageTitleCorrect()
    {
        return driver.getTitle() == PAGE_TITLE;
    }
}
