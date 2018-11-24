package character;

import UI.Images;
import map.Map;

public class Adult extends Visitor{

	public Adult(Map map) {
		super(Images.ADULTL, Images.ADULTR, Images.ADULTU, Images.ADULTD, map);
	}

}
