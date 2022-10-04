package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import amazon.base.TestBase;

public class SignInPage extends TestBase {

	@FindBy(xpath="//h1[contains(text(),'Sign in')]")
	private WebElement signInHeader;
	
	public Boolean verifySignInPage()
	{
		waitVisibilityOf(signInHeader);
		return signInHeader.isDisplayed();
	}
}
