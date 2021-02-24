
package app.picture.parser.jaxb;

import app.picture.XMLParser;
import app.shape.PaintShape;
import app.shape.Square;
import java.io.File;
import java.math.BigInteger;
import java.util.List;
import javafx.scene.paint.Color;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import jaxb.ObjectFactory;
import jaxb.Shape;
import jaxb.Shapes;


public class JAXBParser implements XMLParser{

    @Override
    public List<PaintShape> readPicture(String pictureName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void savePicture(List<PaintShape> paintShapes, String pictureName) {
        try{
            JAXBContext jAXBContext = JAXBContext.newInstance("jaxb.picture");
            Marshaller marshaller = jAXBContext.createMarshaller();
            ObjectFactory objectFactory = new ObjectFactory();
            Shapes shapes = objectFactory.createShapes();
            for(PaintShape paintshape : paintShapes){
                Shape shape = objectFactory.createShape();
                shape.setX(new BigInteger(paintshape.getX()+""));
                shape.setY(new BigInteger(paintshape.getY()+""));
                shape.setColor(paintshape.getColor().equals(Color.BLUE)?"PLAVA":"CRVENA");
                shape.setType((paintshape instanceof Square)?"KVADRAT":"KRUG");
                shapes.getShape().add(shape);
            }
            marshaller.marshal(shapes, new File(pictureName));
        }catch(Exception exc){
            throw new RuntimeException(exc.getMessage());
        }
    }
    
}
