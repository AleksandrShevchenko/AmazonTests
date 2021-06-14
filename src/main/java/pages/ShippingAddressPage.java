package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    public WebElement getFullNameInputField() {return fullNameInputField;}

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

    @FindBy(xpath = "//div[@class=\"a-box first-desktop-address-tile\"]")
    private WebElement addAddressButton;

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

    public void clickOnUseThisAddressButton()
    {
        useThisAddressButton.click();
    }


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

    public Boolean isNameAddedToAddressBookCorrectly(String name)
    {
        return fullNameCardText.getText().contains(name);
    }

    public Boolean isAddressAddedToAddressBookCorrectly(String address)
    {
        return addressCardText.getText().contains(address);
    }
    public Boolean isCountryAddedToAddressBookCorrectly(String country)
    {
        return countryCardText.getText().contains(country);
    }
    public Boolean isPostalCodeAddedToAddressBookCorrectly(String postCode)
    {
        return cityPostalCodeCardText.getText().contains(postCode);
    }

    public Boolean isPhoneNumberAddedToAddressBookCorrectly(String phoneNumber)
    {
        return phoneNumberCardText.getText().contains(phoneNumber);
    }

    public Boolean isCityAddedToAddressBookCorrectly(String city)
    {
        return cityPostalCodeCardText.getText().contains(city);
    }



}
