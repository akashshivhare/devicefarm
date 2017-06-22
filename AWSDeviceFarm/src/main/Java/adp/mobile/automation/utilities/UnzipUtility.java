package adp.mobile.automation.utilities;


/**
 * Created by bhutesac on 8/1/2016.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This utility extracts files and directories of a standard zip file to
 * a destination directory.
 * @author www.codejava.net
 *
 */
public class UnzipUtility {
    /**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;
    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified by
     * destDirectory (will be created if does not exists)
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    public void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                this.extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
    /**
     * Extracts a zip entry (file entry)
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[UnzipUtility.BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public ArrayList<String> listZipFiles(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        ArrayList<String> fileNames = null;
        fileNames = new ArrayList<>();
        for (File file : fList) {
            if (file.isFile() && file.getName().contains(".zip")) {
                fileNames.add(file.getName());
                System.out.println("Zip file Name is : "+ file.getName());
            }
        }
        return fileNames;
    }

    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public ArrayList<String> listPdfFiles(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        ArrayList<String> fileNames = null;
        fileNames = new ArrayList<String>();
        for (File file : fList) {
            if (file.isFile() && file.getName().contains(".pdf")) {
                fileNames.add(file.getName());
                System.out.println("PDF file Names is : "+ file.getName());
            }
        }
        return fileNames;
    }

    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public ArrayList<String> listxlsFiles(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        ArrayList<String> fileNames = null;
        fileNames = new ArrayList<String>();
        for (File file : fList) {
            if (file.isFile() && file.getName().contains(".xlsx") || file.getName().contains(".xls")) {
                fileNames.add(file.getName());
                System.out.println("Excel file Names is : "+ file.getName());
            }
        }
        return fileNames;
    }

    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public ArrayList<String> listTextFiles(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        ArrayList<String> fileNames = null;
        fileNames = new ArrayList<String>();
        for (File file : fList) {
            if (file.isFile() && file.getName().contains(".txt")) {
                fileNames.add(file.getName());
                System.out.println("Text file Names are : "+ file.getName());
            }
        }
        return fileNames;
    }

    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public ArrayList<String> listcsvFiles(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        ArrayList<String> fileNames = null;
        fileNames = new ArrayList<String>();
        for (File file : fList) {
            if (file.isFile() && file.getName().contains(".csv")) {
                fileNames.add(file.getName());
                System.out.println("Text file Names are : "+ file.getName());
            }
        }
        return fileNames;
    }


    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public ArrayList<String> listXmlFiles(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        ArrayList<String> fileNames = null;
        fileNames = new ArrayList<String>();
        for (File file : fList) {
            if (file.isFile() && file.getName().contains(".xml")) {
                fileNames.add(file.getName());
                System.out.println("XML file Names are : "+ file.getName());
            }
        }
        return fileNames;
    }
}