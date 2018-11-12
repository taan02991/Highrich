package character;

import UI.AnimatedImage;
import javafx.scene.image.Image;
import map.Map;

public class Npc extends AnimatedImage{

	public Npc(Image[] frames,Map map, double positionX, double positionY, double velocityX, double velocityY) {
		super(frames,map, positionX, positionY, velocityX, velocityY);
	}
	
	//test talk
	public void talk() {
		System.out.println("talk");
	}
	
}
