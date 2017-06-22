package adp.mobile.automation.utilities;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by BhuteSac on 8/26/2016.
 */
public class RemoteDriverSetUp {
    public static WebDriver driver = null;
    public static String workingDirec = System.getProperty("user.dir");

    public static RemoteWebDriver getDriver(String browser) throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://25.161.13.174:4444/wd/hub"), getBrowserCapabilities(browser));
    }

    private static DesiredCapabilities getBrowserCapabilities(String browserType) {
        WebDriver assigdriver = null;
        switch (browserType) {
            case "firefox":
                System.out.println("Opening firefox driver");
                return DesiredCapabilities.firefox();
            case "chrome":
                System.out.println("Opening chrome driver");
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
                cap.setBrowserName("chrome");
              cap.setPlatform(Platform.ANY);

//            //Test machine ip
       //  assigdriver  = new RemoteWebDriver(new URL("http://25.161.13.174:5582/wd/hub"), cap);


                //hub machine ip : sachin's Machine IP
                //  assigdriver  = new RemoteWebDriver(new URL("http://26.161.13.37:4444/wd/hub"), cap);

                return DesiredCapabilities.chrome();
            case "IE":
                System.out.println("Opening IE driver");
                return DesiredCapabilities.internetExplorer();
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
                return DesiredCapabilities.firefox();
        }
    }
}
