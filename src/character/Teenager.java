package character;

import UI.Images;
import map.Map;

public class Teenager extends Visitor{

	public Teenager(Map map) {
		super(Images.TEENAGERL, Images.TEENAGERR, Images.TEENAGERU, Images.TEENAGERD, map);
	}

	
}
