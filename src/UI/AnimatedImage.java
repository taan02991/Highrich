package UI;

import character.Player;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.Map;

public class AnimatedImage extends Rectangle
{
    private Image[] frames;
    private double moveTick;
    private Map map;
    private double velocityX;
    private double velocityY;
    private double lastPositionX;
    private double lastPositionY; 
    
    public AnimatedImage(Image[] frames,Map map, double positionX, double positionY, double velocityX, double velocityY) {
    	super(positionX, positionY);
    	this.setVelocity(velocityX, velocityY);
    	this.frames = frames;
    	this.map = map;
    	this.moveTick = 0;
    	this.lastPositionX = this.positionX;
    	this.lastPositionY = this.positionY;
    	
    }
    
	public void fixOutOfBound() {
		if(this.positionX < 0) this.positionX = 0;
		if(this.positionX + this.width > 500) this.positionX = 500 - this.width; 
		if(this.positionY < 0) this.positionY = 0;
		if(this.positionY + this.height > 500) this.positionY = 500 - this.height;
	}
	
	public void fixCollide() {
		
		//******if npc,have to check collide with player
		
		for(Rectangle s: map.getStructList()) {
			this.fixCollideWith(s);
		}
		for(Rectangle s: map.getNpcList()) {
			this.fixCollideWith(s);
		}
	}
	
	public void fixCollideWith(Rectangle s) {
		if(s != this && this.intersects(s)) {
			this.positionX = this.lastPositionX;
			this.positionY = this.lastPositionY;
			if(this.lastPositionX + this.width < s.positionX || this.lastPositionX > s.positionX +s.width) {
				if(this.velocityX > 0) this.positionX = s.positionX - this.width;
				else if(this.velocityX < 0) this.positionX = s.positionX + s.width;
			}
			if(this.lastPositionY + this.height < s.positionY || this.lastPositionY > s.positionY + s.height);{
				if(this.velocityY > 0) this.positionY = s.positionY - this.height;
				else if(this.velocityY < 0) this.positionY = s.positionY + s.height;
			}
		}	
	}
    
    @Override
    public void render(GraphicsContext gc)
    {  	
    	this.setImage(this.getFrame(moveTick));
        gc.drawImage(this.image , positionX, positionY );
    }
    
    public void update()
    {
    	this.lastPositionX = this.positionX;
    	this.lastPositionY = this.positionY;
        this.positionX += this.velocityX;
        this.positionY += this.velocityY;
        this.fixOutOfBound();
        this.fixCollide();
        if(this.velocityX != 0 || this.velocityY != 0) {
        	this.moveTick++;
        	if(this.moveTick > 1000) this.moveTick = 0;        	
        }
    }
    
    public void setMap(Map map) {
    	this.map = map;
    }
    
    public Map getMap() {
    	return this.map;
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
    
    public void setVelocity(double x, double y)
    {
        this.velocityX = x;
        this.velocityY = y;
    }

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}  
    
}