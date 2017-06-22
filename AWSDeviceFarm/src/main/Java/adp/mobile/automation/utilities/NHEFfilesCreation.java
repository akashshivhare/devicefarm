package adp.mobile.automation.utilities;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import aws.DeviceFarmTestNG.BaseTempletSteps;

/**
 * Created by BhuteSac on 10/14/2016.
 */
public class NHEFfilesCreation extends BaseTempletSteps {
    public String workingDir = System.getProperty("user.dir");
    DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
    Date date = new Date();


    public void nhefHireWithCurrentDate(String fileName) throws IOException {
        String currentDate = dateFormat.format(date);
        String currentDt = currentDate.substring(4, 6);
        System.out.println(currentDt);
        FileReader fr = null;
        try {
            File filea = new File(workingDir+"\\src\\test\\resources\\NHFLFiles\\"+fileName);

                String ssnvalue = getExcelData().get("SSN");
            System.out.println("NHEF Hiring for SSN Record is in progress : " +ssnvalue);
            String nhfl = getExcelData().get("CLIENT_CODE") +"|"+ getExcelData().get("COMPANY_CODE") +"|"+ getExcelData().get("UNIT_CODE") +"|"+ ssnvalue +"|asd23435|Test|Rec|01/07/1991|Street|Dallas|TX|75253|" + currentdateMmDdYyFormat() +"|"+currentdateMmDdYyFormat() +"|"+currentdateMmDdYyFormat() +"|"+currentdateMmDdYyFormat() +"|"+ getExcelData().get("HIRE_JOB_POSITION") +"|"+ getExcelData().get("HIRE_WAGE");
               System.out.println(nhfl);
                FileWriter wr = new FileWriter(filea);
                wr.flush();
                wr.write(nhfl);
                wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        }

    public static void main(String[] args) throws Exception {
        NHEFfilesCreation statexp = new NHEFfilesCreation();
       // statexp.nhefHireWithCurrentDate();
    }


    public void nhefHireSpecifiedDate(String fileName) throws IOException {
        String currentDate = dateFormat.format(date);
        String currentDt = currentDate.substring(4, 6);
        System.out.println(currentDt);
        FileReader fr = null;
        try {
            File filea = new File(workingDir+"\\src\\test\\resources\\NHFLFiles\\"+fileName);

            String ssnvalue = getExcelData().get("SSN");
            System.out.println("NHEF Hiring for SSN Record is in progress : " +ssnvalue);
            String nhfl = getExcelData().get("CLIENT_CODE") +"|"+ getExcelData().get("COMPANY_CODE") +"|"+ getExcelData().get("UNIT_CODE") +"|"+ ssnvalue +"|asd23435|Test|Rec|01/07/1991|Street|Dallas|TX|75253|" +getExcelData().get("HIRE_GAVE_INFO_DATE")+"|"+ getExcelData().get("HIRE_JOB_OFFER_DATE") +"|"+ getExcelData().get("HIRE_DATE") +"|"+ getExcelData().get("HIRE_START_DATE") +"|"+ getExcelData().get("HIRE_JOB_POSITION") +"|"+ getExcelData().get("HIRE_WAGE");
            System.out.println(nhfl);
            FileWriter wr = new FileWriter(filea);
            wr.flush();
            wr.write(nhfl);
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
