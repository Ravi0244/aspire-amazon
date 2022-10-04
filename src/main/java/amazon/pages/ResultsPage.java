package amazon.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import amazon.base.TestBase;

public class ResultsPage extends TestBase{

	@FindBy(xpath="//span[text()='Featured Brands']")
	private WebElement featuredBrand;
	
	@FindBy(xpath="//span[text()='RESULTS']")
	private WebElement resultsText;
	
	@FindBy(xpath="//span[text()='Belkin']")
	private WebElement Brand_Belkin;
	
	@FindBy(xpath="//input[@id='low-price']")
	private WebElement lowPrice;
	
	@FindBy(xpath="//input[@id='high-price']")
	private WebElement highPrice;
	
	@FindBy(xpath="(//input[@type=\"submit\"])[2]")
	private WebElement priceGoBtn;
	
	@FindBy(xpath="//span[@class='a-price']/span[@class='a-offscreen']")
	private List<WebElement>  productPrices;
	
	@FindBy(xpath="//span[text()='Featured']")
	private WebElement featured;
	
	@FindBy(xpath="//a[text()='Price: High to Low']")
	private WebElement sortHighToLow;
	
	@FindBy(xpath="(//span[@class='s-pagination-strip']/span)[last()]")
	private WebElement lastPageNumber;
	
	@FindBy(xpath="(//a[contains(@aria-label,'Go to next page')]")
	private WebElement nextPage;
	
	@FindBy (xpath="//a[@class='a-link-normal s-no-outline']/div/img")
	private List<WebElement> productLink;
	
	// Initializing the Page Objects:
		public ResultsPage() {
			PageFactory.initElements(driver, this);
		}
		
		public void scrollToFeaturedBrand()
		{
			scrollToWebElement(featuredBrand);
		}
		
		public String verifyResultPage()
		{
			return resultsText.getText();
		}
		
		public void selectBrandBelkin() throws Exception
		{
			Brand_Belkin.click();
			waitPlease(3);
		}
		
		public void filterWithMinAndMaxPrice(double min,double max) throws Exception
		{
			lowPrice.sendKeys(String.valueOf(min));
			highPrice.sendKeys(String.valueOf(max));
			priceGoBtn.click();
			waitPlease(3);
		}
		
		
		public List<String> listOfPrices()
		{
			List<String> priceList=new ArrayList<String>();
			List<WebElement> ele=productPrices;
			for (WebElement webElement : ele) {
				priceList.add(webElement.getAttribute("textContent"));
			}
			return priceList;
		}
		
		public ArrayList<Double> priceWithoutDollar()
		{
			ArrayList<Double> price=new ArrayList<Double>();
			for (String eachPrice : listOfPrices()) {
				String withoutDollar=TestBase.removeDollar(eachPrice);
				price.add(Double.parseDouble(withoutDollar));
				}
			
			return price;
		}
		
		public void sortHighToLow()
		{
			scrollToWebElement(featured);
			featured.click();
			sortHighToLow.click();
		}
		
		public int noOfPages()
		{
			scrollToWebElement(lastPageNumber);
			return Integer.parseInt(lastPageNumber.getText());
		}
		
		public void clickOnNextPage()
		{
			scrollToWebElement(nextPage);
			nextPage.click();
			waitVisibilityOf(resultsText);
		}
		
		public void clickOnRandomLink(int num) throws Exception
		{
			waitPlease(3);
			scrollToWebElement(listOfLinks().get(num));
			jsClick(listOfLinks().get(num));
		}
		
		public List<WebElement> listOfLinks()
		{
			return productLink;
		}
}
