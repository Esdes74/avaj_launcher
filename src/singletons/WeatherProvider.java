package src.singletons;

import src.utils.Coordinates;
import java.lang.String;

public class WeatherProvider {
	private static	WeatherProvider instance;
	private			String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

	private			WeatherProvider() {};

	public static	WeatherProvider getInstance() {
		if (instance == null)
			instance = new WeatherProvider();
		return instance;
	};

	public			String getCurrentWeather(Coordinates p_coordinates) { return ""; };
}