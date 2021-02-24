
package app.shape;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;


public class Square extends PaintShape{

    public Square(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public Shape createShape() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public int getX() {
        return super.getX(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        return super.getY(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor() {
        return super.getColor(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
