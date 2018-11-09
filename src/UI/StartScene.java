package UI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
		background.setImage( Images.startBackground );
		background.setPreserveRatio( true );
		background.setFitWidth( 500 );
		
		startButton = new ImageView();
		startButton.setLayoutX(138);
		startButton.setLayoutY(251);
		startButton.setImage( Images.startButton );
		buttonEventHandler();
		
		root.getChildren().addAll( background, startButton);
	}
	
	private void buttonEventHandler() {
		startButton.setOnMouseClicked( e-> {
			
		});
		startButton.setOnMouseEntered( e-> {
			startButton.setImage( Images.startButtonHover );
		});
		startButton.setOnMouseExited( e-> {
			startButton.setImage( Images.startButton );
		});
	}

}
