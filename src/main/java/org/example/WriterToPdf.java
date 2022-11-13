package org.example;

import Model.Review;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriterToPdf {
    public static void write(ArrayList<Review> allReviews, String fileName) throws DocumentException, IOException {
        Document document = createNewDocument(fileName);
        document.open();

        var text = createTextFromReviewsInfo(allReviews);
        writeToDocument(document, text);

        document.close();
    }

    private static ArrayList<Paragraph> createTextFromReviewsInfo(ArrayList<Review> reviews) throws DocumentException, IOException {
        ArrayList<Paragraph> paragraphsWithReviews = new ArrayList<>();
        var font = loadFont();

        for(Review currentReview : reviews)
            paragraphsWithReviews.add(new Paragraph(currentReview.getText() + "\n\n", font));

        return paragraphsWithReviews;
    }

    private static Document createNewDocument(String fileName) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance
                (document, new FileOutputStream(fileName + ".pdf"));

        return document;
    }

    private static Font loadFont() throws DocumentException, IOException {
        BaseFont arial = BaseFont.createFont
                ("c:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        return new Font(arial, 10, Font.NORMAL);
    }

    private static void writeToDocument(Document document, ArrayList<Paragraph> text) throws DocumentException {
        for(var paragraph : text)
            document.add(paragraph);
    }
}
