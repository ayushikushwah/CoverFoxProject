package CoverFoxTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CoverFoxBase.BaseClass;
import CoverFoxPOM.CoverFoxAddressDetailsPage;
import CoverFoxPOM.CoverFoxHealthPlanPage;
import CoverFoxPOM.CoverFoxHomePage;
import CoverFoxPOM.CoverFoxMemberDetailsPage;
import CoverFoxPOM.CoverFoxResultsPage;
import CoverFoxUtility.UtilityTest;
import jdk.internal.org.jline.utils.Log;

public class TestClass extends BaseClass {

	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPage;
	CoverFoxAddressDetailsPage addressPage;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxResultsPage resultPage;
	String filePath;
	String destForScreenshot;
	public static Logger logger;
//	public static Logger logger;

	@BeforeTest
	public void openBrowser()

	{
//		logger = Logger.getLogger("CoverFoxTest");
//		PropertyConfigurator.configure("Log4j.properties");
		
		logger = Logger.getLogger("CoverFoxTest");
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Launching the browser");
		launchBrowser();		
		homePage = new CoverFoxHomePage(driver);
		healthPage = new CoverFoxHealthPlanPage(driver);
		addressPage = new CoverFoxAddressDetailsPage(driver);
		memberDetails = new CoverFoxMemberDetailsPage(driver);
		resultPage = new CoverFoxResultsPage(driver);
		filePath = System.getProperty("user.dir")+"\\CoverFox.xlsx";
		destForScreenshot = System.getProperty("user.dir")+"\\ScreenShotForMaven\\";


	}

	@BeforeClass
	public void preConditions() throws IOException , FileNotFoundException , InterruptedException

	{
		// Home-Page
		Thread.sleep(3000);
		logger.info("Clicking on get started btn");
		homePage.clickOnGetStarted();
		Reporter.log("Click on get started",true);

		// Health-Plan Page
		Thread.sleep(4000);
		logger.info("Clicking on the next btn");
		healthPage.clickOnNextBtn();
		Reporter.log("Click on next button",true);

		// Member-details Page
		Thread.sleep(2000);
//		memberDetails.selectAgeDropDown(Utility.readDataFromPeroperties("age"));
		logger.info("Selecting age from dropdown");
		memberDetails.selectAgeDropDown(UtilityTest.getExcelData(filePath, "Sheet3", 1, 2));
		Reporter.log("Select age",true);
		logger.info("CLicking on the next btn");
		memberDetails.clickOnNextBtn();
		Reporter.log("Click on next button",true);

		Thread.sleep(2000);

		// Address-Details Page
//		addressPage.enterPinCode(Utility.readDataFromPeroperties("pinCode"));
		logger.info("Entring the pincode");
		addressPage.enterPinCode(UtilityTest.getExcelData(filePath, "Sheet3", 1, 0));
		Reporter.log("enter pincode",true);
//		addressPage.enterMobileNumber(Utility.readDataFromPeroperties("mobNo"));
		logger.info("Entering the mobile number");
		addressPage.enterMobileNumber(UtilityTest.getExcelData(filePath, "Sheet3", 1, 1));
		Reporter.log("enter mobileNo",true);
		logger.info("Click on continue btn");
		addressPage.clickOnContinueBtn();
		Reporter.log("Click on Continue Btn",true);

		Thread.sleep(2000);
	}
	
	@Test
	public void validateBanners() throws IOException

	{
		// Result-Page
		logger.info("Validating the no of banners on result page");
		logger.warn("Number of banners shouldbe equl to the text dispalyed");
		Reporter.log("Validating the no of banners on result page",true);
		Assert.assertEquals(resultPage.getTotalBanners(), resultPage.getTextOnHomePage(),
				"No of Banners and text is not  matching");
		Reporter.log("Taking Screenshot",true);
		UtilityTest.getScreenShotForMaven(driver, destForScreenshot ,"validateBanners");

	}

	@Test
	public void validatePresenceOfSortDropdown() throws IOException {
		//result-page
		logger.info("Validating the presence of sort dropdown on result page");
		logger.warn("Sort dropdown shouldbe displayed on the result page");
		Reporter.log("Validating the presence of sort dropdown on result page",true);
		Assert.assertTrue(resultPage.sortPlanDropdownIsDisplayed(),
				"Sort Plan Dropdown is not displayed , Tc is failed");
		Reporter.log("Taking Screenshot",true);
		UtilityTest.getScreenShotForMaven(driver, destForScreenshot ,"PresenceOfSortDropDown");
	}

	@AfterClass
	public void closeTheBrowser() throws InterruptedException

	{
		logger.info("Closing the browser..");
//		closeBrowser();
	}

}
