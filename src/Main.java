package src;

import src.utils.Utils;
import src.utils.Coordinates;
import src.singletons.AircraftFactory;
import src.singletons.PrintInFile;
import src.models.WeatherTower;
import src.ExitException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static	void main(String[] args) throws ExitException {
		int				simulationLength = 0, weathersChanged = 0;
		String			line;
		String[]		splittedLine;
		WeatherTower	controlTower = new WeatherTower();

		if (args.length != 1) {
			Utils.exit(1, "Error: Must have 1 argument");
		}

		parseArgumentNameAndTestOpening(args[0]);

		try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
			if ((line = reader.readLine()) == null) {
				Utils.exit(1, "File error: empty file");
			} else {
				parseFirstLine(line);
				simulationLength = Integer.parseInt(line);
			}

			AircraftFactory.getInstance().registerTower(controlTower);

			while ((line = reader.readLine()) != null) {
				splittedLine = parseLine(line);
				createAircraft(splittedLine);
			}

			while (weathersChanged < simulationLength) {
				controlTower.changeWeather();
				if (weathersChanged % 10 == 0)
					controlTower.clearCoordinateWeather();
				weathersChanged++;
			}
		} catch (IOException e) {
			Utils.exit(1, "Reading error: " + e.getMessage());
		} catch (ExitException e) {
			System.exit(e.getCode());
		}

		controlTower.closeTower();
		PrintInFile.getInstance().closeFile();
	}

	private static	void parseArgumentNameAndTestOpening(String name) throws ExitException {
		if (Files.exists(Paths.get(name)) == false)
			Utils.exit(1, "File error: File '" + name + "' doesn't exists");

		if (Files.isRegularFile(Paths.get(name)) == false)
			Utils.exit(1, "File error: File '" + name + "' is a repository");

		if (Files.isReadable(Paths.get(name)) == false)
			Utils.exit(1, "File error: Can't read '" + name + "'");

		if (name.endsWith(".txt") == false)
			Utils.exit(1, "File error: Wrong extension");
	};

	private static	void parseFirstLine(String line) throws ExitException {
		String[]	splitted;

		splitted = line.split(" ");
		if (line.isBlank() || 
		splitted.length != 1 || 
		Utils.isOnlyNumber(line) == false) {
			Utils.exit(1, "Reading error: Wrong first line format");
		}
	}

	private static	String[] parseLine(String line) throws ExitException {
		String[]	splitted;
		int			longitude = -1, latitude = -1, height = -1;

		splitted = line.split(" ");
		if (splitted.length != 5)
			Utils.exit(1, "Reading error: Wrong line format");

		if (splitted[0].equalsIgnoreCase("baloon") == false && 
		splitted[0].equalsIgnoreCase("helicopter") == false && 
		splitted[0].equalsIgnoreCase("jetplane") == false)
			Utils.exit(1, "Reading error: Wrong aircraft type");

		if (Utils.isOnlyNumber(splitted[2]) == false ||
		Utils.isOnlyNumber(splitted[3]) == false ||
		Utils.isOnlyNumber(splitted[4]) == false)
			Utils.exit(1, "Reading error: Wrong informations");
			
		longitude = Integer.parseInt(splitted[2]);
		latitude = Integer.parseInt(splitted[3]);
		height = Integer.parseInt(splitted[4]);
		if (longitude < 0 || latitude < 0 || height <0)
			Utils.exit(1, "Reading error: Wrong coordinates");

		return splitted;
	}

	private static	void createAircraft(String[] splittedLine) throws ExitException {
		int			longitude, latitude, height;
		String		type, name;
		Coordinates	lineCoordinates;

		longitude = Integer.parseInt(splittedLine[2]);
		latitude = Integer.parseInt(splittedLine[3]);
		height = Integer.parseInt(splittedLine[4]);
		lineCoordinates = Coordinates.createInstance(longitude, latitude, height);

		type = splittedLine[0];
		name = splittedLine[1];
		AircraftFactory.getInstance().newAircraft(type, name, lineCoordinates);
	}
}