package src.singletons;

import src.utils.Coordinates;
import java.lang.String;

public class WeatherProvider {
	private static	WeatherProvider instance;
	private			String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

	private			WeatherProvider() {};

	public static	WeatherProvider getInstance() {
		if (instance == null)
			instance = new WeatherProvider();
		return instance;
	};

	public			String getCurrentWeather(Coordinates p_coordinates) {
		return weather[calculWeather(p_coordinates)];
	}

	private			int calculWeather(Coordinates p_coordinates) {
		int		longitude = p_coordinates.getLongitude();
		int		latitude = p_coordinates.getLatitude();
		int		height = p_coordinates.getHeight();
		int		drawWeather;
		Random	randomGenerator = new Random();

		if (longitude > 500 || longitude < 0 || latitude > 500 || latitude < 0)
			return 3; // Return 3 for snow (weather[3] = "SNOW")

		drawWeather = randomGenerator.nextInt(100);

		if (height > 70) {
			return drawUpToCloud(height, drawWeather);
		} else if (height <= 70 && height > 55) {
			return drawInCloud(height, drawWeather);
		} else if (height <= 55 && height > 25) {
			return drawUnderCloud(height, drawWeather);
		} else {
			return drawNearFloor(height, drawWeather);
		}
	}

	private	int drawUpToCloud(int height, int drawWeather) {
		int	beforeHundred = 100 - height;
		int	drawBeforeHundred = beforeHundred / 3;

		if (drawWeather < height || height > 97)
			return 2; // weather[2] = "SUN"
		else if (drawWeather < height + drawBeforeHundred)
			return 0; // weather[0] = "RAIN"
		else if (drawWeather < height + (drawBeforeHundred * 2))
			return 1; // weather[1] = "FOG"
		return 3; // weather[3] = "SNOW"
	}

	private	int drawInCloud(int height, int drawWeather) {
		if (drawWeather < 50)
			return 1; // FOG
		else if (drawWeather < 70)
			return 0; // RAIN
		else if (drawWeather < 90)
			return 3; // SNOW
		return 2; // SUN
	}

	private	int drawUnderCloud(int height, int drawWeather) {
		if (drawWeather < 40)
			return 0; // RAIN
		else if (drawWeather < 55)
			return 3; // SNOW
		else if (drawWeather < 85)
			return 2; // SUN
		return 1; // FOG
	}

	private	int drawNearFloor(int height, int drawWeather) {
		if (drawWeather < 70)
			return 1; // FOG
		else if (drawWeather < 75)
			return 0; // RAIN
		else if (drawWeather < 80)
			return 3; // SNOW
		return 2; // SUN
	}
}