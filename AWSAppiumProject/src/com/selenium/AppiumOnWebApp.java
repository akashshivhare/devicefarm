//package com.selenium;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//public class AppiumOnWebApp 
//{
//	RemoteWebDriver driver;
//	@Test(dataProvider="getData")
//	public void f(String mobile) throws MalformedURLException
//	{
//	
//		if(mobile.equals("mobile1")){
//			//		  DesiredCapabilities cap= new DesiredCapabilities();
//			DesiredCapabilities cap= DesiredCapabilities.android();
//			cap.setCapability("browserName", "chrome");
//			cap.setCapability("platformName", "Android");
//			cap.setCapability("udid", "42038373d07fc000");// check extra section in DOC
//			cap.setCapability("platformVersion", "4.2.2");
//
//			driver=new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
//		}
//		else if(mobile.equals("mobile2")){
//			//			  DesiredCapabilities cap= new DesiredCapabilities();
//			DesiredCapabilities cap= DesiredCapabilities.android();
//			cap.setCapability("browserName", "chrome");
//			cap.setCapability("platformName", "Android");
//			cap.setCapability("udid", "4df7e41b643bcf29");
//			cap.setCapability("platformVersion", "4.4.2");
//
//			driver=new RemoteWebDriver(new URL("http://127.0.0.2:4724/wd/hub"), cap);
//		}
//	}
//	@AfterTest
//	public void afterTest() 
//	{
//		driver.quit();
//	}
//
//	public void run() {
//		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//
//		driver.get("https://www.facebook.com/login/");
//
//		driver.findElement(By.name("email")).sendKeys("some@gmail.com");
//
//		driver.findElement(By.name("pass")).sendKeys("abcdefgh");
//
//		driver.findElement(By.id("u_0_1")).click();
//		
//	}
//	@DataProvider(parallel=true)
//	public Object[][] getData(){
//
//		return new Object[][]
// 				{ 
// 					new Object[]{ 1, "mobile1"},
// 					new Object[]{ 2, "mobile2"}, 					
// 				
// 				};
//	}
//}
