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
import org.openqa.selenium.support.ui.Select;
import junit.framework.TestCase;
 
public class EditComputer
extends TestCase
{
    WebDriver driver;
     
    @Test
    public void testEditComputer() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Backbase\\chromedriver.exe");
        driver = new ChromeDriver();

        
        driver.get("http://computer-database.herokuapp.com/computers");
        driver.manage().window().maximize();
        assertTrue(true);
        this.takeScrrenshot("editComputer","Evidence_001");
         
        //search already existed computer
        WebDriverWait wait = new WebDriverWait(driver, 100);
    	WebElement searchElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchbox")));
    	searchElement.clear();
    	searchElement.sendKeys("IBM SureOne");
    	 this.takeScrrenshot("editComputer","Evidence_002");
    	
    	WebElement filterButton = driver.findElement(By.id("searchsubmit"));
    	filterButton.click();
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main")));
    	WebElement computerTable = driver.findElement(By.cssSelector("table.computers"));
   	    this.takeScrrenshot("editComputer","Evidence_003");
    	computerTable.findElement(By.linkText("IBM SureOne")).click();

    	
    	//setting up new values into each field.
    	wait = new WebDriverWait(driver, 10);
    	String editTittle = driver.findElement(By.xpath("//*[@id='main']/h1")).getText();
    	Assert.assertTrue("Text not found!", editTittle.contains("Edit computer"));
    	 this.takeScrrenshot("editComputer","Evidence_004");
    	
    	WebElement computerName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
    	computerName.clear();
    	computerName.sendKeys("iMac Core i7");
    	 this.takeScrrenshot("editComputer","Evidence_005");
    	
    	WebElement introducedDate = driver.findElement(By.id("introduced"));
    	introducedDate.clear();
    	introducedDate.sendKeys("2017-01-08");
    	 this.takeScrrenshot("editComputer","Evidence_006");
    	
    	WebElement discontinuedDate = driver.findElement(By.id("discontinued"));
    	discontinuedDate.clear();
    	discontinuedDate.sendKeys("2020-01-02");   
    	 this.takeScrrenshot("editComputer","Evidence_007");

	 	Select dropdown = new Select(driver.findElement(By.id("company")));
	 	dropdown.selectByVisibleText("Apple Inc.");
	 	 this.takeScrrenshot("editComputer","Evidence_008");
	 	driver.findElement(By.xpath(".//input[@type='submit']")).click();
	 	

	 	//validate success message about update.
	 	wait = new WebDriverWait(driver, 10);
    	String successMessage = driver.findElement(By.cssSelector("div.alert-message.warning")).getText();
    	Assert.assertTrue("Text not found!", successMessage.contains("Computer iMac Core i7 has been updated"));
    	System.out.println("IBM SureOne has been edited to iMac Core i7 model");
    	this.takeScrrenshot("editComputer","Evidence_009");
        driver.close();
       
    }  
    
    public void  takeScrrenshot(String folder,String PageName) throws IOException   {
     	 
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File("C:\\Backbase\\regressionTest\\Screenshots\\"+folder+"\\"+PageName+".png"));
    }
    
}