package com.assignment8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*
 * W.a.maven program to create TestNG with Webdriver Program.
 */
public class Mvn_TestNGWebdriver {
WebDriver driver;
	
	@BeforeTest
	public void before() throws InterruptedException {
		System.setProperty("webdriver.edge.driver","F:\\Selenium\\msedgedriver.exe");
		driver=new EdgeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	
	@Test(priority = 2)
	public void clickLogin() throws InterruptedException {
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 0)
	public void enterLogin() throws InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		Thread.sleep(2000);
	}
	
	@Test(priority = 1)
	public void enterPassword() throws InterruptedException {
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		Thread.sleep(2000);
	}
	
	@AfterClass
	public void after() throws InterruptedException {
		driver.close();
		Thread.sleep(2000);
	}
}
