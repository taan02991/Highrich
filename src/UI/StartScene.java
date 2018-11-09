package UI;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StartScene extends Scene{

	private Pane root;
	private ImageView background;
	
	public StartScene() {
		super(new Pane());
		root = (Pane) getRoot();

		background = new ImageView();
		//background.setImage(new Image(ClassLoader.getSystemResource("images/" + name + ".jpg").toString()));
		
	}

}
