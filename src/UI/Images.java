package UI;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {

		public static final Image startBackground = new Image(ClassLoader.getSystemResource("images/StartScene.png").toString());
		public static final Image startButton = new Image(ClassLoader.getSystemResource("images/StartButton.png").toString());
		public static final Image startButtonHover = new Image(ClassLoader.getSystemResource("images/StartButtonHover.png").toString());
		public static final Image floor = new Image(ClassLoader.getSystemResource("images/floor.png").toString());
		public static final Image[] playerL = {new Image(ClassLoader.getSystemResource("Images/PlayerL.png").toString())};
		public static final Image[] playerR = {new Image(ClassLoader.getSystemResource("Images/PlayerR.png").toString())};
		public static final Image[] playerU = {new Image(ClassLoader.getSystemResource("Images/PlayerU.png").toString())};
		public static final Image[] playerD = {new Image(ClassLoader.getSystemResource("Images/PlayerD.png").toString())};
		public static final Image[] obstacles = new Image[3];
		
		static {
			for(int i = 0; i < 3; i++) {
				obstacles[i] = new Image(ClassLoader.getSystemResource("images/obstacle" + i + ".png").toString());
			}
		}

}
