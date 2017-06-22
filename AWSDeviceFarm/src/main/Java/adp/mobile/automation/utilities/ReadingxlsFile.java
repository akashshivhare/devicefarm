package adp.mobile.automation.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bhutesac on 5/17/2016.
 */
 public class ReadingxlsFile {
    public String workingDir = System.getProperty("user.dir");
    Cell c;

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public static String sheetName;

    public Object readxlsxFileDataAsPerCell(String cellrefrence) throws IOException {
        try{
        FileInputStream fis = new FileInputStream(workingDir + "\\src\\test\\resources\\prerequisitecreation\\ScenarioToAutomate.xlsx" );
        // Get the workbook instance for XLSX file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        // Get first sheet from the workbook
        XSSFSheet sheet = wb.getSheet(sheetName);
       // Cell c;
            CellReference ref = new CellReference(cellrefrence);
            Row r = sheet.getRow(ref.getRow());
            if (r != null) {
                 c = r.getCell(ref.getCol());
                System.out.println("Value for Cell Location " +cellrefrence+" as below : ");
            }

            //Check the cell type and format accordingly
            switch (c.getCellType())
            {
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.println(c.getBooleanCellValue());
                    break;

                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(c.getNumericCellValue());
                    break;

                case Cell.CELL_TYPE_STRING:
                    System.out.println(c.getStringCellValue());
                    break;

                case Cell.CELL_TYPE_BLANK:
                    System.out.println(" ");
                    break;

                default:
                    System.out.println(c);

            }
            wb.close();
            return c;
            
    }
    catch(Exception e)
    {
        System.err.println("Exception :" + e.getMessage());
    }
     return null;

}

    public void writeXLSXFile(String writeCellLocation, String value) throws IOException {

        FileInputStream fis = new FileInputStream(workingDir + "\\src\\test\\resources\\prerequisitecreation\\ScenarioToAutomate.xlsx" );
      //  String sheetName = "ScreeningData";//name of sheet

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
        FileOutputStream fileOut = new FileOutputStream(workingDir + "\\src\\test\\resources\\prerequisitecreation\\ScenarioToAutomate.xlsx");

        //write this workbook to an Outputstream.
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public List readHashSeparatedValueFromXlsx(String cellPosition) throws IOException {
        Object cellData = readxlsxFileDataAsPerCell(cellPosition);
       // ArrayList<String> String = new ArrayList<String>();
        String stringCellData = cellData.toString();
        List<String> dataPerPage = Arrays.asList(stringCellData.split("#"));
        return dataPerPage;
    }


    }

