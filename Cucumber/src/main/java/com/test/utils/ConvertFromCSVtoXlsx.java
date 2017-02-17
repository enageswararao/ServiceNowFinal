package com.test.utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
/*import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;*/
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.service.config.AppConfiguration;

public class ConvertFromCSVtoXlsx {
	
	public   void csvToXLSX(XSSFWorkbook workBook,String filename, String sheetno) {
        try {
            String csvFile = filename; //csv file address
            String excelFile = AppConfiguration.filepathexcel;            
            XSSFSheet sheet = workBook.createSheet( filename.split("\\W+")[7]);
            String currentLine = null;
            int RowNum = 0;
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(",");
                XSSFRow currentRow = sheet.createRow(RowNum);
                RowNum++;
                for (int i = 0; i < str.length; i++) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("successfully converted from csv to xls file format");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Exception in try");
        }
    }

 
	
	public static void main(String args[]){
		ConvertFromCSVtoXlsx cfs=new ConvertFromCSVtoXlsx();
		List<String> filepathList=new ArrayList<String>();
		filepathList.add("D:\\PDF_Test\\TestSuite.csv");
		filepathList.add("D:\\PDF_Test\\TestCase.csv");
		filepathList.add("D:\\PDF_Test\\TestSteps.csv");
		String detxlxs="D:\\PDF_Test\\test.xlsx";
		 
		   int size = filepathList.size();
		   XSSFWorkbook workBook = new XSSFWorkbook();
	        for (int i = 0; i < size; i++) {
	            cfs.csvToXLSX(workBook,filepathList.get(i).toString(), "sheet" + i + 1);
	        }
		
	}
}
