package app.picture;

import app.PaintWindow;
import app.shape.Circle;
import app.shape.PaintShape;
import app.shape.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PaintPanel extends JPanel{

    private final List<PaintShape> paintShapes = new ArrayList<>();

    public PaintPanel() {
        DrawListener drawListener = new DrawListener();
        addMouseListener(drawListener);
        addMouseMotionListener(drawListener);
        setBackground(Color.WHITE);
    }

    public List<PaintShape> getPaintShapes() {
        return paintShapes;
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D graphics2D = (Graphics2D)g;
        for(PaintShape paintShape : paintShapes){
            graphics2D.setColor(paintShape.getColor());
            graphics2D.fill(paintShape.createShape());
        }
    }
    
    
    
    private class DrawListener extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            handleEvent(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            handleEvent(e);
        }
     
          private void handleEvent(MouseEvent e){
              int x = e.getX();
              int y = e.getY();
              Color color = PaintWindow.getInstance().getBlueRadioButton().isSelected() ? Color.BLUE : Color.RED;
              boolean circleSelected = PaintWindow.getInstance().getCircleRadioButton().isSelected();
              PaintShape shape = circleSelected ? new Circle(x, y, color):new Square(x, y, color);
              paintShapes.add(shape);
              repaint();
          }
     }
}
