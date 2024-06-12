package com.ppt.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ppt.qa.base.TestBase;
//import com.ppt.qa.pages.HomePage;
import com.ppt.qa.pages.LoginPage;
import com.ppt.qa.util.TestUtil;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
//	HomePage homePage;
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser)
	{
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\febin\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if (browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();	
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		loginPage=new LoginPage();
	}
	
	
	@Test(priority=1)
	
	public void loginPagetitleTest()
	{
		String title=loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM software for customer relationship management, sales, and support.");
	}
	@Test(priority=0)
	public void crmLogoImageTest()
	{
		boolean flag= loginPage.validateCRMImg();
		Assert.assertTrue(flag);
	}
//	@Test(priority=2)
//	public void loginTest()
//	{
//		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
//	}
//	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}