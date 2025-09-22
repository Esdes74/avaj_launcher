package com.eslamber.src.models;

import com.eslamber.src.abst.Flyable;
import com.eslamber.src.content.Tower;
import com.eslamber.src.utils.Coordinates;
import com.eslamber.src.singletons.WeatherProvider;
import java.lang.String;
import com.eslamber.src.ExitException;
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

			// if (getObservers().getLast().getCoordinates().equals(p_coordinates) || 
			if ((p_coordinates.getLatitude() < 500 && p_coordinates.getLatitude() > 0 &&
			p_coordinates.getLongitude() < 500 && p_coordinates.getLongitude() > 0 && 
			p_coordinates.getHeight() < 90) &&
			(getObservers().get(getObservers().size() - 1).getCoordinates().equals(p_coordinates) || 
			(weatherChanged == false && drawForcedChanged <= observerSize / 2))) {
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