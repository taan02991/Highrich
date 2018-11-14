package UI;

import character.Player;
import controller.GameManager;
import controller.KeyInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.Map;
import map.MapUpStair;
import map.Room;

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
    	this.lastPositionX = super.getPositionX();
    	this.lastPositionY = super.getPositionY();
    }
    
	private void fixOutOfBound() {
		if(super.getPositionX() < 0) {
			super.setPositionX(0);
		}
		if(super.getPositionX() + super.getWidth() > 500) {
			super.setPositionX(500 - super.getWidth()); 
		}
		if(super.getPositionY() < 0) {
			super.setPositionY(0);
		}
		if(super.getPositionY() + super.getHeight() > 500) {
			super.setPositionY(500 - super.getHeight());
		}
	}
	
	private void fixCollide() {
		
		/* if npc,have to check collide with player */
		for(Rectangle s: map.getStructList()) {
			this.fixCollideWith(s);
		}
		for(Rectangle s: map.getNpcList()) {
			this.fixCollideWith(s);
		}
		
		//check bump tractor
		if( map instanceof MapUpStair && this instanceof Player ) {
			for(Room o: ((MapUpStair) map).getRoomsList()) {
				
				/*this.tractorTab[0] = KeyInput.contains("ENTER") && this.intersects(o.getTractor());
				if( this.tractorTab[0] == true && this.tractorTab[1] == false ) {
					System.out.println("ppp");
				}
				this.tractorTab[1] = this.tractorTab[0];*/
				/*fixCollideWithTractor(o.getTractor());*/
			}
		}
	}
	
	/*private void fixCollideWithTractor(Rectangle s) {
		if(s != this && this.intersects(s)) {
			
			super.setPositionX(this.lastPositionX);
			super.setPositionY(this.lastPositionY);
			
			if(this.lastPositionX + super.getWidth() < s.getPositionX()
					|| this.lastPositionX > s.getPositionX() +s.getWidth()) {
				if(this.velocityX > 0) {
					super.setPositionX(s.getPositionX() - super.getWidth());
				}else{
					if(this.velocityX < 0) {
						super.setPositionX(s.getPositionX() + s.getWidth());
					}
				}
			}
			
			if(this.lastPositionY + super.getHeight() < s.getPositionY()
					|| this.lastPositionY > s.getPositionY() + s.getHeight());{
				if(this.velocityY > 0) {
					super.setPositionY(s.getPositionY() - super.getHeight());
				}else {
					if(this.velocityY < 0) {
						super.setPositionY(s.getPositionY() + s.getHeight());
					}
				}
			}
					
		}	
	}*/
	
	private void fixCollideWith(Rectangle s) {
		if(s != this && this.intersects(s)) {
			
			super.setPositionX(this.lastPositionX);
			super.setPositionY(this.lastPositionY);
			
			if(this.lastPositionX + super.getWidth() < s.getPositionX()
					|| this.lastPositionX > s.getPositionX() +s.getWidth()) {
				if(this.velocityX > 0) {
					super.setPositionX(s.getPositionX() - super.getWidth());
				}else{
					if(this.velocityX < 0) {
						super.setPositionX(s.getPositionX() + s.getWidth());
					}
				}
			}
			
			if(this.lastPositionY + super.getHeight() < s.getPositionY()
					|| this.lastPositionY > s.getPositionY() + s.getHeight());{
				if(this.velocityY > 0) {
					super.setPositionY(s.getPositionY() - super.getHeight());
				}else {
					if(this.velocityY < 0) {
						super.setPositionY(s.getPositionY() + s.getHeight());
					}
				}
			}
					
		}
	}
    
    @Override
    public void render(GraphicsContext gc)
    {  	
    	this.setImage(this.getFrame(moveTick));
        gc.drawImage(super.getImage() , super.getPositionX(), super.getPositionY() );
    }
    
    public void update()
    {
    	this.lastPositionX = super.getPositionX();
    	this.lastPositionY = super.getPositionY();
        super.setPositionX(super.getPositionX() + this.velocityX);
        super.setPositionY(super.getPositionY() + this.velocityY);
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