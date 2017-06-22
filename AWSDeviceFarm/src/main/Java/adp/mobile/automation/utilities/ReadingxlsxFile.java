package adp.mobile.automation.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bhutesac on 5/18/2016.
 */
public class ReadingxlsxFile {


    public ReadingxlsxFile() throws FileNotFoundException {
    }

    public void readXLSXFile() throws IOException
    {
        String workingDir = System.getProperty("user.dir");
        FileInputStream ExcelFileToRead = new FileInputStream(workingDir + "\\src\\test\\resources\\prerequisitecreation\\Create_prerequisiteData.xlsx");
        XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFWorkbook test = new XSSFWorkbook();

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();
        ArrayList<Object> arraylist = new ArrayList<Object>();

        while (rows.hasNext())
        {
            row=(XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            while (cells.hasNext())
            {
                cell=(XSSFCell) cells.next();

                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
                {
                    arraylist.add(cell);
                }
                if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
                {
                    arraylist.add(cell);
                }
                if(cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN)
                {
                    arraylist.add(cell);
                }
            }
            System.out.println(arraylist);
        }

    }

    public void writeXLSXFile(int rowNumber, int column) throws IOException {

         String workingDir = System.getProperty("user.dir");
        String sheetName = "ScreeningData";//name of sheet

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        CellReference ref = new CellReference("A2");
        Row r = sheet.getRow(ref.getRow());
        if (r != null) {
            Cell c = r.getCell(ref.getCol());
            System.out.println("Value for :" + c);
            c.setCellValue("Cell " + r + " " + c);

        }
        FileOutputStream fileOut = new FileOutputStream(workingDir + "\\src\\test\\resources\\prerequisitecreation\\Create_prerequisiteData.xlsx");

        //write this workbook to an Outputstream.
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

}
