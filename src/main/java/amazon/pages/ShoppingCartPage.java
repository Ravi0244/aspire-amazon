package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import amazon.base.TestBase;

public class ShoppingCartPage extends TestBase {

	@FindBy(xpath="//h1[contains(text(),'Shopping Cart')]")
	private WebElement shoppingCartHeader;
	
	@FindBy(xpath="//input[@name='proceedToRetailCheckout']")
	private WebElement proceedToCheckOut;
	
	public Boolean verifyShoppingCartHeader()
	{
		waitVisibilityOf(shoppingCartHeader);
		return shoppingCartHeader.isDisplayed();
	}
	
	public void clickOnProceedToCheckOut()
	{
		proceedToCheckOut.click();
	}
}
