package character;

import javafx.scene.image.Image;
import map.Map;

public class Visitor extends Npc implements Walkable{

	public Visitor(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map) {
		super(npcL, npcR, npcU, npcD, map, 250, 480, 0, 0);
		super.setFacing("UP");
	}
	
	@Override
	public void walk() {
		this.walkToReception();
		this.talkWithReceptionist();
		this.walkToWarpUp();
	}
	
	public void walkToReception() {
		//if(super)
		
	}
	
	public void talkWithReceptionist() {
		
	}
	
	public void walkToWarpUp() {
		super.getMap().getNpcList().remove(this);
	}

}
