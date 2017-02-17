package com.service.config;

public interface ApiPaths {
    String baseUrl="https://dev23996.service-now.com";
	String testSuites="/api/now/table/u_test_suite"; 
	String testCases="/api/now/table/u_test_case";
	String testSteps="/api/now/table/u_test_step";
	String table_u_test_case="/api/now/table/u_test_case/";
	String table_u_test_step="/api/now/table/u_test_step/";
	String fileupload="/ecc_queue.do?JSON&sysparm_action=inser";
	String pathLastRunTestCase="?sysparm_fields=u_last_run_status";
	String pathLastRunTestStep="?sysparm_fields=u_number%2Cu_last_run_status";
	
	String pass="{\"u_last_run_status\":\"Pass\"}";
	String fail="{\"u_last_run_status\":\"Fail\"}";
	
	
}
