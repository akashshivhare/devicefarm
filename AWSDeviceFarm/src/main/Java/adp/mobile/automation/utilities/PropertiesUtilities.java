package adp.mobile.automation.utilities;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import aws.DeviceFarmTestNG.BaseTempletSteps;

/**
 * Created by BhuteSac on 4/3/2016.
 */
public class PropertiesUtilities {
	private static final BaseTempletSteps baseTempletSteps= new BaseTempletSteps();
    public static String loadKeyValue(String Key) {
        String workingDir = System.getProperty("user.dir");
        File file = new File(workingDir +"\\src\\test\\resources\\properties\\app.properties");
        
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        
        try {
            prop.load(fileInput);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(Key);
    }
    
    public static String loadPropValues(String Key) {
        String workingDir = System.getProperty("user.dir");        
        File file = new File(workingDir +"\\src\\test\\resources\\env_properties\\"+baseTempletSteps.getEnvName()+".properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        
        try {
            prop.load(fileInput);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(Key);
    }
    
    public static ArrayList<String> getAllEmailList() {
    	List<String> emailList = new ArrayList<String>();
        String workingDir = System.getProperty("user.dir");
        File file = new File(workingDir +"\\src\\test\\resources\\email_properties\\email.properties");


        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        
        try {
            prop.load(fileInput);            
            Enumeration enuKeys = prop.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = prop.getProperty(key);
				emailList.add(value);
				System.out.println(key + ": " + value);
			}
            

        } catch (IOException e) {
            e.printStackTrace();
        }
        return (ArrayList<String>) emailList;
    }    

}
