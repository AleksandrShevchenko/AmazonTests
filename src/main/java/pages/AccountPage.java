package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage
{
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@data-card-identifier=\"SignInAndSecurity\"]")
    private WebElement loginAndSecurityButton;

    public void clickOnLoginAndSecurityButton()
    {
        loginAndSecurityButton.click();
    }
}
