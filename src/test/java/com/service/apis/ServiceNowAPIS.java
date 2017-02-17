package com.service.apis;

 

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.service.config.ApiPaths;
import com.service.config.AppConfiguration;
 
 
public class ServiceNowAPIS {
	
	public String getTestDetails(String url,String path,String jsonfilterparameter) throws ParseException, IOException  {
		
	       String responseBody=null;
 		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(/*https://dev23996.service-now.com/ */ 
               new AuthScope(new HttpHost(AppConfiguration.httpHost)),
                new UsernamePasswordCredentials(AppConfiguration.apiUserName, AppConfiguration.apiPWd));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build(); 
 
        try {
        	System.out.println("base url is ---"+url+path);
            HttpGet httpget = new HttpGet(url+path+jsonfilterparameter);
            httpget.setHeader("Accept", "application/json");          
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("-------------------------------------");
                System.out.println(response.getStatusLine());
           
                  responseBody = EntityUtils.toString(response.getEntity());
                  System.out.println("-------------------------"+responseBody);
         
            }catch(Exception e){
            	
            }
            
        } finally {
     
            httpclient.close();
        }
		return responseBody;


 	}
	
	public void allgetJSONValue(String url,String path ) throws ClientProtocolException, IOException{

	       String responseBody=null;
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
     credsProvider.setCredentials(/*https://dev23996.service-now.com/ */ 
            new AuthScope(new HttpHost(AppConfiguration.httpHost)),
             new UsernamePasswordCredentials(AppConfiguration.apiUserName, AppConfiguration.apiPWd));
     CloseableHttpClient httpclient = HttpClients.custom()
             .setDefaultCredentialsProvider(credsProvider)
             .build(); 

     try {
     	System.out.println("base url is ---"+url+path);
         HttpGet httpget = new HttpGet(url+path );
         httpget.setHeader("Accept", "application/json");          
         System.out.println("Executing request " + httpget.getRequestLine());
         CloseableHttpResponse response = httpclient.execute(httpget);
         try {
             System.out.println("-------------------------------------");
             System.out.println(response.getStatusLine());
        
               responseBody = EntityUtils.toString(response.getEntity());
               System.out.println("-------------------------"+responseBody);
      
         }catch(Exception e){
         	
         }
         
     } finally {
   
     }
		 

	}
 
	public void convertFromJSONToCSV(String jsonString,String jsonArray,String filename ){

		  JSONObject output;
 
		     
      	File file=new File(System.getProperty("user.dir")+"\\csv\\"+filename+".csv");
	        try {
	        	  
	
	          if(filename.equals("TestSuite")){
	            output = new JSONObject(jsonString);
	            JSONArray docs = output.getJSONArray(jsonArray);	         	            
	            String csv = CDL.toString(docs);  
	            FileUtils.writeStringToFile(file, csv);
	        	   }
	    
	            if(filename.equals("TestCase")){
	            	output = new JSONObject(jsonString);
	            	 JSONArray docsarray = new JSONArray();	            	 
	            	 docsarray = output.getJSONArray(jsonArray);
		            for (int i = 0, len = docsarray.length(); i < len; i++) {
		                /*obj1 =   (JSONObject) docsarray.getJSONObject(i).get("u_test_suite");
		        
		               HashMap<String,Object> subJson=jsontoMap(obj1.toString() );
		               subJson.remove("link");
			            //System.out.println("value is-------"+subJson+"------------------");
			             
			      
			              
			             docsarray.put(subJson);
			              System.out.println("value is-------"+docsarray+"------------------");
			              docsarray.getJSONObject(i).remove("u_test_suite");
			              System.out.println("value is-------"+docsarray+"------------------");*/
		            }
		            String csvtestcase = CDL.toString(docsarray);            
		            FileUtils.writeStringToFile(file, csvtestcase);
	            }
	       
	            if(filename.equals("TestSteps")){
	            	output = new JSONObject(jsonString);
	            	 JSONArray docsarray = new JSONArray();	            	 
	            	 docsarray = output.getJSONArray(jsonArray);
		           /* for (int i = 0, len = docsarray.length(); i < len; i++) {
		                JSONObject obj = (JSONObject) docsarray.getJSONObject(i).remove("u_test_case");
		              
		            }*/
		      
		            String csvtestcase = CDL.toString(docsarray);            
		            FileUtils.writeStringToFile(file, csvtestcase);
	            }
	          
	            System.out.println("Successfully created with file name");
	        } catch (JSONException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	           
	            e.printStackTrace();
	        }        
	    }

	
	public String updateTestCaseLastRunStatus( HashMap<String,String> testcaseinfo,String path,String pathLastRunTest,String statusjson ) throws ParseException, IOException  {
		
	    String responseBody=null;
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(/*https://dev23996.service-now.com/ */ 
            new AuthScope(new HttpHost(AppConfiguration.httpHost)),
             new UsernamePasswordCredentials(AppConfiguration.apiUserName, AppConfiguration.apiPWd));
        CloseableHttpClient httpclient = HttpClients.custom()
             .setDefaultCredentialsProvider(credsProvider)
             .build(); 

     HttpPut httpput ;
     try {
    	 if(path.equals(ApiPaths.table_u_test_step)){
				System.out.println("base url is ---"+ ApiPaths.baseUrl+ path + testcaseinfo.get("sys_id")
						+ pathLastRunTest);

				httpput = new HttpPut(ApiPaths.baseUrl + path + testcaseinfo.get("sys_id")
						+ pathLastRunTest);
				httpput.addHeader("Content-Type", "application/json");
				StringEntity entity = new StringEntity(statusjson);
				httpput.setEntity(entity);

				System.out.println("Executing request " + httpput.getRequestLine());
				CloseableHttpResponse response = httpclient.execute(httpput);
				System.out.println("-------------------------------------");
				System.out.println(response.getStatusLine());

				responseBody = EntityUtils.toString(response.getEntity());
				System.out.println("-------------------------" + responseBody);
    	 }else if(path.equals(ApiPaths.table_u_test_case)){
    		 
				System.out.println("base url is ---" + ApiPaths.baseUrl + path + testcaseinfo.get("tcid")
				+ pathLastRunTest);

		httpput = new HttpPut(ApiPaths.baseUrl+ path + testcaseinfo.get("tcid")
				+ pathLastRunTest);
		httpput.addHeader("Content-Type", "application/json");
		StringEntity entity = new StringEntity(statusjson);
		httpput.setEntity(entity);

		System.out.println("Executing request " + httpput.getRequestLine());
		CloseableHttpResponse response = httpclient.execute(httpput);
		System.out.println("-------------------------------------");
		System.out.println(response.getStatusLine());

		responseBody = EntityUtils.toString(response.getEntity());
		System.out.println("-------------------------" + responseBody);
    	 
        }
 
      
         }catch(Exception e){
         	
         }
         
     finally {
  
         httpclient.close();
     }
		return responseBody;


	}


	
	public  HashMap< String, Object> jsontoMap(String json ) throws JsonParseException, JsonMappingException, IOException{
		
		  ObjectMapper mapper = new ObjectMapper();
	      //  String json = "{\"name\":\"mkyong\", \"age\":29}";

	        HashMap<String, Object> map = new HashMap<String, Object>();

	        // convert JSON string to Map
	        map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});

	        System.out.println(map);
			return map;

	}
 
	
	public static void main(String[] args) throws IOException, HttpException {
 
		
		//TestSuite CSV file Genaration 
		ServiceNowAPIS sapis=new ServiceNowAPIS();
/*		
		String displayTestSuiteparmaeters="?sysparm_query=u_active%3Dtrue%5EORDERBYu_number%5EORDERBYu_order&sysparm_fields=u_name%2Csys_id%2Cu_number%2Cu_active";
		String reponse=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testSuites,displayTestSuiteparmaeters);
		String Filename="TestSuite";
 		sapis.convertFromJSONToCSV(reponse,"result",Filename);
 	 
		//TestCase CSV file Genaration   
	 	String displayTestCaseparmaeters="?sysparm_query=u_active%3Dtrue%5EORDERBYu_number%5EORDERBYu_order&sysparm_fields=u_name%2Csys_id%2Cu_number%2Cu_order%2Cu_active%2Cu_test_suite%2Csys_id%2Cu_description%3Dtrue&sysparm_exclude_reference_link=true";
 
		String reponse_testcase=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testCases,displayTestCaseparmaeters);
		String fileName_testCases="TestCase";
		sapis.convertFromJSONToCSV(reponse_testcase,"result",fileName_testCases);
	 
		//TestCase CSV file Genaration 
  
		//String displayTestStepparameters="?sysparm_fields=u_name%2Cu_number%2Cu_order%2Cu_value%2Cu_step_type.u_value%2Cu_test_case%2Cu_field_name%2Cu_user_name%2Cu_active";
		*/
		String displayTestStepparameters="?sysparm_query=u_active%3Dtrue%5EORDERBYu_test_case%5EORDERBYu_order&sysparm_fields=u_name%2Cu_number%2Cu_order%2Cu_value%2Cu_step_type.u_name%2Cu_test_case%2Cu_field_name%2Csys_id%2Cu_active%3Dtrue&sysparm_exclude_reference_link=true";

		// String displayTestStepparameters="?sysparm_fields=u_name%2Cu_number%2Cu_order%2Cu_value%2Cu_step_type.u_value%2Cu_test_case%2Cu_field_name%2Cu_user_name%2Cu_active";
		String reponse_testSteps=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testSteps,displayTestStepparameters);
		String fileName_testSteps="TestSteps";
		sapis.convertFromJSONToCSV(reponse_testSteps,"result",fileName_testSteps);
	 
		//******************************//
		
		// sapis.allgetJSONValue(AppConfiguration.baseUrl,ApiPaths.testSteps);
		 
	}
 	
}