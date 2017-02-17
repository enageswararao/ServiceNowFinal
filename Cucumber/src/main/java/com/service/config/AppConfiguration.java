package com.service.config;

public interface AppConfiguration {
	public String baseUrl="https://dev23996.service-now.com";	
	public String httpHost="dev23996.service-now.com";
	public String apiUserName="admin";
	public String  apiPWd="Google@123";
    public String filepathexcel=System.getProperty("user.dir")+"\\TestData\\test.xlsx";
    public String pdffilepath=System.getProperty("user.dir")+"\\PDFResult\\";
    
}
