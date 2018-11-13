package UI;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Rectangle
{
    private Image image;
    private double positionX;
    private double positionY;    
    private double width;
    private double height;

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
    
    public void setPositionX(double positionX) {
    	this.positionX = positionX;
    }

	public double getPositionX() {
		return positionX;
	}
	
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Image getImage() {
		return image;
	}
	
	
    
    
    
    
    
}