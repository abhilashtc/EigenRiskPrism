package org.eigenrisk.commonutilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.eigenrisk.testlab.Log;

import com.google.common.io.Files;
import com.sun.jna.platform.FileUtils;

public class CreateTestReport {
		public static void moveEmailableReport() throws Exception {
		try{
			Date date = new Date();
			System.out.println(date.toString());
			
			String date1 = date.toString();
			date1 = date1.replace(':', '-');
			
			System.out.println(date1);
			
	    	File afile =new File("C:\\EigenRisk_Prism_Project\\EigenRiskPrism\\test-output\\emailable-report.html");
	    	
	    	System.out.println("File -> " + afile.exists());
	    	if(afile.renameTo(new File("C:\\_AutomaionTestResults\\RegressionTestResults_" + date1 +".html"))){
				System.out.println("Test Report generated successful!");
	    	} else {
				System.out.println("Failed to generate Test Report!!!");
	    	}	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}
			
}
