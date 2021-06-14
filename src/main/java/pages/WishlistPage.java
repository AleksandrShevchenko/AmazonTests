package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class WishlistPage extends BasePage
{
    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[@class=\"a-size-base\"]/a[@class=\"a-link-normal\"]")
    private WebElement itemIntoWishlist;

    public Boolean isProductAddedToWishlistProperly(String searchQuery) {
        return itemIntoWishlist.getText().contains(searchQuery);
    }

    @FindBy(xpath = "//span[@id=\"profile-list-name\"]")
    private WebElement listHeader;

    public Boolean isListHeaderIsCorrect(String listName) {
        return listHeader.getText() == listName;
    }

    @FindBy(xpath = "//span[@class=\"a-declarative\"]/a[contains(text(),\"Add comment, quantity & priority\")]")
    private WebElement addCommentQuantityAndPriorityButton;

    public void clickOnAddCommentQuantityAndPriorityButton() {
        addCommentQuantityAndPriorityButton.click();
    }

    @FindBy(xpath = "//div[@class=\"a-popover-wrapper\"]")
    private WebElement addCommentQuantityAndPriorityPopup;

    public WebElement getAddCommentQuantityAndPriorityPopup() {
        return addCommentQuantityAndPriorityPopup;
    }

    @FindBy(xpath = "//textarea[@name=\"itemComment\"]")
    private WebElement commentInputField;

    public void enterComment(String comment) {
        commentInputField.sendKeys(comment);
    }

    @FindBy(xpath = "//span[@class=\"g-comment-quote a-text-quote\"]/text()")
    private WebElement commentTextField;

    public Boolean isCommentWrittenProperly(String comment) {
        return commentTextField.getText() == comment;
    }

    @FindBy(xpath = "//span[@id=\"WLNOTES_save-announce\"]")
    private WebElement saveButton;

    public void clickOnSaveButton() {
        saveButton.click();
    }

    @FindBy(xpath = "//div[@class=\"a-row\"]//span[@class=\"a-button-text a-declarative\"]")
    private WebElement priorityDropdownList;

    public void clickOnPriorityDropdownList() {
        priorityDropdownList.click();
    }

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[text() = \"low\"]")
    private WebElement lowPriorityDropdownListItem;
    public WebElement getLowPriorityDropdownListItem(){return lowPriorityDropdownListItem;}

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[contains(text(),\"lowest\")]")
    private WebElement lowestPriorityDropdownListItem;
    public WebElement getLowestPriorityDropdownListItem(){return lowestPriorityDropdownListItem;}

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[contains(text(),\"medium\")]")
    private WebElement mediumPriorityDropdownListItem;
    public WebElement getMediumPriorityDropdownListItem(){return mediumPriorityDropdownListItem;}

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[text() = \"high\"]")
    private WebElement highPriorityDropdownListItem;
    public WebElement getHighPriorityDropdownListItem(){return highPriorityDropdownListItem;}

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[contains(text(),\"highest\")]")
    private WebElement highestPriorityDropdownListItem;
    public WebElement getHighestPriorityDropdownListItem(){return highestPriorityDropdownListItem;}

    public void clickOnPriorityDropdownListItem(String priority) {
        if (priority.toLowerCase() == "highest")
            highestPriorityDropdownListItem.click();
        else if (priority.toLowerCase() == "medium")
            mediumPriorityDropdownListItem.click();
        else if (priority.toLowerCase() == "low")
            lowPriorityDropdownListItem.click();
        else if (priority.toLowerCase() == "lowest")
            lowestPriorityDropdownListItem.click();
        else
            highPriorityDropdownListItem.click();
    }

    @FindBy(xpath = "//span[@class=\"a-size-small dropdown-priority item-priority-medium\"]")
    private WebElement priorityRank;

    public Boolean isPriorityRankCorrect(String priority)
    {
        return priorityRank.getText().toLowerCase() == priority.toLowerCase();
    }
    @FindBy(xpath = "//div[contains(@class,\"g-span7when-wide\")]//a[contains(@title, \"QNAP\")]")
    private WebElement wishListItem;

    public void clickOnWishListItem()
    {
        wishListItem.click();
    }




}
