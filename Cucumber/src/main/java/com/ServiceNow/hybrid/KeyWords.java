package com.ServiceNow.hybrid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.http.ParseException;

import com.ServiceNow.Utils.Xls_Reader;
import com.service.apis.ServiceNowAPIS;
import com.service.config.ApiPaths;

public class KeyWords  {

	 
		
	public List<HashMap<String, String>> executeKeywords(String currentTestSutieExecutionString,String currentTestCaseExecution,Xls_Reader xls) throws Exception{
		
		List<HashMap<String, String>> list = null;
		HashMap<String,String> testcaseDetails= new HashMap<String,String>();
    	testcaseDetails.put("tS", "TestSuite:");
		testcaseDetails.put("tc", "TestCases:");
		testcaseDetails.put("tStep", "TestSteps:");	
		
		LinkedHashMap<String,String> tSteps=new LinkedHashMap<String,String>();
		 
		LinkedHashMap<String,String> tStepsImages=new LinkedHashMap<String,String>();
		LinkedHashMap<String,String> tStepstatus=new LinkedHashMap<String,String>();
		
		GeneralKeywords app =new GeneralKeywords();
		ServiceNowAPIS spis=new ServiceNowAPIS();
		 

	 	//TestSuite Level Start
		int rowTestSuites=xls.getRowCount("TestSuite");
		for(int rTSNum=2;rTSNum<=rowTestSuites;rTSNum++){
			
			System.out.println("sys_id value ......"+xls.getCellData("TestSuite", "sys_id", rTSNum));
			System.out.println("u_active value ....."+xls.getCellData("TestSuite", "u_active", rTSNum));
		if(xls.getCellData("TestSuite", "sys_id", rTSNum).equals(currentTestSutieExecutionString)&&xls.getCellData("TestSuite", "u_active", rTSNum).equals("true")){
			testcaseDetails.put("tSdesc",xls.getCellData("TestSuite", "u_name", rTSNum));

			
		//TestCase Level Start
		int rowTestCase=xls.getRowCount("TestCase");
		for(int rTCNum=2;rTCNum<=rowTestCase;rTCNum++){
			if(xls.getCellData("TestCase", "sys_id", rTCNum).equals(currentTestCaseExecution)&&xls.getCellData("TestCase", "u_active", rTCNum).equals("true")){
				
				testcaseDetails.put("tCdesc",xls.getCellData("TestCase", "u_name", rTCNum));
				testcaseDetails.put("TestCaseResult", "Passed");
				
		//StepsLevel start...
  		int rows=xls.getRowCount("TestSteps");
  		int step=1;
		for(int rNum=2;rNum<=rows;rNum++){
	 
			HashMap<String,String> stepsinfo=new HashMap<String,String>();
			String tcid=xls.getCellData("TestSteps", "u_test_case", rNum);
			stepsinfo.put("tcid", xls.getCellData("TestSteps", "u_test_case", rNum));
			String step_active=xls.getCellData("TestSteps", "u_active", rNum);
			stepsinfo.put("step_active",xls.getCellData("TestSteps", "u_active", rNum));
			if(tcid.equals(currentTestCaseExecution) && step_active.equals("true") ){
			String keyword=xls.getCellData("TestSteps", "u_step_type.u_name", rNum);
			stepsinfo.put("keyword",xls.getCellData("TestSteps", "u_step_type.u_name", rNum));
			String locator=xls.getCellData("TestSteps", "u_field_name", rNum);
			stepsinfo.put("locator",xls.getCellData("TestSteps", "u_field_name", rNum));
			String data=xls.getCellData("TestSteps", "u_value", rNum);
			stepsinfo.put("data",xls.getCellData("TestSteps", "u_value", rNum));
			String u_number=xls.getCellData("TestSteps", "u_number", rNum);
			stepsinfo.put("u_number",xls.getCellData("TestSteps", "u_number", rNum));
			String u_name=xls.getCellData("TestSteps", "u_name", rNum); 
			stepsinfo.put("u_name",xls.getCellData("TestSteps", "u_name", rNum));
			String sys_id=xls.getCellData("TestSteps", "sys_id", rNum);
			stepsinfo.put("sys_id",xls.getCellData("TestSteps", "sys_id", rNum));

			System.out.println(tcid+"----@-"+keyword+"--@---"+u_number+"----"+locator+"--@---"+sys_id+"-------"+data +"-@-------"+step_active);
				 
			
			tSteps.put("Step_"+step, xls.getCellData("TestSteps", "u_name", rNum));
/*
			 if(stepsinfo.get("keyword").equals("openBrowser")){
				 
					if(app.openBrowser(stepsinfo)){
						tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
						
						spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.pass);
						tStepstatus.put("Step_"+step, "Pass");
						
					}else{
					tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
					spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.fail);
					tStepstatus.put("Step_"+step, "Fail");
					}
				
					step++;
			         }*/
			  if(stepsinfo.get("keyword").equals("openURL")){
		 
				if(app.openURL(stepsinfo)){
					tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
					
					spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.pass);
					tStepstatus.put("Step_"+step, "Pass");
					
				}else{
				tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
				spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.fail);
				tStepstatus.put("Step_"+step, "Fail");
				}
			
				step++;
		         }
			else if(stepsinfo.get("keyword").equals("enterText")){
			 		
								
				if(app.enterText(locator, data )){
					tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
					
					spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.pass);
					tStepstatus.put("Step_"+step, "Pass");
				}else{
				tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
				spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.fail);
				tStepstatus.put("Step_"+step, "Fail");
				}
 
				step++;
			}
			else if(stepsinfo.get("keyword").equals("click")){
		 
				
				if(app.click(locator )){
					tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
					
					spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.pass);
					tStepstatus.put("Step_"+step, "Pass");
				}else{
				tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
				spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.fail);
				tStepstatus.put("Step_"+step, "Fail");
				}

				step++;
			}
		      
			else if(stepsinfo.get("keyword").equals("verifyText")){
		 
				
				if(app.verifyText(locator,data )){
					tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
					
					spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.pass);
					tStepstatus.put("Step_"+step, "Pass");
				}else{
				tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
				spis.updateTestCaseLastRunStatus(stepsinfo, ApiPaths.table_u_test_step, ApiPaths.pathLastRunTestStep,ApiPaths.fail);
				tStepstatus.put("Step_"+step, "Fail");
				}

				step++;
			}
		      
		}
			
		}
		//StepsLevel end...
			}else{
				
		///		TestCases Level Else condition
			}
		}//TestCase Level End
		}else{
			//TestSuite level Else condition
		}
		list=new ArrayList<HashMap<String, String>>();
		list.add(testcaseDetails);
		list.add(tSteps);
		list.add(tStepsImages);
		list.add(tStepstatus);
		}//TestSuite Level End
		
		
		return list;
		
	}
	
}
