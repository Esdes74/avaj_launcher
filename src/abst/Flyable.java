package src.abst;

import src.models.WeatherTower;
import src.singletons.AircraftFactory;
import src.ExitException;
import java.lang.String;

public abstract class Flyable {
	protected		WeatherTower weatherTower;

	public abstract	void updateConditions() throws ExitException;
	public			void registerTower(WeatherTower p_tower) {
		weatherTower = p_tower;
		AircraftFactory.getInstance().registerTower(p_tower);
	};

	public abstract	long getId();
	public abstract	String getName();
	public abstract	String registeredMessage() throws ExitException;
	public abstract	String unregisteredMessage() throws ExitException;
	public abstract String crashMessage() throws ExitException;
	public abstract	Coordinates getCoordinates();
}