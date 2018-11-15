package UI;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {

		public static final Image STARTBACKGROUND = new Image(ClassLoader.getSystemResource("images/StartScene.png").toString());
		public static final Image STARTBUTTON = new Image(ClassLoader.getSystemResource("images/StartButton.png").toString());
		public static final Image STARTBUTTONHOVER = new Image(ClassLoader.getSystemResource("images/StartButtonHover.png").toString());
		public static final Image FLOOR = new Image(ClassLoader.getSystemResource("images/floor.png").toString());
		//Add Player PIC later
		public static final Image[] PLAYERL = {new Image(ClassLoader.getSystemResource("images/PlayerL.png").toString())};
		public static final Image[] PLAYERR = {new Image(ClassLoader.getSystemResource("images/PlayerR.png").toString())};
		public static final Image[] PLAYERU = {new Image(ClassLoader.getSystemResource("images/PlayerU.png").toString())};
		public static final Image[] PLAYERD = {new Image(ClassLoader.getSystemResource("images/PlayerD.png").toString())};
		//
		public static final Image TREE = new Image(ClassLoader.getSystemResource("images/Tree.png").toString());
		public static final Image SOFA = new Image(ClassLoader.getSystemResource("images/Sofa.png").toString());
		public static final Image WARP = new Image(ClassLoader.getSystemResource("images/Warp.png").toString());
		
		public static final Image TRACTOR = new Image(ClassLoader.getSystemResource("images/Tractor.png").toString());
		
		public static final Image CONSTRUCTIONROOM = new Image(ClassLoader.getSystemResource("images/ConstructionRoom.png").toString());
		public static final Image STANDARDROOM = new Image(ClassLoader.getSystemResource("images/StandardRoom.png").toString());
		public static final Image EXECUTIVEROOM = new Image(ClassLoader.getSystemResource("images/ExecutiveRoom.png").toString());
		public static final Image PRESIDENTIALROOM = new Image(ClassLoader.getSystemResource("images/PresidentialRoom.png").toString());
		
		public static final Image HORIZONTALWALL = new Image(ClassLoader.getSystemResource("images/HorizontalWall.png").toString());
		public static final Image VERTICALWALL = new Image(ClassLoader.getSystemResource("images/VerticalWall.png").toString());

}
