package UI;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Welcome extends Scene {

	private VBox root;
	
	public Welcome() {
		super(new VBox() ,500 ,700);
		root = (VBox) getRoot();
		root.getChildren().add(new Label("xxx"));
	}

}
