package com.ppt.qa.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.ppt.qa.util.TestUtil;
import com.ppt.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverListener eventListener;
	
	public TestBase()
	{
		try {
			prop=new Properties();
			FileInputStream ip= new FileInputStream("C:\\Users\\febin\\eclipse-workspace\\ParallelTest\\src\\main\\java\\com\\ppt\\qa\\config\\config.properties");
			prop.load(ip);
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
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
			System.setProperty("webdriver.gecko.driver","C:\\Firefox\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");
			driver=new FirefoxDriver();	
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
	}
		
	public static void initialization()
	{
		String browserName= prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
				System.setProperty("webdriver.chrome.driver","C:\\Users\\febin\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
				driver=new ChromeDriver();
		}
		else if (browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Firefox\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");
			driver=new FirefoxDriver();	
		}
	//Event Listener code
		
		eventListener=new WebEventListener();
		driver = new EventFiringDecorator<WebDriver>(eventListener).decorate(driver);
		
		
//        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(eventListener); //Pass listener to constructor
//        WebDriver decorated = decorator.decorate(driver);
//        driver=decorated;
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
		//driver.get(prop.getProperty("url"));
		
		
	}
}
