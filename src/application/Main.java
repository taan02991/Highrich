package application;
	
import UI.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	
	
	public void start(Stage primaryStage) {
		Scene startScene = new StartScene();
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
