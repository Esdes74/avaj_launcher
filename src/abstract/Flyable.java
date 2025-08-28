package avaj_launcher.abst;

import avaj_launcher.model.WeatherTower;
import avaj_launcher.singleton.AircraftFactory;

public abstract class Flyable {
	protected		WeatherTower weatherTower;

	public abstract	void updateConditions();
	public			void registerTower(WeatherTower p_tower) {
		weatherTower = p_tower;
		AircraftFactory.getInstance().registerTower(p_tower);
	};
}