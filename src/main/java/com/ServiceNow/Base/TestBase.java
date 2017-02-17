package com.ServiceNow.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	
	WebDriver driver;
	
	/**
	 * initialize webDriver
	 * Excel sheet initialize
	 */
	public void initialize(){
		
		driver=new FirefoxDriver();
		
	}

}
