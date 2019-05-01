package guidewire.test1.fileParser.masterDetail;
import org.jsapar.schema.Schema;

import java.io.*;

public class MasterDetailLineParser {

    public static void main(String[] args) throws IOException {
        MasterDetailLineParser parser = new MasterDetailLineParser();
        parser.parseFixedWidth();
    }
    /**
     * This example shows how to parse a csv where first line denotes the type of the line.
     *
     * @throws IOException In case of error reading file.
     */
    public void parseFixedWidth() throws IOException {
        try (Reader schemaReader = new FileReader("F:\\Lithium\\src\\test\\java\\guidewire\\test1\\fileParser\\masterDetail\\masterDetailSchema.xml");
             Reader fileReader = new FileReader("F:\\Lithium\\src\\test\\java\\guidewire\\test1\\fileParser\\masterDetail\\masterDetailData.txt")) {
            Schema schema = Schema.ofXml(schemaReader);



            // Full Document Parsing
            /*TextParser parser = new TextParser(schema);

            Document document = new Document();
            DocumentBuilderLineEventListener listener = new DocumentBuilderLineEventListener(document);
            parser.parse(fileReader, listener);

            Line headerLine = document.getLine(0);
            System.out.println(LineUtils.getStringCellValue(headerLine, "FileName"));
            System.out.println(LineUtils.getLocalDateCellValue(headerLine, "Created date").orElse(null));
            System.out.println(headerLine.getLineType());

            System.out.println("--------------------");
            Line line1 = document.getLine(1);
            System.out.println(line1.getLineType());
            System.out.println(LineUtils.getStringCellValue(line1, "Last name"));
            System.out.println(LineUtils.getStringCellValue(line1, "First name"));
            System.out.println(LineUtils.getStringCellValue(line1, "Last name"));

            System.out.println("--------------------");
            Line line2 = document.getLine(2);
            System.out.println(LineUtils.getStringCellValue(line2, "First name"));
            System.out.println(LineUtils.getStringCellValue(line2, "Last name"));

            System.out.println("--------------------");
            Line petLine = document.getLine(3);
            System.out.println(LineUtils.getStringCellValue(petLine, "Name"));

            System.out.println("--------------------");
            Line footerLine = document.getLine(4);
            System.out.println(LineUtils.getStringCellValue(footerLine, "Rowcount"));*/



        }
    }
}