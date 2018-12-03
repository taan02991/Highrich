package UI;

import application.Main;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StartScene extends Scene{

	private Pane root;
	private ImageView background;
	private ImageView startButton;
	
	public StartScene() {
		
		super( new Pane() ,500 ,700 );
		root = (Pane) getRoot();
		
		background = new ImageView();
		background.setImage( Images.STARTBACKGROUND );
		background.setPreserveRatio( true );
		background.setFitWidth( 500 );
		
		startButton = new ImageView();
		startButton.setLayoutX(138);
		startButton.setLayoutY(251);
		startButton.setImage( Images.STARTBUTTON );
		buttonEventHandler();
		
		root.getChildren().addAll( background, startButton);
	}
	
	private void buttonEventHandler() {
		startButton.setOnMouseClicked( e-> {
			Audio.MENU.setVolume(1);
			Audio.MENU.play();
			Main.gameScene();
		});
		startButton.setOnMouseEntered( e-> {
			startButton.setImage( Images.STARTBUTTONHOVER );
			root.setCursor( Cursor.HAND );
		});
		startButton.setOnMouseExited( e-> {
			startButton.setImage( Images.STARTBUTTON );
			root.setCursor( Cursor.MOVE );
		});
	}

}
