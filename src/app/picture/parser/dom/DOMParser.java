/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.picture.parser.dom;

import app.picture.XMLParser;
import app.shape.Circle;
import app.shape.PaintShape;
import app.shape.Square;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author mesa
 */
public class DOMParser implements XMLParser {

    @Override
    public List<PaintShape> readPicture(String pictureName) {
        try {
            List<PaintShape> paintShapes = new ArrayList<>();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(pictureName);
            NodeList nodeList = document.getElementsByTagName("shape");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Element xElement = (Element) element.getElementsByTagName("x").item(0);
                    Element yElement = (Element) element.getElementsByTagName("y").item(0);
                    Element colorElement = (Element) element.getElementsByTagName("color").item(0);
                    Element typeElement = (Element) element.getElementsByTagName("type").item(0);
                    int x = Integer.parseInt(xElement.getTextContent());
                    int y = Integer.parseInt(yElement.getTextContent());
                    String type = typeElement.getTextContent();
                    Color colorAwt = colorElement.getTextContent().equalsIgnoreCase("PLAVA") ? Color.BLUE : Color.RED;
                    PaintShape paintShape;
                    if ("KVADRAT".equalsIgnoreCase(type)) {
                          paintShape = new Square(i, i, colorAwt);
                    }else{
                        paintShape = new Circle(x, y, colorAwt);
                    }
                    paintShapes.add(paintShape);
                }
            }
            return paintShapes;
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void savePicture(List<PaintShape> paintShapes, String pictureName) {
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element shapesElement = document.createElement("shapes");
            document.appendChild(shapesElement);
            for(PaintShape paintShape : paintShapes){
                Element shapeElement = document.createElement("shape");
                
                Element xElement = document.createElement("x");
                xElement.setTextContent(paintShape.getX()+"");
                shapeElement.appendChild(xElement);
                
                Element yElement = document.createElement("y");
                yElement.setTextContent(paintShape.getY()+"");
                shapeElement.appendChild(yElement);
                
                Element colorElement = document.createElement("color");
                colorElement.setTextContent(paintShape.getColor().equals(Color.BLUE)?"PLAVA":"CRVENA");
                shapeElement.appendChild(colorElement);
                
                Element typeElement = document.createElement("type");
                typeElement.setTextContent((paintShape instanceof Square)?"KVADRAT":"KRUG");
                shapeElement.appendChild(typeElement);
                shapesElement.appendChild(shapeElement);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dOMSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(pictureName));
            transformer.transform(dOMSource, streamResult);
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

}
