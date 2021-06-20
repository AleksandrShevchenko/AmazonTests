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
    public void logInSiteAsLoginPassword(String login, String password) {
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
    public void checkThatUserIsAuthorized(String login, String password) {
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
    public void clickComputerMenuItem() {
        homePage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getComputersButton());
        homePage.clickOnComputersMenuItem();
    }

    @When("User clicks 'Sorting type' drop-down list button")
    public void clickSortingTypeDropDownListButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForPageLoad(DEFAULT_TIMEOUT);
        productsPage.clickOnSortingDropDownListButton();
    }

    @And("User chooses {string} and click this sorting type dropdown list item")
    public void clickOnChosenSortingType(String sortingType) {
        if (sortingType.contains("asc"))
            productsPage.clickOnSortingFromLowToHighDropDownListItem();
        else if (sortingType.contains("desc"))
            productsPage.clickOnSortingFromHighToLowDropDownListItem();
    }

    @And("User clicks 'Data storage' menu item")
    public void clickOnDataStorageMenuItem() {
        homePage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getDataStorageButton());
        homePage.clickOnDataStorageMenuItem();
    }

    @Then("User checks that products are sorted by {string}")
    public void checkThatProductsAreSortedInProperWay(String sortingType) {
        if (sortingType.contains("asc")) {
            assertTrue(productsPage.isSortingWorksProperly("asc"));
        } else if (sortingType.contains("desc")) {
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
    public void clickOnCountryRegionDropDownListButton() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickOnCountryRegionDropdownListButton();
    }

    @And("User clicks on 'Ukraine' item into drop-down list")
    public void clickOnUkraineItemIntoDropDownList() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickOnUkraineItemIntoCountryRegionDropdownList();
    }

    @And("User enters his {string}, {string}, {string}, {string} and {string}")
    public void enterHisNameStreetCityZipCodeAndPhoneNumber(String name, String streetAddress, String city, String zipCode, String phoneNumber) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.waitForInputFieldCleaner(DEFAULT_TIMEOUT, shippingAddressPage.getFullNameInputField(), "value", "");
        shippingAddressPage.enterFullName(name);
        shippingAddressPage.enterStreetAddress(streetAddress);
        shippingAddressPage.enterZipCode(zipCode);
        shippingAddressPage.enterCity(city);
        shippingAddressPage.enterPhoneNumber(phoneNumber);
    }

    @And("User clicks 'Use this address' button")
    public void clickUseThisAddressButton() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickOnUseThisAddressButton();
    }

    @And("User clicks on 'Continue' button")
    public void clickOnContinueButton() {
        shippingOptionsPage = pageFactoryManager.getShippingOptionsPage();
        shippingOptionsPage.clickOnTheContinueButton();
    }


    @When("User clicks on 'Add to list' button")
    public void clickOnAddToListButton() {
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
    public void enterListName(String listName) {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productCardPage.getListNameInputField());
        productCardPage.enterTheListName(listName);
    }

    @Then("User checks that {string} is added to wish list correctly")
    public void checkThatProductIsAddedToWishListCorrectly(String productName) {
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


    @Then("User checks that information box about password changing is {string}")
    public void checkThatInformationBoxAboutPasswordChangingIsShown(String expectedStatus) {
        assertTrue(loginAndSecurityPage.isInformationBoxShown(expectedStatus));
    }

    @And("User clicks on 'Save changes' button")
    public void clickOnSaveChangesButton() {
        loginAndSecurityPage.clickOnSaveChangesButton();
    }

    @And("User clicks on ' Edit password' button")
    public void clickOnEditPasswordButton() {
        loginAndSecurityPage = pageFactoryManager.getLoginAndSecurityPage();
        loginAndSecurityPage.clickEditPasswordButton();
    }

    @And("User clicks on 'Cart' button")
    public void clickOnCartButton() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productCardPage.getCartButton());
        productCardPage.clickCartButton();
    }

    @When("User clicks on 'Delete' button")
    public void clickOnDeleteButton() {
        cartPage = pageFactoryManager.getCartPage();
        try {
            cartPage.clickDeleteButton();
        } catch (ElementNotInteractableException ignored) {
        }
    }

    @Then("User checks that cart is empty")
    public void checkThatCartIsEmpty() {
        cartPage = pageFactoryManager.getCartPage();
        cartPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, cartPage.getEmptyCartMessage());
        assertTrue(cartPage.isEmptyCartMessageIsVisible());
    }

    @When("User clicks 'External Hard Drives' button")
    public void clickExternalHardDrivesButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForPageLoad(DEFAULT_TIMEOUT);
        productsPage.clickExternalHardDrivesFilter();
    }

    @And("User clicks 'PC' button for 'Platform support'")
    public void clickPCButtonForPlatformSupport() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getPCPlatformSupportFilterCheckbox());
        productsPage.clickPCPlatformSupportFilterCheckbox();
    }

    @And("User clicks '4TB & Above' for 'Hard Drive Size' filter")
    public void clickFourTBAndAboveForHardDriveSizeFilter() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getHardDriveSizeMoreThan4TBFilterCheckbox());
        productsPage.clickHardDriveSizeMoreThan4TBFilterCheckbox();
    }

    @And("User clicks 'Tablet' button as 'Compatible Devices'")
    public void clickTabletButtonAsCompatibleDevices() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getTabletAsCompatibleDeviceFilterCheckbox());
        productsPage.clickTabletAsCompatibleDeviceFilterCheckbox();
    }

    @And("User clicks '4 stars & up' button")
    public void clickFourStarsUpButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.clickAverageCustomerReviewMoreThanFourStarsFilterCheckbox();
    }

    @And("User clicks 'Toshiba' button")
    public void clickToshibaButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getToshibaBrandFilterCheckbox());
        productsPage.clickToshibaBrandFilterCheckbox();
    }

    @And("User clicks 'Price $50 to $100' button")
    public void clickPriceFiftyToHundredButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getFromFiftyToHundredDollarsPriceFilterButton());
        productsPage.clickFrom50To100DollarsPriceFilterButton();
    }

    @And("User clicks 'Portable' button")
    public void clickPortableButton() {
        productsPage = pageFactoryManager.getProductsPage();
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getPortableHardDriveTypeFilterCheckbox());
        productsPage.clickPortableHardDriveTypeFilterCheckbox();
    }

    @And("User clicks on 'Buy now' button")
    public void clickOnBuyNowButton() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.clickBuyNowButton();
    }

    @And("User clicks on 'Deliver to' button")
    public void clickOnDeliverToButton() {
        homePage = pageFactoryManager.getHomePage();
        homePage.clickDeliverToLocationButton();
    }

    @And("User clicks 'Add address' button")
    public void clickAddAddressButton() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickAddNewAddressButton();
    }

    @Then("User checks that address was added to list correctly")
    public void checkThatAddressWasAddedToListCorrectly() {
    }

    @And("User hovers on 'Sign In' button")
    public void hoverOnSignInButton() {
        homePage.moveMousePointerToElement(homePage.getSignInMenuButtonText());
    }

    @Then("User checks that account is switched to {string}")
    public void checkThatAccountIsSwitched(String accountName) {
        assertTrue(homePage.isAccountNameEquals(accountName));
    }

    @Then("User checks 'Service Area Restriction' popup visibility")
    public void checkServiceAreaRestrictionPopupVisibility() {
        primeVideoPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, primeVideoPage.getServiceAreaRestrictionPopup());
        assertTrue(primeVideoPage.isServiceAreaRestrictionPopupVisible());
    }

    @When("User clicks on 'Add address' panel")
    public void clickOnAddAddressPanel() {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        shippingAddressPage.clickAddAddressButton();
    }

    @And("User clicks on 'Manage address book' button")
    public void clickOnManageAddressBookButton() {
        homePage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getManageAddressBookButton());
        homePage.clickManageAddressBookButton();
    }

    @And("User clicks 'Switch accounts' button")
    public void clickSwitchAccountsButton() {
        homePage.clickSwitchAccountButton();
    }

    @When("User clicks on 'Add account' button")
    public void clickOnAddAccountButton() {
        authorizationPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, authorizationPage.getAddAccountButton());
        authorizationPage.clickAddAccountButton();
    }

    @And("User clicks 'Watch Trailer' button")
    public void clickWatchTrailerButton() {
        primeVideoPage.clickWatchTheTrailerButton();
    }

    @When("User clicks on 'Quantity' button")
    public void clickOnQuantityButton() {
        cartPage.waitForPageLoad(DEFAULT_TIMEOUT);
        cartPage.clickQuantityButton();
    }

    @And("User clicks on 10+ dropdown list item")
    public void clickOnMoreThanTenDropdownListItem() {
        cartPage.clickMoreThanTenDropdownListButton();
    }

    @And("User enters {string} to 'Quantity input field'")
    public void enterAmountOfProductsToQuantityInputField(String amountOfProducts) {
        cartPage.enterQuantityOfProducts(amountOfProducts);
    }

    @And("User clicks 'Update' button")
    public void clickUpdateButton() {
        cartPage = pageFactoryManager.getCartPage();
        cartPage.clickUpdateButton();
    }

    @Then("User checks that total price is changed accordingly to {int} or existence of error")
    public void checkTotalPriceOrExistenceOfError(Integer quantity) {
        if (quantity != 0) {
            if (quantity > 9 || quantity < -9)
                cartPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, cartPage.getFilledQuantityInputField());
            else
                cartPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, cartPage.getQuantityBetweenOneAndNineButton());
            assertTrue(cartPage.isTotalPriceIsChangedAccordinglyToQuantityOfProducts(quantity));
        }
    }

    @And("User checks that 'Add credit cart' button is visible")
    public void checkThatAddCreditCartButtonIsVisible() {
        shippingOptionsPage = pageFactoryManager.getShippingOptionsPage();
        shippingOptionsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, shippingOptionsPage.getAddCreditCardButton());
        assertTrue(shippingOptionsPage.isAddCreditCardButtonVisible());
    }

    @And("User checks that 'Enter a gift card, voucher or promotional code' link is visible")
    public void checkThatEnterAGiftCardVoucherOrPromotionalCodeLinkIsVisible() {
        assertTrue(shippingOptionsPage.isEnterAGiftCardVoucherOrPromotionalCodeButtonVisible());
    }

    @And("User checks that 'Learn more' link in the 'Amazon store card' block is visible")
    public void checkThatLearnMoreLinkInTheAmazonStoreCardBlockIsVisible() {
        assertTrue(shippingOptionsPage.isLearnMoreAboutAmazonStoreCardButtonVisible());
    }

    @And("User checks that 'Add a personal checking account' button is visible")
    public void checkThatAddAPersonalCheckingAccountButtonIsVisible() {
        shippingOptionsPage = pageFactoryManager.getShippingOptionsPage();
        assertTrue(shippingOptionsPage.isAddAPersonalCheckingAccountButtonVisible());
    }

    @And("User checks that current page is 'Select a payment method' page")
    public void checkThatCurrentPageIsSelectAPaymentMethodPage() {
        shippingOptionsPage = pageFactoryManager.getShippingOptionsPage();
        shippingOptionsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, shippingOptionsPage.getPageHeader());
        assertTrue(shippingOptionsPage.isPageTitleCorrect());
    }

    @And("User checks that product is external hard drive")
    public void checkThatProductIsExternalHardDrive() {
        productCardPage = pageFactoryManager.getProductCardPage();
        productCardPage.waitForPageLoad(DEFAULT_TIMEOUT);
        assertTrue(productCardPage.isProductExternalHardDrive());
    }

    @And("User checks that product has rate '4 stars' or higher")
    public void checkThatProductHasRateFourStarsOrHigher() {
        productCardPage = pageFactoryManager.getProductCardPage();
        assertTrue(productCardPage.isProductsRateUpperThanFour());
    }

    @And("User checks that product is manufactured by {string}")
    public void checkThatProductIsManufacturedByToshiba(String companyName) {
        productCardPage = pageFactoryManager.getProductCardPage();
        assertTrue(productCardPage.isProductManufacturedByCompany(companyName));
    }

    @And("User checks that product's price is between '50$' and '100$'")
    public void checkThatProductSPriceIsBetweenFiftyAndHundredDollars() {
        productCardPage = pageFactoryManager.getProductCardPage();
        assertTrue(productCardPage.isPriceBetweenFiftyAndHundredDollars());
    }

    @And("User checks that product is portable")
    public void checkThatProductIsPortable() {
        productCardPage = pageFactoryManager.getProductCardPage();
        assertTrue(productCardPage.isProductPortable());
    }

    @And("User checks that product supports {string} platform")
    public void checkThatProductSupportsPCPlatform(String platformName) {
        productCardPage = pageFactoryManager.getProductCardPage();
        assertTrue(productCardPage.isProductHardwarePlatform(platformName));
    }

    @And("User checks that product's capacity is {int}TB or above")
    public void checkThatProductCapacityIsEqualOrAbove(int capacity) {
        productCardPage = pageFactoryManager.getProductCardPage();
        assertTrue(productCardPage.isProductCapacityMoreOrEquals(capacity));
    }

    @Then("User checks that product comparable with {string}")
    public void checkThatProductComparableWithTablets(String deviceName) {
        productCardPage = pageFactoryManager.getProductCardPage();
        assertTrue(productCardPage.isProductCompatibleWith(deviceName));
    }

    @And("User checks that name {string} was added to addressBook correctly")
    public void checkThatNameWasAddedToAddressBookCorrectly(String name) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        if (!shippingAddressPage.isAnyAlertVisible()) {
            shippingAddressPage.waitForPageLoad(DEFAULT_TIMEOUT);
            assertTrue(shippingAddressPage.isNameAddedToAddressBookCorrectly(name));
        }
    }

    @And("User checks that street {string} was added to addressBook correctly")
    public void checkThatStreetWasAddedToAddressBookCorrectly(String street) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        if (!shippingAddressPage.isAnyAlertVisible())
            assertTrue(shippingAddressPage.isAddressAddedToAddressBookCorrectly(street));
    }

    @And("User checks that city {string} was added to addressBook correctly")
    public void checkThatCityWasAddedToAddressBookCorrectly(String city) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        if (!shippingAddressPage.isAnyAlertVisible())
            assertTrue(shippingAddressPage.isCityAddedToAddressBookCorrectly(city));
    }

    @And("User checks that zipCode {string} was added to addressBook correctly")
    public void checkThatZipCodeWasAddedToAddressBookCorrectly(String zipCode) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        if (!shippingAddressPage.isAnyAlertVisible())
            assertTrue(shippingAddressPage.isPostalCodeAddedToAddressBookCorrectly(zipCode));
    }

    @Then("User checks that phone number {string} was added to addressBook correctly")
    public void checkThatPhoneNumberWasAddedToAddressBookCorrectly(String phoneNumber) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        if (!shippingAddressPage.isAnyAlertVisible())
            assertTrue(shippingAddressPage.isPhoneNumberAddedToAddressBookCorrectly(phoneNumber));
    }

    @Then("User checks that country {string} was added to addressBook correctly")
    public void checkThatCountryWasAddedToAddressBookCorrectly(String country) {
        shippingAddressPage = pageFactoryManager.getShippingAddressPage();
        if (!shippingAddressPage.isAnyAlertVisible())
         assertTrue(shippingAddressPage.isCountryAddedToAddressBookCorrectly(country));
    }

    @And("User clicks 'Watchlist' button")
    public void clickWatchlistButton() {
        homePage = pageFactoryManager.getHomePage();
        homePage.clickWatchlistButton();
    }

    @And("User clicks 'The boys' series block")
    public void clickTheBoysSeriesBlock() {
        primeVideoPage = pageFactoryManager.getPrimeVideoPage();
        primeVideoPage.clickBoysSeriesFirstSeasonPanel();
    }

    @When("User enter {string} as 'Current password'")
    public void enterAsCurrentPassword(String currentPassword) {
        loginAndSecurityPage.enterCurrentPassword(currentPassword);
    }

    @And("User enter {string} to 'Reenter new password' input field")
    public void enterToReenterNewPasswordInputField(String newPassword) {
        loginAndSecurityPage.reenterNewPassword(newPassword);
    }

    @And("User enter {string} to 'New password' input field")
    public void enterToNewPasswordInputField(String newPassword) {
        loginAndSecurityPage.enterNewPassword(newPassword);
    }

    @And("User clicks 'Warranties&Services' menu item")
    public void clickWarrantiesServicesMenuItem() {
        homePage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getWarrantiesAndServicesMenuItem());
        homePage.clickWarrantiesAndServicesMenuItem();
    }

    @And("User clicks on first 'Toshiba' product")
    public void clickOnFirstToshibaProduct() {
        productsPage.waitForVisibilityOfElement(DEFAULT_TIMEOUT, productsPage.getFirstToshibaHardDrive());
        productsPage.clickFirstToshibaHardDrive();
    }

    @And("User checks that address is added to addressBook with {string}, {string}, {string}, {string} and {string}")
    public void checkThatAddressIsAddedToAddressBookWithAnd(String name, String street, String city, String zipCode, String phoneNumber)
    {
        if (shippingAddressPage.isAnyAlertVisible())
        {
            assertTrue(shippingAddressPage.isProperlyErrorIsShown(name, street, city, zipCode,phoneNumber));
        }
    }
}
