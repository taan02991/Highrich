package character;

import UI.Images;
import map.Map;

public class OldMan extends Visitor{

	public OldMan(Map map) {
		super(Images.OLDMANL, Images.OLDMANR, Images.OLDMANU, Images.OLDMAND, map);
	}

}
