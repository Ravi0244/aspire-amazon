package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.base.TestBase;

public class HomePage extends TestBase{


	@FindBy(xpath = "//a[@id='nav-logo-sprites']")
	@CacheLookup
	private WebElement amazonLogo;
	
	@FindBy(xpath="//a[text()='Amazon']")
	private WebElement amazon;
	
	@FindBy(xpath="//form[@id='nav-search-bar-form']")
	private WebElement form;
	
	@FindBy(xpath="//*[@id='searchDropdownBox']")
	private WebElement searchDropDown;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	private WebElement searchTextBox;

	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	private WebElement submitBtn;
	
	@FindBy(xpath="(//span[@class='a-button-inner'])[1]")
	private WebElement DeliverTo;
	
	
	// Initializing the Page Objects:
		public HomePage() {
			PageFactory.initElements(driver, this);
		}
		
		@SuppressWarnings("finally")
		public String verifyHomePageTitle() throws Exception{
			Boolean res=false;
			try {
				res = amazon.isDisplayed()? true: false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Navigated to Expected amazon page without Refreshing");
			}finally {
			if(res.equals(false)) return driver.getTitle();
			else {refreshPage(); return driver.getTitle();}}
		}
		
		public void switchToForm()
		{
			driver.switchTo().frame(form);
		}
		
		public void selectCategory(String value)
		{
			System.out.println("Waited enough");
			searchDropDown.click();
			TestBase.selecFromDropDown(searchDropDown, value);
		}
		
		
		public void waitForDropDown()
		{
			waitVisibilityOf(searchDropDown);
		}
		
		public void searchWith(String input)
		{
			searchTextBox.sendKeys(input);
			submitBtn.click();;
		}
		
		
		
}
