package avaj_launcher.singleton;

import avaj_launcher.abst.Flyable;
import avaj_launcher.utils.Coordinates;
import java.lang.String;

public class AircraftFactory {
	private	static AircraftFactory	instance;

	private	AircraftFactory() {};

	public static	AircraftFactory getInstance() {
		if (instance == null)
			instance = new AircraftFactory();
		return instance;
	};

	public	Flyable	newAircraft(String p_type, String p_name, Coordinates p_coordinates) { return null; };
}