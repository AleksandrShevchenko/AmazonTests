package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShippingAddressPage extends  BasePage
{
    public ShippingAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class=\"a-section a-spacing-base adddress-ui-widgets-form-field-container\"]//span[@class=\"a-button-text a-declarative\"]")
    private WebElement countryRegionDropdownListButton;

    @FindBy(xpath = "//a[contains(text(),\"Ukraine\")]")
    private WebElement ukraineItemIntoCountryRegionDropdownList;

    @FindBy(xpath = "//input[@name=\"address-ui-widgets-enterAddressFullName\"]")
    private WebElement fullNameInputField;

    @FindBy(xpath = "//input[@name=\"address-ui-widgets-enterAddressLine1\"]")
    private WebElement streetAddressInputField;

    @FindBy(xpath = "//input[@name=\"address-ui-widgets-enterAddressCity\"]")
    private WebElement cityInputField;

    @FindBy(xpath = "//input[@name=\"address-ui-widgets-enterAddressPostalCode\"]")
    private WebElement zipCodeInputField;

    @FindBy(xpath = "//input[@name=\"address-ui-widgets-enterAddressPhoneNumber\"]")
    private WebElement phoneNumberInputField;

    @FindBy(xpath = "//input[@class=\"a-button-input\"]")
    private WebElement useThisAddressButton;

    @FindBy(xpath = "//span[@id=\"address-ui-widgets-form-submit-button\"]//input[@type=\"submit\"]")
    private WebElement addNewAddressButton;

    @FindBy(xpath = "//a[@class=\"a-link-normal add-new-address-button\"]")
    private WebElement addAddressButton;

    @FindBy(xpath = "//div[@class=\"a-section address-section-no-default\"]//span[@class=\"a-list-item\"]//h5[@id=\"address-ui-widgets-FullName\"]")
    private WebElement fullNameCardText;

    @FindBy(xpath = "//div[@class=\"a-section address-section-no-default\"]//span[@class=\"a-list-item\"]//span[@id=\"address-ui-widgets-AddressLineOne\"]")
    private WebElement addressCardText;

    @FindBy(xpath = "//div[@class=\"a-section address-section-no-default\"]//span[@class=\"a-list-item\"]//span[@id=\"address-ui-widgets-CityStatePostalCode\"]")
    private WebElement cityPostalCodeCardText;

    @FindBy(xpath = "//div[@class=\"a-section address-section-no-default\"]//span[@class=\"a-list-item\"]//span[@id=\"address-ui-widgets-Country\"]")
    private WebElement countryCardText;

    @FindBy(xpath = "//div[@class=\"a-section address-section-no-default\"]//span[@class=\"a-list-item\"]//span[@id=\"address-ui-widgets-PhoneNumber\"]")
    private WebElement phoneNumberCardText;

    @FindBy(xpath = "//div[contains(text(),\"Please enter a name.\")]")
    private WebElement emptyNameFieldError;

    @FindBy(xpath = "//div[contains(text(),\"Please enter an address.\")]")
    private WebElement emptyStreetAddressFieldError;

    @FindBy(xpath = "//div[@class=\"a-box a-alert-inline a-alert-inline-error\"]//div[contains(text(),\"Please enter a city name.\")]")
    private WebElement emptyCityFieldError;

    @FindBy(xpath = "//div[@class=\"a-box a-alert-inline a-alert-inline-error\"]//div[contains(text(),\"Please enter a ZIP or postal code.\")]")
    private WebElement emptyZipCodeError;

    @FindBy(xpath = "//div[contains(text(),\"Please enter a phone number so we can call if there are any issues with delivery.\")]")
    private WebElement emptyPhoneNumberFieldError;

    @FindBy(xpath = "//div[@class=\"a-box a-alert-inline a-alert-inline-warning\"]//div[contains(text(),\"Please enter a valid ZIP or postal code.\")]")
    private WebElement invalidZipCodeFieldAlert;

    @FindBy(xpath = "//div[contains(text(),\"Please provide a valid phone number\")]")
    private WebElement invalidPhoneNumberError;

    @FindBy(xpath = "//div[@class=\"a-box a-alert-inline a-alert-inline-error\"]//i[@class=\"a-icon a-icon-alert\"]")
    private List<WebElement> alertIconsList;

    public WebElement getFullNameInputField() {return fullNameInputField;}

    public void clickAddAddressButton() {addAddressButton.click();}
    public void clickAddNewAddressButton(){addNewAddressButton.click();}
    public void clickOnCountryRegionDropdownListButton()
    {
        countryRegionDropdownListButton.click();
    }
    public void clickOnUkraineItemIntoCountryRegionDropdownList()
    {
        ukraineItemIntoCountryRegionDropdownList.click();
    }
    public void clickOnUseThisAddressButton()
    {
        useThisAddressButton.click();
    }

    public void enterFullName(String fullName)
    {
        fullNameInputField.sendKeys(fullName);
    }
    public void enterStreetAddress(String streetAddress)
    {
        streetAddressInputField.sendKeys(streetAddress);
    }
    public void enterCity(String city)
    {
        cityInputField.sendKeys(city);
    }
    public void enterZipCode(String zipCode)
    {
        zipCodeInputField.sendKeys(zipCode);
    }
    public void enterPhoneNumber(String phoneNumber)
    {
        phoneNumberInputField.sendKeys(phoneNumber);
    }

    public Boolean isNameAddedToAddressBookCorrectly(String name)
    {
        return fullNameCardText.getText().contains(name);
    }
    public Boolean isAddressAddedToAddressBookCorrectly(String address) { return addressCardText.getText().contains(address); }
    public Boolean isCountryAddedToAddressBookCorrectly(String country) { return countryCardText.getText().contains(country); }
    public Boolean isPostalCodeAddedToAddressBookCorrectly(String postCode) { return cityPostalCodeCardText.getText().contains(postCode); }
    public Boolean isPhoneNumberAddedToAddressBookCorrectly(String phoneNumber) { return phoneNumberCardText.getText().contains(phoneNumber); }
    public Boolean isCityAddedToAddressBookCorrectly(String city) { return cityPostalCodeCardText.getText().contains(city); }
    public Boolean isAnyAlertVisible()
    {
        for (WebElement webElement:alertIconsList)
        {
            if (webElement.isDisplayed())
                return true;
        }
        return false;
    }

    public Boolean isPhoneNumberValid(String phoneNumber)
    {
        for (Character symbol:phoneNumber.toCharArray()) {
            if (!Character.isDigit(symbol))
                return false;
        }
        return true;
    }

    public Boolean isProperlyErrorIsShown(String name, String street, String city, String zipCode, String phoneNumber)
    {
        if (name.isEmpty()) return emptyNameFieldError.isDisplayed();
        else if (street.isEmpty()) return emptyStreetAddressFieldError.isDisplayed();
        else if(city.isEmpty()) return emptyCityFieldError.isDisplayed();
        else if (zipCode.isEmpty()) return emptyZipCodeError.isDisplayed();
        else if (phoneNumber.isEmpty()) return emptyPhoneNumberFieldError.isDisplayed();
        else if (!isPhoneNumberValid(phoneNumber)) return invalidPhoneNumberError.isDisplayed();
        return false;
    }
}
