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

    @FindBy(xpath = "//span[@id=\"profile-list-name\"]")
    private WebElement listHeader;

    @FindBy(xpath = "//span[@class=\"a-declarative\"]/a[contains(text(),\"Add comment, quantity & priority\")]")
    private WebElement addCommentQuantityAndPriorityButton;

    @FindBy(xpath = "//div[@class=\"a-popover-wrapper\"]")
    private WebElement addCommentQuantityAndPriorityPopup;

    @FindBy(xpath = "//textarea[@name=\"itemComment\"]")
    private WebElement commentInputField;

    @FindBy(xpath = "//span[@class=\"g-comment-quote a-text-quote\"]/text()")
    private WebElement commentTextField;

    @FindBy(xpath = "//span[@id=\"WLNOTES_save-announce\"]")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class=\"a-row\"]//span[@class=\"a-button-text a-declarative\"]")
    private WebElement priorityDropdownList;

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[text() = \"low\"]")
    private WebElement lowPriorityDropdownListItem;

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[contains(text(),\"lowest\")]")
    private WebElement lowestPriorityDropdownListItem;

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[contains(text(),\"medium\")]")
    private WebElement mediumPriorityDropdownListItem;

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[text() = \"high\"]")
    private WebElement highPriorityDropdownListItem;

    @FindBy(xpath = "//div[@id=\"a-popover-7\"]//a[contains(text(),\"highest\")]")
    private WebElement highestPriorityDropdownListItem;

    @FindBy(xpath = "//span[@class=\"a-size-small dropdown-priority item-priority-medium\"]")
    private WebElement priorityRank;

    @FindBy(xpath = "//div[contains(@class,\"g-span7when-wide\")]//a[contains(@title, \"QNAP\")]")
    private WebElement wishListItem;

    public Boolean isProductAddedToWishlistProperly(String searchQuery) { return itemIntoWishlist.getText().contains(searchQuery); }

/*
    public Boolean isListHeaderIsCorrect(String listName) {
        return listHeader.getText() == listName;
    }
    public void clickOnAddCommentQuantityAndPriorityButton() {
        addCommentQuantityAndPriorityButton.click();
    }
    public WebElement getAddCommentQuantityAndPriorityPopup() {
        return addCommentQuantityAndPriorityPopup;
    }
    public void enterComment(String comment) {
        commentInputField.sendKeys(comment);
    }
    public Boolean isCommentWrittenProperly(String comment) {
        return commentTextField.getText() == comment;
    }
    public void clickOnSaveButton() {
        saveButton.click();
    }
    public void clickOnPriorityDropdownList() {
        priorityDropdownList.click();
    }
    public WebElement getLowPriorityDropdownListItem(){return lowPriorityDropdownListItem;}
    public WebElement getLowestPriorityDropdownListItem(){return lowestPriorityDropdownListItem;}
    public WebElement getMediumPriorityDropdownListItem(){return mediumPriorityDropdownListItem;}
    public WebElement getHighPriorityDropdownListItem(){return highPriorityDropdownListItem;}
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

        public Boolean isPriorityRankCorrect(String priority) { return priorityRank.getText().toLowerCase() == priority.toLowerCase(); }
    public void clickOnWishListItem() { wishListItem.click(); }


 */
}
