package UI;

import application.Main;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

public class StartScene extends Scene{

	private StackPane root;
	private ImageView startButton;
	private Canvas canvas;
	private int positionYOne = -456;
	private int positionYTwo = -347;
	private int positionYThree = -155;
	private int positionYFour = -136;
	private int positionYFive = 700;
	private int positionYSix = 700;
	private int positionYSeven = -77;
	
	public StartScene() {
		
		super( new StackPane() ,500 ,700 );
		root = (StackPane) getRoot();
		root.setStyle("-fx-background-color:#fefbf8");

		canvas = new Canvas(500, 700);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		startButton = new ImageView();
		startButton.setLayoutX(138);
		startButton.setLayoutY(251);
		startButton.setImage( Images.STARTBUTTON );
		startButton.setVisible(false);
		buttonEventHandler();
		
		Audio.BGMSTARTSCENE.play();
		Audio.BGMSTARTSCENE.setCycleCount(AudioClip.INDEFINITE);
		
		Thread Background = new Thread(()->{
			try {
				while(this.positionYOne < 19) {
					this.drawImage(gc);
					this.positionYOne++;
					Thread.sleep(2);
					gc.clearRect(0, 0, 500, 700);
				}
				while(this.positionYTwo < 128 ) {
					this.drawImage(gc);
					this.positionYTwo++;
					Thread.sleep(2);
					gc.clearRect(0, 0, 500, 700);
				}
				while(this.positionYThree < 320 ) {
					this.drawImage(gc);
					this.positionYThree++;
					Thread.sleep(2);
					gc.clearRect(0, 0, 500, 700);
				}
				while(this.positionYFour < 339 ) {
					this.drawImage(gc);
					this.positionYFour++;
					Thread.sleep(2);
					gc.clearRect(0, 0, 500, 700);
				}
				while(this.positionYFive > 475 ) {
					this.drawImage(gc);
					this.positionYFive--;
					Thread.sleep(2);
					gc.clearRect(0, 0, 500, 700);
				}
				while(this.positionYSix > 335 ) {
					this.drawImage(gc);
					this.positionYSix--;
					Thread.sleep(1);
					gc.clearRect(0, 0, 500, 700);
				}
				while(this.positionYSeven < 60 ) {
					this.drawImage(gc);
					this.positionYSeven++;
					Thread.sleep(2);
					gc.clearRect(0, 0, 500, 700);
				}
				this.drawImage(gc);
				startButton.setVisible(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Background.start();
		
		
		root.getChildren().addAll(canvas, startButton);
	}
	
	private void buttonEventHandler() {
		startButton.setOnMouseClicked( e-> {
			Audio.BGMSTARTSCENE.stop();
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

	private void drawImage(GraphicsContext gc) {
		gc.drawImage(Images.BACKGROUND[0], 0, this.positionYOne);
		gc.drawImage(Images.BACKGROUND[1], 0, this.positionYTwo);
		gc.drawImage(Images.BACKGROUND[2], 0, this.positionYThree);
		gc.drawImage(Images.BACKGROUND[3], 0, this.positionYFour);
		gc.drawImage(Images.BACKGROUND[4], 0, this.positionYFive);
		gc.drawImage(Images.BACKGROUND[5], 0, this.positionYSix);
		gc.drawImage(Images.BACKGROUND[6], 0, this.positionYSeven);
	}

}
