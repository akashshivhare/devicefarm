package adp.mobile.automation.utilities;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import aws.DeviceFarmTestNG.BaseTempletSteps;

public class GenerateTabularReport extends BaseTempletSteps{
	
	
	public String getReportData(){
		
		String sRowColor = "";
		String body = "";
		String sColor = "";

		body += "<HTML><BODY><FONT FACE=VERDANA COLOR=BLACK SIZE=2>Hi All,<BR/><BR/>Please find automation report below:"
				+ "<BR/><BR/></FONT>";
		body += "<BR/>";


		body += "<TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100% BGCOLOR=BLACK><TR><TD WIDTH=90% ALIGN=CENTER BGCOLOR=WHITE><FONT FACE=VERDANA COLOR=RED SIZE=3><B>ADP</B></FONT></TD></TR><TR><TD ALIGN=CENTER BGCOLOR=ORANGE><FONT FACE=VERDANA COLOR=WHITE SIZE=3><B>Selenium BDD Framework Reporting</B></FONT></TD></TR></TABLE><BR/>";
        body += "<TABLE  CELLPADDING=3 CELLSPACING=1 WIDTH=100%>";           
        body += "<TR COLS=6 BGCOLOR=ORANGE><TD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>S. No.</B></FONT></TD><TD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Machine Name</B></FONT></TD><TD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Browser Name</B></FONT></TD><TD WIDTH=7%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Browser Version</B></FONT></TD><TD  WIDTH=30%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Test Case Name</B></FONT></TD><TD  WIDTH=6%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SSN</B></FONT></TD><TD  WIDTH=5%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD><TD  WIDTH=9%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Duration</B></FONT></TD></TR>";
		        
		int i=0;
		for(ReportMapping mapping: comprehensiveMap){
			i++;
			if (i % 2 == 0) {
		        sRowColor = "#EEEEEE";
		      } else {
		        sRowColor = "#D3D3D3";
		      }
			
			if(mapping.getStatus().equals("PASS")){
				sColor = "GREEN";
			}else{				
				sColor = "RED";
			}
						
			body += "<TR COLS=6 BGCOLOR=" + sRowColor + "><TD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + i + "</FONT></TD><TD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + mapping.getMachineName() + "</FONT></TD><TD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + mapping.getBrowserName() + "</FONT></TD><TD WIDTH=7%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + mapping.getBrowserVersion() + "</FONT></TD><TD  WIDTH=30%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + mapping.getTestName() + "</FONT></TD><TD WIDTH=6%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + mapping.getSSN() + "</FONT></TD><TD  WIDTH=5%><FONT FACE=VERDANA COLOR=" + sColor + " SIZE=2><B>" + mapping.getStatus() + "</B></FONT></TD><TD WIDTH=9%><FONT FACE=VERDANA COLOR=BLACK SIZE=2>" + mapping.getDuration() + "</FONT></TD></TR>";
		}
		
		body += "</TABLE> <BR><BR><FONT FACE=VERDANA COLOR=BLACK SIZE=2>Please find attached report for more details.</FONT>";


		body += "<BR/><BR/><BR/>Thanks & Regards,<BR/>Tax Credits Automation Team</FONT></BODY></HTML>";;
		
		System.out.println(body);
		
		return body;		
	}
	
	public void generateReportFile(String reportBody, String filePath) {
		try {
			OutputStream htmlfile= new FileOutputStream(new File(filePath));
			PrintStream printhtml = new PrintStream(htmlfile);
			printhtml.print(reportBody);
			printhtml.close();
	        htmlfile.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
