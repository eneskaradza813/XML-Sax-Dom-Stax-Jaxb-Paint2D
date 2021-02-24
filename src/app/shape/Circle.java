package app.shape;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Circle extends PaintShape{

    public Circle(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public Shape createShape() {
        return new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
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
