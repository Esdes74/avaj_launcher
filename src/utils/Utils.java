package src.utils;

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
}