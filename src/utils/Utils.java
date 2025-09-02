package src.utils;

import src.ExitException;
import src.singletons.PrintInFile;

public class Utils {
	public static	boolean isOnlyNumber(String line) {
		int	index = 0;

		while (index < line.length() && isNumber(line.charAt(index))) {
			index++;
		}

		if (index == line.length())
			return true;
		return false;
	}

	public static	boolean isOnlyAlphabet(String line) {
		int	index = 0;

		while (index < line.length() && isAlphabet(line.charAt(index))) {
			index++;
		}

		if (index == line.length())
			return true;
		return false;
	}

	public static	boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}

	public static	boolean isAlphabet(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	public static	void exit(int code, String message) throws ExitException {
		System.out.println(message);
		PrintInFile.getInstance().closeFile();
		throw new ExitException(code);
	}
}