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
		Assert.assertEquals(homepage.verifyHomePageTitle(), "Amazon.com. Spend less. Smile more.","HomePage is not displayed");
		homepage.selectCategory(category);
		homepage.searchWith(searchItem);
		Assert.assertEquals(resultpage.verifyResultPage(), "RESULTS");
		resultpage.filterWithMinAndMaxPrice(minPrice,maxPrice);
		resultpage.sortHighToLow();
		for(int i=0;i<resultpage.noOfPages();i++)
		{
			ArrayList<Double> princeInPage=resultpage.priceWithoutDollar();
			System.out.println("List of Prices in page "+i+1+" are ="+princeInPage);
			Assert.assertTrue(descendingCheck(princeInPage));
			resultpage.clickOnNextPage();
		}
	}
	
	@AfterMethod
	public void QuitBrowser()
	{
		TestBase.tearDown();
	}
}
