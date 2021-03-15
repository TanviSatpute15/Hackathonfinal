package com.AutomationScript.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.io.Files;


public class AutomateCopWellness1 extends BaseUI {

	// navigation the corporate wellness page.
	public void newwindow() {

		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> itr = windowIDs.iterator();
		String mainpage = itr.next();
		String nextpage = itr.next();
		driver.switchTo().window(nextpage);
	}

	// entering the details in the forms
	public void enterdetails() throws IOException {

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "\\testdata\\testdata hackathon.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheetAt(1);// sheet (1)
		XSSFRow wr = ws.getRow(1);
		XSSFCell wc = wr.getCell(0);
		XSSFCell wc1 = wr.getCell(1);
		XSSFCell wc2 = wr.getCell(2);
		XSSFCell wc3 = wr.getCell(3);
		String data1 = wc.getStringCellValue();
		String data2 = wc1.getStringCellValue();
		String data3 = wc2.getStringCellValue();
		String data4 = wc3.getStringCellValue();

		// enter the name.
		enterText("name_id", data1);
		// enter the organization name.
		enterText("org_id", data2);
		// enter the email id.
		enterText("offemail_id", data3);
		// enter the phone Number.
		enterText("offphn_id", data4);
		/// click on the submit.
		clickElement("submit_id");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
	}

	// Capturing the warning message from the alert box.
	public void alertBox() {
		Alert alt = driver.switchTo().alert();
		System.out.println("*********************Alert Message for invalid details***********************");
		System.out.println("****************************************************************************");
		System.out.println(alt.getText());

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		alt.accept();
	}	
	
		//taking the screenShot
		public void screenshoot() throws IOException
		{ 
			   //ScreenShot:
			   TakesScreenshot capture = (TakesScreenshot) driver;
			   File srcFile = capture.getScreenshotAs(OutputType.FILE);
			   File destFile = new File("E:\\hackathon latest\\FindingDetails\\screenshot\\Screen.png");
			    Files.copy(srcFile, destFile);
			   
	}

}