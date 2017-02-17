package com.ServiceNow.api;

 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;
 
 
public class GetAction2 {
	
	public String getRequest(String url) throws ParseException, IOException  {
		
	       String responseBody=null;
 		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(/*https://dev23996.service-now.com/ */ 
               new AuthScope(new HttpHost("dev23996.service-now.com")),
                new UsernamePasswordCredentials("admin", "Google@123"));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build(); 
 
        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setHeader("Accept", "application/json");
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
           
                  responseBody = EntityUtils.toString(response.getEntity());
               
         
            }catch(Exception e){
            	
            }
            
        } finally {
     
            httpclient.close();
        }
		return responseBody;


 	}
 
	
	public static void main(String[] args) throws IOException, HttpException {
 		GetAction2 restAction = new GetAction2();
 		String url_testSuite="https://dev23996.service-now.com/api/now/table/u_test_suite";
 		String url_testcase="https://dev23996.service-now.com/api/now/table/u_test_case";
 		String url_testSteps="https://dev23996.service-now.com/api/now/table/u_test_step";
 		String jsonStringrep=restAction.getRequest(url_testSteps);
 		System.out.println("------------------------"+jsonStringrep);

        JSONObject output;
        JSONObject output1;
        try {
            output = new JSONObject(jsonStringrep);
            output1 = new JSONObject("u_test_case");

            JSONArray docs = output.getJSONArray("result");
             File file=new File("D:\\PDF_Test\\TestSteps.csv");
            String csv = CDL.toString(docs).toString();
            
            FileUtils.writeStringToFile(file, csv);
            System.out.println("Created successfully .....");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
 	}
 
 	
 	
}