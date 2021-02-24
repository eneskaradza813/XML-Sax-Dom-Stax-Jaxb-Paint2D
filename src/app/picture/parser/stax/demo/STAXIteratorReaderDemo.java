package app.picture.parser.stax.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class STAXIteratorReaderDemo {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xMLInputFactory = XMLInputFactory.newFactory();
        XMLEventReader xMLEventReader = xMLInputFactory.createXMLEventReader(new FileReader("note.xml"));
        while(xMLEventReader.hasNext()){
            XMLEvent xMLEvent = xMLEventReader.nextEvent();
            int typeElement = xMLEvent.getEventType();
            switch (typeElement) {
                case XMLStreamReader.START_ELEMENT:
                    System.out.println("Start Element: " + xMLEvent.asStartElement().getName());
                    break;
                case XMLStreamReader.CHARACTERS:
                    System.out.println("Data of element: " + xMLEvent.asCharacters().getData());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    System.out.println("End element : " + xMLEvent.asEndElement().getName());
                    break;
            }
        }        
    }
}
