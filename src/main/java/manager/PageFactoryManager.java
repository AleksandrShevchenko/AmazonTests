package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager
{
    WebDriver driver;
    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }
    public AuthorizationPage getAuthorizationPage() {
        return new AuthorizationPage(driver);
    }
    public ProductsPage getProductsPage() {
        return new ProductsPage(driver);
    }
    public ProductCardPage getProductCardPage() {return new ProductCardPage(driver);}
    public CartPage getCartPage() {return new CartPage(driver);}
    public ShippingAddressPage getShippingAddressPage() {return new ShippingAddressPage(driver);}
    public ShippingOptionsPage getShippingOptionsPage() {return new ShippingOptionsPage(driver);}
    public WishlistPage getWishlistPage() {return new WishlistPage(driver);}
    public AccountPage getAccountPage() {return new AccountPage(driver);}
    public LoginAndSecurityPage getLoginAndSecurityPage() {return new LoginAndSecurityPage(driver);}
    public PrimeVideoPage getPrimeVideoPage() {return new PrimeVideoPage(driver);}
}
