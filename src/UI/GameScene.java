package UI;


import controller.GameManager;
import controller.KeyInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameScene extends Scene {

	public static Timeline gameLoop;
	public static KeyFrame kf;
	public static StackPane stackPane;
	public static GraphicsContext gc;
	private VBox root;
	private ControlBar controlBar;
	
	public GameScene() {
		super(new VBox() ,500 ,700);
		root = (VBox) getRoot();
		
		Canvas canvas = new Canvas(500,498);
		
		stackPane = new StackPane();
		stackPane.setPrefHeight(500);
		stackPane.setPrefWidth(498);
		stackPane.getChildren().addAll(canvas);
		ControlBar controlBar = new ControlBar();
		
		root.getChildren().add(stackPane);
		root.getChildren().add(controlBar);
	
		this.gc = canvas.getGraphicsContext2D();
		
        //Prepare Image
		GameManager gameManager = new GameManager();
		
    	//To read input from keyBoard
        KeyInput.setKeyHandler(this);
        
        //Loop to update()
		Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        

        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.016),                // 60 FPS
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                	if(!GameManager.isGamePausing()) {
                		gameManager.update(gc);
                		gameManager.render(gc);
                	}
                	controlBar.update();
                	

                }
            });        
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();		
	}
	


}
