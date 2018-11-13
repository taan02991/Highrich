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

	public Npc(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map,
			double positionX, double positionY, double velocityX, double velocityY) {
		super(npcR, map, positionX, positionY, velocityX, velocityY);
		this.setNpcImage(npcL, npcR, npcU, npcD);
	}
	
	public void setNpcImage(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD) {
		this.npcL = npcL;
		this.npcR = npcR;
		this.npcU = npcU;
		this.npcD = npcD;
	}
	
	public void setFacing(String s) {
		if(s.equals("LEFT")) this.setFrame(npcL);
		else if(s.equals("RIGHT")) this.setFrame(npcR);
		else if(s.equals("UP")) this.setFrame(npcU);
		else if(s.equals("DOWN")) this.setFrame(npcD);
	}
	

	
	//test talk
	public void talk() {
		System.out.println("talk");
	}
	
}
