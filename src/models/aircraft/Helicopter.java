package src.models.aircraft;

import src.content.Aircraft;
import src.singletons.WeatherProvider;
import src.utils.Coordinates;
import src.singletons.PrintInFile;
import src.ExitException;

public class Helicopter extends Aircraft {
	public	Helicopter(long p_id, String p_name, Coordinates p_coordinates) { super(p_id, p_name, p_coordinates); };
	public	void updateConditions() throws ExitException {
		String	currentWeather;

		currentWeather = WeatherProvider.getInstance().getCurrentWeather(coordinates);
		if (currentWeather.contentEquals("RAIN"))
			PrintInFile.getInstance().print("Here's Jhonny !!");
		else if (currentWeather.contentEquals("FOG"))
			PrintInFile.getInstance().print("Tell my wife and childrens i love them");
		else if (currentWeather.contentEquals("SUN"))
			PrintInFile.getInstance().print("I love my life !!!");
		else if (currentWeather.contentEquals("SNOW"))
			PrintInFile.getInstance().print("Winter is comming");
	};
}