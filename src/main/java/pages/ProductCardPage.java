package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertTrue;

public class ProductCardPage extends BasePage
{
    public ProductCardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name=\"submit.add-to-cart\"]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//input[@name=\"submit.buy-now\"]")
    private WebElement buyNowButton;

    public void clickBuyNowButton() {buyNowButton.click();}

    public void clickOnAddToCartButton()
    {
        addToCartButton.click();
    }

    @FindBy(xpath = "//span[@class=\"a-button a-button-dropdown a-button-group-last\"]//input[@name=\"submit.add-to-registry.wishlist\"][@type=\"submit\"]")
    private WebElement addToListButton;
    public WebElement getAddToListButton() {return addToListButton;}

    @FindBy(xpath = "//a[@href=\"/hz/wishlist/create\"]")
    private WebElement createNewListButton;

    @FindBy(xpath = "//div[@data-toaster-type=\"LOCATION_ALERT\"]")
    private WebElement shippingDelaysPopup;
    public WebElement getShippingDelaysPopup() {return shippingDelaysPopup;}
    public void clickCreateNewListButton()
    {
        createNewListButton.click();
    }

    public void clickOnTheAddToListButton()
    {
        addToListButton.click();
    }

    @FindBy(xpath = "//div[@id=\"huc-v2-subcart-buttons-wrapper\"]//a[contains(@href,\"/gp/cart/view.html/ref=lh_cart\")]")
    private WebElement cartButton;
    public WebElement getCartButton(){return cartButton;}

    public void clickCartButton() {cartButton.click();}

    @FindBy(xpath = "//input[@name=\"submit.add-to-registry.wishlist\"]")
    private WebElement addToFirstListButton;

    public void clickAddToListButton()
    {
        addToFirstListButton.click();
    }

    @FindBy(xpath = "//span[contains(text(),\"Brand\")]//..//..//span[@class=\"a-size-base\"]")
    private WebElement productManufacturerBrandName;

    @FindBy(xpath = "//span[contains(text(),\"Compatible Devices\")]//..//..//span[@class=\"a-size-base\"]")
    private WebElement productCompatibleDevices;

    @FindBy(xpath = "//div[@class=\"a-section a-spacing-micro\"]//span[@class=\"a-size-medium a-color-price\"]")
    private WebElement productPrice;

    @FindBy(xpath = "//th[contains(text(),\"Hard Drive\")]//..//td[@class=\"a-size-base prodDetAttrValue\"]")
    private WebElement hardDriveInfo;

    @FindBy(xpath = "//label[contains(text(), \"Capacity:\")]/../span")
    private WebElement hardDriveCapacity;

@FindBy(xpath = "//th[contains(text(),\"Hardware Platform\")]//..//td[@class=\"a-size-base prodDetAttrValue\"]")
    private WebElement hardwarePlatform;

@FindBy(xpath = "//div[@class=\"centerColAlign centerColAlign-bbcxoverride\"]//i[contains(@class,\"a-icon-star\")]")
    private WebElement userReviewRateStars;

@FindBy(xpath = "//span[@class=\"ac-for-text\"]//span")
    private WebElement amazonsChoiceProductSection;

public Boolean isProductExternalHardDrive()
{
    return amazonsChoiceProductSection.getText().contains("External Hard Drives");
}
public Boolean isProductsRateUpperThanFour()
{
    return userReviewRateStars.getAttribute("class").contains("a-star-4-5");
}

public Boolean isProductManufacturedByCompany(String company)
{
    return  productManufacturerBrandName.getText().contains(company);
}

public Boolean isPriceBetweenFiftyAndHundredDollars()
{
    String priceAsText = "";
    for (int i = 0; i < productPrice.getText().length(); i++)
    {
        if (Character.isDigit(productPrice.getText().toCharArray()[i]))
            priceAsText += productPrice.getText().toCharArray()[i];
    }
    int price = parseInt(priceAsText.substring(0, priceAsText.length() - 2));
    return price >50 && price <100;
}

public Boolean isProductPortable()
{
    return hardDriveInfo.getText().contains("Portable");
}
public Boolean isProductCompatibleWith(String deviceName)
{
    return productCompatibleDevices.getText().contains(deviceName);
}

@FindBy(xpath = "//div[@class=\"a-popover a-popover-modal a-declarative\"]")
private WebElement createNewListPopup;
public WebElement getCreateNewListPopup(){return createNewListPopup;}


public Boolean isProductCapacityMoreOrEquals(Integer capacity)
{
    String capacityAsText = "";
    for (int i = 0; i < hardDriveCapacity.getText().length(); i++)
        if (Character.isDigit(hardDriveCapacity.getText().toCharArray()[i]))
            capacityAsText += hardDriveCapacity.getText().toCharArray()[i];
    return parseInt(capacityAsText) >= capacity;
}

public Boolean isProductHardwarePlatform(String platformName)
{
    return hardwarePlatform.getText().contains(platformName);
}

    @FindBy(xpath = "//div[contains(text(), \"Please enter a name for the list.\")]")
    private WebElement emptyWishlistNameAlert;

    public Boolean isEmptyWishlistNameAlertVisible()
    {
        return emptyWishlistNameAlert.isDisplayed();
    }
    @FindBy(xpath = "//input[@name=\"list-name\"]")
    private WebElement listNameInputField;
    public WebElement getListNameInputField() {return listNameInputField;}

    @FindBy(xpath = "//div[@class=\"a-popover a-popover-modal a-declarative  a-popover-modal-fixed-height pop-huc-create\"]")
    private WebElement addToYourListPopup;

    @FindBy(xpath = "//span[@data-action=\"create-list-submit\"]//input[@class=\"a-button-input a-declarative\"]")
    private WebElement createListButton;

    @FindBy(xpath = "//span[@class=\"a-button-inner\"]//a[contains(@href, \"/hz/wishlist\")]")
    private WebElement viewListButton;
    public WebElement getViewListButton() {return viewListButton;}

    public void clickOnViewListButton()
    {
        viewListButton.click();
    }
    public void clickOnTheCreateListButton()
    {
        createListButton.click();
    }
    public void enterTheListName(String listName)
    {
        if (listName.length()>0)
        {
            listNameInputField.clear();
            listNameInputField.sendKeys(listName);
        }
        else
        {
            assertTrue(isEmptyWishlistNameAlertVisible());
        }
    }


}
