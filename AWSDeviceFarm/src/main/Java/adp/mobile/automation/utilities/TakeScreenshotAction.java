package adp.mobile.automation.utilities;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import aws.DeviceFarmTestNG.BaseTempletSteps;

import java.io.File;

/**
 * Created by BhuteSac on 4/13/2016.
 */
public class TakeScreenshotAction extends BaseTempletSteps {

    public String workingDir = System.getProperty("user.dir");
    ExtendReport extendRert = new ExtendReport();


    public void getScreenshot(String methodName) throws Exception
    {
        File scrFile = ((TakesScreenshot) this.getDriver()).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        FileUtils.copyFile(scrFile, new File(this.workingDir + "//test//All-Screenshot//" + methodName + ".png"));
        System.out.println(this.workingDir + "//test//All-Screenshot//" + methodName + ".png");
       // extendRert.whenStepsforfail(this.workingDir + "//test//All-Screenshot//" + methodName + ".png");

    }

    public String getTestClassName(String testName) {
        String[] reqTestClassname = testName.split("\\.");
        int i = reqTestClassname.length - 1;
        System.out.println("Required Test Name : " + reqTestClassname[i]);
        return reqTestClassname[i];
    }
}
