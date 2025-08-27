package avaj_launcher.singleton;

import avaj_launcher.abst.Flyable;
import avaj_launcher.utils.Coordinates;
import avaj_launcher.model.Helicopter;
import avaj_launcher.model.Baloon;
import avaj_launcher.model.JetPlane;
import avaj_launcher.content.Tower;
import java.lang.String;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AircraftFactory {
	private	static	AircraftFactory instance;
	private			Tower registeredTower;
	private			ArrayList<Long> registeredId;

	private			AircraftFactory() {};
	private			long generateId() {
		long	lastId;

		try {
			lastId = registeredId.getLast();
		}
		catch (NoSuchElementException expt) {
			lastId = -1;
		}

		return lastId + 1;
	};

	public static	AircraftFactory getInstance() {
		if (instance == null)
			instance = new AircraftFactory();
		return instance;
	};

	public			Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		long	id = generateId();
		Flyable	newFlyable = null;

		if (p_type.equalsIgnoreCase("helicopter"))
			newFlyable = new Helicopter(id, p_name, p_coordinates);
		if (p_type.equalsIgnoreCase("baloon"))
			newFlyable = new Baloon(id, p_name, p_coordinates);
		if (p_type.equalsIgnoreCase("jetplane"))
			newFlyable = new JetPlane(id, p_name, p_coordinates);

		registeredId.add(id);
		registeredTower.register(newFlyable);

		return newFlyable;
	};

	public			void registerTower(Tower p_tower) { registeredTower = p_tower; }
}
