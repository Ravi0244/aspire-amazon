package amazon.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;

import amazon.base.TestBase;

public class TestListenerClass extends TestBase implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

	@Override
	public void onTestStart(ITestResult result) {
//	  test.log(Status.INFO, result.getMethod().getMethodName()+" has been started");
	  }
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
//		test.log(Status.PASS,result.getMethod().getMethodName()+" validations are done" );
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
//		test.log(Status.FAIL, result.getMethod().getMethodName()+" has been failed");
		try {
//			test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName())).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
