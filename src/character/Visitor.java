package character;

import javafx.scene.image.Image;
import map.Map;

public class Visitor extends Npc{

	public Visitor(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map) {
		super(npcL, npcR, npcU, npcD, map, 250, 480, 0, 0);
		this.setFacing("UP");
		this.setVelocity(0, -1);
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
		
		map.getNpcList().remove(this);
	}
	
}
