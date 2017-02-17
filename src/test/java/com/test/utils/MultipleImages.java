package com.test.utils;

 
 
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
 
public class MultipleImages {
    public static final String[] IMAGES = {
        "D:\\API\\Cloud Environment not accessible.png ",
        "D:\\API\\testImage.png ",
        "D:\\API\\Cloud Environment not accessible.png "
    };
    public static final String DEST = "D:\\PDF_Test\\multiple_images.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MultipleImages().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Image img = Image.getInstance(IMAGES[0]);
        Document document = new Document(img);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        for (String image : IMAGES) {
            img = Image.getInstance(image);
            document.setPageSize(img);
            document.newPage();
           // img.setAbsolutePosition(50, 50);
            img.scaleAbsolute(1300,800);
            document.add(img);
        }
        document.close();
    }
}
