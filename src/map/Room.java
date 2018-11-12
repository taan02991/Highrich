package map;

import UI.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Room {
	protected boolean isAvailable;
	protected int position;
	protected Image image;
	
	public Room(Image image, int position) {
		this.isAvailable = true;
		this.position = position;
		this.image = image;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public int getPosition() {
		return position;
	}

	public Image getImage() {
		return image;
	}
	
    public void render(GraphicsContext gc){
    	if( position < 3 ) {
    		gc.drawImage(image, 0, 166*(position) );	
    	}else {
    		gc.drawImage(image, 300, 166*(position-3) );
    	}

    }
}
