package adp.mobile.automation.utilities;


import com.jramoyo.io.IndexedFileReader;

import aws.DeviceFarmTestNG.BaseTempletSteps;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

public class TextFilesValidation extends BaseTempletSteps {


    public boolean verifytextfilewithloc(String fileLocation, String textToVerify, int from, int to) {

        boolean flag = false;
        String d = "";
        char c ;
        String sCurrentLinetext="";
        //Creating FileReader object
        FileReader fr = null;
        try{
            File filea = new File(fileLocation);
            IndexedFileReader reader = new IndexedFileReader(filea);
            int lineCount = reader.getLineCount();
            for(int i=1; i<= lineCount; i++)
            {
                String ssnvalue = getExcelData().get("SSN");
                SortedMap<Integer,String> lineValues = reader.readLines(i,i);
                String currentStringValue = lineValues.get(i);
                if(currentStringValue.contains(ssnvalue))
                {
                    sCurrentLinetext =lineValues.get(i);
                    break;
                }
            }
            System.out.println("line printed : " + sCurrentLinetext);
            String expectedText = sCurrentLinetext.substring(from,to);
            if(expectedText.contains(textToVerify))
            {
                flag=true;
            }
            System.out.println("String from Position "+from+" to " +to+" is : " + expectedText);
            System.out.println("Expected Text : - " + textToVerify + " Actual Text : - " + expectedText);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
        }


    public boolean verifytextfilewitPipeSep(String fileLocation, String textToVerify,int pipeStart, String formValue) {

        boolean flag = false;
        String d = "";
        String actualvalue ="";
        String sCurrentLine = "";
        //Creating FileReader object
        FileReader fr = null;
        try{
            File filea = new File(fileLocation);
            IndexedFileReader reader = new IndexedFileReader(filea);
            int lineCount = reader.getLineCount();
            for(int i=1; i<= lineCount; i++)
            {
                String ssnvalue = getExcelData().get("SSN");
                // String ssnvalue = "113771746";
                SortedMap<Integer,String> lineValues = reader.readLines(i,i);
                String currentStringValue = lineValues.get(i);
                if(currentStringValue.contains(ssnvalue)&& currentStringValue.contains(formValue))
                {
                    sCurrentLine =lineValues.get(i);
                    break;
                }
            }
          //  String sCurrentLine =lines.get(lineNumber);
            System.out.println("line printed :- " + sCurrentLine);
            List<String> pipeSeparatedValue = Arrays.asList(sCurrentLine.split("\\|"));
            pipeSeparatedValue.size();
            for(int i=1; i<pipeSeparatedValue.size(); i++)
            {
                if(i==pipeStart) {
                    actualvalue = pipeSeparatedValue.get(pipeStart);
                    System.out.println("value for Pipe position = "+pipeStart+" is "+actualvalue);
                    break;
                }
            }
            if(actualvalue.contains(textToVerify))
            {
                flag=true;
            }
            System.out.println("String from Position "+pipeStart+" is : " + actualvalue);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public boolean verifytextfilewithCommaSeparated(String fileLocation, String textToVerify,int textLocation) {

        boolean flag = false;
        String d = "";
        String actualvalue ="";
        String newStringvalue="";
        String sCurrentLine = "";
        //Creating FileReader object
        FileReader fr = null;
        try{
            File filea = new File(fileLocation);
            IndexedFileReader reader = new IndexedFileReader(filea);
            int lineCount = reader.getLineCount();
            for(int i=1; i<= lineCount; i++)
            {
                String ssnvalue = getExcelData().get("SSN");
                //String ssnvalue = "487346564";
                SortedMap<Integer,String> lineValues = reader.readLines(i,i);
                String currentStringValu = lineValues.get(i);
                String currentStringValue =currentStringValu.replace("-","");
                if(currentStringValue.contains(ssnvalue))
                {
                    sCurrentLine =lineValues.get(i);
                    break;
                }
            }
            System.out.println("line printed :- " + sCurrentLine);
            List<String> pipeSeparatedValue = Arrays.asList(sCurrentLine.split(","));
            pipeSeparatedValue.size();
            for(int i=1; i<pipeSeparatedValue.size(); i++)
            {
                if(i==textLocation) {
                    actualvalue = pipeSeparatedValue.get(textLocation);
                    newStringvalue =actualvalue.replace("\"","");
                    System.out.println("value for position = "+textLocation+" is "+newStringvalue);
                    break;
                }
            }
            if(newStringvalue.contains(textToVerify))
            {
                flag=true;
            }
            System.out.println("Value from Position "+textLocation+" is : " + newStringvalue);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}





