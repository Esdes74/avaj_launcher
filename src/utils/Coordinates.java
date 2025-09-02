package src.utils;

public class Coordinates {
	private	int longitude;
	private	int latitude;
	private	int height;

	Coordinates(int p_longitude, int p_latitude, int p_height) {
		longitude = p_longitude;
		latitude = p_latitude;
		height = p_height;
	};

	public static	Coordinates createInstance(int p_longitude, int p_latitude, int p_height) {
		if (p_height > 100)
			p_height = 100;

		return new Coordinates(p_longitude, p_latitude, p_height);
	};

	public	int getLongitude() { return longitude; };
	public	int getLatitude() { return latitude; };
	public	int getHeight() { return height; };
	public	void setLongitude(int p_longitude) { longitude = p_longitude; };
	public	void setLatitude(int p_latitude) { latitude = p_latitude; };
	public	void setHeight(int p_height) {
		height = p_height;
		if (height > 100)
			height = 100;
	};
}