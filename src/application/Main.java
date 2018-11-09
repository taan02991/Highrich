package application;
	
import UI.StartScene;
import UI.Welcome;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private static Stage primaryStage;
	private static Scene startScene;
	private static Scene gameScene;
	
	public void start(Stage primaryStage) {
		startScene = new StartScene();
		gameScene = new Welcome();
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getStage() {
		return primaryStage;
	}
	
	public static Scene getGameScene() {
		return gameScene;
	}
}
