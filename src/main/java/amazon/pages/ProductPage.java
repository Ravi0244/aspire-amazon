package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import amazon.base.TestBase;

public class ProductPage extends TestBase{

	@FindBy(xpath="//div[@id='nav-packard-glow-loc-icon']")
	private WebElement deliverTo;
	
	@FindBy(xpath="//input[@aria-label='or enter a US zip code']")
	private WebElement usZipCode;
	
	@FindBy(xpath="(//input[@class='a-button-input' and @type='submit'])[last()]")
	private WebElement applyZipCode;
	
	@FindBy(xpath="//button[@name='glowDoneButton']")
	private WebElement done;
			
	@FindBy(xpath="//input[@id='add-to-cart-button']//following-sibling::span")
	private WebElement addToCart;
	
	public void clickOnDeliverTo()
	{
		mouseClick(deliverTo);
	}
	
	public void applyZipCode(String zipcode) throws Exception
	{
		waitPlease(5);
		//waitVisibilityOf(deliverTo);
		mouseClick(deliverTo);
		usZipCode.sendKeys(zipcode);
		applyZipCode.click();
		done.click();
	}
	
	public void clickAddToCart() throws Exception
	{
		mouseClick(addToCart);
	}
}
