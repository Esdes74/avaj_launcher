package src.content;

import src.abst.Flyable;
import src.utils.Coordinates;
import java.lang.String;
import src.ExitException;

public class Aircraft extends Flyable {
	protected	long id;
	protected	String name;
	protected	Coordinates coordinates;

	public		void updateConditions() throws ExitException {};
	protected	Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
		id = p_id;
		name = p_name;
		coordinates = p_coordinates;
	};

	public		Coordinates getCoordinates() { return coordinates; }

	public		long getId() { return id; };
	public		String getName() { return name; };
	public		String registeredMessage() throws ExitException { return ""; }
	public		String unregisteredMessage() throws ExitException { return ""; }
	public		String crashMessage() throws ExitException { return ""; }
}