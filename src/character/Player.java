package character;

import UI.AnimatedImage;
import UI.Images;
import javafx.scene.image.Image;

public class Player extends AnimatedImage{
	private static final Image[] playerWalkL = {new Image(ClassLoader.getSystemResource("Images/PlayerL.png").toString())};
	private static final Image[] playerWalkR = {new Image(ClassLoader.getSystemResource("Images/PlayerR.png").toString())};
	private static final Image[] playerWalkU = {new Image(ClassLoader.getSystemResource("Images/PlayerU.png").toString())};
	private static final Image[] playerWalkD = {new Image(ClassLoader.getSystemResource("Images/PlayerD.png").toString())};
	
	public Player(Image[] frames) {
		super(frames);
	}
	
	public void setFacing() {
		if(this.velocityX < 0) this.setFrame(playerWalkL);
		else if(this.velocityX > 0) this.setFrame(playerWalkR);
		else if(this.velocityY > 0) this.setFrame(playerWalkD);
		else if(this.velocityY < 0) this.setFrame(playerWalkU);
	}
	

	
	
	
	
}
