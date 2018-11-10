package character;

import UI.AnimatedImage;
import UI.Images;
import javafx.scene.image.Image;

public class Player extends AnimatedImage{
	private static final Image[] playerWalkL = Images.playerHorizontal;
	//private static final Image[] playerWalkR = 
	private static final Image[] playerWalkU = Images.playerVertical;
	//private static final Image[] playerWaldD =
	
	public Player(Image[] frames) {
		super(frames);
	}
	
	public void setFacing() {
		if(this.velocityX < 0) this.setFrame(playerWalkL);
		else if(this.velocityX > 0) this.setFrame(playerWalkL);
		else if(this.velocityY > 0) this.setFrame(playerWalkU);
		else if(this.velocityY < 0) this.setFrame(playerWalkU);
	}
	

	
	
	
	
}
