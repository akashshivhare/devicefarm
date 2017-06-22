package adp.mobile.automation.utilities;


/**
 * Created by bhutesac on 8/2/2016.
 */
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class PDFfilesValidation {

    /**
     * To verify PDF content in the pdf document
     */

    public boolean verifyPDFContent(String filePath, String reqTextInPDF) throws IOException {

        boolean flag = false;

        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        String parsedText = null;
        File file  = new File(filePath);;
        PDFParser parser ;
        RandomAccessBufferedFileInputStream randomAccessBufferedFileInputStream = new RandomAccessBufferedFileInputStream(file);
        try {

             parser = new PDFParser(randomAccessBufferedFileInputStream);

            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            int endpage = pdfStripper.getEndPage();
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(endpage);

            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (MalformedURLException e2) {
            System.err.println("PDF FILE could not be parsed " + e2.getMessage());
        } catch (IOException e) {
            System.err.println("Unable to open PDF Parser. " + e.getMessage());
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e.printStackTrace();
            }

        }
        finally{

            randomAccessBufferedFileInputStream.close();
        }

        System.out.println("+++++++++++++++++");
        System.out.println(parsedText);
        System.out.println("+++++++++++++++++");

        if(parsedText.contains(reqTextInPDF)) {
            flag=true;
        }
       // cosDoc.close();
        cosDoc.close();
        cosDoc.dereferenceObjectStreams();
        pdDoc.close();
        return flag;
    }



}