package adp.mobile.automation.utilities;


import com.jramoyo.io.IndexedFileReader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by BhuteSac on 9/7/2016.
 */
public class CSVfilesValidation {


    public Boolean csvValidate(String fileLocation,String textToVerify, int textLoc) throws IOException {

        boolean flag = false;
        String d = "";
        String actualvalue ="";
        String newStringvalue="";
        String sCurrentLine = "";
      //  CSVReader reader = new CSVReader(new FileReader(fileLocation ));
        File filea = new File(fileLocation);
        IndexedFileReader reader = new IndexedFileReader(filea);
        int lineCount = reader.getLineCount();
        for(int i=1; i<= lineCount; i++)
        {
            //String ssnvalue = getExcelData().get("SSN");
            String ssnvalue = "821821821";
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
        List<String> commaSeparatedValue = Arrays.asList(sCurrentLine.split(","));
        commaSeparatedValue.size();
        for(int i=1; i<commaSeparatedValue.size(); i++)
        {
            if(i==textLoc) {
                actualvalue = commaSeparatedValue.get(textLoc);
                newStringvalue =actualvalue.replace("\"","");
                System.out.println("value for Text/flag on csv file position = "+textLoc+" is "+newStringvalue);
                break;
            }
        }
        if(newStringvalue.contains(textToVerify))
        {
            flag=true;
        }
        System.out.println("Value from Position "+textLoc+" is : " + newStringvalue);
        reader.close();
        return flag;
    }
}
