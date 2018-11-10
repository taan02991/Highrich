package UI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimatedImage extends Sprite
{
    private Image[] frames;
    private double moveTick;
    
    public AnimatedImage(Image[] frames) {
    	this.frames = frames;
    	this.moveTick = 0;
    }
    
    public void setFrame(Image[] frames)
    {
        this.frames = frames;
    }
     
    public Image getFrame(double moveTick)
    {
        int index = (int)((moveTick % (frames.length * 3)) / 3);
        return frames[index];
    }
    
	public void isOutOfBound() {
		if(this.positionX < 0) this.positionX = 0;
		if(this.positionX + this.width > 500) this.positionX = 500 - this.width; 
		if(this.positionY < 0) this.positionY = 0;
		if(this.positionY + this.height > 500) this.positionY = 500 - this.height;
	}
	
	public void isCollide(Sprite s) {
		if(this.intersects(s)) {
    		this.positionX -= this.velocityX;
    		this.positionY -= this.velocityY;
    	}
	}
    
    @Override
    public void render(GraphicsContext gc)
    {  	
    	this.setImage(this.getFrame(moveTick));
        gc.drawImage(this.image , positionX, positionY );
    }
    
    @Override
    public void update()
    {
        positionX += velocityX;
        positionY += velocityY;
        if(velocityX != 0 || velocityY != 0) {
        	moveTick++;
        	if(moveTick > 1000) moveTick = 0;        	
        }
    }
    
    
    
}