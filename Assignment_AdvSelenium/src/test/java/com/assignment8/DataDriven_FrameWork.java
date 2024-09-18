package com.assignment8;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class DataDriven_FrameWork {
	public String[][] readData() throws InvalidFormatException, IOException {
		String[][] data = null;
		//1.to get a filepath
		String filepath="F:\\Selenium\\22may.xlsx";
		//2.to make file
		File file=new File(filepath);
		//3.to open a excel file
		XSSFWorkbook workbook =new XSSFWorkbook(file);
		//4.to open a sheet
		Sheet sheet=(Sheet) workbook.getSheet("Sheet1");
		//5.to select a row
		int n_row=sheet.getPhysicalNumberOfRows();
		System.out.println("no of row is :"+n_row);
		data=new String[n_row][];
		for (int i = 0; i < data.length; i++) {
			Row row =sheet.getRow(i);
			
			//6.to select col
			int ncol=row.getPhysicalNumberOfCells();
			System.out.println("no of col is :"+ncol);
			data[i]=new String[ncol];
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
	@Test
	public void test() throws InterruptedException, InvalidFormatException, IOException {
		String[][] data=readData();
		for (int i = 0; i < data.length; i++) {
			
			
			System.setProperty("webdriver.edge.driver", 
					"F:\\Selenium\\msedgedriver.exe");
			 driver=new EdgeDriver();
			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			Thread.sleep(2000);
			
			driver.findElement(By.name("user-name"))
			.sendKeys(data[i][0]);
			Thread.sleep(2000);
			driver.findElement(By.name("password"))
			.sendKeys(data[i][1]);
			Thread.sleep(2000);
			driver.findElement(By.name("login-button")).click();
			Thread.sleep(2000);
			 driver.close();
		}
}
}
