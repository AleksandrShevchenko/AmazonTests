package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginAndSecurityPage extends BasePage
{
    public LoginAndSecurityPage(WebDriver driver) {
    super(driver);
}

    @FindBy(xpath = "//input[@id=\"auth-cnep-edit-name-button\"]")
    private WebElement editNameButton;

    @FindBy(xpath = "//input[@id=\"auth-cnep-edit-password-button\"]")
    private WebElement editPasswordButton;

    @FindBy(xpath = "//label[@class=\"a-form-label\"][contains(text(),\"Current password\")]//..//..//input[@type=\"password\"]")
    private WebElement currentPasswordInputField;

    @FindBy(xpath = "//label[@class=\"a-form-label\"][contains(text(),\"New password\")]//..//..//input[@type=\"password\"]")
    private WebElement newPasswordInputField;

    @FindBy(xpath = "//input[@name=\"passwordNewCheck\"]")
    private WebElement reenterNewPasswordInputField;

    @FindBy(xpath = "//h4[@class=\"a-alert-heading\"]")
    private WebElement editPasswordInfoMessage;

    @FindBy(xpath = "//input[@name=\"customerName\"]")
    private WebElement newNameInputField;

    @FindBy(xpath = "//span[contains(text(),\"Save changes\")]//..")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//form[@id=\"cnep_1a_name_form\"]//div[@class=\"a-fixed-right-grid-col a-col-left\"]//div[not(contains(span, \"Name:\"))]")
    private WebElement accountName;

    public WebElement getEditPasswordInfoMessage() {return editPasswordInfoMessage;}

    public void clickEditPasswordButton() {editPasswordButton.click();}
    public void clickOnSaveChangesButton() {
        saveChangesButton.click();
    }

    public void enterCurrentPassword(String currentPassword) {currentPasswordInputField.sendKeys(currentPassword);}
    public void enterNewPassword(String newPassword) {newPasswordInputField.sendKeys(newPassword);}
    public void reenterNewPassword(String newPassword) {reenterNewPasswordInputField.sendKeys(newPassword);}

    public Boolean isInformationBoxShown(String expectedStatus)
    {
        if (expectedStatus.equals("true")) {
            waitForVisibilityOfElement(10, editPasswordInfoMessage);
            return editPasswordInfoMessage.getText().contains("Success");
        }
        else
            return editPasswordInfoMessage.getText().contains("There was a problem");
    }
}
