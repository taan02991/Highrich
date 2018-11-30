package UI;

import character.Player;
import controller.GameManager;
import controller.Time;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class ControlBar extends HBox{
	private static Text money;
	private static Text popularity;
	private static Text customer;
	private static Text time;
	private static Text day;
	private static VBox showMenu;
	private static Button menuButton;
	private static Button buyRoom;
	private static Button buyRecButton;
	private static Button continueButton;
	private static Button exitButton;
	private static VBox showStatus;
	private static HBox showTime;
	private static VBox showButton;
	
	public ControlBar() {
		
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		
        money = new Text();
        popularity = new Text();
        customer = new Text();
        time = new Text();
        day = new Text();
        
        //Left side
        showStatus = new VBox(10);
        showStatus.setPrefWidth(140);
        showStatus.setPrefHeight(180);
        showStatus.getChildren().addAll(money, popularity, customer, day);
        showStatus.setBorder(new Border(new BorderStroke(Color.web("#000000"), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
    	showStatus.setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
    	getChildren().add(showStatus);
    	
    	//time
    	showTime = new HBox();
    	showTime.setBorder(new Border(new BorderStroke(Color.web("A5A5A3"), BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(5))));
    	showTime.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null)));
    	showTime.setAlignment(Pos.CENTER);
    	showTime.setPrefWidth(170);
    	showTime.setMaxHeight(95);
    	time.setFont(Font.font("Digital Dismay", 50));
    	time.setFill(Color.WHITE);
    	showTime.getChildren().add(time);
    	this.getChildren().add(showTime);	  
        
        //right side
    	showButton = new VBox(10);
    	showButton.setPrefSize(140, 180);
        menuButton = new Button("Menu");
        exitButton = new Button("Exit");
        showButton.getChildren().addAll(menuButton, exitButton);
        this.getChildren().add(showButton);
        
        //Popup Menu
        showMenu = new VBox(10);
        showMenu.setAlignment(Pos.CENTER);
        showMenu.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-boarder-radius: 25px; -fx-background-radius: 25px");
        showMenu.setMaxSize(250, 150);
        buyRecButton = new Button("Buy Receptionist");
        buyRecButton.setPrefWidth(200);
        buyRoom = new Button("Buy Room");
        buyRoom.setPrefWidth(200);
        continueButton = new Button("Continue");
        continueButton.setPrefWidth(200);
        showMenu.getChildren().addAll(continueButton, buyRecButton, buyRoom);
        showMenu.setVisible(false);
        GameScene.stackPane.getChildren().add(showMenu);
        
    	
       menuButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			GameManager.setGamePausing(true);
			showMenu.setVisible(true);
		}
    	   
       });
       
       exitButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Stage stage = (Stage) exitButton.getScene().getWindow();
		    stage.close();
			
		}
	});
       
       continueButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			GameManager.setGamePausing(false);
			showMenu.setVisible(false);
		}
	});
    	
       buyRecButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				GameManager.getPlayer().buyReceptionist();
				GameManager.setGamePausing(false);
				showMenu.setVisible(false);
			}		
    		
    	});
    	
    	buyRoom.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				GameManager.getPlayer().buyRoom(GameScene.gc);
				GameManager.setGamePausing(false);
				showMenu.setVisible(false);	
			}
    		
    	});
    	

        
	}
	
	public void update() {
    	money.setText(Integer.toString(Player.getMoney()));
    	popularity.setText(Integer.toString(GameManager.getPopularity()));
    	customer.setText(Integer.toString(GameManager.getCustomer()));
    	time.setText(String.format("%02d:%02d", Time.getHour(), Time.getMin()));
    	day.setText(Integer.toString(GameManager.getDay()));
	}

	public static Button getBuyRoom() {
		return buyRoom;
	}
	
	public static Button getbuyRecButton() {
		return buyRecButton;
	}
	
}
