package map;

import UI.Images;
import UI.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class MapTerrace extends Map{
	
	public MapTerrace() {
		super();
		super.setWarpDown(new Rectangle(Images.WARPDOWN, 216, 0));
		super.setBackground(Images.FLOOR);
	}

	@Override
	public void render(GraphicsContext gc) {
		super.getBackground().render(gc);
		super.getWarpDown().render(gc);
		for(Rectangle r: super.getStructList()) {
			r.render(gc);
		}
	}
}
