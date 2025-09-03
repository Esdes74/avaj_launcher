package src.models.aircraft;

import src.content.Aircraft;
import src.singletons.WeatherProvider;
import src.utils.Coordinates;
import src.singletons.PrintInFile;
import src.ExitException;
import java.lang.String;
import java.util.ArrayList;

public class JetPlane extends Aircraft {
	public		JetPlane(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public		void updateConditions() throws ExitException {
		String	currentWeather;

		currentWeather = weatherTower.getWeather(coordinates);
		if (currentWeather.contentEquals("RAIN")) {
			coordinates.setLatitude(coordinates.getLatitude() + 5);
			PrintInFile.getInstance().print("JetPlane#" + name + "(" + id + "): It's raining men! Hallelujah!");
		}
		else if (currentWeather.contentEquals("FOG")) {
			coordinates.setLatitude(coordinates.getLatitude() + 1);
			PrintInFile.getInstance().print("JetPlane#" + name + "(" + id + "): There is fog everywhere... I can't even see my nose");
		}
		else if (currentWeather.contentEquals("SUN")) {
			coordinates.setLatitude(coordinates.getLatitude() + 10);
			coordinates.setHeight(coordinates.getHeight() + 2);
			PrintInFile.getInstance().print("JetPlane#" + name + "(" + id + "): Oh i can see the ground, you'r soooo tiny");
		}
		else if (currentWeather.contentEquals("SNOW")) {
			coordinates.setHeight(coordinates.getHeight() - 7);
			PrintInFile.getInstance().print("JetPlane#" + name + "(" + id + "): I love the snow, you know ? Jon FUCKING SNOW !!!");
		}

		if (coordinates.getHeight() <= 0) {
			PrintInFile.getInstance().print("JetPlane#" + name + "(" + id + ") Landing");
			weatherTower.unregister(this);
		}

		crashVerification();
	};

	public		String registeredMessage() throws ExitException { return "JetPlane#" + name + "(" + id + ") Registered to weather tower"; }
	public		String unregisteredMessage() throws ExitException { return "JetPlane#" + name + "(" + id + ") Unregistered to weather tower"; }
	public		String crashMessage() throws ExitException { return "JetPlane#" + name + "(" + id + ") OOOH NOOO, WAIT I CAN TRY TO... AAAH !!!"; }

	protected	void crashVerification() throws ExitException {
		ArrayList<Flyable> observers = weatherTower.getObservers();
		ArrayList<Flyable> observersCopy = new ArrayList<>(observers);

		for (Flyable aircraft: observersCopy) {
			if (this != aircraft && coordinates.equals(aircraft.getCoordinates())) {
				PrintInFile.getInstance().print(weatherTower.crashMessage(this, aircraft));
				PrintInFile.getInstance().print("JetPlane#" + name + "(" + id + ") OOOH MY GOD, WATCH OUT " + aircraft.getName().toUpperCase() + "(" + aircraft.getId() + ") I'M GONNA HIT YOU !!");
				PrintInFile.getInstance().print(aircraft.crashMessage());
				weatherTower.unregister(aircraft);
				weatherTower.unregister(this);
				break;
			}
		}
	}
}