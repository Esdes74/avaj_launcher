package src.models.aircraft;

import src.content.Aircraft;
import src.singletons.WeatherProvider;
import src.utils.Coordinates;
import src.singletons.PrintInFile;
import src.ExitException;
import java.lang.String;

public class Baloon extends Aircraft {
	public	Baloon(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public	void updateConditions() throws ExitException {
		String	currentWeather;

		currentWeather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
		if (currentWeather.contentEquals("RAIN"))
			PrintInFile.getInstance().print("Baloon#" + name + "(" + id + "): It's raining, IT'S RAINING !!! It's a miracle !!!");
		else if (currentWeather.contentEquals("FOG"))
			PrintInFile.getInstance().print("Baloon#" + name + "(" + id + "): I can't see the ground, may i crash ?");
		else if (currentWeather.contentEquals("SUN"))
			PrintInFile.getInstance().print("Baloon#" + name + "(" + id + "): What a beautifull day !");
		else if (currentWeather.contentEquals("SNOW"))
			PrintInFile.getInstance().print("Baloon#" + name + "(" + id + "): Liberated, freed. I'll never lie again");
	};

	public	String registeredMessage() throws ExitException { return "Baloon#" + name + "(" + id + ") Registered to weather tower"; }
	public	String unregisteredMessage() throws ExitException { return "Baloon#" + name + "(" + id + ") Unregistered to weather tower"; }
}