package avaj_launcher.model;

import avaj_launcher.content.Tower;
import avaj_launcher.utils.Coordinates;
import avaj_launcher.singleton.WeatherProvider;
import java.lang.String;

public class WeatherTower extends Tower {
	public	String getWeather(Coordinates p_coordinates) {
		return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
	};

	public	void changeWeather() { conditionChanged(); };
}