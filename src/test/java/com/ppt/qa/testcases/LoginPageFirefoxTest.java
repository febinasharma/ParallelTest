package com.ppt.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPageFirefoxTest {
	
		
		static {
//			System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver","C:\\Drivers\\geckodriver.exe");
				}

		public static void main(String[] args)
		{
			WebDriver driver=new FirefoxDriver();
//			WebDriver driver1=new ChromeDriver();
			driver.get("https://classic.crmpro.com");
//			driver1.get("https://classic.crmpro.com");
			
		}

	}
