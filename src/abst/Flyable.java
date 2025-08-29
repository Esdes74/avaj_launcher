package src.abst;

import src.models.WeatherTower;
import src.singletons.AircraftFactory;
import src.ExitException;

public abstract class Flyable {
	protected		WeatherTower weatherTower;

	public abstract	void updateConditions() throws ExitException;
	public			void registerTower(WeatherTower p_tower) {
		weatherTower = p_tower;
		AircraftFactory.getInstance().registerTower(p_tower);
	};
}