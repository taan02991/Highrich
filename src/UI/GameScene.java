package UI;


import character.Player;
import controller.GameManager;
import controller.KeyInput;
import controller.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameScene extends Scene {

	private VBox root;
	private GridPane control;
	
	
	public static Timeline gameLoop;
	public static KeyFrame kf;
	
	public GameScene() {
		super(new VBox() ,500 ,700);
		root = (VBox) getRoot();
		
		Canvas canvas = new Canvas(500,498);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
        //Prepare Image
		GameManager gameManager = new GameManager();
		
    	//To read input from keyBoard
        KeyInput.setKeyHandler(this);
        
        //Loop to update()
		Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        Text money = new Text();
        Text popularity = new Text();
        Text customer = new Text();
        Text time = new Text();
        Text day = new Text();

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
                	
                	money.setText(Integer.toString(Player.getMoney()));
                	popularity.setText(Integer.toString(GameManager.getPopularity()));
                	customer.setText(Integer.toString(GameManager.getCustomer()));
                	time.setText(String.format("%02d:%02d", Time.getHour(), Time.getMin()));
                	day.setText(Integer.toString(GameManager.getDay()));
                }
            });        
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();		
        control = new GridPane();
        control.setPadding(new Insets(10));
        control.add(new HBox(5, new Label("Current Money :"), money), 0, 0);
        control.add(new HBox(5, new Label("Popularity :"), popularity), 0, 1);
        control.add(new HBox(5, new Label("Customer :"), customer), 0, 2);
        control.add(new HBox(5, new Label("Time :"), time), 0, 3);
        control.add(new HBox(5, new Label("Day :"), day), 0, 4);
        
        root.getChildren().add(control);
	}
	


}
