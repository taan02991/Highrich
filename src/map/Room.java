package map;

import java.util.ArrayList;

import UI.Images;
import UI.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Room {
	protected boolean isAvailable;
	protected int position;
	protected Image image;
	protected Rectangle tractor;
	protected ArrayList<Rectangle> roomStruct;
	
	public Room(Image image, int position) {
		this.roomStruct = new ArrayList<Rectangle>();
		this.isAvailable = true;
		this.position = position;
		this.image = image;
		initTractor();
	}


	private void initTractor() {
		if( this.position < 3 ) {
			this.tractor = new Rectangle(Images.TRACTOR, 78, 166*(this.position) + 65);
		}else {
			this.tractor = new Rectangle(Images.TRACTOR, 378, 166*(this.position-3) + 65);
		}
	}
	
	public void addRoomStruct(Rectangle s) {
		this.roomStruct.add(s);
	}
	
	public Rectangle getTractor() {
		return tractor;
	}

	public void setTractor(Rectangle tractor) {
		this.tractor = tractor;
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
    	if( this.position < 3 ) {
    		gc.drawImage(this.image, 0, 166*(this.position) );
    	}else{
    		gc.drawImage(this.image, 300, 166*(this.position-3) );
    	}
    	this.tractor.render(gc);
    }
}
