package src.models.aircraft;

import src.content.Aircraft;
import src.singletons.WeatherProvider;
import src.utils.Coordinates;
import src.singletons.PrintInFile;
import src.ExitException;

public class JetPlane extends Aircraft {
	public	JetPlane(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public	void updateConditions() throws ExitException {
		String	currentWeather;

		currentWeather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
		if (currentWeather.contentEquals("RAIN"))
			PrintInFile.getInstance().print("It's raining men! Hallelujah!");
		else if (currentWeather.contentEquals("FOG"))
			PrintInFile.getInstance().print("There is fog everywhere... I can't even see my nose");
		else if (currentWeather.contentEquals("SUN"))
			PrintInFile.getInstance().print("Oh i can see the ground, your soooo tiny");
		else if (currentWeather.contentEquals("SNOW"))
			PrintInFile.getInstance().print("I love the snow, you know ? Jon FUCKING SNOW !!!");
	};
}