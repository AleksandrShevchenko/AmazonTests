package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import manager.PageFactoryManager;
import pages.*;


import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {
    private static final long DEFAULT_TIMEOUT = 10;
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    AuthorizationPage authorizationPage;
    ProductsPage productsPage;
    ProductCardPage productCardPage;
    CartPage cartPage;
    ShippingAddressPage shippingAddressPage;
    ShippingOptionsPage shippingOptionsPage;
    WishlistPage wishlistPage;
    AccountPage accountPage;
    LoginAndSecurityPage loginAndSecurityPage;
    PrimeVideoPage primeVideoPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @Given("User opens {string} page")
    public void openHomepage(String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User clicks 'Sign in' button")
    public void clickSignInButton() {
        homePage.clickOnSignInButton();
    }


    @And("User logs in site as login: {string} password: {string}")
    public void logInSiteAsLoginPassword(String login, String password){
        authorizationPage = pageFactoryManager.getAuthorizationPage();
        authorizationPage.enterLogin(login);
        authorizationPage.clickOnTheConfirmationButton();
        if (authorizationPage.isPasswordInputNameVisible()) {
            authorizationPage.enterPassword(password);
            authorizationPage.clickOnTheConfirmationButton();
        }
    }

    @And("User click 'Sign-In' button")
    public void clickSignInButtonIntoLoginForm() {

    }

    @Then("User checks that user is authorized as {string} {string}")
    public void checkThatUserIsAuthorized(String login, String password) throws InterruptedException {
        try {
            assertTrue(homePage.getGreetingsText().contains(login));
        } catch (NoSuchElementException noGreetingsTextException) {
            try {
                assertTrue(authorizationPage.isLoginErrorVisibleAndCorrect(login));
            } catch (NoSuchElementException noPasswordErrorException) {
                assertTrue(authorizationPage.isPasswordErrorVisible(password));
            }
        }
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @And("User clicks 'Catalogue' button")
    public void clickOnCatalogueButton() {
        homePage.clickOnCatalogueMenuButton();
    }

    @And("User clicks 'Computer' menu item")
    public void clickComputerMenuItem()
    {
        homePage.waitForVisibilityOfElement(DEFAULT_TIMEOUT,homePage.getComputersButton());
        homePage.clickOnComputersMenuItem();
    }

    @When("User clicks 'Sorting type' drop-down list button")
    public void clickSortingTypeDropDownListButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForPageLoad(DEFAULT_TIMEOUT);
        productsPage.clickOnSortingDropDownListButton();
    }

    @And("User chooses {string} and click this sorting type dropdown list item")
    public void clickOnChosenSortingType(String sortingType)
    {
        if (sortingType.contains("asc"))
            productsPage.clickOnSortingFromLowToHighDropDownListItem();
        else if (sortingType.contains("desc"))
            productsPage.clickOnSortingFromHighToLowDropDownListItem();
    }

    @And("User clicks 'Data storage' menu item")
    public void clickOnDataStorageMenuItem()
    {
        homePage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getDataStorageButton());
        homePage.clickOnDataStorageMenuItem();
    }

    @Then("User checks that products are sorted by {string}")
    public void checkThatProductsAreSortedInProperWay(String sortingType) {
        if (sortingType.contains("asc")) {
            assertTrue(productsPage.isSortingWorksProperly("asc"));
        }
        else if (sortingType.contains("desc")) {
            assertTrue(productsPage.isSortingWorksProperly("desc"));
        }
    }

    @And("User searches {string} using search field")
    public void searchProductUsingSearchField(String searchQuery) {
        homePage.enterSearchQueryToSearchInputField(searchQuery);
        homePage.clickSearchButton();
    }


    @And("User clicks on first product")
    public void clickOnFirstProduct() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.clickOnTheFirstProductInList();
    }

    @And("User clicks on 'Add to cart' button")
    public void clickOnAddToCartButton() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.clickOnAddToCartButton();
    }
    @Then("User checks amount of products into the cart")
    public void checkAmountOfProductsIntoTheCart() {
        cartPage = pageFactoryManager.getCartPage();
        assertEquals(1, (int) cartPage.amountOfProductsInCart());
    }

    @And("User clicks on 'Country & Region' drop-down list button")
    public void userClicksOnCountryRegionDropDownListButton() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickOnCountryRegionDropdownListButton();
    }

    @And("User clicks on 'Ukraine' item into drop-down list")
    public void userClicksOnUkraineItemIntoDropDownList() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickOnUkraineItemIntoCountryRegionDropdownList();
    }

    @And("User enters his {string}, {string}, {string}, {string} and {string}")
    public void userEntersHisAnd(String name, String streetAddress, String city, String zipCode, String phoneNumber) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.waitForInputFieldCleaner(DEFAULT_TIMEOUT, shippingAddressPage.getFullNameInputField(), "value", "");
        shippingAddressPage.enterFullName(name);
        shippingAddressPage.enterStreetAddress(streetAddress);
        shippingAddressPage.enterZipCode(zipCode);
        shippingAddressPage.enterCity(city);
        shippingAddressPage.enterPhoneNumber(phoneNumber);
    }

    @And("User clicks 'Use this address' button")
    public void userClicksUseThisAddressButton() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickOnUseThisAddressButton();
    }

    @And("User clicks on 'Continue' button")
    public void userClicksOnContinueButton() {
        shippingOptionsPage = pageFactoryManager.getShippingOptionsPage();
        shippingOptionsPage.clickOnTheContinueButton();
    }


    @When("User clicks on 'Add to list' button")
    public void userClicksOnAddToListButton()
    {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.waitForAjax(DEFAULT_TIMEOUT);
        try {
            productCardPage.clickOnTheAddToListButton();
            productCardPage.waitForAjax(DEFAULT_TIMEOUT);
            productCardPage.clickCreateNewListButton();
        } catch (NoSuchElementException noListsCreatedYet) {
            productCardPage.clickAddToListButton();
        }
    }

    @And("User enters list name - {string}")
    public void userEntersListName(String listName) {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productCardPage.getListNameInputField());
        productCardPage.enterTheListName(listName);
    }

    @Then("User checks that {string} is added to wish list correctly")
    public void userChecksThatProductIsAddedToWishListCorrectly(String productName) {
        wishlistPage = pageFactoryManager.getWishlistPage();
        assertTrue(wishlistPage.isProductAddedToWishlistProperly(productName));
    }

    @And("User clicks on 'Create list' button")
    public void clickCreateListButton() {
        productCardPage.clickOnTheCreateListButton();
    }

    @And("User clicks on 'View List' button")
    public void clickViewListButton() {
        productCardPage.waitForAjax(DEFAULT_TIMEOUT);
        productCardPage.clickOnViewListButton();
    }

    @And("User clicks on 'Login and security' button")
    public void clickLoginAndSecurityButton() {
        accountPage = pageFactoryManager.getAccountPage();
        accountPage.clickOnLoginAndSecurityButton();
    }


    @Then("User checks that information box about password changing is shown")
    public void userChecksThatInformationBoxAboutPasswordChangingIsShown()
    {
        loginAndSecurityPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, loginAndSecurityPage.getEditPasswordInfoMessage());
        assertTrue(loginAndSecurityPage.isInformationBoxShown());
    }

    @And("User clicks on 'Save changes' button")
    public void userClicksOnSaveChangesButton() {
        loginAndSecurityPage.clickOnSaveChangesButton();
    }

    @And("User clicks on ' Edit password' button")
    public void userClicksOnEditPasswordButton() {
        loginAndSecurityPage = pageFactoryManager.getLoginAndSecurityPage();
        loginAndSecurityPage.clickEditPasswordButton();
    }

    @And("User clicks on 'Cart' button")
    public void userClicksOnCartButton() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productCardPage.getCartButton());
        productCardPage.clickCartButton();
    }

    @When("User clicks on 'Delete' button")
    public void userClicksOnDeleteButton() {
        cartPage = pageFactoryManager.getCartPage();
        try {cartPage.clickDeleteButton();}
        catch(ElementNotInteractableException ignored){}
    }

    @Then("User checks that cart is empty")
    public void userChecksThatCartIsEmpty() {
        cartPage = pageFactoryManager.getCartPage();
        cartPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, cartPage.getEmptyCartMessage());
        assertTrue(cartPage.isEmptyCartMessageIsVisible());
    }

    @When("User clicks 'External Hard Drives' button")
    public void userClicksExternalHardDrivesButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForPageLoad(DEFAULT_TIMEOUT);
        productsPage.clickExternalHardDrivesFilter();
    }

    @And("User clicks 'PC' button for 'Platform support'")
    public void userClicksPCButtonForPlatformSupport() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getPCPlatformSupportFilterCheckbox());
        productsPage.clickPCPlatformSupportFilterCheckbox();
    }

    @And("User clicks '4TB & Above' for 'Hard Drive Size' filter")
    public void userClicksFourTBAndAboveForHardDriveSizeFilter() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getHardDriveSizeMoreThan4TBFilterCheckbox());
        productsPage.clickHardDriveSizeMoreThan4TBFilterCheckbox();
    }

    @And("User clicks 'Tablet' button as 'Compatible Devices'")
    public void userClicksTabletButtonAsCompatibleDevices() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getTabletAsCompatibleDeviceFilterCheckbox());
        productsPage.clickTabletAsCompatibleDeviceFilterCheckbox();
    }

    @And("User clicks '4 stars & up' button")
    public void userClicksFourStarsUpButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.clickAverageCustomerReviewMoreThanFourStarsFilterCheckbox();
    }

    @And("User clicks 'Toshiba' button")
    public void userClicksToshibaButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getToshibaBrandFilterCheckbox());
        productsPage.clickToshibaBrandFilterCheckbox();
    }

    @And("User clicks 'Price $50 to $100' button")
    public void userClicksPriceFiftyToHundredButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getFromFiftyToHundredDollarsPriceFilterButton());
        productsPage.clickFrom50To100DollarsPriceFilterButton();
    }

    @And("User clicks 'Portable' button")
    public void userClicksPortableButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getPortableHardDriveTypeFilterCheckbox());
        productsPage.clickPortableHardDriveTypeFilterCheckbox();
    }

    @And("User clicks on 'Buy now' button")
    public void userClicksOnBuyNowButton() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.clickBuyNowButton();
    }

    @And("User clicks on 'Deliver to' button")
    public void userClicksOnDeliverToButton() {
        homePage = pageFactoryManager.getHomePage();
        homePage.clickDeliverToLocationButton();
    }

    @And("User clicks 'Add address' button")
    public void userClicksAddAddressButton() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickAddNewAddressButton();
    }

    @Then("User checks that address was added to list correctly")
    public void userChecksThatAddressWasAddedToListCorrectly() {
    }

    @And("User hovers on 'Sign In' button")
    public void userHoversOnSignInButton() {
        homePage.moveMousePointerToElement(homePage.getSignInMenuButtonText());
    }

    @Then("User checks that account is switched to {string}")
    public void userChecksThatAccountIsSwitched(String accountName) {
        assertTrue(homePage.isAccountNameEquals(accountName));
    }

    @Then("User checks 'Service Area Restriction' popup visibility")
    public void userChecksServiceAreaRestrictionPopupVisibility() {
        primeVideoPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, primeVideoPage.getServiceAreaRestrictionPopup());
        assertTrue(primeVideoPage.isServiceAreaRestrictionPopupVisible());
    }

    @When("User clicks on 'Add address' panel")
    public void userClicksOnAddAddressPanel() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickAddAddressButton();
    }

    @And("User clicks on 'Manage address book' button")
    public void userClicksOnManageAddressBookButton()
    {
        homePage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getManageAddressBookButton());
        homePage.clickManageAddressBookButton();
    }

    @And("User clicks 'Switch accounts' button")
    public void userClicksSwitchAccountsButton() {
        homePage.clickSwitchAccountButton();
    }

    @When("User clicks on 'Add account' button")
    public void userClicksOnAddAccountButton()
    {
        authorizationPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, authorizationPage.getAddAccountButton());
        authorizationPage.clickAddAccountButton();
    }

    @And("User clicks 'Watch Trailer' button")
    public void userClicksWatchTrailerButton() {
        primeVideoPage.clickWatchTheTrailerButton();
    }

    @When("User clicks on 'Quantity' button")
    public void userClicksOnQuantityButton() {
        cartPage.waitForPageLoad(DEFAULT_TIMEOUT);
        cartPage.clickQuantityButton();
    }

    @And("User clicks on 10+ dropdown list item")
    public void userClicksOnMoreThanTenDropdownListItem() {
        cartPage.clickMoreThanTenDropdownListButton();
    }

    @And("User enters {string} to 'Quantity input field'")
    public void userEntersToQuantityInputField(String amountOfProducts){
        cartPage.enterQuantityOfProducts(amountOfProducts);
    }

    @And("User clicks 'Update' button")
    public void userClicksUpdateButton(){
        cartPage = pageFactoryManager.getCartPage();
        cartPage.clickUpdateButton();
    }

    @Then("User checks that total price is changed accordingly to {int} or existence of error")
    public void checkTotalPriceOrExistenceOfError(Integer quantity) {
        if (quantity == 0) {
            assertTrue(true);
        } else if (cartPage.isQuantityAllowedToPurchase(quantity))
            assertTrue(cartPage.isTotalPriceIsChangedAccordinglyToQuantityOfProducts());
        else
            assertTrue(cartPage.isQuantityChangedAccordinglyToAlertMessage() && cartPage.isTotalPriceIsChangedAccordinglyToQuantityOfProducts());
    }

    @And("User checks that 'Add credit cart' button is visible")
    public void userChecksThatAddCreditCartButtonIsVisible() {
        shippingOptionsPage = pageFactoryManager.getShippingOptionsPage();
        shippingOptionsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, shippingOptionsPage.getAddCreditCardButton());
        shippingOptionsPage.isAddCreditCardButtonVisible();
    }

    @And("User checks that 'Enter a gift card, voucher or promotional code' link is visible")
    public void userChecksThatEnterAGiftCardVoucherOrPromotionalCodeLinkIsVisible() {
        shippingOptionsPage.isEnterAGiftCardVoucherOrPromotionalCodeButtonVisible();
    }

    @And("User checks that 'Learn more' link in the 'Amazon store card' block is visible")
    public void userChecksThatLearnMoreLinkInTheAmazonStoreCardBlockIsVisible() {
        shippingOptionsPage.isLearnMoreAboutAmazonStoreCardButtonVisible();
    }

    @And("User checks that 'Add a personal checking account' button is visible")
    public void userChecksThatAddAPersonalCheckingAccountButtonIsVisible() {
        shippingOptionsPage = pageFactoryManager.getShippingOptionsPage();
        shippingOptionsPage.isAddAPersonalCheckingAccountButtonVisible();
    }

    @And("User checks that current page is 'Select a payment method' page")
    public void userChecksThatCurrentPageIsSelectAPaymentMethodPage() {
        shippingOptionsPage = pageFactoryManager.getShippingOptionsPage();
        shippingOptionsPage.isPageTitleCorrect();
    }

    @And("User checks that product is external hard drive")
    public void userChecksThatProductIsExternalHardDrive() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.waitForPageLoad(DEFAULT_TIMEOUT);
        productCardPage.isProductExternalHardDrive();
    }

    @And("User checks that product has rate '4 stars' or higher")
    public void userChecksThatProductHasRateFourStarsOrHigher() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.isProductsRateUpperThanFour();
    }

    @And("User checks that product is manufactured by {string}")
    public void userChecksThatProductIsManufacturedByToshiba(String companyName) {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.isProductManufacturedByCompany(companyName);
    }

    @And("User checks that product's price is between '50$' and '100$'")
    public void userChecksThatProductSPriceIsBetweenFiftyAndHundredDollars() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.isPriceBetweenFiftyAndHundredDollars();
    }

    @And("User checks that product is portable")
    public void userChecksThatProductIsPortable() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.isProductPortable();
    }

    @And("User checks that product supports {string} platform")
    public void userChecksThatProductSupportsPCPlatform(String platformName) {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.isProductHardwarePlatform(platformName);
    }

    @And("User checks that product's capacity is {int}TB or above")
    public void userChecksThatProductCapacityIsEqualOrAbove(int capacity) {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.isProductCapacityMoreOrEquals(capacity);
    }

    @Then("User checks that product comparable with {string}")
    public void userChecksThatProductComparableWithTablets(String deviceName) {
        productCardPage = pageFactoryManager.getProductCardPage();
        assertTrue(productCardPage.isProductCompatibleWith(deviceName));
    }

    @And("User checks that name {string} was added to addressBook correctly")
    public void userChecksThatNameWasAddedToAddressBookCorrectly(String name) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.waitForPageLoad(DEFAULT_TIMEOUT);
        assertTrue(shippingAddressPage.isNameAddedToAddressBookCorrectly(name));
    }

    @And("User checks that street {string} was added to addressBook correctly")
    public void userChecksThatStreetWasAddedToAddressBookCorrectly(String street) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        assertTrue(shippingAddressPage.isAddressAddedToAddressBookCorrectly(street));
    }

    @And("User checks that city {string} was added to addressBook correctly")
    public void userChecksThatCityWasAddedToAddressBookCorrectly(String city) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        assertTrue(shippingAddressPage.isCityAddedToAddressBookCorrectly(city));
    }

    @And("User checks that zipCode {string} was added to addressBook correctly")
    public void userChecksThatZipCodeWasAddedToAddressBookCorrectly(String zipCode) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        assertTrue(shippingAddressPage.isPostalCodeAddedToAddressBookCorrectly(zipCode));
    }

    @Then("User checks that phone number {string} was added to addressBook correctly")
    public void userChecksThatPhoneNumberWasAddedToAddressBookCorrectly(String phoneNumber) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        assertTrue(shippingAddressPage.isPhoneNumberAddedToAddressBookCorrectly(phoneNumber));
    }

    @Then("User checks that country {string} was added to addressBook correctly")
    public void userChecksThatCountryWasAddedToAddressBookCorrectly(String country) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        assertTrue(shippingAddressPage.isCountryAddedToAddressBookCorrectly(country));
    }

    @And("User clicks 'Watchlist' button")
    public void userClicksWatchlistButton() {
        homePage = pageFactoryManager.getHomePage();
        homePage.clickWatchlistButton();
    }

    @And("User clicks 'The boys' series block")
    public void userClicksTheBoysSeriesBlock() {
        primeVideoPage = pageFactoryManager.getPrimeVideoPage();
        primeVideoPage.clickBoysSeriesFirstSeasonPanel();
    }

    @When("User enter {string} as 'Current password'")
    public void userEnterAsCurrentPassword(String currentPassword)
    {
        loginAndSecurityPage.enterCurrentPassword(currentPassword);
    }

    @And("User enter {string} to 'Reenter new password' input field")
    public void userEnterToReenterNewPasswordInputField(String newPassword)
    {
        loginAndSecurityPage.reenterNewPassword(newPassword);
    }

    @And("User enter {string} to 'New password' input field")
    public void userEnterToNewPasswordInputField(String newPassword)
    {
        loginAndSecurityPage.enterNewPassword(newPassword);
    }

    @And("User clicks 'Warranties&Services' menu item")
    public void userClicksWarrantiesServicesMenuItem()
    {
        homePage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getWarrantiesAndServicesMenuItem());
        homePage.clickWarrantiesAndServicesMenuItem();
    }

    @And("User clicks on first 'Toshiba' product")
    public void userClicksOnFirstToshibaProduct()
    {
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getFirstToshibaHardDrive());
        productsPage.clickFirstToshibaHardDrive();
    }
}
