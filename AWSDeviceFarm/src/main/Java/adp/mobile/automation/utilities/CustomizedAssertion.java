package adp.mobile.automation.utilities;


import org.junit.Assert;

import aws.DeviceFarmTestNG.BaseTempletSteps;

/**
 * Created by BhuteSac on 4/13/2016.
 */
public class CustomizedAssertion extends BaseTempletSteps{

    TakeScreenshotAction screenshot = new TakeScreenshotAction();

    public void verifyText(String actual, String expected , String methodName) throws Exception {
        if(expected.equals(actual)){
        	
        	verifyTest("Test Step Passed :",true);
            reportMapping.setStatus("PASS");
            Assert.assertEquals(actual,expected);
            System.out.println("Expected Value : <" + expected + "> Actual Value <" + actual +">");        
            
            }else{
            	
            verifyTest("Test Step Faield :",false);
            reportMapping.setStatus("FAIL");
            screenshot.getScreenshot(methodName);
            Assert.assertEquals(actual,expected);
            System.out.println("Expected Value : <" + expected + "> Actual Value <" + actual +">");
            
        }
    }

    public void verifyTrue(boolean flag , String methodName) throws Exception {
        if(flag)
        {
        	verifyTest("Test Step Passed :",flag);
        	reportMapping.setStatus("PASS");
            Assert.assertTrue("Expected value for flag is" + flag + "Actual is True",flag);
            System.out.println("Expected Value : <True> Actual Value <" + flag +">");
            
        }else
        {
            screenshot.getScreenshot(methodName);
            verifyTest("Test Step Faield :",flag);
            reportMapping.setStatus("FAIL");
            Assert.assertTrue("Expected value for flag is" + flag + "Actual is True",flag);
            
        }
    }

    public void verifyFalse(boolean flag , String methodName) throws Exception {
        if(!flag)
        {
            Assert.assertFalse("Expected value for flag is" + flag + "Actual is True",flag);
        }else
        {
            screenshot.getScreenshot(methodName);
            Assert.assertFalse("Expected value for flag is" + flag + "Actual is True",flag);
            System.out.println("Expected Value : <False> Actual Value <" + flag +">");
        }
    }
}
