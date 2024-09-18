package com.assignment8;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class KeywordDriven_FrameWork {
	
	@DataProvider(name="Swara")
	public Object[] readdata() throws InvalidFormatException, IOException{
		Object[][] data=null;
	String filepath="F:\\Selenium\\22may.xlsx";
	
	File file=new File(filepath);
	
	XSSFWorkbook workbook=new XSSFWorkbook(file);
	
	Sheet sheet =workbook.getSheet("Sheet2");
	
	int n_row=sheet.getPhysicalNumberOfRows();
	System.out.println("no of row is :"+n_row);
	
	data=new Object[n_row][];
	for (int i = 0; i < data.length; i++) {
		Row row =sheet.getRow(i);
		
		//6.to select col
		int ncol=row.getPhysicalNumberOfCells();
		System.out.println("no of col is :"+ncol);
		data[i]=new Object[ncol];
		for (int j = 0; j < data[i].length; j++) {
			
			
			Cell cell=row.getCell(j);
			//7.to convert all value into string 
			cell.setCellType(CellType.STRING);
			
			//8.to get value from the cell
			data[i][j]=cell.getStringCellValue();
	}
	}
	return data;
}

WebDriver driver=null;
@Test(dataProvider = "Swara")
public void test(String keyword) throws InterruptedException {
	
	if (keyword.equalsIgnoreCase("open browser")) {
		System.setProperty("webdriver.edge.driver","F:\\Selenium\\msedgedriver.exe");
		driver=new EdgeDriver();
	}
	else if (keyword.equalsIgnoreCase("enter url")) {
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	else if (keyword.equalsIgnoreCase("enter username")) {
		driver.findElement(By.name("user-name"))
		.sendKeys("standard_user");
		Thread.sleep(2000);
	}
	else if (keyword.equalsIgnoreCase("enter password")) {
		driver.findElement(By.name("password"))
		.sendKeys("secret_sauce");
		Thread.sleep(2000);
	}
	else if (keyword.equalsIgnoreCase("click login")) {
		driver.findElement(By.name("login-button"))
		.click();
		Thread.sleep(2000);
	}
	else if (keyword.equalsIgnoreCase("close browser")) {
		driver.close();
	}
}
}
