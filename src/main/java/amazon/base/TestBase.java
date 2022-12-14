package amazon.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import amazon.listeners.TestListenerClass;
import amazon.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(TestListenerClass.class)
public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/amazon/config/config.properties"); 
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void initialize()
	{
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();		
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
	
	@BeforeSuite
	public void setup()
	{
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("extentreport.html");
		extent.attachReporter(spark);
		
		extent.setSystemInfo("OS", "Win 10");
	    extent.setSystemInfo("Environment", "PROD");
	    extent.setSystemInfo("User Name", "Ravi theja Tallam");

	}
	@AfterSuite
	public void tearDownExtent()
	{
		extent.flush();
	}
	
	public void refreshPage() throws Exception
	{
		driver.navigate().refresh();
		waitPlease(3);
	}
	

	public static void tearDown()
	{
		driver.quit();
	}
	
	public static void waitPlease(int sec) throws Exception
	{
		Thread.sleep(sec*1000);
	}
	public static void selecFromDropDown(WebElement webelement,String value )
	{
		Select sel=new Select(webelement);
		sel.selectByVisibleText(value);
	}
	
	public static void waitVisibilityOf(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	public static void jsClick(WebElement element) throws Exception  {
		try {
			if (element.isEnabled()) {
				System.out.println("Clicking on element with using java script click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}} catch (StaleElementReferenceException e) {
				System.out.println("Element is not attached to the page document "+ e.getStackTrace());
			} catch (NoSuchElementException e) {
				System.out.println("Element was not found in DOM "+ e.getStackTrace());
			} catch (Exception e) {
				System.out.println("Unable to click on element "+ e.getStackTrace());
			}}
	
	public static void switchToMainWindow()
	{
		driver.switchTo().defaultContent();
	}
	
	public static void scrollToWebElement(WebElement element) 
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static String removeDollar(String str)
	{
		StringBuilder sb = new StringBuilder(str);
		String res=sb.deleteCharAt(0).toString();
		return res;
	}
	
	public boolean descendingCheck(ArrayList<Double> data)
	{
		for (int i = 0; i < data.size()-1; i++) {
            if (data.get(i) < data.get(i+1)) {
                return false;
            }else if (data.get(i)==data.get(i+1))  
            	return true;     
         }return true;
	}
	
	public int generateRandom(int size)
	{
		int randumNumber = ThreadLocalRandom.current().nextInt(0, size);
		return randumNumber;
	}
	
	public void mouseClick(WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).click().build().perform();
	}
	
	public static void waitToBeClickable(WebElement element) {
		wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static String getScreenshot(String screenShotName) throws Exception
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot scrnshot= (TakesScreenshot)driver;
		File source = scrnshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\TestsScreenshots\\"+screenShotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
}
