package UI;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Rectangle
{
    protected Image image;
    protected double positionX;
    protected double positionY;    
    protected double width;
    protected double height;

    public Rectangle()
    {
        positionX = 0;
        positionY = 0;    
    }
    
    public Rectangle(Image i, double positionX, double positionY) {
    	this.setImage(i);
    	this.setPosition(positionX , positionY);
    }
    
    public Rectangle(double positionX, double positionY) {
    	this.setPosition(positionX , positionY);
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }


    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Rectangle s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    
    
}