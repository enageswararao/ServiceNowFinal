package com.service.apis;

import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.http.client.methods.HttpPost;
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

import com.ServiceNow.Utils.Base64EncodingExample;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.jayway.jsonpath.JsonPath;
import com.service.config.ApiPaths;
import com.service.config.AppConfiguration;

public class ServiceNowAPIS_JerseyClient {

	String file = "C:\\Users\\neslavath\\Desktop\\Cucumber\\Cucumber\\PDFResult\\TC_003_UpdateIncident.pdf";

	public String updateTestCaseLastRunStatus(String url, String path, String tcid, String jsonfilterparameter)
			throws ParseException, IOException {

		String responseBody = null;
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials( /*https://dev23996.service-now.com/ */ 
				new AuthScope(new HttpHost(AppConfiguration.httpHost)),
				new UsernamePasswordCredentials(AppConfiguration.apiUserName, AppConfiguration.apiPWd));
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

		try {
			System.out.println("base url is ---" + url + path + tcid + jsonfilterparameter);

			HttpPut httpput = new HttpPut(url + path + tcid + jsonfilterparameter);
			httpput.addHeader("Content-Type", "application/json");
			StringEntity entity = new StringEntity("{\"u_last_run_status\":\"Fail\"}"); //check again here..
			httpput.setEntity(entity);
			System.out.println("--------entity ---------" + entity);
			System.out.println("Executing request " + httpput.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpput);
			try {
				System.out.println("-------------------------------------");
				System.out.println(response.getStatusLine());

				responseBody = EntityUtils.toString(response.getEntity());
				System.out.println("-------------------------" + responseBody);

			} catch (Exception e) {

			}

		} finally {

			httpclient.close();
		}
		return responseBody;

	}

	public String fileattachedTestCaseLastRunStatus() throws ParseException, IOException {
		Base64EncodingExample be = new Base64EncodingExample();
		String responseBody = null;
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials( /*https://dev23996.service-now.com/ */  
			new AuthScope(new HttpHost(AppConfiguration.httpHost)),
				new UsernamePasswordCredentials(AppConfiguration.apiUserName, AppConfiguration.apiPWd));
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

		try {
			 

			HttpPost httppost = new HttpPost("https://dev23996.service-now.com/api/now/table/ecc_queue");
					/*"https://dev23996.service-now.com/ecc_queue.do?JSON&sysparm_action=insert");*/
			 

			httppost.addHeader("Content-Type", "application/json");

			String strencodefiletobase64binary = be.encodeFileToBase64Binary(file);

			StringEntity entity = new StringEntity(
				"{\"agent\": \"AttachmentCreator\",\"topic\": \"AttachmentCreator\",\"name\": \"TC_003_UpdateIncident.pdf:application/pdf\",\"source\": \"u_test_case:168a8f71db10320075277befbf9619f2\",\"payload\": \"'"
							+ strencodefiletobase64binary + "'\"}");
			System.out.println("------Entity-----" + entity);
			httppost.setEntity(entity);

			System.out.println("Executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				System.out.println("-------------------------------------");
				System.out.println(response.getStatusLine());

				responseBody = EntityUtils.toString(response.getEntity());
				System.out.println("-------------------------" + responseBody);
				//16726623db00760075277befbf961930
			} catch (Exception e) {

			}

		} finally {

			httpclient.close();
		}
		return responseBody;

	}

	public static void main(String[] args) throws IOException, HttpException {

		// TestSuite CSV file Genaration
		ServiceNowAPIS_JerseyClient sapis = new ServiceNowAPIS_JerseyClient();

		String displayTestcaseparmaeters = "?sysparm_fields=u_last_run_status";
		String tcid = "f8d0dc27db23220075277befbf9619ce";
		// String
		// reponse=sapis.updateTestCaseLastRunStatus(AppConfiguration.baseUrl,ApiPaths.table_u_test_case,tcid,displayTestcaseparmaeters);

		// sapis.convertFromJSONToCSV(reponse,"result",Filename);

		sapis.fileattachedTestCaseLastRunStatus();

	}

}