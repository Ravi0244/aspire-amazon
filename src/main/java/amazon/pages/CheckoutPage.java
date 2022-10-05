package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.base.TestBase;

public class CheckoutPage extends TestBase{

	@FindBy(xpath="//span[contains(text(),'Added to Cart')]")
	private WebElement addedToCart;
	
	@FindBy(xpath="//a[contains(text(),'Go to Cart')]")
	private WebElement goToCart;
	
	// Initializing the Page Objects:
			public CheckoutPage() {
				PageFactory.initElements(driver, this);
			}
	
	public Boolean verifyCheckOutPage()
	{
		waitVisibilityOf(addedToCart);
		return addedToCart.isDisplayed();
	}
	
	public void clickGoToCart()
	{
		goToCart.click();
	}
	
}
