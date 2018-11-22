package character;

import UI.AnimatedImage;
import UI.Images;
import javafx.scene.image.Image;
import map.Map;

public class Npc extends AnimatedImage{
	
	private Image[] npcR;
	private Image[] npcL;
	private Image[] npcU;
	private Image[] npcD;
	private boolean isActive;

	public Npc(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map,
			double positionX, double positionY, double velocityX, double velocityY) {
		super(npcR, map, positionX, positionY, velocityX, velocityY);
		this.setNpcImage(npcL, npcR, npcU, npcD);
		this.isActive = true;
	}
	
	public void setNpcImage(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD) {
		this.npcL = npcL;
		this.npcR = npcR;
		this.npcU = npcU;
		this.npcD = npcD;
	}
	
	public void setFacing(String s) {
		if(s.equals("LEFT")) {
			super.setFrame(npcL);
		}
		else if(s.equals("RIGHT")) {
			super.setFrame(npcR);
		}
		else if(s.equals("UP")) {
			super.setFrame(npcU);
		}
		else if(s.equals("DOWN")) {
			super.setFrame(npcD);
		}
	}	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
