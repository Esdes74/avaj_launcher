package avaj_launcher.model;

import avaj_launcher.content.Aircraft;
import avaj_launcher.singleton.WeatherProvider;
import avaj_launcher.utils.Coordinates;

public class Baloon extends Aircraft {
	public	Baloon(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public	void updateConditions() {
		String	currentWeather;

		currentWeather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
		// if (currentWeather.contentEquals("RAIN"))
		// 	printInFile("It's raining, IT'S RAINING !!! It's a miracle !!!");
		// else if (currentWeather.contentEquals("FOG"))
		// 	printInFile("I can't see the ground, may i crash ?");
		// else if (currentWeather.contentEquals("SUN"))
		// 	printInFile("What a beautifull day !");
		// else if (currentWeather.contentEquals("SNOW"))
		// 	printInFile("Liberated, freed. I'll never lie again");
	};
}