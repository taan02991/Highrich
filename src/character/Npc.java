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

	public Npc(Image[] frames,Map map, double positionX, double positionY, double velocityX, double velocityY) {
		super(frames, map, positionX, positionY, velocityX, velocityY);
	}
	
	public void setFrames(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD) {
		this.npcL = npcL;
		this.npcR = npcR;
		this.npcU = npcU;
		this.npcD = npcD;
	}
	
	public void setFacing() {
		if(this.velocityX < 0) this.setFrame(npcL);
		else if(this.velocityX > 0) this.setFrame(npcR);
		else if(this.velocityY > 0) this.setFrame(npcD);
		else if(this.velocityY < 0) this.setFrame(npcU);
	}
	
	//test talk
	public void talk() {
		System.out.println("talk");
	}
	
}
