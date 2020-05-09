package equipment;

import java.awt.Image;

import equipment.EquipmentManager.Stuff;
import equipment.Stat.Stats;

public class Grieves extends Equipment {
	
	public Grieves(Image img) throws Exception {
		super();
		imageEquip = img;
		int rarity = statTable.get(Stats.Rarity);
		statTable.put(Stats.Price, 100+100*rarity);
		statTable.put(Stats.Resistance, 10);
		statTable.put(Stats.Health, 5);
	}
	
	@Override
	public Stuff toStuff() {
		// TODO Auto-generated method stub
		return Stuff.Grieves;
	}
}
