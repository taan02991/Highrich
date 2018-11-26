package UI;

import com.sun.prism.paint.Color;

import character.Player;
import controller.GameManager;
import controller.Time;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControlBar extends GridPane{
	private Text money;
	private Text popularity;
	private Text customer;
	private Text time;
	private Text day;
	private Button menu;
	private VBox showMenu;
	private Button buyRoom;
	private Button buyRecButton;
	private Button exitButton;
	private ProgressBar popularityBar;
	
	public ControlBar() {
		
        this.money = new Text();
        this.popularity = new Text();
        this.customer = new Text();
        this.time = new Text();
        this.day = new Text();
        this.menu = new Button("Menu");
        
        this.showMenu = new VBox(10);
        this.showMenu.setMaxSize(250, 150);;
        this.showMenu.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(255, 255, 255, 0.5);";
        this.showMenu.setStyle(style);
        this.buyRecButton = new Button("Buy Receptionist");
        this.buyRecButton.setPrefWidth(200);
        this.buyRoom = new Button("Buy Room");
        this.buyRoom.setPrefWidth(200);
        this.exitButton = new Button("Exit");
        this.exitButton.setPrefWidth(200);
        
        this.popularityBar = new ProgressBar();
        
        this.add(new HBox(5, new Label("Current Money :"), money), 0, 0);
        this.add(new HBox(5, new Label("Popularity :"), popularity, popularityBar), 0, 1);
        this.add(new HBox(5, new Label("Customer :"), customer), 0, 2);
        this.add(new HBox(5, new Label("Time :"), time), 0, 3);
        this.add(new HBox(5, new Label("Day :"), day), 0, 4);
        
        
    	menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showMenu.setVisible(true);
			}
		});
    	
    	exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showMenu.setVisible(false);		
			}   		
    	});
    	
    	buyRecButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				GameManager.getPlayer().buyReceptionist();
				showMenu.setVisible(false);
			}		
    		
    	});
    	
    	this.buyRoom.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				GameManager.getPlayer().buyRoom(GameScene.getGC());
				showMenu.setVisible(false);
				
			}
    		
    	});
        
        this.showMenu.getChildren().addAll(this.buyRecButton , this.buyRoom, this.exitButton);
        this.showMenu.setVisible(false);
        this.showMenu.setAlignment(Pos.CENTER);
        GameScene.stackPane.getChildren().add(this.showMenu);
        this.add(menu , 0, 5);
        
        this.setPadding(new Insets(10));
	}
	
	public void update() {
    	money.setText(Integer.toString(Player.getMoney()));
    	popularity.setText(Integer.toString(GameManager.getPopularity()));
    	customer.setText(Integer.toString(GameManager.getCustomer()));
    	time.setText(String.format("%02d:%02d", Time.getHour(), Time.getMin()));
    	day.setText(Integer.toString(GameManager.getDay()));
    	popularityBar.setProgress(GameManager.getPopularity()/100.0);
	}
}
