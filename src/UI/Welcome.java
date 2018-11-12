package UI;


import character.Player;
import controller.GameManager;
import controller.KeyInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import map.MapWelcome;

public class Welcome extends Scene {

	private VBox root;
	
	public Welcome() {
		super(new VBox() ,500 ,700);
		root = (VBox) getRoot();
		
		Canvas canvas = new Canvas(500,500);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
        
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
                	gameManager.update();
                	gameManager.render(gc);
                }
            });
        
        
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();

		
	}

}
