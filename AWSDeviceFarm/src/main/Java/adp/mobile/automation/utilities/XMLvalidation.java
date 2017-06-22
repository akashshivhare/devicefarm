package adp.mobile.automation.utilities;

/**
 * Created by BhuteSac on 9/7/2016.
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLvalidation {
    public boolean xmlvalidate(String filelocation, String verifytext, String attributeName, String node){
String actualtext = "";
        boolean flag = false;
        try {
            File inputFile = new File(filelocation);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName(node);
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                Element eElement = (Element) nNode;
                System.out.println("Value for XML Node : - " + eElement.getTextContent());
                actualtext = eElement.getElementsByTagName(attributeName)
                        .item(0).getTextContent();
                //String ssnvalue = getExcelData().get("SSN");
                String ssnvalue = "515563452";
                if (eElement.getTextContent().contains(ssnvalue)) {
                    if (verifytext.equalsIgnoreCase(actualtext)) {
                        break;
                    }
                }
            }
            if(actualtext.equalsIgnoreCase(verifytext)){
                flag= true;
            }
            System.out.println("Actual Value : - "+actualtext + " Expected Value : - "+ verifytext);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            return flag;

        }

    }

}
