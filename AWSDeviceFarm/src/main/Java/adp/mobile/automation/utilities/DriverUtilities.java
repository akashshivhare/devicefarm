package adp.mobile.automation.utilities;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by bhutesac on 4/1/2016.
 */
public class DriverUtilities {

    public static WebDriver driver = null;
    String workingDirec = System.getProperty("user.dir");   
    public RemoteWebDriver assigdriver = null;
   

    public WebDriver setBrowser(String browser) throws MalformedURLException {

        WebDriver assigdriver = null;
        if (browser.equalsIgnoreCase("IE")) {
            DesiredCapabilities d = DesiredCapabilities.internetExplorer();
            d.setCapability("nativeEvents", false);
            System.setProperty("webdriver.ie.driver", "CustomizedJar/IEDriverServer.exe");
            assigdriver = new InternetExplorerDriver(d);

        }
        if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.firefox.profile", "default");
            
            assigdriver = new FirefoxDriver();
        }
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "CustomizedJar/chromedriver.exe");
            String downloadFilepath = "/test/DownloadedFiles";
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups",1);
            chromePrefs.put("download.default_directory", workingDirec + downloadFilepath);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            assigdriver = new ChromeDriver(cap);
            
        }
        setThreadDriver(assigdriver);
        return driver;
    }
    
    
    
    public DesiredCapabilities setBrowserForHub(String browser) throws MalformedURLException {
        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();

        if (browser.equalsIgnoreCase("IE")) {
            cap.setCapability("nativeEvents", false);
            System.setProperty("webdriver.ie.driver", "CustomizedJar/IEDriverServer.exe");
        }
        if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.firefox.profile", "default");

        }
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "CustomizedJar/chromedriver.exe");
            String downloadFilepath = "/test/DownloadedFiles";
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups",1);
            chromePrefs.put("download.default_directory", workingDirec + downloadFilepath);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.ANY);
        }
       return cap;
    }

    public RemoteWebDriver getRemoteDriver(String BrowserName) throws MalformedURLException, IOException
    {
       // Runtime.getRuntime().exec("cmd /c start C:/workspace/orbis_regression/Tax_Credits_Automation/CustomizedJar/hubStart.bat");

                  //Test machine : purdvmatcs002 : Hub Ip
        //Before Excuting the script start the hub server on machine purdvmatcs002 and also register the Node with driver information
         // assigdriver  = new RemoteWebDriver(new URL("http://25.161.13.173:5585/wd/hub"), setBrowser(BrowserName));
        assigdriver  = new RemoteWebDriver(new URL("http://11.161.0.241:4444/wd/hub"), setBrowserForHub(BrowserName));
        setThreadDriver(assigdriver);
        return assigdriver;
    }

    public void setThreadDriver(WebDriver driver)
    {

        DriverUtilities.driver = driver;
    }

    public WebDriver getThreadDriver()
    {
        return driver;
    }

}
