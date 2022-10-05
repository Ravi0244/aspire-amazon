package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.base.TestBase;

public class ProductPage extends TestBase{

	@FindBy(xpath="//span[@id='glow-ingress-line1']")
	private WebElement deliverTo;
	
	@FindBy(xpath="//input[@aria-label='or enter a US zip code']")
	private WebElement usZipCode;
	
	@FindBy(xpath="(//input[@class='a-button-input' and @type='submit'])[last()]")
	private WebElement applyZipCode;
	
	@FindBy(xpath="//button[@name='glowDoneButton']")
	private WebElement done;
			
	@FindBy(xpath="//input[@id='add-to-cart-button']//following-sibling::span")
	private WebElement addToCart;
	
	@FindBy(xpath="(//span[text()='Continue'])[2]")
	private WebElement continueBtn;
	
	// Initializing the Page Objects:
				public ProductPage() {
					PageFactory.initElements(driver, this);
				}
	
	public void clickOnDeliverTo()
	{
		mouseClick(deliverTo);
	}
	
	public void applyZipCode(String zipcode) throws Exception
	{
		waitVisibilityOf(deliverTo);
		mouseClick(deliverTo);
		waitVisibilityOf(usZipCode);
		usZipCode.sendKeys(zipcode);
		waitVisibilityOf(applyZipCode);
		mouseClick(applyZipCode);
//		mouseClick(done);
//		waitPlease(5);
		mouseClick(continueBtn);
		waitToBeClickable(addToCart);
	}
	
	public void clickAddToCart() throws Exception
	{
		mouseClick(addToCart);
	}
}
