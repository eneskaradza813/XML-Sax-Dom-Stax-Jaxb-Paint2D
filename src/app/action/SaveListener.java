
package app.action;

import app.PaintWindow;
import app.picture.PaintPanel;
import app.picture.XMLParser;
import app.shape.PaintShape;
import app.shape.Square;
import com.sun.prism.paint.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;


public class SaveListener implements ActionListener{

    private static final String PITCTURE_EXTENSION = ".nfs";
    
    private XMLParser xMLParser;

    public SaveListener(XMLParser xMLParser) {
        this.xMLParser = xMLParser;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        String pictureName = JOptionPane.showInputDialog("Unesite ime slike: ");
        if(pictureName == null || pictureName.trim().isEmpty()){
            return;
        }
        if(!pictureName.endsWith(PITCTURE_EXTENSION)){
            pictureName+=PITCTURE_EXTENSION;
        }
        PaintPanel paintPanel = PaintWindow.getInstance().getPaintPanel();
        xMLParser.savePicture(paintPanel.getPaintShapes(), pictureName);
        paintPanel.getPaintShapes().clear();
        paintPanel.repaint();
    }
    
}
