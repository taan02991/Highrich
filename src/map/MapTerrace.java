package map;

import UI.AnimatedImage;
import UI.Images;
import UI.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class MapTerrace extends Map{
	
	private static AnimatedImage airPlane; 
	
	public MapTerrace() {
		super();
		super.setBackground(Images.TERRACE);
		airPlane = new AnimatedImage(Images.AIRPLANE, this, 297, 250, 0, 0);
	}

	public static void activeAirplane() {
		airPlane.setPosition(airPlane.getPositionX() - 1, airPlane.getPositionY() - 0.05);	
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.getBackground().render(gc);
		airPlane.render(gc);
		for(Rectangle r: super.getStructList()) {
			r.render(gc);
		}
		gc.drawImage(Images.CONGRATULATION, 25, 200);
	}
	
}
