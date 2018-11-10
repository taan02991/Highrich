package character;

import UI.AnimatedImage;
import UI.Images;
import javafx.scene.image.Image;

public class Player extends AnimatedImage{
	private static final Image[] playerL = Images.playerL;
	private static final Image[] playerR = Images.playerR;
	private static final Image[] playerU = Images.playerU;
	private static final Image[] playerD = Images.playerD;
	
	public Player(Image[] frames) {
		super(frames);
	}
	
	public void setFacing() {
		if(this.velocityX < 0) this.setFrame(playerL);
		else if(this.velocityX > 0) this.setFrame(playerR);
		else if(this.velocityY > 0) this.setFrame(playerD);
		else if(this.velocityY < 0) this.setFrame(playerU);
	}
	

	
	
	
	
}
