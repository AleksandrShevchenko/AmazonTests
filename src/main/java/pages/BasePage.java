package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePage {
    WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[contains(@href, \"warranties_and_services\")]")
    private WebElement warrantiesAndServicesMenuItem;
    public WebElement getWarrantiesAndServicesMenuItem() {return warrantiesAndServicesMenuItem;}

    public void clickWarrantiesAndServicesMenuItem() {warrantiesAndServicesMenuItem.click();}
    @FindBy(xpath = "//span[@class=\"nav-line-2 \"]")
    protected WebElement signInMenuButton;

    public void waitForInputFieldCleaner(long timeToWait, WebElement webElement, String value, String expValue)
    {
        new WebDriverWait(driver, timeToWait)
                .until(ExpectedConditions.attributeToBe(webElement, value, expValue));
    }
    public void waitForVisibilityOfElement(long timeToWait, WebElement webElement) {
        new WebDriverWait(driver, timeToWait)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForPageLoad(long timeToWait) {
        new WebDriverWait(driver, timeToWait)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @FindBy(xpath = "//div[@class=\"nav-sprite\"]/div[@class=\"nav-left\"]")
    private WebElement catalogueMenuButton;

    @FindBy(xpath = "//div[text()=\"Computers\"]")
    private WebElement computersButton;
    public WebElement getComputersButton() {return computersButton;}

    @FindBy(xpath = "//a[contains(text(),\"Data Storage\")]")
    private WebElement dataStorageButton;
    public WebElement getDataStorageButton() {return dataStorageButton;}

    @FindBy(xpath = "//input[@name=\"field-keywords\"]")
    private WebElement searchInputField;

    @FindBy(xpath = "//span[@class=\"nav-line-1 nav-progressive-content\"][contains(text(),\"Hello\")]")
    private WebElement signInMenuButtonText;

    public WebElement getSignInMenuButtonText() {
        return signInMenuButtonText;
    }

    public void clickOnCatalogueMenuButton() {
        catalogueMenuButton.click();
    }

    public void clickOnSignInButton() {
        signInMenuButton.click();
    }

    public void clickOnComputersMenuItem() {
        computersButton.click();
    }

    public void clickOnDataStorageMenuItem() {
        dataStorageButton.click();
    }

    public void enterSearchQueryToSearchInputField(String searchQuery) {
        searchInputField.sendKeys(searchQuery);
    }

    @FindBy(xpath = "//input[@id=\"nav-search-submit-button\"]")
    private WebElement searchButton;

    public void clickSearchButton() {
        searchButton.click();
    }

    public void moveMousePointerToElement(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).build().perform();
    }

    public void hoverOnSignInButton() {
        moveMousePointerToElement(signInMenuButton);
    }

    @FindBy(xpath = "//div[@class=\"nav-panel\"]//span[@class=\"nav-text\"]")
    private WebElement shoppingListItem;

    public void ClickOnShoppingListItem(String listName) {
        shoppingListItem.click();
    }

    @FindBy(xpath = "//span[@class=\"nav-text\"][text()=\"list1\"]")
    private WebElement wishListItemInSignInMenu;

    public void clickOnWishListItemInSignInMenu() {
        wishListItemInSignInMenu.click();
    }

    @FindBy(xpath = "//span[contains(text(),\"Sign Out\")]")
    private WebElement signInMenuSignOutButton;

    public void signOut() {
        signInMenuSignOutButton.click();
    }

    @FindBy(xpath = "//div[@class=\"nav-line-1-container\"]/span[@class=\"nav-line-1 nav-progressive-content\"]")
    private WebElement greetingsText;

    public WebElement getGreetingsBlock() {
        return greetingsText;
    }

    public String getGreetingsText() {
        return greetingsText.getText();
    }

    public void waitForAjax(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return window.jQuery != undefined && jQuery.active == 0;"));
    }
@FindBy(xpath = "//span[@data-action=\"GLUXManageAddressLinkAction\"]")
private WebElement manageAddressBookButton;
    public WebElement getManageAddressBookButton() {return manageAddressBookButton;}

    public void clickManageAddressBookButton() {manageAddressBookButton.click();}

    @FindBy(xpath = "//a[@id=\"nav-global-location-popover-link\"]//span[@class=\"nav-line-1 nav-progressive-content\"]")
    private WebElement locationDeliverTuButton;

    public void clickDeliverToLocationButton() {locationDeliverTuButton.click();}

    @FindBy(xpath = "//span[contains(text(),\"Switch\")]")
    private WebElement switchAccountsButton;

    public void clickSwitchAccountButton() {switchAccountsButton.click();}

    public Boolean isAccountNameEquals(String name){ return greetingsText.getText().contains(name);}

    @FindBy(xpath = "//a[contains(@href, \"/gp/video/watchlist\")]")
    private WebElement watchlistButton;

    public void clickWatchlistButton() {watchlistButton.click();}


}
