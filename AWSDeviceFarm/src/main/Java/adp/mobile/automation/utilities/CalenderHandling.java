package adp.mobile.automation.utilities;


import org.openqa.selenium.JavascriptExecutor;

import aws.DeviceFarmTestNG.BaseTempletSteps;

/**
 * Created by bhutesac on 7/20/2016.
 */
public class CalenderHandling extends BaseTempletSteps {

    public void startdateToSearch(String date, String idValue)
    {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript("document.getElementById('"+idValue+"').value='"+ date + "'");
    }

    public void selectDateOnMassProcessing(String date, String idValue)
    {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript("document.getElementsByName('"+idValue+"').value='"+ date + "'");
    }



}
