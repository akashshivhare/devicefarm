package adp.mobile.automationtest;

import org.openqa.selenium.remote.RemoteWebDriver;
import java.awt.RenderingHints.Key;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


/**
 * Created by shivhara on 11/9/2016.
 */
public class Screenings {

    RemoteWebDriver driver;
//    By click=By.xpath("//android.widget.Button[contains(@content-desc,'Click Here')]");
//    By add=By.xpath("//android.view.View[@content-desc='Add New Shipwreck']");
//    By nameElement=By.xpath("//*[@resource-id='name']");
//    By description=By.xpath("//*[@resource-id='description']");
//    By condition=By.xpath("//*[@resource-id='condition']");
//    By discover=By.xpath("//*[@resource-id='yearDiscovered']");
//    By dow=By.xpath("//*[@resource-id='depth']");
//    By lat=By.xpath("//*[@resource-id='latitude']");
//    By longi=By.xpath("//*[@resource-id='longitude']");
//    By saveBtn=By.xpath("//*[@content-desc='Save']");

    @BeforeTest
    public void beforeTest() throws MalformedURLException {

   //     File app= new File("Resource/android-debug.apk");
        DesiredCapabilities cap= DesiredCapabilities.android();
        cap.setCapability("deviceName","Motox");
        cap.setCapability("platformVersion", "6.0");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformName", "chrome");
   //     cap.setCapability("app", app.getAbsolutePath());

        driver=new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void f() throws InterruptedException
    {
        takeScreenshot ("Screening Launch");
      //  driver.get("https://stage.tcs.adp.com/screen/index.html?cc=lockheed");

        homePage();
        performUnitPageActions();
        performContinuePageActions();
        performContinuePageActions()
  
    }



    @AfterTest
    public void afterTest()
    {
        driver.quit();
    }

    public boolean takeScreenshot(final String name) {
        String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
    }

    public void homePage() throws InterruptedException
    {
        Thread.sleep(2000);
        driver.get("https://stage.tcs.adp.com/screen/index.html?cc=lockheed");
        takeScreenshot ("Unit Selection Page");
      
    }
    
    
    
    public void performUnitPageActions(){
        try {
        	Thread.sleep(2000);
            selectDropdown(screeningPages.unit_State_Dropdown(),getExcelData().get("UNIT_STATE")); // Select unit state dropdown.
            selectDropdown(screeningPages.unit_City_Dropdown(),getExcelData().get("UNIT_CITY")); // Select unit city dropdown.
            selectDropdown(screeningPages.unit_Location_Dropdown(),getExcelData().get("UNIT_LOCATION")); // Select unit location dropdown.
            sleep(3000);
            screeningPages.unit_Continue_Button().click();  // Click on unit continue button
        } catch (Exception e) {
            verifyTest("Faliure in performUnitPageActions page",false);
            e.printStackTrace();
        }
    }

    public void performContinuePageActions(){
        try {
            screeningPages.welcome_Continue_Button().click();  // Click on Welcome continue button
        } catch (Exception e) {
            e.printStackTrace();
            verifyTest("Faliure in performContinuePageActions page",false);
        }
    }

    public void performPersonalInfoPageActions(){
        try {
            SSN = String.valueOf(ssnGenerator.generateSSN());

            getExcelData().put("SSN", SSN);
            getExcelData().put("CSSN", SSN);
            screeningPages.personalInfo_SSN_TxtBox().sendKeys(getExcelData().get("SSN"));  // user input in SSN text field.
            screeningPages.personalInfo_ConfirmSSN_TxtBox().sendKeys(getExcelData().get("CSSN"));  // user input in Confirm SSN text field.
            screeningPages.personalInfo_FirstName_TxtBox().sendKeys(getExcelData().get("FNAME"));  // user input in First Name text field.
            screeningPages.personalInfo_MiddleName_TxtBox().sendKeys(getExcelData().get("MNAME"));  // user input in Middle Name text field.
            screeningPages.personalInfo_LastName_TxtBox().sendKeys(getExcelData().get("LNAME"));  // user input in Last Name text field.
            screeningPages.personalInfo_Telephone_TxtBox().sendKeys(getExcelData().get("TELEPHONE"));  // user input in Telephone text field.
            screeningPages.personalInfo_Email_TxtBox().sendKeys(getExcelData().get("EMAIL"));  // user input in email address text field.
            screeningPages.personalInfo_Continue_Button().click();  // Click on Personal info continue button.
        } catch (Exception e) {
            e.printStackTrace();
            verifyTest("Faliure in performPersonalInfoPageActions page",false);
        }
    }

//    public void dashboard() throws InterruptedException
//    {
//        Thread.sleep(2000);
//        WebElement addNewShipwreck=driver.findElement(add);
//        addNewShipwreck.click();
//    }
//    public void addDetails() throws InterruptedException
//    {
//        Thread.sleep(5000);
//
//        WebDriverWait wait= new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.presenceOfElementLocated(nameElement));
//        //WebElement name=driver.findElement(By.id("name"));
//        WebElement name=driver.findElement(nameElement);
//        name.clear();name.sendKeys("Ship1");
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(description));
//        WebElement desc=driver.findElement(description);
//        desc.clear();desc.sendKeys("Ship desc");
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(condition));
//        WebElement cond=driver.findElement(condition);
//        cond.clear();cond.sendKeys("Ship condition");
//        cond.sendKeys(Keys.ALT);
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(discover));
//        WebElement disco=driver.findElement(discover);
//        disco.clear();disco.sendKeys("2016");
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(dow));
//        WebElement dOfWreck=driver.findElement(dow);
//        dOfWreck.clear();dOfWreck.sendKeys("1000");
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(lat));
//        WebElement latitude=driver.findElement(lat);
//        latitude.clear();latitude.sendKeys("32");
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(longi));
//        WebElement longitude=driver.findElement(longi);
//        longitude.clear();longitude.sendKeys("54");
//        takeScreenshot ("Performed Click");
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(saveBtn));
//        WebElement save=driver.findElement(saveBtn);
//        save.click();
//        Thread.sleep(5000);
//        takeScreenshot ("Last page");

//}


}




