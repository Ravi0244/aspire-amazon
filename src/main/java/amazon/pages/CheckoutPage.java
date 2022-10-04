package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import amazon.base.TestBase;

public class CheckoutPage extends TestBase{

	@FindBy(xpath="//span[contains(text(),'Added to Cart')]")
	private WebElement addedToCart;
	
	@FindBy(xpath="//a[contains(text(),'Go to Cart')]")
	private WebElement goToCart;
	
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
