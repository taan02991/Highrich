package UI;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite
{
    protected Image image;
    protected double positionX;
    protected double positionY;    
    protected double velocityX;
    protected double velocityY;
    protected double width;
    protected double height;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
    }
    
    public Sprite(Image i, double positionX, double positionY, double velocityX, double velocityY) {
    	this.setImage(i);
    	this.setPosition(positionX , positionY);
    	this.setVelocity(velocityX, velocityY);
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update()
    {
        positionX += velocityX;
        positionY += velocityY;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
    
    
}