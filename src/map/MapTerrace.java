package map;

import UI.AnimatedImage;
import UI.Images;
import UI.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class MapTerrace extends Map{
	
	private AnimatedImage airPlane; 
	
	public MapTerrace() {
		super();
		super.setBackground(Images.TERRACE);
		this.airPlane = new AnimatedImage(Images.AIRPLANE, this, 297, 250, 0, 0);
	}

	@Override
	public void render(GraphicsContext gc) {
		super.getBackground().render(gc);
		this.airPlane.render(gc);
		for(Rectangle r: super.getStructList()) {
			r.render(gc);
		}
	}
}
