package com.test.utils;
 
import java.io.FileOutputStream;
import java.util.HashMap;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

 
 	
	public class CreatePDFFile {
		public void createpdfFile(HashMap<String,String> testDetail){
		{
			Document document = new Document();
			Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
			Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
			Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
			try
			{
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\PDF_Test\\AddTableExample.pdf"));
				document.open();
				Anchor anchor = new Anchor("First Chapter", blueFont);
                anchor.setName("First Chapter");

				document.add(new Paragraph("A Hello World PDF document.",redFont));
			    Chapter catPart = new Chapter(new Paragraph(anchor), 1);
				 Paragraph paragraph = new Paragraph();
				  Section subCatPart = catPart.addSection(paragraph);
	                subCatPart.add(paragraph);

	                // add a table
	                createTable(subCatPart);

				/*PdfPTable table = new PdfPTable(3); // 3 columns.
				table.setWidthPercentage(100); //Width 100%
				table.setSpacingBefore(10f); //Space before table
				table.setSpacingAfter(10f); //Space after table

				//Set Column widths
				float[] columnWidths = {1f, 1f, 1f};
				table.setWidths(columnWidths);
				table.setWidths(columnWidths);
				PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
				cell1.setBorderColor(BaseColor.BLUE);
				cell1.setPaddingLeft(30);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
*/
				/*PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
				cell2.setBorderColor(BaseColor.GREEN);
				cell2.setPaddingLeft(10);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 
				PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
				cell3.setBorderColor(BaseColor.RED);
				cell3.setPaddingLeft(10);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);*/
 
				//To avoid having the cell border and the content overlap, if you are having thick cell borders
				//cell1.setUserBorderPadding(true);
				//cell2.setUserBorderPadding(true);
				//cell3.setUserBorderPadding(true);

			/*	table.addCell(cell1);
			//	table.addCell(cell2);
			///	table.addCell(cell3);

				document.add(table);
*/
				document.close();
				writer.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		}
		
		@SuppressWarnings("unused")
		private static void createTable(Section subCatPart) throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

}

		public static void main(String[] args){
			String testSuite="LoginTestSuite";
			String testCase="TC_01_LoginTestCase";
			String step1="step_01 .Click the URL";
			String step2="step_02 .Enter the UserName";
			String step3="step_03 .Enter the Password";
			String step4="step_04 .Click on Submit ";
			HashMap<String,String> testDetail=new HashMap<String,String>();
			testDetail.put("testSuite", testSuite);
			testDetail.put("testCase", testCase);
			testDetail.put("step1", step1);
			testDetail.put("step2", step2);
			testDetail.put("step3", step3);
			testDetail.put("step4", step4);
			CreatePDFFile cpf=new CreatePDFFile();
			cpf.createpdfFile(testDetail);
		
		}
 
	}
