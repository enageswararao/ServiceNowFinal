package com.ServiceNow.api;

 

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.jayway.jsonpath.JsonPath;
 
public class GetAction {
	
	public HashMap<String ,HashMap<String ,String>> getRequest() throws HttpException, IOException {
 		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(/*https://dev23996.service-now.com/ */ 
               new AuthScope(new HttpHost("dev23996.service-now.com")),
                new UsernamePasswordCredentials("admin", "Google@123"));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build(); 
 
        try {
            HttpGet httpget = new HttpGet("https://dev23996.service-now.com/api/now/table/u_test_step");
            httpget.setHeader("Accept", "application/json");
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
           
                String responseBody = EntityUtils.toString(response.getEntity());
              // JSONObject jsonObj = new JSONObject(responseBody);
              	 HashMap<String,String> step_1=new HashMap<String,String>(); 
              	 HashMap<String,String> step_2=new HashMap<String,String>(); 
              	 HashMap<String,String> step_3=new HashMap<String,String>(); 
              	 HashMap<String,String> step_4=new HashMap<String,String>(); 
                List<String> dmeslist = JsonPath.read(responseBody, "$.result[*].u_name");
                List<String> dmeslist1 = JsonPath.read(responseBody, "$.result[*].u_order");
                
                HashMap<String ,HashMap<String ,String>> TestCase1=new  HashMap<String ,HashMap<String ,String>>();
                
                for(int i=1;i<dmeslist1.size();i++){               
                	
                	if(JsonPath.read(responseBody, "$.result["+i+"].u_order").equals("1")){
                		
                   		 
                	step_1.put("u_name", (String) JsonPath.read(responseBody, "$.result["+i+"].u_name"));
                	step_1.put("u_number",(String) JsonPath.read(responseBody, "$.result["+i+"].u_number"));
                	step_1.put("u_order", (String) JsonPath.read(responseBody, "$.result["+i+"].u_order"));
                	step_1.put("u_step_type",(String) JsonPath.read(responseBody, "$.result["+i+"].u_step_type"));
                	step_1.put("u_value",(String) JsonPath.read(responseBody, "$.result["+i+"].u_value"));
                	step_1.put("u_field_name",(String) JsonPath.read(responseBody, "$.result["+i+"].u_field_name"));
                   }
                		
                 	if(JsonPath.read(responseBody, "$.result["+i+"].u_order").equals("2")){
                		
                  		 
                 		step_2.put("u_name",(String) JsonPath.read(responseBody, "$.result["+i+"].u_name"));
                 		step_2.put("u_number",(String) JsonPath.read(responseBody, "$.result["+i+"].u_number"));
                 		step_2.put("u_order", (String) JsonPath.read(responseBody, "$.result["+i+"].u_order"));
                 		step_2.put("u_step_type",(String) JsonPath.read(responseBody, "$.result["+i+"].u_step_type"));
                 		step_2.put("u_value",(String) JsonPath.read(responseBody, "$.result["+i+"].u_value"));
                 		step_2.put("u_field_name",(String) JsonPath.read(responseBody, "$.result["+i+"].u_field_name"));

                       }
                    		
               		
                 	if(JsonPath.read(responseBody, "$.result["+i+"].u_order").equals("3")){
                		
                  		 
                 		step_3.put("u_name",(String) JsonPath.read(responseBody, "$.result["+i+"].u_name"));
                 		step_3.put("u_number",(String) JsonPath.read(responseBody, "$.result["+i+"].u_number"));
                 		step_3.put("u_order",(String) JsonPath.read(responseBody, "$.result["+i+"].u_order"));
                 		step_3.put("u_step_type",(String) JsonPath.read(responseBody, "$.result["+i+"].u_step_type"));
                 		step_3.put("u_value",(String) JsonPath.read(responseBody, "$.result["+i+"].u_value"));
                 		step_3.put("u_field_name",(String) JsonPath.read(responseBody, "$.result["+i+"].u_field_name"));
                       }
                 	
                 /*	if(JsonPath.read(responseBody, "$.result["+i+"].u_order").equals("4")){
                		
                 		 
                 		step_4.put("u_name",(String) JsonPath.read(responseBody, "$.result["+i+"].u_name"));
                 		step_4.put("u_number",(String) JsonPath.read(responseBody, "$.result["+i+"].u_number"));
                 		step_4.put("u_order",(String) JsonPath.read(responseBody, "$.result["+i+"].u_order"));
                 		step_4.put("u_step_type",(String) JsonPath.read(responseBody, "$.result["+i+"].u_step_type"));
                 		step_4.put("u_value",(String) JsonPath.read(responseBody, "$.result["+i+"].u_value"));
                 		step_4.put("u_field_name",(String) JsonPath.read(responseBody, "$.result["+i+"].u_field_name"));
                       }
                    		*/
                }
                System.out.println("------------step_1----------------------------"+step_1);
                System.out.println("------------step_2----------------------------"+step_2);
                System.out.println("------------step_3----------------------------"+step_3);
            //    System.out.println("------------step_4----------------------------"+step_4);
                TestCase1.put("step_1", step_1);
                TestCase1.put("step_2", step_2);
                TestCase1.put("step_3", step_3);
             //   TestCase1.put("step_4", step_4);
        		return TestCase1;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

 	}
 
	
	public static void main(String[] args) throws IOException, HttpException {
 		GetAction restAction = new GetAction();
 		restAction.getRequest();
 	}
 
 	
 	
}