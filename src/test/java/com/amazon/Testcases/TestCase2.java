package com.amazon.Testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

public class TestCase2 extends TestBase {

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
	public void verifySortingOrder(double minPrice,double maxPrice) throws Exception
	{
		try {
			test = extent.createTest("TestCase2", "Verify the sorting order of the product based on their prices");
			Assert.assertEquals(homepage.verifyHomePageTitle(), "Amazon.com. Spend less. Smile more.","HomePage is not displayed");
			test.log(Status.INFO, "Verified the AmazonPage title");
			homepage.selectCategory(category);
			test.log(Status.INFO, "Selected the Category");
			homepage.searchWith(searchItem);
			test.log(Status.INFO, "Selected the searchItem");
			Assert.assertEquals(resultpage.verifyResultPage(), "RESULTS");
			waitPlease(5);
			resultpage.filterWithMinAndMaxPrice(minPrice,maxPrice);
			test.log(Status.PASS, "Filtered with Minimum price of "+minPrice+" and Maximum price of "+maxPrice+" ");
			resultpage.sortHighToLow();
			test.log(Status.INFO, resultpage.listOfPrices().toString());
			test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("TestCase2")).build());
			for(int i=0;i<resultpage.noOfPages();i++)
			{
				ArrayList<Double> princeInPage=resultpage.priceWithoutDollar();
				test.log(Status.INFO, "List of Prices in page "+i+1+" are ="+princeInPage);
				Assert.assertTrue(descendingCheck(princeInPage));
				resultpage.clickOnNextPage();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			test.log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot("TestCase2_failure")).build() );
		}
	}
	
	@AfterMethod
	public void QuitBrowser()
	{
		TestBase.tearDown();
	}
}
