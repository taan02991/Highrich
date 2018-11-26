package UI;

import character.Player;
import controller.GameManager;
import controller.Time;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private Button b2;
	private Button buyRecButton;
	private Button exitButton;
	
	public ControlBar() {
		
        this.money = new Text();
        this.popularity = new Text();
        this.customer = new Text();
        this.time = new Text();
        this.day = new Text();
        this.menu = new Button("Menu");
        
        this.showMenu = new VBox(10);
        this.buyRecButton = new Button("Buy Receptionist");
        this.b2 = new Button("Buy Room");
        this.exitButton = new Button("Exit");
        
        this.add(new HBox(5, new Label("Current Money :"), money), 0, 0);
        this.add(new HBox(5, new Label("Popularity :"), popularity), 0, 1);
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
        
        this.showMenu.getChildren().addAll(this.buyRecButton ,b2, this.exitButton);
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

	}
}
