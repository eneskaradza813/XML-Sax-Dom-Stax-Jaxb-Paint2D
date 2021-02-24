package app.picture.parser.sax;

import app.PaintWindow;
import app.picture.PaintPanel;
import app.picture.XMLParser;
import app.shape.PaintShape;
import app.shape.Square;
import com.sun.prism.paint.Color;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;

public class SAXParser implements XMLParser {

    @Override
    public List<PaintShape> readPicture(String pictureName) {
        SAXParserFactory saxpf = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser sAXParser;
        try {
            sAXParser = saxpf.newSAXParser();
            PictureHandler pictureHandler = new PictureHandler();
            sAXParser.parse(pictureName, pictureHandler);
            return pictureHandler.getPaintShapes();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void savePicture(List<PaintShape> paintShapes, String pictureName) {
        try (PrintWriter out = new PrintWriter(new FileWriter(pictureName))) {
            out.println("<? version =\"1.0\"?>");
            out.println("<shapes>");
            List<PaintShape> shapes = PaintWindow.getInstance().getPaintPanel().getPaintShapes();
            for (PaintShape paintShape : shapes) {
                out.println("<shape>");
                out.println("<x>" + paintShape.getX() + "</x>");
                out.println("<y>" + paintShape.getY() + "</y>");
                String color = paintShape.getColor().equals(Color.BLUE) ? "Plava" : "Crvena";
                out.println("<color>" + color + "</color>");
                String type = (paintShape instanceof Square) ? "Kvadrat" : "Krug";
                out.println("<type>" + type + "</type>");
                out.println("</shape>");
            }
            out.println("</shapes>");
            PaintPanel paintPanel = PaintWindow.getInstance().getPaintPanel();
            paintPanel.getPaintShapes().clear();
            paintPanel.repaint();
        } catch (Exception exc) {
            throw new RuntimeException(exc.getMessage());
        }
    }

}
