package CoverFoxBase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;

public class BaseForListener {
	
	static protected  WebDriver driver;
	public void  launchBrowser()
	{
		ChromeOptions ch = new ChromeOptions();
		ch.addArguments("--disable-notifications");
		Reporter.log("Launching the browser",true);
		driver = new ChromeDriver(ch);
		driver.manage().window().maximize();
		driver.get("https://www.coverfox.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		
	}
}
