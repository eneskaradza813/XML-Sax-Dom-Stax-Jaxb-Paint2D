package app.picture;

import app.shape.PaintShape;
import java.util.List;

public interface XMLParser {

    public List<PaintShape> readPicture(String pictureName);
    
    public void savePicture(List<PaintShape> paintShapes, String pictureName);
}
