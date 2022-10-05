package com.amazon.Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import amazon.base.TestBase;
import amazon.pages.CheckoutPage;
import amazon.pages.HomePage;
import amazon.pages.ProductPage;
import amazon.pages.ResultsPage;
import amazon.pages.ShoppingCartPage;
import amazon.pages.SignInPage;

public class TestCase3 extends TestBase {

	HomePage homepage;
	ResultsPage resultpage;
	ProductPage productpage;
	CheckoutPage checkoutpage;
	ShoppingCartPage shoppingcartpage;
	SignInPage signinpage;
	
	
	private String category="Electronics";
	private String searchItem="iphone 14";
	
	@BeforeMethod
	public void setUp()
	{
		initialize();
		homepage=new HomePage();
		resultpage=new ResultsPage();
		productpage=new ProductPage();
		checkoutpage=new CheckoutPage(); 
		shoppingcartpage=new ShoppingCartPage();
		signinpage=new SignInPage();
	}
	

	@Test
	@Parameters({"minPrice","maxPrice"})
	public void verifySignInScreen(double minPrice,double maxPrice) throws Exception
	{
		test = extent.createTest("TestCase3", "Verify sign in page is displayed after checking out the product");
		Assert.assertEquals(homepage.verifyHomePageTitle(), "Amazon.com. Spend less. Smile more.","HomePage is not displayed");
		test.log(Status.INFO, "Verified the AmazonPage title");
		homepage.selectCategory(category);
		test.log(Status.INFO, "Selected the Category");
		homepage.searchWith(searchItem);
		test.log(Status.INFO, "Selected the searchItem");
		Assert.assertEquals(resultpage.verifyResultPage(), "RESULTS");
		test.log(Status.INFO, "Selected the Category");
		resultpage.filterWithMinAndMaxPrice(minPrice,maxPrice);
		int random=resultpage.generateRandom(resultpage.listOfLinks().size());
		System.out.println(random);
		resultpage.clickOnRandomLink(random);
		try {
			productpage.applyZipCode("99950");
			productpage.clickAddToCart();
			Assert.assertTrue(checkoutpage.verifyCheckOutPage(),"CheckOut page is not displayed yet");
			checkoutpage.clickGoToCart();
			Assert.assertTrue(shoppingcartpage.verifyShoppingCartHeader(),"ShoppingCart Page is not displayed yet");
			shoppingcartpage.clickOnProceedToCheckOut();
			Assert.assertTrue(signinpage.verifySignInPage(),"SignIn page is not displayed yet");
			test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("TestCase3")).build());
			test.log(Status.PASS, "Signin page is displayed as expected");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@AfterMethod
	public void QuitBrowser()
	{
		TestBase.tearDown();
	}
	

}
