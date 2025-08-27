package avaj_launcher.abst;

import avaj_launcher.model.WeatherTower;

public abstract class Flyable {
	protected		WeatherTower weatherTower;

	public abstract	void updateConditions();
	public			void registerTower(WeatherTower p_tower) {
		weatherTower = p_tower;
	};
}