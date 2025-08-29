package src.model;

import src.content.Aircraft;
import src.singleton.WeatherProvider;
import src.utils.Coordinates;

public class Helicopter extends Aircraft {
	public	Helicopter(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public	void updateConditions() {
		String	currentWeather;

		currentWeather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
		// if (currentWeather.contentEquals("RAIN"))
		// 	printInFile("Here's Jhonny !!");
		// else if (currentWeather.contentEquals("FOG"))
		// 	printInFile("Tell my wife and childrens i love them");
		// else if (currentWeather.contentEquals("SUN"))
		// 	printInFile("I love my life !!!");
		// else if (currentWeather.contentEquals("SNOW"))
		// 	printInFile("Winter is comming");
	};
}