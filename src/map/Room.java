package map;

import java.util.ArrayList;

import UI.Images;
import UI.Rectangle;
import character.Visitor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Room {
	protected boolean isAvailable;
	protected Map map;
	protected int position;
	protected Image image;
	protected Rectangle tractor;
	protected int constructionCost;
	protected int fee;
	protected ArrayList<Rectangle> roomStruct;
	protected Visitor visitor;
	
	public Room(Image image, int position, int constructionCost, int fee, Map map) {
		this.roomStruct = new ArrayList<Rectangle>();
		this.position = position;
		this.image = image;
		this.constructionCost = constructionCost;
		this.fee = fee;
		this.visitor = null;
		this.map = map;
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

	public void setAvailable(boolean b) {
		this.isAvailable = b;
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
    	if( !(this instanceof RoomPresidential) ) {
    		this.tractor.render(gc);
    	}
    	for(Rectangle r: roomStruct){
    		r.render(gc);
    	}
    }
    
    public void renderDusty(GraphicsContext gc){
    	if( this.position < 3 ) {
    		gc.drawImage(Images.PRESIDENTIALROOM, 0, 166*(this.position) );
    	}else{
    		gc.drawImage(Images.PRESIDENTIALROOM, 300, 166*(this.position-3) );
    	}
    }


	public int getConstructionCost() {
		return constructionCost;
	}


	public int getFee() {
		return fee;
	}


	public Visitor getVisitor() {
		return visitor;
	}


	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
		visitor.setMap(this.map);
		this.map.addNpc(visitor);
	}


	public ArrayList<Rectangle> getRoomStruct() {
		return roomStruct;
	}
    
	public void clear() {
		if(this instanceof RoomConstruction) {
			return;
		}
		this.isAvailable = true;
		this.visitor = null;			
	}
	
	
}
