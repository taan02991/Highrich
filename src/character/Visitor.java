package character;

import javafx.scene.image.Image;
import map.Map;

public class Visitor extends Npc{

	public Visitor(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map) {
		super(npcL, npcR, npcU, npcD, map, 250, 480, 0, 0);
		super.setFacing("UP");
		super.setVelocity(0, -1);
	}
	
	public void walk() {
		this.walkToReception();
		this.talkWithReceptionist();
		this.walkToWarpUp();
	}
	
	public void walkToReception() {
	}
	
	public void talkWithReceptionist() {
		
	}
	
	public void walkToWarpUp() {
		
		super.getMap().getNpcList().remove(this);
	}
	
}
