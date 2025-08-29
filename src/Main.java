package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static	void main(String[] args) {
		String	line;
		int		simulationLenght = 0;

		if (args.length != 1) {
			exit(1, "Must have 1 argument");
		}

		parseArgumentNameAndTestOpening(args[0]);

		try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
			if ((line = reader.readLine()) == null) {
				exit(1, "File error: empty file");
			} else {
				parseFirstLine(line);
				simulationLenght = Integer.parseInt(line);
			}

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
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
		if (line.isBlank() || splitted.length != 1 || isOnlyNumber(line) == false) {
			exit(1, "Reading error: Wrong first line format");
		}
	}

	private static	boolean isOnlyNumber(String line) {
		int	index = 0;

		while (index < line.length() && isNumber(line.charAt(index))) {
			index++;
		}

		if (index == line.length())
			return true;
		return false;
	}

	private static	boolean isOnlyAlphabet(String line) {
		int	index = 0;

		while (index < line.length() && isAlphabet(line.charAt(index))) {
			index++;
		}

		if (index == line.length())
			return true;
		return false;
	}

	private static	boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}

	private static	boolean isAlphabet(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	private static	void exit(int code, String message) {
		System.out.println(message);
		System.exit(code);
	}
}