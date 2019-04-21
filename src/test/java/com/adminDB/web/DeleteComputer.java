package com.adminDB.web;
 
import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.TestCase;
 
public class DeleteComputer
    extends TestCase
{
    WebDriver driver;
     
    @Test
    public void testDeleteComputer() throws Exception {
    	//long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
    	
        System.setProperty("webdriver.chrome.driver", "C:\\Backbase\\chromedriver.exe");
        driver = new ChromeDriver();
         
        driver.get("http://computer-database.herokuapp.com/computers");
        driver.manage().window().maximize();
        assertTrue(true);
        this.takeScreenshot("deleteComputer","Evidence_001");
        
        //search already existed computer
        WebDriverWait wait = new WebDriverWait(driver, 100);
    	WebElement searchElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchbox")));
    	searchElement.clear();
    	searchElement.sendKeys("iMac Core i7");
    	this.takeScreenshot("deleteComputer","Evidence_002");
    	
    	WebElement filterButton = driver.findElement(By.id("searchsubmit"));
    	filterButton.click();
    	this.takeScreenshot("deleteComputer","Evidence_003");
    	
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main")));
    	WebElement computerTable = driver.findElement(By.cssSelector("table.computers"));
    	computerTable.findElement(By.linkText("iMac Core i7")).click();
    	
    	
    	//setting up new values into each field.
    	wait = new WebDriverWait(driver, 10);
    	String editTittle = driver.findElement(By.xpath("//*[@id='main']/h1")).getText();
    	Assert.assertTrue("Text not found!", editTittle.contains("Edit computer"));
    	this.takeScreenshot("deleteComputer","Evidence_004");
    	
        //delete computer 
	 	WebElement deleteButton = driver.findElement(By.cssSelector("input.btn.danger"));
	 	deleteButton.click();
	 	
	 	
	 	//validate message
	 	wait = new WebDriverWait(driver, 10);
    	String successMessage = driver.findElement(By.cssSelector("div.alert-message.warning")).getText();
    	Assert.assertTrue("Text not found!", successMessage.contains("Computer has been deleted"));
    	this.takeScreenshot("deleteComputer","Evidence_005");
        driver.close();
        
    }  
    
    public void  takeScreenshot(String folder,String PageName) throws IOException   {
    	 
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File("C:\\Backbase\\regressionTest\\Screenshots\\"+folder+"\\"+PageName+".png"));
    }
    
}
