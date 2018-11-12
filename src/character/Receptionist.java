package character;

import UI.Images;
import javafx.scene.image.Image;
import map.Map;

public class Receptionist extends Npc{
	
	public Receptionist(Image[] frames, Map map, double positionX, double positionY) {
		super(frames , map, positionX, positionY, 0, 0);
		//Have to change to Receptionist pic later
		this.setFrames(Images.playerL, Images.playerR, Images.playerU, Images.playerD);
	}

}
