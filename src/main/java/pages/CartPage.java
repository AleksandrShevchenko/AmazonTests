package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.lang.Integer.parseInt;

public class CartPage extends BasePage
{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@class=\"a-unordered-list a-nostyle a-vertical a-spacing-mini sc-info-block\"]")
    private List<WebElement> productsInCart;

    @FindBy(xpath = "//span[@class=\"a-button a-button-dropdown quantity\"]")
    private WebElement quantityButton;

    @FindBy(xpath = "//a[contains(text(), '10+')]")
    private WebElement moreThanTenButton;

    @FindBy(xpath = "//input[@name=\"quantityBox\"]")
    private WebElement quantityInputField;
    public WebElement getQuantityInputField(){return quantityInputField;}

    @FindBy(xpath = "//span[@class=\"a-button a-button-primary a-button-small sc-update-link\"]")
    private WebElement updateButton;

    @FindBy(xpath = "//div[contains(@class,\"sc-subtotal-buybox\")]//span[contains(text(),\"$\")]")
    private WebElement totalPrice;

    @FindBy(xpath = "//span[@class=\"a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold\"]")
    private WebElement priceOfOneProduct;

    @FindBy(xpath = "//span[contains(text(),\"This seller has only\")]")
    private WebElement quantityAlertMessage;
    public WebElement getQuantityAlertMessage() {return quantityAlertMessage;}

    @FindBy(xpath = "//input[@value=\"Delete\"]")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[contains(text(),\"Your Amazon Cart is empty\")]")
    private WebElement emptyCartMessage;
    public WebElement getEmptyCartMessage(){return emptyCartMessage;}

    public void clickDeleteButton()
    {
        try {
            deleteButton.click();
        }
        catch (NoSuchElementException noDeleteButtonOnThePage) {}

    }
    public Boolean isEmptyCartMessageIsVisible()
    {
        return emptyCartMessage.isDisplayed();
    }

    public Boolean isAlertMessageIsVisible()
    {
        return quantityAlertMessage.isDisplayed();
    }

    public Boolean isQuantityAllowedToPurchase(Integer quantity)
    {
        if (quantity < 0) {quantity *= -1;}
        return quantity == parseInt(quantityInputField.getAttribute("value"));
    }

    public Boolean isQuantityChangedAccordinglyToAlertMessage()
    {
        waitForVisibilityOfElement(10,quantityAlertMessage);
        String sellerAmountOfProducts = "";
        for (int i = 0; i < quantityAlertMessage.getText().toCharArray().length; i++)
        {
            if (Character.isDigit(quantityAlertMessage.getText().toCharArray()[i]))
            {
                sellerAmountOfProducts += quantityAlertMessage.getText().toCharArray()[i];
            }
        }
        return parseInt(quantityInputField.getAttribute("value")) == parseInt(sellerAmountOfProducts);
    }

    public Boolean isTotalPriceIsChangedAccordinglyToQuantityOfProducts()
    {
        String price = "";
        for (int i = 0; i < priceOfOneProduct.getText().toCharArray().length; i++)
        {
            if (Character.isDigit(priceOfOneProduct.getText().toCharArray()[i]))
            {
                price += priceOfOneProduct.getText().toCharArray()[i];
            }
        }
        String totPrice = "";
        for (int i = 0; i < totalPrice.getText().toCharArray().length; i++)
        {
            if (Character.isDigit(totalPrice.getText().toCharArray()[i]))
            {
                totPrice += totalPrice.getText().toCharArray()[i];
            }
        }
        return parseInt(totPrice) == parseInt(price) * parseInt(quantityInputField.getAttribute("value"));
    }
    public void clickUpdateButton() {updateButton.click();}

    public void enterQuantityOfProducts(String quantity) {quantityInputField.sendKeys(quantity);}

    public void clickMoreThanTenDropdownListButton(){moreThanTenButton.click();}

    public void clickQuantityButton()
    {
        quantityButton.click();
    }
    public Integer amountOfProductsInCart(){return productsInCart.size();}
}
