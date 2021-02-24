package app.picture.parser.sax;

import app.PaintWindow;
import app.picture.PaintPanel;
import app.shape.Circle;
import app.shape.PaintShape;
import app.shape.Square;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PictureHandler extends DefaultHandler {

    private int x;
    private boolean xOpen = false;
    private int y;
    private boolean yOpen = false;
    private String color;
    private boolean colorOpen = false;
    private String type;
    private boolean typeOpen = false;
    private final List<PaintShape> paintShapes = new ArrayList<>();

    public List<PaintShape> getPaintShapes() {
        return paintShapes;
    }

    
    
    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("x".equals(qName)) {
            xOpen = true;
        } else if ("y".equals(qName)) {
            yOpen = true;
        } else if ("color".equals(qName)) {
            colorOpen = true;
        } else if ("type".equals(qName)) {
            typeOpen = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        if (xOpen) {
            xOpen = false;
            int x = Integer.parseInt(content);
        } else if (yOpen) {
            yOpen = false;
            y = Integer.parseInt(content);
        } else if (colorOpen) {
            colorOpen = false;
            color = content;
        } else if (typeOpen) {
            typeOpen = false;
            type = content;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("type".equals(qName)) {
            Color colorAWT = "PLAVA".equalsIgnoreCase(color) ? Color.BLUE : Color.RED;
            PaintShape paintShape;
            if ("Krug".equalsIgnoreCase(type)) {
                paintShape = new Circle(x, y, colorAWT);
            } else {
                paintShape = new Square(x, y, colorAWT);
            }
            paintShapes.add(paintShape);
        }

    }

    @Override
    public void endDocument() throws SAXException {
        PaintPanel paintPanel = PaintWindow.getInstance().getPaintPanel();
        paintPanel.repaint();
    }

}
