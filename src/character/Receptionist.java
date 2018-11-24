package character;

import javafx.scene.image.Image;
import map.Map;

public class Receptionist extends Npc{
	
	private static int cost = 5000;
	private boolean isBusy;
	
	public Receptionist(Image[] image, Map map, double positionX, double positionY) {
		super(image, image, image, image, map, positionX, positionY, 0, 0);
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
