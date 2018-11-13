package character;

import UI.Images;
import javafx.scene.image.Image;
import map.Map;

public class Receptionist extends Npc{
	
	private boolean isBusy;
	
	public Receptionist(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map, double positionX, double positionY) {
		super(npcL, npcR, npcU, npcD, map, positionX, positionY, 0, 0);
		this.isBusy = false;
		//Have to change to Receptionist pic later
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

}
