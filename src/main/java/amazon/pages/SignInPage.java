package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.base.TestBase;

public class SignInPage extends TestBase {

	@FindBy(xpath="//h1[contains(text(),'Sign in')]")
	private WebElement signInHeader;
	
	public SignInPage() {
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifySignInPage()
	{
		waitVisibilityOf(signInHeader);
		return signInHeader.isDisplayed();
	}
}
