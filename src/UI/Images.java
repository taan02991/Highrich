package UI;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {

		public static final Image startBackground = new Image(ClassLoader.getSystemResource("images/StartScene.png").toString());
		public static final Image startButton = new Image(ClassLoader.getSystemResource("images/StartButton.png").toString());
		public static final Image startButtonHover = new Image(ClassLoader.getSystemResource("images/StartButtonHover.png").toString());
		public static final Image floor = new Image(ClassLoader.getSystemResource("images/floor.png").toString());
		public static final Image[] playerVertical = new Image[3];
		public static final Image[] playerHorizontal = new Image[3];
		
		static {
			for(int i = 0; i < 3; i++) {
				playerVertical[i] = new Image(ClassLoader.getSystemResource("images/PlayerVertical" + i + 
						".png").toString());
				playerHorizontal[i] = new Image(ClassLoader.getSystemResource("images/PlayerHorizontal" + i + ".png").toString());
			}
		}

}
