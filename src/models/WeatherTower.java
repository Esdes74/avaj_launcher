package src.models;

import src.content.Tower;
import src.utils.Coordinates;
import src.singletons.WeatherProvider;
import java.lang.String;
import src.ExitException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class WeatherTower extends Tower {
	private	LinkedHashMap<Coordinates, String> coordinateWeather = new LinkedHashMap<Coordinates, String>();

	public	String getWeather(Coordinates p_coordinates) {
		String	weather;

		weather = WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
		if (coordinateWeather.get(p_coordinates) == null) 
			coordinateWeather.put(p_coordinates, weather);
		else
			coordinateWeather.replace(p_coordinates, weather);

		return coordinateWeather.get(p_coordinates);
	};

	public	void changeWeather() throws ExitException { conditionChanged(); };

	public	void clearCoordinateWeather() {
		boolean					existing = false;
		Coordinates				coordinateValue;
		Set<Coordinates>		coordinateSet = coordinateWeather.keySet();
		Iterator<Coordinates>	iteratorCoordinateValue = coordinateSet.iterator();

		while (iteratorCoordinateValue.hasNext()) {
			coordinateValue = iteratorCoordinateValue.next();
			for (Flyable aircraft: getObservers()) {
				if (aircraft.getCoordinates().equals(coordinateValue) == true)
					existing = true;
			}
			if (!existing)
				iteratorCoordinateValue.remove();
		}
	}
}