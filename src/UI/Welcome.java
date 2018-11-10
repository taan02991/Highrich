package UI;

import character.Player;
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

public class Welcome extends Scene {

	private VBox root;
	public Welcome() {
		super(new VBox() ,500 ,700);
		root = (VBox) getRoot();
		
		Canvas canvas = new Canvas(500,500);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
        
        //Prepare Image
        Sprite background = new Sprite(Images.floor, 0, 0, 0, 0);
        Player player = new Player(Images.playerHorizontal);
        Sprite obstacle = new Sprite(Images.startButton, 100, 100, 0, 0);
 
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
                	player.setVelocity(0, 0);
                	if(KeyInput.contains("RIGHT")) player.addVelocity(10, 0);
                	if(KeyInput.contains("LEFT")) player.addVelocity(-10, 0);
                	if(KeyInput.contains("UP")) player.addVelocity(0, -10);
                	if(KeyInput.contains("DOWN")) player.addVelocity(0, 10);
                	player.setFacing();
                	player.update();                		                		
                	player.isOutOfBound();
                	background.render(gc);
                	if(player.intersects(obstacle)) {
                		player.positionX -= player.velocityX;
                		player.positionY -= player.velocityY;
                	}
                	obstacle.render(gc);
                	player.render(gc);
                }
            });
        
        
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();

		
	}

}
