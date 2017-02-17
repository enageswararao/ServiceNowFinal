package com.test.utils;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TestDemo {

	public static void main(String[] args)
	{
		
		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
		Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
		Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
	    Document document = new Document();
	    try
	    {
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\PDF_Test\\AddTableExample.pdf"));
	        document.open();
	 
	        PdfPTable table = new PdfPTable(1); // 3 columns.
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	 
	        //Set Column widths
	        float[] columnWidths = {1f };
	        table.setWidths(columnWidths);
	 
	        PdfPCell cell1 = new PdfPCell(new Paragraph("TC_Suites :                                 TC-01-LoginTestCases   ")); 
	        cell1.setBorderColor(BaseColor.BLUE);
	        cell1.setPaddingLeft(10);
	        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 

	        PdfPCell cell11 = new PdfPCell(new Paragraph("TC_Name :                                 TC-01-LoginTestCases   ")); 
	        cell1.setBorderColor(BaseColor.BLUE);
	        cell1.setPaddingLeft(10);
	        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table.addCell(cell1);
	        table.addCell(cell11);
	        document.add(table);
 
	       // document.add(new Paragraph("Image Example1").setSpacingAfter(10f));
	        document.add(new Paragraph(10f,"Image Example1",redFont));
	        Image image1 = Image.getInstance("D:\\API\\Cloud Environment not accessible.png ");
	        //Fixed Positioning
	        image1.setAbsolutePosition(40f, 550f);
	        //Scale to new height and new width of image
	        image1.scaleAbsolute(500,200);
	        //Add to document
	        document.add(image1);
	   
	        
	        
	        
	        document.add(new Paragraph(10f,"Image Example2",redFont));
	        Image image2 = Image.getInstance("D:\\API\\Cloud Environment not accessible.png ");
	        //Fixed Positioning
	        image1.setAbsolutePosition(40f, 550f);
	        //Scale to new height and new width of image
	        image1.scaleAbsolute(500,200);
	        //Add to document
	        document.add(image2);
	   
	        document.close();
	        writer.close();
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}

}
