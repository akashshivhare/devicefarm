package adp.mobile.automation.utilities;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bhutesac on 8/4/2016.
 */
public class XlsFileValidation {
    public String workingDir = System.getProperty("user.dir");
    Cell c;

    public void setSheetName(String sheetName) {
        XlsFileValidation.sheetName = sheetName;
    }

    public static String sheetName;

    public Object readxlsxFileDataAsPerCell(String cellrefrence, String actualpath, String textToVerify) throws IOException {
        try{
            FileInputStream fis = new FileInputStream(actualpath);
            // Get the workbook instance for XLSX file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            // Get first sheet from the workbook
            XSSFSheet sheet = wb.getSheet(XlsFileValidation.sheetName);
            // Cell c;
            CellReference ref = new CellReference(cellrefrence);
            Row r = sheet.getRow(ref.getRow());
            if (r != null) {
                this.c = r.getCell(ref.getCol());
                System.out.println("Value for Cell Location " +cellrefrence+" as below : ");
            }

            //Check the cell type and format accordingly
            switch (this.c.getCellType())
            {
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.println(this.c.getBooleanCellValue());
                    break;

                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(this.c.getNumericCellValue());
                    break;

                case Cell.CELL_TYPE_STRING:
                    System.out.println(this.c.getStringCellValue());
                    break;

                case Cell.CELL_TYPE_BLANK:
                    System.out.println(" ");
                    break;

                default:
                    System.out.println(this.c);

            }
            wb.close();
            return this.c;

        }
        catch(Exception e)
        {
            System.err.println("Exception :" + e.getMessage());
        }
        return null;

    }

    public void writeXLSXFile(String writeCellLocation, String value) throws IOException {

        FileInputStream fis = new FileInputStream(this.workingDir + "\\src\\test\\resources\\prerequisitecreation\\ScenarioToAutomate.xlsx" );
        //  String sheetName = "ScreeningData";//name of sheet
        fis.markSupported();
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        // XSSFSheet sheet = wb.createSheet(sheetName);
        XSSFSheet sheet = wb.getSheetAt(1);
        CellReference ref = new CellReference(writeCellLocation);
        Row r = sheet.getRow(ref.getRow());
        if (r != null) {
            Cell c = r.getCell(ref.getCol());
            System.out.println("Value for :" + c);
            c.setCellValue(value);

        }
        FileOutputStream fileOut = new FileOutputStream(this.workingDir + "\\src\\test\\resources\\prerequisitecreation\\ScenarioToAutomate.xlsx");

        //write this workbook to an Outputstream.
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public List readHashSeparatedValueFromXlsx(String cellPosition, String actualpath, String textToVerify) throws IOException {
        Object cellData = this.readxlsxFileDataAsPerCell(cellPosition, actualpath,textToVerify);
        // ArrayList<String> String = new ArrayList<String>();
        String stringCellData = cellData.toString();
        List<String> dataPerPage = Arrays.asList(stringCellData.split("#"));
        return dataPerPage;
    }

    public boolean verifyExcelText(String cellPosition, String actualpath, String textToVerify) throws IOException
    { boolean flag = false;
        List<String> excelDate = readHashSeparatedValueFromXlsx(cellPosition,actualpath, textToVerify);
        System.out.println("Data From Excel : - " + excelDate);
        String actualString = excelDate.get(0);
        System.out.println("Actual Data From Excel : - " + actualString);

        if(textToVerify.equalsIgnoreCase(actualString)){
            flag=true;
    }
        System.out.println("Flag Value for Excel Records : - " + flag);
        return flag;
    }


    public Boolean xlsValidate(String fileLocation, String cellrefrence, String textToVerify) throws IOException {
        try {
            boolean flag = false;
            Row row = null;
            int rowNum =0;
            FileInputStream fis = new FileInputStream(fileLocation);
            // Get the workbook instance for XLSX file
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            // Get first sheet from the workbook
            HSSFSheet sheet = wb.getSheetAt(0);
            // Cell c;
            sheet.getLastRowNum();
            //String ssnvalue = getExcelData().get("SSN");
            String ssnvalue = "821821822";
                HSSFSheet spreadsheet = sheet;
                Iterator<Row> rowIterator = spreadsheet.iterator();
                while (rowIterator.hasNext()) {
                    row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();


                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (ssnvalue.trim().equalsIgnoreCase(cell.getStringCellValue().trim())) {
                                rowNum = row.getRowNum();
                                break;
                            }
                        }
                    }
                }

            row = sheet.getRow(rowNum);
            CellReference ref = new CellReference(cellrefrence+rowNum);
            System.out.println("Cell Reference : - "+ cellrefrence+rowNum);

            if (row != null) {
                this.c = row.getCell(ref.getCol());
                System.out.println("Value for Cell Location " + cellrefrence+rowNum + " as below : ");
            }

            //Check the cell type and format accordingly
            switch (this.c.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.println(this.c.getBooleanCellValue());
                    break;

                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(this.c.getNumericCellValue());
                    break;

                case Cell.CELL_TYPE_STRING:
                    System.out.println(this.c.getStringCellValue());
                    break;

                case Cell.CELL_TYPE_BLANK:
                    System.out.println(" ");
                    break;

                default:
                    System.out.println(this.c);

            }
            if(c.getStringCellValue().contains(textToVerify))
            {
                flag= true;
            }
            wb.close();
            System.out.println("Expected Value : " +textToVerify + " Actual Value : "+c.getStringCellValue());
            return flag;


        } catch (Exception ex) {
            System.err.println("Exception :" + ex.getMessage());
        }
        return null;
    }

}
