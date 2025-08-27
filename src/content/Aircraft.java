package avaj_launcher.content;

import avaj_launcher.abst.Flyable;
import avaj_launcher.utils.Coordinates;
import java.lang.String;

public class Aircraft extends Flyable {
	protected	long id;
	protected	String name;
	protected	Coordinates coordinates;

	public		void updateConditions() {};
	protected	Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
		id = p_id;
		name = p_name;
		coordinates = p_coordinates;
	};

	public		long getId() { return id; };
}