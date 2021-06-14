package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.lang.Double.*;
import static java.lang.Integer.parseInt;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class=\"a-dropdown-prompt\"]")
    private WebElement sortingDropDownListButton;

    @FindBy(xpath = "//a[contains(text(),\"Price: Low to High\")]")
    private WebElement sortingFromLowToHighDropDownListItem;

    @FindBy(xpath = "//a[contains(text(),\"Price: High to Low\")]")
    private WebElement sortingFromHighToLowDropDownListItem;

    @FindBy(xpath = "//a[contains(text(),\"Avg. Customer Review\")]")
    private WebElement sortingTypeAvgCustomerReview;

    @FindBy(xpath = "//span[@class=\"a-price-whole\"]")
    private List<WebElement> priceListWhole;

    @FindBy(xpath = "//span[@class=\"a-price-fraction\"]")
    private List<WebElement> priceListFraction;

    @FindBy(xpath = "//div[@class=\"a-section aok-relative s-image-square-aspect\"]//img[contains(@alt,\"Toshiba\")]")
    private WebElement firstToshibaHardDrive;
    public WebElement getFirstToshibaHardDrive() {return firstToshibaHardDrive;}

    public void clickFirstToshibaHardDrive() {firstToshibaHardDrive.click();}

    @FindBy(xpath = "//span[@class=\"a-size-medium a-color-base a-text-normal\"]")
    private List<WebElement> productsList;
    public WebElement getFirstProductFromProductsList() {return productsList.get(0);}

    @FindBy(xpath = "//span[@class=\"a-size-base-plus a-color-base a-text-normal\"]")
    private List<WebElement> productsBackupList;

    public void clickOnSortingDropDownListButton() {
        sortingDropDownListButton.click();
    }

    public void clickOnSortingFromLowToHighDropDownListItem() {
        sortingFromLowToHighDropDownListItem.click();
    }

    public void clickOnSortingFromHighToLowDropDownListItem() {
        sortingFromHighToLowDropDownListItem.click();
    }


    public Boolean isSortingWorksProperly(String sortingType) {
        if (sortingType.contains("asc")) {
            for (int i = 1; i < priceListWhole.size(); i++) {
                if (parseInt(priceListWhole.get(i).getText()) < (parseInt(priceListWhole.get(0).getText())))
                    return false;
                else if (parseInt(priceListWhole.get(i).getText()) == (parseInt(priceListWhole.get(0).getText()))) {
                    if (parseInt(priceListFraction.get(i).getText()) < (parseInt(priceListFraction.get(0).getText())))
                        return false;
                }
            }
        }
        else if (sortingType.contains("desc")) {
            for (int i = 1; i < priceListWhole.size(); i++) {
                if (parseInt(priceListWhole.get(i).getText()) > (parseInt(priceListWhole.get(0).getText())))
                    return false;
                else if (parseInt(priceListWhole.get(i).getText()) == (parseInt(priceListWhole.get(0).getText()))) {
                    if (parseInt(priceListFraction.get(i).getText()) > (parseInt(priceListFraction.get(0).getText())))
                        return false;
                }
            }
        }
        return true;
    }

    public void clickOnTheFirstProductInList() {
        try {
            productsList.get(0).click();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            productsBackupList.get(0).click();
        }

    }

    @FindBy(xpath = "//ul[@class=\"a-unordered-list a-nostyle a-vertical a-spacing-medium\"]//span[contains(text(),'External Hard Drives')]")
    private WebElement externalHardDrivesFilterButton;

    public void clickExternalHardDrivesFilter() {
        externalHardDrivesFilterButton.click();
    }

    @FindBy(xpath = "//span[contains(text(),\"PC\")][@class=\"a-size-base a-color-base\"]")
    private WebElement pcPlatformSupportFilterCheckbox;
    public WebElement getPCPlatformSupportFilterCheckbox(){return pcPlatformSupportFilterCheckbox;}

    public void clickPCPlatformSupportFilterCheckbox() {
        pcPlatformSupportFilterCheckbox.click();
    }

    @FindBy(xpath = "//span[contains(text(),\"4 TB & Above\")][@class=\"a-size-base a-color-base\"]")
    private WebElement hardDriveSizeMoreThan4TBFilterCheckbox;
    public WebElement getHardDriveSizeMoreThan4TBFilterCheckbox() {return hardDriveSizeMoreThan4TBFilterCheckbox;}

    public void clickHardDriveSizeMoreThan4TBFilterCheckbox() {
        hardDriveSizeMoreThan4TBFilterCheckbox.click();
    }

    @FindBy(xpath = "//span[contains(text(),\"Tablet\")][@class=\"a-size-base a-color-base\"]")
    private WebElement tabletAsCompatibleDeviceFilterCheckbox;
    public WebElement getTabletAsCompatibleDeviceFilterCheckbox() {return tabletAsCompatibleDeviceFilterCheckbox;}

    public void clickTabletAsCompatibleDeviceFilterCheckbox() {
        tabletAsCompatibleDeviceFilterCheckbox.click();
    }

    @FindBy(xpath = "//i[@class=\"a-icon a-icon-star-medium a-star-medium-4\"]")
    private WebElement averageCustomerReviewMoreThanFourStarsFilterCheckbox;

    public void clickAverageCustomerReviewMoreThanFourStarsFilterCheckbox() {
        averageCustomerReviewMoreThanFourStarsFilterCheckbox.click();
    }

    @FindBy(xpath = "//span[@class=\"a-size-base a-color-base\"][contains(text(),\"Toshiba\")]")
    private WebElement toshibaBrandFilterCheckbox;
    public WebElement getToshibaBrandFilterCheckbox() {return toshibaBrandFilterCheckbox;}

    public void clickToshibaBrandFilterCheckbox() {
        toshibaBrandFilterCheckbox.click();
    }

    @FindBy(xpath = "//span[@class=\"a-size-base a-color-base\"][contains(text(),\"$50 to $100\")]")
    private WebElement fromFiftyToHundredDollarsPriceFilterButton;
    public WebElement getFromFiftyToHundredDollarsPriceFilterButton() {return fromFiftyToHundredDollarsPriceFilterButton;}
    public void clickFrom50To100DollarsPriceFilterButton() {
        fromFiftyToHundredDollarsPriceFilterButton.click();
    }

    @FindBy(xpath = "//span[@class=\"a-size-base a-color-base\"][contains(text(),\"Portable\")]")
    private WebElement portableHardDriveTypeFilterCheckbox;
    public WebElement getPortableHardDriveTypeFilterCheckbox() {return portableHardDriveTypeFilterCheckbox;}

    public void clickPortableHardDriveTypeFilterCheckbox() {
        portableHardDriveTypeFilterCheckbox.click();
    }

}
