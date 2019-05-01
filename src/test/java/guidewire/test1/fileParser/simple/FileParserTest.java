package guidewire.test1.fileParser.simple;

import guidewire.test1.fileParser.simple.fixedWidth.Employee;
import org.jsapar.Text2BeanConverter;
import org.jsapar.schema.Schema;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileParserTest {
    public static void main(String[] args) {
        try(Reader schemaReader = new FileReader("F:\\Lithium\\src\\test\\java\\guidewire\\test1\\fileParser\\simple\\fixedWidth\\fileSchema.xml")){
            Reader fileReader = new FileReader("F:\\Lithium\\src\\test\\java\\guidewire\\test1\\fileParser\\simple\\test.csv");
            Schema schema = Schema.ofXml(schemaReader);

            // Ful document in memory parsing
//            TextParser parser = new TextParser(schema);
//            Document document = new Document();
//            DocumentBuilderLineEventListener listener = new DocumentBuilderLineEventListener(document);
//            parser.parse(fileReader,listener);
//            Line firstLine = document.iterator().next();
//            firstLine.getCell("First name").ifPresent(cell -> System.out.println(cell.getStringValue()));


            // interpreted object parsing
            Text2BeanConverter<Employee> converter = new Text2BeanConverter<>(schema);
            converter.convert(fileReader, beanEvent -> {
                Employee employee = beanEvent.getBean();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
