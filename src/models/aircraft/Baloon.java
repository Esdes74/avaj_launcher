package src.models.aircraft;

import src.content.Aircraft;
import src.singletons.WeatherProvider;
import src.utils.Coordinates;
import src.singletons.PrintInFile;
import src.ExitException;

public class Baloon extends Aircraft {
	public	Baloon(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public	void updateConditions() throws ExitException {
		String	currentWeather;

		currentWeather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
		if (currentWeather.contentEquals("RAIN"))
			PrintInFile.getInstance().print("It's raining, IT'S RAINING !!! It's a miracle !!!");
		else if (currentWeather.contentEquals("FOG"))
			PrintInFile.getInstance().print("I can't see the ground, may i crash ?");
		else if (currentWeather.contentEquals("SUN"))
			PrintInFile.getInstance().print("What a beautifull day !");
		else if (currentWeather.contentEquals("SNOW"))
			PrintInFile.getInstance().print("Liberated, freed. I'll never lie again");
	};
}