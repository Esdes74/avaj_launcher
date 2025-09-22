package com.eslamber.src.models.aircraft;

import com.eslamber.src.abst.Flyable;
import com.eslamber.src.content.Aircraft;
import com.eslamber.src.utils.Coordinates;
import com.eslamber.src.singletons.PrintInFile;
import com.eslamber.src.ExitException;
import java.lang.String;
import java.util.ArrayList;

public class Helicopter extends Aircraft {
	public		Helicopter(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public		void updateConditions() throws ExitException {
		String	currentWeather;

		currentWeather = weatherTower.getWeather(coordinates);
		if (currentWeather.contentEquals("RAIN")) {
			coordinates.setLongitude(coordinates.getLongitude() + 5);
			PrintInFile.getInstance().print("Helicopter#" + name + "(" + id + "): Here's Jhonny !!");
		}
		else if (currentWeather.contentEquals("FOG")) {
			coordinates.setLongitude(coordinates.getLongitude() + 1);
			PrintInFile.getInstance().print("Helicopter#" + name + "(" + id + "): Tell my wife and childrens i love them");
		}
		else if (currentWeather.contentEquals("SUN")) {
			coordinates.setHeight(coordinates.getHeight() + 2);
			coordinates.setLongitude(coordinates.getLongitude() + 10);
			PrintInFile.getInstance().print("Helicopter#" + name + "(" + id + "): I love my life !!!");
		}
		else if (currentWeather.contentEquals("SNOW")) {
			coordinates.setHeight(coordinates.getHeight() - 12);
			PrintInFile.getInstance().print("Helicopter#" + name + "(" + id + "): Winter is comming");
		}

		if (coordinates.getHeight() <= 0) {
			PrintInFile.getInstance().print("Helicopter#" + name + "(" + id + ") Landing");
			weatherTower.unregister(this);
		}

		crashVerification();
	};

	public		String registeredMessage() throws ExitException { return "Helicopter#" + name + "(" + id + ") Registered to weather tower"; }
	public		String unregisteredMessage() throws ExitException { return "Helicopter#" + name + "(" + id + ") Unregistered to weather tower"; }
	public		String crashMessage() throws ExitException { return "Helicopter#" + name + "(" + id + ") OOOH NO NO NO, PLEASE NOOOOO !!!"; }

	protected	void crashVerification() throws ExitException {
		ArrayList<Flyable> observers = weatherTower.getObservers();
		ArrayList<Flyable> observersCopy = new ArrayList<>(observers);

		for (Flyable aircraft: observersCopy) {
			if (this != aircraft && coordinates.equals(aircraft.getCoordinates())) {
				PrintInFile.getInstance().print(weatherTower.crashMessage(this, aircraft));
				PrintInFile.getInstance().print("Helicopter#" + name + "(" + id + ") MAYDAY MAYDAY " + aircraft.getName().toUpperCase() + "(" + aircraft.getId() + ") I'M GONNA HIT YOU !! DO YOU COPY ME ? CHANGE YOUR TRAJECTORY !!!");
				PrintInFile.getInstance().print(aircraft.crashMessage());
				weatherTower.unregister(aircraft);
				weatherTower.unregister(this);
				break;
			}
		}
	}
}