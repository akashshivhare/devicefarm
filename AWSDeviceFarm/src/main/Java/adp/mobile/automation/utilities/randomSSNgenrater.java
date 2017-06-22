package adp.mobile.automation.utilities;


import java.util.Random;

import aws.DeviceFarmTestNG.BaseTempletSteps;

/**
 * Created by BhuteSac on 5/19/2016.
 */
public class randomSSNgenrater extends BaseTempletSteps{
    public int actualSSN;

    public int generateSSN () {
        //Get SSN
        int randomInt;
        int SSN;
        randomInt = 1;
        SSN = 0;
        Random randomGenerator = new Random();
        for (int idx = 1; idx <= 1000; ++idx) {
            randomInt = randomGenerator.nextInt();
            SSN = (int) ((800000000 - 200000000 + 1) * randomInt + 200000000);
            if (java.lang.String.valueOf(SSN).length() == 9 && SSN > 0) {
                if (!java.lang.String.valueOf(SSN).startsWith("666") && !java.lang.String.valueOf(SSN).startsWith("000") && !java.lang.String.valueOf(SSN).startsWith("9") && !java.lang.String.valueOf(SSN).substring(4, 5).equals("00") && !java.lang.String.valueOf(SSN).substring(3, 5).equals("00")) {
                    if (!java.lang.String.valueOf(SSN).endsWith("0000")) {
                        actualSSN = SSN;
                        break;
                    }
                }
            }
        }
        setSSN(actualSSN);
        return actualSSN;
    }
    
    public int generateUniqueIdForDocufree() {
        //Get SSN
        int randomInt;
        int finalUniqueId = 0;
        int uniqueId;
        randomInt = 1;
        uniqueId = 0;
        Random randomGenerator = new Random();
        for (int idx = 1; idx <= 1000; ++idx) {
            randomInt = randomGenerator.nextInt();
            uniqueId = (int) ((800000000 - 2000000000 + 1) * randomInt + 200000000 * 4);
            if (java.lang.String.valueOf(uniqueId).length() == 10 && uniqueId > 0) {                
                    	finalUniqueId = uniqueId;                 
            }
        }        
        return finalUniqueId;
    }

}
