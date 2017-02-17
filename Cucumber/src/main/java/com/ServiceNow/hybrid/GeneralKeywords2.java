package com.ServiceNow.hybrid;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ServiceNow.api.ConstantsValue;

import junit.framework.Assert;

public class GeneralKeywords2 {

	
	
	WebDriver driver;
	public GeneralKeywords2(){
	 
	}
	public boolean openBrowser( ){
		 boolean value = false;
			if("firefox".equals("firefox")){
				
				driver=new FirefoxDriver();
				value=true;
			}
			else if("chrome".equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:/Users/neslavath/Desktop/Cucumber/Cucumber/chromedriver_win32/chromedriver.exe");
				 
				// Initialize browser
				  driver=new ChromeDriver();
					value=true;
			}
			else if("ie".equals("ie")){
				
				value=true;
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return value;
	}
	
	public boolean openURL(HashMap<String,String> stepsinfo ) throws InterruptedException{
		openBrowser();
		 boolean value;
		Thread.sleep(5000);
		driver.get(stepsinfo.get("data"));	
		value=true;
		Thread.sleep(5000);
		
		return value;
		 
	}
	public boolean click(String locator ) throws InterruptedException  {
		boolean value;
		Thread.sleep(5000);
		if(locator.equals("Incidents@linkText")){
			Thread.sleep(5000);
			//switchToFrame("#gsft_nav");
		       WebElement element=getElement(locator);
		       value=true;
		         element.click();
		      	 
		}else if (locator.equals("//a[text()='INC0010009']@xpath")){
			Thread.sleep(5000);
			//switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		       value=true;
		       element.click();
		 
		}
		else if (locator.equals("Normal: Changes without predefined plans that require approval and CAB authorization.@linkText")){
			 driver.switchTo().defaultContent();
			Thread.sleep(5000);
		  switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		       value=true;
		       element.click();
		 
		}
	/*	else if (locator.equals("//span[@class='tab_caption_text' and text()='Notes']@xpath")){
			// driver.switchTo().defaultContent();
			Thread.sleep(5000);
		  switchToFrame("#gsft_main");
		
		       WebElement element=getElement(locator);
		       value=true;
		       element.click();
		 
		}*/
		
		else{
		Thread.sleep(5000);
	    WebElement element=getElement(locator);
	    value=true;
        element.click();
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	return value;
       
	}
	
	
	public boolean enterText(String locator,String data ) throws InterruptedException{
		
		boolean value=false;
		Thread.sleep(5000);
		if(locator.equals("user_name@id")){
			Thread.sleep(5000);
			switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		       value=true;
		         element.clear();
		         element.sendKeys(data);
		 
		}else if (locator.equals("sys_display.incident.caller_id@id")){
			 driver.switchTo().defaultContent();
			Thread.sleep(5000);
			switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		       value=true;
		        // element.clear();
		         element.sendKeys(data);
		 
		}
		
	 else if (locator.equals("div[id='element.incident.short_description'] input[id='incident.short_description']@cssSelector")){
		 driver.switchTo().defaultContent();
		 Thread.sleep(5000);
		switchToFrame("#gsft_main");
	       WebElement element=getElement(locator);
	       value=true;
	       
	        // element.clear();
	         element.sendKeys(data);
	 		
	}
		
	 else if (locator.equals("#list_nav_incident input[placeholder='Search']@cssSelector")){
		 driver.switchTo().defaultContent();
		Thread.sleep(5000);
		switchToFrame("#gsft_main");
	       WebElement element=getElement(locator);
	       value=true;
	        // element.clear();
	         element.sendKeys(data);
	         element.sendKeys(Keys.ENTER);
	}
	 else if (locator.equals("filter@id")){
			Thread.sleep(5000);
			switchToFrame("#gsft_nav");
		       WebElement element=getElement(locator);
		       value=true;
		        // element.clear();
		         element.sendKeys(data);
		 
		}
		else if (locator.equals("change_request.description@id")){
			 driver.switchTo().defaultContent();
			Thread.sleep(5000);
			switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		       value=true;
		        // element.clear();
		         element.sendKeys(data);
		 
		}
	 
		else if (locator.equals("sys_display.change_request.cmdb_ci@id")){
			 driver.switchTo().defaultContent();
			Thread.sleep(5000);
			switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		       value=true;
		        // element.clear();
		         element.sendKeys(data);
		 
		}
		
		else if (locator.equals("change_request.work_notes@id")){
			 driver.switchTo().defaultContent();
			Thread.sleep(5000);
			switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		       value=true;
		        // element.clear();
		         element.sendKeys(data);
		 
		}
		else{
			Thread.sleep(5000);
	       WebElement element=getElement(locator);
	       value=true;
	         element.clear();
	         element.sendKeys(data);
	          
		}
		return value;
  
	}
	
 
	
	public boolean verifyText(String locator,String exceptedText) throws InterruptedException{
		boolean value;
		Thread.sleep(5000);
	    WebElement element=getElement(locator);	       
        if( element.getText().equals(exceptedText))
        {
          value =true;	
          System.out.println("actual Text display ---"+element.getText() +"==="+"ExceptedText----"+exceptedText);
        }
        else{
        	value =false;
        	 System.out.println("actual Text display ---"+element.getText() +"==="+"ExceptedText----"+exceptedText);
        }
	return value;
       
		
	}
	
	public void logout(String locator){
		
		WebElement element= getElement(locator);
		element.click();
		
	}
	
	public void browserQuit(){
		
		driver.close();
	}
	/***************** Utitity Function ************************/
	
	public WebElement getElement(String locator){
		WebElement element=null;
		WebDriverWait wait = new WebDriverWait(driver, 30l);
		try{
		 if(locator.endsWith("@id")){
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.split("@")[0])));
			   element=driver.findElement(By.id(locator.split("@")[0]));
		 }
		 else  if(locator.endsWith("@name")){
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator.split("@")[0])));
			 element= driver.findElement(By.name(locator.split("@")[0]));
		 }
		 else  if(locator.endsWith("@xpath")){
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.split("@")[0])));
			 element=driver.findElement(By.xpath(locator.split("@")[0]));
		 }
		 else  if(locator.endsWith("@className")){
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator.split("@")[0])));
			 element=driver.findElement(By.className(locator.split("@")[0]));
		 }
		 else  if(locator.endsWith("@linkText")){
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator.split("@")[0])));
			 element=driver.findElement(By.linkText(locator.split("@")[0]));
		 }
		 else  if(locator.endsWith("@tagName")){
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locator.split("@")[0])));
			 element=driver.findElement(By.tagName(locator.split("@")[0]));
		 }
		 else  if(locator.endsWith("@cssSelector")){
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator.split("@")[0])));
			 element=driver.findElement(By.cssSelector(locator.split("@")[0]));
		 }
		 else  if(locator.endsWith("@partialLinkText")){
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locator.split("@")[0])));
			 element=driver.findElement(By.partialLinkText(locator.split("@")[0]));
		 }
		}catch(Exception ex){
			Assert.fail("Failure in Element Extraction ..."+locator.split("@")[0]);
		}
		return element;
		
	}
	
	public void switchToFrame(String locator)
	{

		WebElement frame = driver.findElement(By.cssSelector(locator));
		driver.switchTo().frame(frame);
		 
		
	}
	
	public String takeScreenShot(String stepName) {
		File scrFile=null;
		try{
			scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
           FileUtils.copyFile(scrFile, new File(ConstantsValue.filepathScreenshot+stepName+".png"));
		}catch(Exception exception){
			exception.getMessage();
		}
		return scrFile.toString();
	}
	
}
