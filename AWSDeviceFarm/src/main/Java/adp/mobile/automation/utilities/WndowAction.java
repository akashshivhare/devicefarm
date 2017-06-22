package adp.mobile.automation.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import aws.DeviceFarmTestNG.BaseTempletSteps;

import java.util.Set;

/**
 * Created by BhuteSac on 4/3/2016.
 */
public class WndowAction extends BaseTempletSteps {

    public WebDriver switchtoChildWindow()
    {
        try {
            Set<String> windows = getDriver().getWindowHandles();
            String currentWindow = getDriver().getWindowHandle();
            for (String window : windows) {

                if (!window.equalsIgnoreCase(currentWindow)) {
                	driver.switchTo().window(currentWindow).close();
                    getDriver().switchTo().window(window);
                    break;
                }
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return getDriver();
    }

    public WebDriver switchtoChildWindowOpen()
    {
        try {
            Set<String> windows = getDriver().getWindowHandles();
            String currentWindow = getDriver().getWindowHandle();
            for (String window : windows) {

                if (!window.equalsIgnoreCase(currentWindow)) {
                    //driver.switchTo().window(currentWindow).close();
                    getDriver().switchTo().window(window);
                    break;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return getDriver();
    }

    public WebDriver switchtoChildWindowReturnParent()
    {
        try {
            Set<String> windows = getDriver().getWindowHandles();
            String currentWindow = getDriver().getWindowHandle();
            for (String window : windows) {

                if (!window.equalsIgnoreCase(currentWindow)) {
                    driver.switchTo().window(currentWindow).close();
                    getDriver().switchTo().window(window);
                    break;
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return getDriver();
    }

    public WebDriver switchtoMultiChildWindow(WebElement clickElement)
    {
        Set<String> beforeClickWindows = getDriver().getWindowHandles();
        clickElement.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> afterWindows = getDriver().getWindowHandles();

        for(String window : beforeClickWindows)
        {
            driver.switchTo().window(window).close();
        }

        afterWindows.removeAll(beforeClickWindows);
        if(afterWindows.size() == 1) {
            getDriver().switchTo().window((String)afterWindows.toArray()[0]);
            String thirdWindow = getDriver().getWindowHandle();
            System.out.println("window id third screen: " + thirdWindow);
        }
        return getDriver();
    }
}
