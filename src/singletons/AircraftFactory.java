package src.singletons;

import src.ExitException;
import src.abst.Flyable;
import src.utils.Coordinates;
import src.utils.Utils;
import src.models.aircraft.Helicopter;
import src.models.aircraft.Baloon;
import src.models.aircraft.JetPlane;
import src.content.Tower;
import java.lang.String;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AircraftFactory {
	private	static	AircraftFactory instance = null;
	private			Tower registeredTower = null;
	private			ArrayList<Long> registeredId;

	private			AircraftFactory() { registeredId = new ArrayList<Long>(); };
	private			long generateId() throws ExitException {
		long	lastId = -1;

		try {
			if (registeredId != null && registeredId.isEmpty() == false)
				lastId = registeredId.getLast();
			else
				lastId = -1;
		}
		catch (NoSuchElementException expt) {
			Utils.exit(1, "Error: No such element in arraylist");
		}

		return lastId + 1;
	};

	public static	AircraftFactory getInstance() {
		if (instance == null)
			instance = new AircraftFactory();
		return instance;
	};

	public			Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws ExitException {
		long	id = generateId();
		Flyable	newFlyable = null;

		if (p_type.equalsIgnoreCase("helicopter"))
			newFlyable = new Helicopter(id, p_name, p_coordinates);
		else if (p_type.equalsIgnoreCase("baloon"))
			newFlyable = new Baloon(id, p_name, p_coordinates);
		else if (p_type.equalsIgnoreCase("jetplane"))
			newFlyable = new JetPlane(id, p_name, p_coordinates);

		registeredId.add(id);
		if (registeredTower != null)
			registeredTower.register(newFlyable);

		return newFlyable;
	};

	public			void registerTower(Tower p_tower) { registeredTower = p_tower; }
}
