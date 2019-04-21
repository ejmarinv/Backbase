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
 
public class addComputer
    extends TestCase
{
    WebDriver driver;
     
    @Test
    public void testAddComputer() throws Exception {

        System.setProperty("webdriver.chrome.driver", "C:\\Backbase\\chromedriver.exe");
        driver = new ChromeDriver();
         
        
        driver.get("http://computer-database.herokuapp.com/computers");
        driver.manage().window().maximize();
        assertTrue(true);
        
        WebDriverWait wait = new WebDriverWait(driver, 100);
    	WebElement addElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("add")));
    	this.takeScrrenshot("addComputer","Evidence_001");
    	addElement.click();
    	
    
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main")));
    	WebElement computerName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
    	computerName.clear();
    	computerName.sendKeys("IBM SureOne");
    	this.takeScrrenshot("addComputer","Evidence_002");
    	
    	WebElement introducedDate = driver.findElement(By.id("introduced"));
    	introducedDate.clear();
    	introducedDate.sendKeys("2019-10-01");
    	this.takeScrrenshot("addComputer","Evidence_003");
    	
    	WebElement discontinuedDate = driver.findElement(By.id("discontinued"));
    	discontinuedDate.clear();
    	discontinuedDate.sendKeys("2019-10-10");   
    	this.takeScrrenshot("addComputer","Evidence_004");

	 	Select dropdown = new Select(driver.findElement(By.id("company")));
	 	dropdown.selectByVisibleText("IBM");
		this.takeScrrenshot("addComputer","Evidence_005");
	 	driver.findElement(By.xpath(".//input[@type='submit']")).click();
	 
	 	
	 	//Validate success message
	 	wait = new WebDriverWait(driver, 10);
    	String successMessage = driver.findElement(By.cssSelector("div.alert-message.warning")).getText();
    	Assert.assertTrue("Text not found!", successMessage.contains("Computer IBM SureOne has been created"));
    	this.takeScrrenshot("addComputer","Evidence_006");
        //driver.close();
        
    }  
    

     public void  takeScrrenshot(String folder,String PageName) throws IOException    {
      	 
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File("C:\\Backbase\\regressionTest\\Screenshots\\"+folder+"\\"+PageName+".png"));
    }
}
