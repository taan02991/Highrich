package UI;

import character.Player;
import controller.GameManager;
import controller.Time;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class ControlBar extends HBox{
	private static Text money;
	private static Text popularity;
	private static Text customer;
	private static Text time;
	private static Text day;
	private static VBox showMenu;
	private static Button buyRoom;
	private static Button buyRecButton;
	private static Button exitButton;
	private static VBox showStatus;
	private static HBox showTime;
	private static VBox roomChoice;
	public static Button level1,level2,level3;
	
	public ControlBar() {
		
//		this.setBorder(new Border(new BorderStroke(Color.web("#A5A5A3"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
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
    	
    	//Right side
        this.showMenu = new VBox(10);
        this.showMenu.setPrefSize(140, 180);;
        this.buyRecButton = new Button("Buy Receptionist");
        this.buyRecButton.setPrefWidth(100);
        this.buyRoom = new Button("Buy Room");
        this.buyRoom.setPrefWidth(100);
        this.showMenu.getChildren().addAll(this.buyRecButton, this.buyRoom);
        this.getChildren().add(this.showMenu);
        
        this.roomChoice = new VBox(10);
        this.level1 = new Button("Standard");
        this.level1.setPrefWidth(100);
        this.level2 = new Button("Executive");
        this.level2.setPrefWidth(100);
        this.level3 = new Button("Presidential");
        this.level3.setPrefWidth(100);
        this.exitButton = new Button("Exit");
        this.exitButton.setPrefWidth(100);
        this.roomChoice.getChildren().addAll(this.level1, this.level2, this.level3, this.exitButton);
        this.roomChoice.setVisible(false);
        GameScene.stackPane.getChildren().add(this.roomChoice);
        
        
    	
    	exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {	
				roomChoice.setVisible(false);
			}   		
    	});
    	
    	buyRecButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				GameManager.getPlayer().buyReceptionist();
			}		
    		
    	});
    	
    	this.buyRoom.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				roomChoice.setVisible(true);
				
			}
    		
    	});
    	
    	level1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				GameManager.getPlayer().buyRoom(GameScene.gc, "Standard");
				roomChoice.setVisible(false);
				
			}
    		
    	});
    	
    	level2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				 GameManager.getPlayer().buyRoom(GameScene.gc, "Executive");
				 roomChoice.setVisible(false);
				
			}
    		
    	});
    	
    	level3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				GameManager.getPlayer().buyRoom(GameScene.gc, "Presidential");
				roomChoice.setVisible(false);		
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
}
