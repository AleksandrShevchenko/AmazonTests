package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizationPage extends BasePage {
    private final Pattern pattern;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public AuthorizationPage(WebDriver driver) {
        super(driver);
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @FindBy(xpath = "//input[@name=\"email\"]")
    private WebElement emailAndPhoneNumberInputField;

    @FindBy(xpath = "//input[@name=\"password\"]")
    private WebElement passwordInputField;

    @FindBy(xpath = "//h4[@class=\"a-alert-heading\"]")
    private WebElement anotherPasswordErrorSecurityWarning;

    @FindBy(xpath = "//input[@class=\"a-button-input\"]")
    private WebElement confirmationButton;

    @FindBy(xpath = "//div[@id=\"auth-error-message-box\"]")
    private WebElement invalidEmailOrPhoneNumberError;

    @FindBy(xpath = "//div[contains(text(),\"Enter your password\")]")
    private WebElement emptyPasswordError;

    @FindBy(xpath = "//div[contains(text(),\"Enter your email or mobile phone number\")]")
    private WebElement emptyEmailAndPhoneNumberInputFieldError;

    @FindBy(xpath = "//div[@class=\"a-column a-span12 a-text-left\"][contains(text(),\"Add\")]")
    private WebElement addAccountToSwitchButton;

    @FindBy(xpath = "//div[@class=\"a-box-inner a-alert-container\"]//span[contains(text(),\"mobile number\")]")
    private WebElement invalidPhoneNumberError;

    @FindBy(xpath = "//div[@class=\"a-box-inner a-alert-container\"]//span[contains(text(),\"Your password is incorrect\")]")
    private WebElement invalidPasswordError;

    public WebElement getInvalidEmailOrPhoneNumberError() { return invalidEmailOrPhoneNumberError; }
    public WebElement getAddAccountButton() { return addAccountToSwitchButton; }

    public Boolean isPasswordInputNameVisible() { return passwordInputField.isDisplayed(); }
    public Boolean isLoginErrorVisibleAndCorrect(String login)
    {
        if (!validateEmail(login))
            return emptyEmailAndPhoneNumberInputFieldError.isDisplayed() || invalidEmailOrPhoneNumberError.isDisplayed();
        else
            return true;
    }
    public Boolean isPasswordErrorVisible(String password)
    {
        if (emptyPasswordError.isDisplayed())
            return emptyPasswordError.isDisplayed();
        else if (anotherPasswordErrorSecurityWarning.isDisplayed())
            return anotherPasswordErrorSecurityWarning.isDisplayed();
        else
            return invalidPasswordError.isDisplayed();
    }
    public Boolean validateEmail(final String email)
    {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void enterLogin(String login) {
        emailAndPhoneNumberInputField.sendKeys(login);
    }
    public void enterPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    public void clickOnTheConfirmationButton() {
        confirmationButton.click();
    }
    public void clickAddAccountButton() { addAccountToSwitchButton.click(); }
}
