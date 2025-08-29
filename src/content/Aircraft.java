package src.content;

import src.abst.Flyable;
import src.utils.Coordinates;
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