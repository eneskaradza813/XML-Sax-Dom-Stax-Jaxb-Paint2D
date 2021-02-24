package app.picture.parser.stax.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StaxCursorReaderDemo {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xMLInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xMLStreamReader = xMLInputFactory.createXMLStreamReader(new FileReader("note.xml"));
        while (xMLStreamReader.hasNext()) {
            int typeOfElement = xMLStreamReader.next();
            switch (typeOfElement) {
                case XMLStreamReader.START_ELEMENT:
                    System.out.println("Start Element: " + xMLStreamReader.getName());
                    break;
                case XMLStreamReader.CHARACTERS:
                    System.out.println("Data of element: " + xMLStreamReader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    System.out.println("End element : " + xMLStreamReader.getName());
                    break;
            }
        }
    }
}
