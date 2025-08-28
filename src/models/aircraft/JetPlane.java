package avaj_launcher.model;

import avaj_launcher.content.Aircraft;
import avaj_launcher.singleton.WeatherProvider;
import avaj_launcher.utils.Coordinates;

public class JetPlane extends Aircraft {
	public	JetPlane(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public	void updateConditions() {
		String	currentWeather;

		currentWeather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
		// if (currentWeather.contentEquals("RAIN"))
		// 	printInFile("It's raining men! Hallelujah!");
		// else if (currentWeather.contentEquals("FOG"))
		// 	printInFile("There is fog everywhere... I can't even see my nose");
		// else if (currentWeather.contentEquals("SUN"))
		// 	printInFile("Oh i can see the ground, your soooo tiny");
		// else if (currentWeather.contentEquals("SNOW"))
		// 	printInFile("I love the snow, you know ? Jon FUCKING SNOW !!!");
	};
}