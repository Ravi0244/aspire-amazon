package com.amazon.Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import amazon.base.TestBase;
import amazon.pages.HomePage;
import amazon.pages.ResultsPage;

public class TestCase1 extends TestBase{

	HomePage homepage;
	ResultsPage resultpage;
	private String category="Electronics";
	private String searchItem="iphone 14";
		

	@BeforeMethod
	public void setUp()
	{
		initialize();
		homepage=new HomePage();
		resultpage=new ResultsPage();
	}
	
	
	@Test
	@Parameters({"minPrice","maxPrice"})
	public void verifyPriceOfItem(double minPriceT,double maxPriceT) throws Exception
	{
		test = extent.createTest("TestCase1", "Verify all items are within the range of $"+minPriceT+ " and $"+maxPriceT+" on the resultpage");
		test.log(Status.INFO, "Waiting to Verify the AmazonPage title from Base");
		Assert.assertEquals(homepage.verifyHomePageTitle(), "Amazon.com. Spend less. Smile more.","HomePage is not displayed");
		homepage.selectCategory(category);
		test.log(Status.INFO, "Selected the Category");
		homepage.searchWith(searchItem);
		test.log(Status.INFO, "Selected the Search Item");
		Assert.assertEquals(resultpage.verifyResultPage(), "RESULTS");
		resultpage.scrollToFeaturedBrand();
		resultpage.selectBrandBelkin();
		test.log(Status.INFO, "Selected the Brand from the Featured brand list");
		resultpage.filterWithMinAndMaxPrice(minPriceT,maxPriceT);
		test.log(Status.PASS, "Filtered with Minimum price of "+minPriceT+" and Maximum price of "+maxPriceT+" ");
		test.log(Status.INFO,"Printing the price of products after filtering="+resultpage.listOfPrices());
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("TestCase1")).build());
		for (String eachPrice : resultpage.listOfPrices()) {
			String withoutDollar=TestBase.removeDollar(eachPrice);			
			Assert.assertTrue(Double.parseDouble(withoutDollar)>minPriceT && Double.parseDouble(withoutDollar)<maxPriceT, "This price value is not in the selected range of 100-2000$ ="+eachPrice);
		}		
	}
	
	@AfterMethod
	public void QuitBrowser()
	{
		TestBase.tearDown();
	}

	
}
