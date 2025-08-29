package src.content;

import src.abst.Flyable;
import java.util.ArrayList;
import java.util.ListIterator;

public class Tower {
	private		ArrayList<Flyable> observers = new ArrayList<Flyable>();

	public		void register(Flyable p_flyable) {
		observers.add(p_flyable);
	}

	public		void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
	}

	public		ArrayList<Flyable> getObservers() { return observers; };

	protected	void conditionChanged() {
		ListIterator<Flyable>	iter = null;
		Flyable					currentAircraft = null;

		iter = observers.listIterator();

		while (iter.hasNext()) {
			currentAircraft = iter.next();
			currentAircraft.updateConditions();
		}
	};
}