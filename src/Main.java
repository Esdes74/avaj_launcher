package src;

import src.utils.Utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static	void main(String[] args) {
		int			simulationLength = 0;
		String		line;
		String []	splittedLine;

		if (args.length != 1) {
			exit(1, "Must have 1 argument");
		}

		parseArgumentNameAndTestOpening(args[0]);

		try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
			if ((line = reader.readLine()) == null) {
				exit(1, "File error: empty file");
			} else {
				parseFirstLine(line);
				simulationLength = Integer.parseInt(line);
			}

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				splittedLine = parseLine(line);
			}

		} catch (IOException e) {
			exit(1, "Reading error: " + e.getMessage());
		}
	}

	private static	void parseArgumentNameAndTestOpening(String name) {
		if (Files.exists(Paths.get(name)) == false)
			exit(1, "File error: File '" + name + "' doesn't exists");

		if (Files.isRegularFile(Paths.get(name)) == false)
			exit(1, "File error: File '" + name + "' is a repository");

		if (Files.isReadable(Paths.get(name)) == false)
			exit(1, "File error: Can't read '" + name + "'");

		if (name.endsWith(".txt") == false)
			exit(1, "File error: Wrong extension");
	};

	private static	void parseFirstLine(String line) {
		String[]	splitted;

		splitted = line.split(" ");
		if (line.isBlank() || 
		splitted.length != 1 || 
		Utils.isOnlyNumber(line) == false) {
			exit(1, "Reading error: Wrong first line format");
		}
	}

	private static	String[] parseLine(String line) {
		String[]	splitted;
		int			height = -1;

		splitted = line.split(" ");
		if (splitted.length != 5)
			exit(1, "Reading error: Wrong line format");

		if (splitted[0].equalsIgnoreCase("baloon") == false && 
		splitted[0].equalsIgnoreCase("helicopter") == false && 
		splitted[0].equalsIgnoreCase("jetplane") == false)
			exit(1, "Reading error: Wrong aircraft type");

		height = Integer.parseInt(splitted[4]);
		if ((Utils.isOnlyNumber(splitted[2]) == false ||
		Utils.isOnlyNumber(splitted[3]) == false ||
		Utils.isOnlyNumber(splitted[4]) == false) && height > 0 && height <= 100)
			exit(1, "Reading error: Wrong informations");
		
		return splitted;
	}

	private static	void exit(int code, String message) {
		System.out.println(message);
		System.exit(code);
	}
}