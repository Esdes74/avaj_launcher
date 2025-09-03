package src.models;

import src.abst.Flyable;
import src.content.Tower;
import src.utils.Coordinates;
import src.singletons.WeatherProvider;
import java.lang.String;
import src.ExitException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Random;

public class WeatherTower extends Tower {
	private	boolean weatherChanged = false;
	private	LinkedHashMap<Coordinates, String> coordinateWeather = new LinkedHashMap<Coordinates, String>();

	public	String getWeather(Coordinates p_coordinates) {
		int		drawForcedChanged;
		int		observerSize = getObservers().size();
		String	weather;
		Random	randomGenerator = new Random();

		weather = WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
		if (coordinateWeather.get(p_coordinates) == null) {
			coordinateWeather.put(p_coordinates, weather);
		} 
		else {
			if (weather != coordinateWeather.get(p_coordinates))
				weatherChanged = true;
			
			drawForcedChanged = randomGenerator.nextInt(observerSize);

			if (getObservers().getLast().getCoordinates().equals(p_coordinates) || 
			(weatherChanged == false && drawForcedChanged <= observerSize / 2)) {
				while (weatherChanged == false && weather == coordinateWeather.get(p_coordinates)) {
					weather = WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
				}
				weatherChanged = false;
			}

			coordinateWeather.replace(p_coordinates, weather);
		}

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