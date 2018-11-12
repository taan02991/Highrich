package UI;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {

		public static final Image startBackground = new Image(ClassLoader.getSystemResource("images/StartScene.png").toString());
		public static final Image startButton = new Image(ClassLoader.getSystemResource("images/StartButton.png").toString());
		public static final Image startButtonHover = new Image(ClassLoader.getSystemResource("images/StartButtonHover.png").toString());
		public static final Image floor = new Image(ClassLoader.getSystemResource("images/floor.png").toString());
		//Add Player PIC later
		public static final Image[] playerL = {new Image(ClassLoader.getSystemResource("images/PlayerL.png").toString())};
		public static final Image[] playerR = {new Image(ClassLoader.getSystemResource("images/PlayerR.png").toString())};
		public static final Image[] playerU = {new Image(ClassLoader.getSystemResource("images/PlayerU.png").toString())};
		public static final Image[] playerD = {new Image(ClassLoader.getSystemResource("images/PlayerD.png").toString())};
		//
		public static final Image tree = new Image(ClassLoader.getSystemResource("images/Tree.png").toString());
		public static final Image sofa = new Image(ClassLoader.getSystemResource("images/Sofa.png").toString());
		public static final Image warp = new Image(ClassLoader.getSystemResource("images/Warp.png").toString());
		
		static {
			
		}

}
