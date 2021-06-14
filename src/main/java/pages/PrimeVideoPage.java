package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrimeVideoPage extends BasePage
{
    public PrimeVideoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()=\"The Boys - Season 1\"]")
    private WebElement theBoysSeriesFirstSeasonPanel;

    @FindBy(xpath = "//div[contains(text(),\"Service Area Restriction\")]")
    private WebElement serviceAreaRestrictionPopup;

    @FindBy(xpath = "//a[contains(@href,\"ref=atv_dp_watch_trailer_tv\")]")
    private WebElement watchTheTrailerButton;

    public void clickBoysSeriesFirstSeasonPanel(){theBoysSeriesFirstSeasonPanel.click();}
    public void clickWatchTheTrailerButton() {watchTheTrailerButton.click();}

    public WebElement getServiceAreaRestrictionPopup() {return serviceAreaRestrictionPopup;}

    public Boolean isServiceAreaRestrictionPopupVisible(){return serviceAreaRestrictionPopup.isDisplayed();}
}
