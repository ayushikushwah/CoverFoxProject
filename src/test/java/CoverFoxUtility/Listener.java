package CoverFoxUtility;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import CoverFoxBase.BaseForListener;
import CoverFoxUtility.Utility;

public class Listener extends BaseForListener implements ITestListener{
	

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("TC "+result.getName()+" exceution is started",true);
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("TC "+result.getName()+" failed",true);
		String filePath = System.getProperty("user.dir")+"\\ScreenShotForMaven\\";
		try {
			Utility.getScreenShotForMaven(driver, filePath, "Ayushi");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		Reporter.log("Tc"+result.getName()+"is successful or passed",true);
	}
	
	
}
