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

    public void clickEditPasswordButton() {editPasswordButton.click();}

    @FindBy(xpath = "//label[@class=\"a-form-label\"][contains(text(),\"Current password\")]//..//..//input[@type=\"password\"]")
    private WebElement currentPasswordInputField;

    public void enterCurrentPassword(String currentPassword) {currentPasswordInputField.sendKeys(currentPassword);}

    @FindBy(xpath = "//label[@class=\"a-form-label\"][contains(text(),\"New password\")]//..//..//input[@type=\"password\"]")
    private WebElement newPasswordInputField;

    public void enterNewPassword(String newPassword) {newPasswordInputField.sendKeys(newPassword);}

    @FindBy(xpath = "//input[@name=\"passwordNewCheck\"]")
    private WebElement reenterNewPasswordInputField;

    public void reenterNewPassword(String newPassword) {reenterNewPasswordInputField.sendKeys(newPassword);}

    @FindBy(xpath = "//h4[@class=\"a-alert-heading\"]")
    private WebElement editPasswordInfoMessage;
    public WebElement getEditPasswordInfoMessage() {return editPasswordInfoMessage;}

    public void clickOnEditNameButton() {
        editNameButton.click();
    }

    @FindBy(xpath = "//input[@name=\"customerName\"]")
    private WebElement newNameInputField;

    public void enterNewName(String name) {
        newNameInputField.clear();
        newNameInputField.sendKeys(name);
    }

    @FindBy(xpath = "//input[@class=\"a-button-input\"]")
    private WebElement saveChangesButton;

    public void clickOnSaveChangesButton() {
        saveChangesButton.click();
    }

    @FindBy(xpath = "//form[@id=\"cnep_1a_name_form\"]//div[@class=\"a-fixed-right-grid-col a-col-left\"]//div[not(contains(span, \"Name:\"))]")
    private WebElement accountName;

    public Boolean isInformationBoxShown()
    {
            return editPasswordInfoMessage.getText().contains("There was a problem") || editPasswordInfoMessage.getText().contains("Success");
    }
}
