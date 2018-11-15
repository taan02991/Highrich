package character;

import UI.AnimatedImage;
import UI.Images;
import UI.Rectangle;
import controller.KeyInput;
import javafx.scene.image.Image;
import map.Map;

public class Player extends AnimatedImage implements Walkable{
	private static final Image[] PLAYERL = Images.PLAYERL;
	private static final Image[] PLAYERR = Images.PLAYERR;
	private static final Image[] PLAYERU = Images.PLAYERU;
	private static final Image[] PLAYERD = Images.PLAYERD;
	private static int Money;
	
	public Player(Image[] frames, Map map, double positionX, double positionY, double velocityX, double velocityY) {
		super(frames, map, positionX, positionY, velocityX, velocityY);
		Money = 20000;
	}

	public static int getMoney() {
		return Money;
	}


	public void payMoney(int o) {
		if( Money - o < 0 ) {
//throw exception
		}
		Money = Money - o;
	}

	public void setFacing() {
		if(super.getVelocityX() < 0) {
			super.setFrame(PLAYERL);
		}
		else if(super.getVelocityX() > 0) {
			super.setFrame(PLAYERR);
		}
		else if(super.getVelocityY() > 0) {
			super.setFrame(PLAYERD);
		}
		else if(super.getVelocityY() < 0) {
			super.setFrame(PLAYERU);
		}
	}
	
	public void setVelocityOnKeyPressed() {
		super.setVelocity(0, 0);
    	if(KeyInput.contains("RIGHT")) {
    		super.setVelocity(2, 0);
    	}
    	if(KeyInput.contains("LEFT")) {
    		super.setVelocity(-2, 0);
    	}
    	if(KeyInput.contains("UP")) {
    		super.setVelocity(0, -2);
    	}
    	if(KeyInput.contains("DOWN")) {
    		super.setVelocity(0, 2);
    	}
	}
	
	public int warp() {
		if(super.getMap().getWarpUp() != null &&
				super.getMap().getWarpUp().intersects(this)) {	
			//set position after warp later
			super.setPosition(100, 100);
			return 1;
		}
		else if(super.getMap().getWarpDown() != null && 
				super.getMap().getWarpDown().intersects(this)) {
			//set position after warp later
			this.setPosition(100, 100);
			return -1;
		}
		return 0;
	}
	
	
	@Override
	public void walk() {
		this.setVelocityOnKeyPressed();
		this.setFacing();	
	}
	
	@Override 
	public void update() {
    	this.walk();
    	super.update();                		                		
	}

	
	
	
	
}
