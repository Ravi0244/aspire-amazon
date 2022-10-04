package com.amazon.Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
		Assert.assertEquals(homepage.verifyHomePageTitle(), "Amazon.com. Spend less. Smile more.","HomePage is not displayed");
		homepage.selectCategory(category);
		homepage.searchWith(searchItem);
		Assert.assertEquals(resultpage.verifyResultPage(), "RESULTS");
		resultpage.scrollToFeaturedBrand();
		resultpage.selectBrandBelkin();
		resultpage.filterWithMinAndMaxPrice(minPriceT,maxPriceT);
		for (String eachPrice : resultpage.listOfPrices()) {
			String withoutDollar=TestBase.removeDollar(eachPrice);
			System.out.println(withoutDollar);
			Assert.assertTrue(Double.parseDouble(withoutDollar)>minPriceT && Double.parseDouble(withoutDollar)<maxPriceT, "This price value is not in the selected range of 100-2000$ ="+eachPrice);
		}		
	}
	
	@AfterMethod
	public void QuitBrowser()
	{
		TestBase.tearDown();
	}
	
	
}
