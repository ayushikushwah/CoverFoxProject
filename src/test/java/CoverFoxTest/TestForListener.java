package CoverFoxTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CoverFoxBase.BaseForListener;
import CoverFoxPOM.CoverFoxHealthPlanPage;
import CoverFoxPOM.CoverFoxHomePage;


@Listeners(ListenersStudy.Listener.class)
public class TestForListener extends BaseForListener {
	
	CoverFoxHomePage homepage ;
	CoverFoxHealthPlanPage healthpage;
	
	@Test
	public void Test() throws InterruptedException
	{
		launchBrowser();
		Thread.sleep(3000);
		homepage = new CoverFoxHomePage(driver);
		homepage.clickOnGetStarted();
		Reporter.log("Click on get started",true);
		
		Assert.fail();
		healthpage = new CoverFoxHealthPlanPage(driver);
		healthpage.clickOnNextBtn();	
		Reporter.log("Click on next btn",true);
		
	}
	
	@Test
	public void closeBrowser()
	{
		Reporter.log("Closing the browser",true);
		driver.close();
	}
	
	

}
