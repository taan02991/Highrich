package character;

import UI.AnimatedImage;
import UI.Images;
import UI.Rectangle;
import controller.KeyInput;
import javafx.scene.image.Image;
import map.Map;

public class Player extends AnimatedImage{
	private static final Image[] playerL = Images.playerL;
	private static final Image[] playerR = Images.playerR;
	private static final Image[] playerU = Images.playerU;
	private static final Image[] playerD = Images.playerD;
	
	public Player(Image[] frames, Map map, double positionX, double positionY, double velocityX, double velocityY) {
		super(frames, map, positionX, positionY, velocityX, velocityY);
	}
	
	public void setFacing() {
		if(this.velocityX < 0) this.setFrame(playerL);
		else if(this.velocityX > 0) this.setFrame(playerR);
		else if(this.velocityY > 0) this.setFrame(playerD);
		else if(this.velocityY < 0) this.setFrame(playerU);
	}
	
	public void setVelocity() {
		super.setVelocity(0, 0);
    	if(KeyInput.contains("RIGHT")) super.setVelocity(5, 0);
    	if(KeyInput.contains("LEFT")) super.setVelocity(-5, 0);
    	if(KeyInput.contains("UP")) super.setVelocity(0, -5);
    	if(KeyInput.contains("DOWN")) super.setVelocity(0, 5);
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
    	this.setVelocity();
		this.setFacing();
    	super.update();                		                		
	}

	
	
	
	
}
