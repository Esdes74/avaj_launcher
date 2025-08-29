package src.models;

import src.content.Tower;
import src.utils.Coordinates;
import src.singletons.WeatherProvider;
import java.lang.String;
import src.ExitException;

public class WeatherTower extends Tower {
	public	String getWeather(Coordinates p_coordinates) {
		return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
	};

	public	void changeWeather() throws ExitException { conditionChanged(); };
}