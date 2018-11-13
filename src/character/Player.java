package character;

import UI.AnimatedImage;
import UI.Images;
import UI.Rectangle;
import controller.KeyInput;
import javafx.scene.image.Image;
import map.Map;

public class Player extends AnimatedImage{
	private static final Image[] PLAYERL = Images.PLAYERL;
	private static final Image[] PLAYERR = Images.PLAYERR;
	private static final Image[] PLAYERU = Images.PLAYERU;
	private static final Image[] PLAYERD = Images.PLAYERD;
	
	public Player(Image[] frames, Map map, double positionX, double positionY, double velocityX, double velocityY) {
		super(frames, map, positionX, positionY, velocityX, velocityY);
	}
	
	public void setFacing() {
		if(this.velocityX < 0) this.setFrame(PLAYERL);
		else if(this.velocityX > 0) this.setFrame(PLAYERR);
		else if(this.velocityY > 0) this.setFrame(PLAYERD);
		else if(this.velocityY < 0) this.setFrame(PLAYERU);
	}
	
	public void setVelocityOnKeyPressed() {
		super.setVelocity(0, 0);
    	if(KeyInput.contains("RIGHT")) super.setVelocity(5, 0);
    	if(KeyInput.contains("LEFT")) super.setVelocity(-5, 0);
    	if(KeyInput.contains("UP")) super.setVelocity(0, -5);
    	if(KeyInput.contains("DOWN")) super.setVelocity(0, 5);
	}
	
	public int warp() {
		if(this.getMap().getWarpUp() != null && this.getMap().getWarpUp().intersects(this)) {
			
			//set position after warp later
			this.setPosition(100, 100);
			
			return 1;
		}
		else if(this.getMap().getWarpDown() != null && this.getMap().getWarpDown().intersects(this)) {
			
			//set position after warp later
			this.setPosition(100, 100);

			return -1;
		}
		return 0;
	}
	
	//test talk
	public void talkWith() {
		if(KeyInput.contains("SPACE")) {
			System.out.println("Space");
			for(Npc npc: this.map.getNpcList()) {
				if(this.intersects(npc)) {
					npc.talk();
				}
			}
		}
	}
	
	@Override 
	public void update() {
		this.talkWith();
    	this.setVelocityOnKeyPressed();
		this.setFacing();
    	super.update();                		                		
	}

	
	
	
	
}
