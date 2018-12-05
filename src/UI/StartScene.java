package UI;

import application.Main;
import javafx.animation.AnimationTimer;
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
	private int drawState = 1;
	private AnimationTimer animationTimer;
	
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
		root.getChildren().addAll(canvas, startButton);
		
		this.animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				drawBackground(gc);

			}
		};
		this.animationTimer.start();
	}
	
	private void drawBackground(GraphicsContext gc) {
		if(this.drawState == 1) {
			gc.clearRect(0, 0, 500, 700);
			this.drawImage(gc);
			this.positionYOne += 15;
			if(this.positionYOne > 19) {
				this.drawState = 2;
			}
		}else if(this.drawState == 2) {
			gc.clearRect(0, 0, 500, 700);
			this.drawImage(gc);
			this.positionYTwo += 15;
			if(this.positionYTwo > 128) {
				this.drawState = 3;
			}
		}else if(this.drawState == 3) {
			gc.clearRect(0, 0, 500, 700);
			this.drawImage(gc);
			this.positionYThree += 15;
			if(this.positionYThree > 320) {
				this.drawState = 4;
			}
		}else if(this.drawState == 4) {
			gc.clearRect(0, 0, 500, 700);
			this.drawImage(gc);
			this.positionYFour += 15;
			if(this.positionYFour > 339) {
				this.drawState = 5;
			}
		}else if(this.drawState == 5) {
			gc.clearRect(0, 0, 500, 700);
			this.drawImage(gc);
			this.positionYFive -= 12;
			if(this.positionYFive < 475) {
				this.drawState = 6;
			}
		}else if(this.drawState == 6) {
			gc.clearRect(0, 0, 500, 700);
			this.drawImage(gc);
			this.positionYSix -= 12;
			if(this.positionYSix < 345) {
				this.drawState = 7;
			}
		}else if(this.drawState == 7) {
			gc.clearRect(0, 0, 500, 700);
			this.drawImage(gc);
			this.positionYSeven += 12;
			if(this.positionYSeven > 60) {
				this.drawState = 8;
			}
		}else {
			this.startButton.setVisible(true);
			this.animationTimer.stop();
		}
		
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
			Audio.TAP.play();
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
