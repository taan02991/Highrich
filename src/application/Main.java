package application;
	
import UI.StartScene;
import UI.Welcome;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene startScene;
	private static Scene gameScene;
	
	public void start(Stage primaryStage) {
		stage = primaryStage;
		startScene = new StartScene();
		gameScene = new Welcome();
		stage.setScene(startScene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void gameScene() {
		stage.setScene(gameScene);
	}
}
