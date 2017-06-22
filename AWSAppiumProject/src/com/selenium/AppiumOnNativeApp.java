package com.selenium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppiumOnNativeApp 
{
	RemoteWebDriver driver;
	
	
	  @BeforeTest
	  public void beforeTest() throws MalformedURLException {
		  File app= new File ("Resource\TheMallApp.apk");
		  
		  DesiredCapabilities cap= DesiredCapabilities.android();
		  cap.setCapability("platformName", "Android");
		  cap.setCapability("deviceName", "MOTO");
		  cap.setCapability("platformVersion", "6.0.1");
		  cap.setCapability("app", app.getAbsolutePath());
		  
		  driver=new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  @Test
	  public void f() 
		{
			WebElement loginID=driver.findElement(By.id("com.demo.amanora:id/loginGold"));
//			 WebElement loginID=driver.findElement(By.name("Kedar Bindu (Gold)"));
			loginID.click();
			WebElement subelement=driver.findElement(By.id("com.demo.amanora:id/OffersLayout"));
			subelement.click();
	  }
	  
	  @AfterTest
	  public void afterTest() 
	  {
		  driver.quit();
	  }


}
