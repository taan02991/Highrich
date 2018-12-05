package UI;

import controller.GameManager;
import controller.Time;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.MapUpStair;
import map.MapWelcome;
import javafx.scene.paint.Color;

public class ControlBar extends HBox{
	private static Text money;
	private static Text popularity;
	private static Text customer;
	private static Text time;
	private static Text day;
	private static VBox showMenu;
	private static VBox rightSide;
	private static Button menuButton;
	private static Button buyRoom;
	private static Button buyRecButton;
	private static Button continueButton;
	private static Button continueButtonOnStatus;
	private static Button statusButton;
	private static Button exitButton;
	private static StackPane showTime;
	private static VBox showButton;
	private static GridPane info;
	private static GridPane showStatus;
	private static ProgressBar progressStandard;
	private static ProgressBar progressExecutive;
	private static ProgressBar progressPresidential;
	private static ProgressBar progressPresidentialOnStatus;
	private static ProgressBar progressAvailable;
	private static ImageView currentFloor;
	
	public ControlBar() {
		
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		
        
        //Left side
		money = new Text();
		popularity = new Text();
		customer = new Text();
		time = new Text();
		day = new Text();
    	info = new GridPane();
    	info.setVgap(7);
    	info.setHgap(10);
    	info.add(new ImageView(Images.ICONMONEY), 0, 0);
    	info.add(new Label("Money :"), 1, 0);
    	info.add(money, 2, 0);
    	info.add(new ImageView(Images.ICONPOPULARITY), 0, 1);
    	info.add(new Label("Popularity :"), 1, 1);
    	info.add(popularity, 2, 1);
    	info.add(new ImageView(Images.ICONCUSTOMER), 0, 2);
    	info.add(new Label("Customer :"), 1, 2);
    	info.add(customer, 2, 2);
    	info.add(new ImageView(Images.ICONDAY), 0, 3);
    	info.add(new Label("Day :"), 1, 3);
    	info.add(day, 2, 3);
    	info.add(new ImageView(Images.ICONROOM), 0, 4);
    	info.add(new Label("N' Presidential :"), 1, 4);
    	progressPresidential = new ProgressBar(0);
    	info.add(progressPresidential, 2, 4);
    	this.getChildren().add(info);
    	
    	//time
    	showTime = new StackPane();
    	showTime.setBorder(new Border(new BorderStroke(Color.web("A5A5A3"), BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(5))));
    	showTime.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null)));
    	showTime.setAlignment(Pos.CENTER);
    	showTime.setPrefWidth(170);
    	showTime.setMaxHeight(95);
    	time.setFont(Font.font("Digital Dismay", 50));
    	time.setFill(Color.WHITE);
    	showTime.getChildren().add(time);
        
        //right side
    	showButton = new VBox(10);
    	showButton.setPrefSize(140, 180);
        menuButton = new Button("Pay~~");
        menuButton.setPrefWidth(170);
        statusButton = new Button("Status");
        statusButton.setPrefWidth(170);
        exitButton = new Button("Exit");
        exitButton.setPrefWidth(170);
        showButton.getChildren().addAll(menuButton, statusButton, exitButton);
        rightSide = new VBox(10);
    	rightSide.getChildren().addAll(showTime, showButton);
    	this.getChildren().add(rightSide);
        
        //Popup Menu
        showMenu = new VBox(10);
        showMenu.setAlignment(Pos.CENTER);
        showMenu.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-boarder-radius: 25px; -fx-background-radius: 25px");
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
        
        //Popup status
        showStatus = new GridPane();
        showStatus.setAlignment(Pos.CENTER);
        showStatus.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-boarder-radius: 25px; -fx-background-radius: 25px");
        showStatus.setMaxSize(250, 200);
        showStatus.setVgap(7);
        showStatus.setHgap(10);
        continueButtonOnStatus = new Button("Continue");
        continueButtonOnStatus.setPrefWidth(200);
        GridPane.setHalignment(continueButtonOnStatus, HPos.CENTER);
        GridPane.setValignment(continueButtonOnStatus, VPos.CENTER);
        showStatus.add(continueButtonOnStatus, 0, 0, 2, 1);
        showStatus.add(new Label("Current Floor:"), 0, 1);
        currentFloor = new ImageView(Images.ICONFLOOR[1]);
        showStatus.add(currentFloor, 1, 1);
        showStatus.add(new Label("N' Standard :"), 0, 2);
        progressStandard = new ProgressBar(0);
        showStatus.add(progressStandard, 1, 2);
        showStatus.add(new Label("N' Executive :"), 0, 3);
        progressExecutive =  new ProgressBar(0);
        showStatus.add(progressExecutive, 1, 3);
        showStatus.add(new Label("N' Presidential :"), 0, 4);
        progressPresidentialOnStatus = new ProgressBar(0);
        showStatus.add(progressPresidentialOnStatus, 1, 4);
        showStatus.add(new Label("N' Available Room :"), 0, 5);
        progressAvailable = new ProgressBar(0);
        showStatus.add(progressAvailable, 1, 5);
        showStatus.setVisible(false);
        GameScene.stackPane.getChildren().add(showStatus);
        
    	
        menuButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent arg0) {
    			GameManager.setGamePausing(true);
    			showMenu.setVisible(true);
    			Audio.MENU.setVolume(1);
    			Audio.MENU.play();
    		}
        });
       
        statusButton.setOnAction(e->{
			GameManager.setGamePausing(true);
			showStatus.setVisible(true);
			Audio.MENU.setVolume(1);
			Audio.MENU.play();
        });
        
        continueButtonOnStatus.setOnAction(e->{
			GameManager.setGamePausing(false);
			showStatus.setVisible(false);
			Audio.MENU.setVolume(1);
			Audio.MENU.play();
        });
        
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			Stage stage = (Stage) exitButton.getScene().getWindow();
    		    stage.close();
    			Audio.MENU.setVolume(1);
    			Audio.MENU.play();
    		}
    	});
       
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			GameManager.setGamePausing(false);
    			showMenu.setVisible(false);
    			Audio.MENU.setVolume(1);
    			Audio.MENU.play();
    		}
    	});
    	
        buyRecButton.setOnAction(new EventHandler<ActionEvent>(){
 			@Override
 			public void handle(ActionEvent event) {
 				GameManager.getPlayer().buyReceptionist();
 				GameManager.setGamePausing(false);
 				showMenu.setVisible(false);
    			Audio.MENU.setVolume(1);
    			Audio.MENU.play();
 			}
     	});
    	
    	buyRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GameManager.getPlayer().buyRoom(GameScene.gc);
				GameManager.setGamePausing(false);
				showMenu.setVisible(false);	
    			Audio.MENU.setVolume(1);
    			Audio.MENU.play();
			}
    	});
 
	}
	
	public void update() {
    	money.setText(Integer.toString(GameManager.getPlayer().getMoney()));
    	popularity.setText(Integer.toString(GameManager.getPopularity()));
    	customer.setText(Integer.toString(GameManager.getCustomer()));
    	time.setText(String.format("%02d:%02d", Time.getHour(), Time.getMin()));
    	day.setText(Integer.toString(GameManager.getDay()));
    	progressPresidential.setProgress((double)GameManager.getnPresidential()/18.0);
    	progressPresidentialOnStatus.setProgress((double)GameManager.getnPresidential()/18.0);
    	progressStandard.setProgress((double)GameManager.getnStandard()/18.0);
    	progressExecutive.setProgress((double)GameManager.getnExecutive()/18.0);
    	progressAvailable.setProgress((double)GameManager.getAvailableRoom()/18.0);
    	if(GameManager.getCurrentMap() instanceof MapWelcome) {
    		currentFloor.setImage(Images.ICONFLOOR[1]);
    	}else if(GameManager.getCurrentMap() instanceof MapUpStair) {
    		currentFloor.setImage(Images.ICONFLOOR[GameManager.getMaps().indexOf(GameManager.getCurrentMap())+1]);
    	}
	}

	public static Button getBuyRoom() {
		return buyRoom;
	}
	
	public static Button getbuyRecButton() {
		return buyRecButton;
	}
	
}
