package src.model;

import src.content.Tower;
import src.utils.Coordinates;
import src.singleton.WeatherProvider;
import java.lang.String;

public class WeatherTower extends Tower {
	public	String getWeather(Coordinates p_coordinates) {
		return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
	};

	public	void changeWeather() { conditionChanged(); };
}