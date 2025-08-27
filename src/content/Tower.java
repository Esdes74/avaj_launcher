package avaj_launcher.content;

import avaj_launcher.abst.Flyable;
import java.util.ArrayList;

public class Tower {
	private		ArrayList<Flyable>	observers;

	public		void register(Flyable p_flyable) {};
	public		void unregister(Flyable p_flyable) {};

	protected	void conditionChanged() {};
}