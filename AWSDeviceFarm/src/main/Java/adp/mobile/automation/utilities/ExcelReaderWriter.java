package adp.mobile.automation.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import aws.DeviceFarmTestNG.BaseTempletSteps;

public class ExcelReaderWriter {

    HashMap<String, String> ScreeningData = new HashMap<String, String>();
    BaseTempletSteps baseTempletSteps = new BaseTempletSteps();

    public String workingDir = System.getProperty("user.dir");
    Cell c;
    public static String sheetName;

    public HashMap<String, String> readxlsxFileDataAsPerCell(String sheetName, String scenario) {
        try{
            FileInputStream fis = new FileInputStream(workingDir + "\\src\\test\\resources\\prerequisitecreation\\ScenarioToAutomateTest.xlsx" );
            // Get the workbook instance for XLSX file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            // Get first sheet from the workbook
            XSSFSheet sheet = wb.getSheet(sheetName);


            Iterator<Row> rowIterator = sheet.iterator();
            int scenarioRow = 0;
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                int rowIndex = nextRow.getRowNum();                
                
                if (rowIndex != 0) {
                    Iterator<Cell> cellIterator = nextRow.cellIterator();                    
                    if(rowIndex == 1){
                    while (cellIterator.hasNext()) {
                    	
                    	Cell nextCell = cellIterator.next();
                        int columnIndex = nextCell.getColumnIndex();                        
                        
                        if (columnIndex != 0) {
                            if(scenario.equals(nextCell.getStringCellValue())){
                            	scenarioRow = columnIndex;
                            	break;
                            }                               
                            }
                    }
                    }
	                   String snenariokey = null;
	                   String snenarioValue = null;
                    
	                   Cell keyCell = nextRow.getCell(1);	                    
	                   snenariokey = returnExcelValues(keyCell);
	                   System.out.print(snenariokey +"=");
                    
                       Cell valuecell = nextRow.getCell(scenarioRow);
                       snenarioValue = returnExcelValues(valuecell);
                       System.out.println(snenarioValue);                      
                       ScreeningData.put(snenariokey, snenarioValue);
                }
            }
            wb.close();
            }
        catch(Exception e)
        {
            System.err.println("Exception :" + e.getMessage());
        }
        return ScreeningData;
    }
    
    
    public void writeSSNAndConfirmationNumber(){
    	System.out.println("SCenario "+baseTempletSteps.getScenarioName());
    	try{
    		FileInputStream fis = new FileInputStream(workingDir + "\\src\\test\\resources\\prerequisitecreation\\ScenarioToAutomateTest.xlsx" );            
            XSSFWorkbook wb = new XSSFWorkbook(fis);            
            System.out.println(baseTempletSteps.getScenarioName());
            XSSFSheet sheet = wb.getSheet(baseTempletSteps.getEnvName());
            Cell cell = null;
            
            
            Iterator<Row> rowIterator = sheet.iterator();
            int scenarioRow = 0;
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                int rowIndex = nextRow.getRowNum();                
                
                if (rowIndex != 0) {
                    Iterator<Cell> cellIterator = nextRow.cellIterator();                    
                    if(rowIndex == 1){
                    while (cellIterator.hasNext()) {
                    	
                    	Cell nextCell = cellIterator.next();
                        int columnIndex = nextCell.getColumnIndex();                        
                        
                        if (columnIndex != 0) {
                            if(baseTempletSteps.getScenarioName().equals(nextCell.getStringCellValue())){
                            	scenarioRow = columnIndex;
                            	System.out.println(columnIndex);
                            	
                            	cell = sheet.getRow(12).getCell(scenarioRow);                    
                                cell.setCellValue(baseTempletSteps.getExcelData().get("Confirmation_Number"));
                                
                                cell = sheet.getRow(13).getCell(scenarioRow);                    
                                cell.setCellValue(baseTempletSteps.getExcelData().get("SSN"));
                                
                                cell = sheet.getRow(14).getCell(scenarioRow);                    
                                cell.setCellValue(baseTempletSteps.getExcelData().get("CSSN"));
                            	break;
                            }                               
                            }
                    }
                    }             
                                       
                    
	                   /*String snenariokey = null;
	                   String snenarioValue = null;
                    
	                   Cell keyCell = nextRow.getCell(1);	                    
	                   snenariokey = returnExcelValues(keyCell);
	                   System.out.print(snenariokey +"=");
                    
                       Cell valuecell = nextRow.getCell(scenarioRow);
                       snenarioValue = returnExcelValues(valuecell);
                       System.out.println(snenarioValue);                      
                       ScreeningData.put(snenariokey, snenarioValue);*/
                }
            }

            fis.close();

            FileOutputStream outFile =new FileOutputStream(new File(workingDir + "\\src\\test\\resources\\prerequisitecreation\\ScenarioToAutomateTest.xlsx"));
            wb.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    public String returnExcelValues(Cell cell){
    	String excelValues = null;
    	switch (cell.getCellType())
        {
            case Cell.CELL_TYPE_BOOLEAN:
            	excelValues = String.valueOf(cell.getBooleanCellValue());
                break;

            case Cell.CELL_TYPE_NUMERIC:                
                excelValues = String.valueOf(cell.getNumericCellValue());
                break;

            case Cell.CELL_TYPE_STRING:                
                excelValues = cell.getStringCellValue();
                break;

            case Cell.CELL_TYPE_BLANK:                
                excelValues = "";
                break;

            default:                
                excelValues = String.valueOf(cell);
        }    	
    	
		return excelValues;    	
    }
    
    
    
}