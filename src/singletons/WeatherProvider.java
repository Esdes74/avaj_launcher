package avaj_launcher.singleton;

import avaj_launcher.utils.Coordinates;
import java.lang.String;

public class WeatherProvider {
	private static	WeatherProvider	instance;
	private			String[]		weather;

	private	WeatherProvider() {};

	public	static WeatherProvider	getInstance() {
		if (instance == null)
			instance = new WeatherProvider();
		return instance;
	};

	public	String	getCurrentWeather(Coordinates p_coordinates) { return ""; };
}