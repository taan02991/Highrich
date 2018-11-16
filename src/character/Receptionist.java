package character;

import UI.Images;
import javafx.scene.image.Image;
import map.Map;

public class Receptionist extends Npc{
	
	private static int cost = 5000;
	private boolean isBusy;
	
	public Receptionist(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map, double positionX, double positionY) {
		super(npcL, npcR, npcU, npcD, map, positionX, positionY, 0, 0);
		this.isBusy = false;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	
	public static int getCost() {
		return cost;
	}
	

}
