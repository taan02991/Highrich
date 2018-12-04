package map;

import UI.AnimatedImage;
import UI.Audio;
import UI.Images;
import UI.Rectangle;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;

public class MapTerrace extends Map{
	
	private static AnimatedImage airPlane; 
	private static int state = 1;
	
	public MapTerrace() {
		super();
		super.setBackground(Images.TERRACE);
		airPlane = new AnimatedImage(Images.AIRPLANE1, this, 297, 250, 0, 0);
	}

	public static void activeAirplane() {
		if( MapTerrace.state == 1 ) {
			airPlane.setPosition(airPlane.getPositionX() - 3, airPlane.getPositionY() - 2);	
			if( airPlane.getPositionX() < -90 ) {
				MapTerrace.state = 2;
			}
		}else if( MapTerrace.state == 2 ) {
			airPlane.setFrame(Images.AIRPLANE2);
			airPlane.setPosition(airPlane.getPositionX()+3, airPlane.getPositionY() );	
			if( airPlane.getPositionX() > 520 ) {
				MapTerrace.state = 3;
			}
		}else if( MapTerrace.state == 3 ) {
			airPlane.setFrame(Images.AIRPLANE6);
			airPlane.setPosition(airPlane.getPositionX()-3, airPlane.getPositionY()+2 );	
			if( airPlane.getPositionX() < -90 ) {
				MapTerrace.state = 4;
			}
		}else if( MapTerrace.state == 4 ) {
			airPlane.setFrame(Images.AIRPLANE2);
			airPlane.setPosition(airPlane.getPositionX()+3, airPlane.getPositionY());	
			if( airPlane.getPositionX() > 200 ) {
				MapTerrace.state = 5;
			}
		}else if( MapTerrace.state == 5 ) {
			//up
			airPlane.setFrame(Images.AIRPLANE5);
			airPlane.setPosition(airPlane.getPositionX(), airPlane.getPositionY()-2.5);	
			if( airPlane.getPositionY() < 0 ) {
				MapTerrace.state = 6;
			}
		}else if( MapTerrace.state == 6 ) {
			//right
			airPlane.setFrame(Images.AIRPLANE7);
			airPlane.setPosition(airPlane.getPositionX()+3, airPlane.getPositionY());	
			if( airPlane.getPositionX() > 430 ) {
				MapTerrace.state = 7;
			}
		}else if( MapTerrace.state == 7 ) {
			//down
			airPlane.setFrame(Images.AIRPLANE8);
			airPlane.setPosition(airPlane.getPositionX(),airPlane.getPositionY()+3);	
			if( airPlane.getPositionY() > 468 ) {
				MapTerrace.state = 8;
			}
		}else if( MapTerrace.state == 8 ) {
			//left-up
			airPlane.setFrame(Images.AIRPLANE3);
			airPlane.setPosition(airPlane.getPositionX()-3,airPlane.getPositionY()-3);
			if( airPlane.getPositionX() < 120 ) {
				MapTerrace.state = 9;
			}
		}else if( MapTerrace.state == 9 ) {
			//left-up
			airPlane.setPosition(0,0);	
			airPlane.setFrame(Images.BOMB);
			Audio.BOMB.play();
			MapTerrace.state = 10;
		}else if( MapTerrace.state == 10 ) {
			Thread o = new Thread(()-> {
				try {
					Thread.sleep(1000);
					airPlane.setPosition(108,133);
					airPlane.setFrame(Images.AZURE);
					Thread.sleep(200);
					GameManager.setGamePausing(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			o.start();
			MapTerrace.state = 11;
		}
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
