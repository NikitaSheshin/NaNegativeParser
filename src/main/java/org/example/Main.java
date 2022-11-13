package org.example;

import Parser.Parser;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws DocumentException, IOException {

        Parser parser = Parser.initParser("https://nanegative.ru/aliekspress");

        parser.parse();
    }
}