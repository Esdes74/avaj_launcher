package src.content;

import src.abst.Flyable;
import src.ExitException;
import src.singletons.PrintInFile;
import src.utils.Utils;
import src.ExitException;
import java.util.ArrayList;
import java.util.ListIterator;

public class Tower {
	private		ArrayList<Flyable> observers = new ArrayList<Flyable>();

	public		void register(Flyable p_flyable) throws ExitException {
		PrintInFile.getInstance().print("Tower say: " + p_flyable.registeredMessage());
		observers.add(p_flyable);
	}

	public		void unregister(Flyable p_flyable) throws ExitException {
		PrintInFile.getInstance().print("Tower say: " + p_flyable.unregisteredMessage());
		observers.remove(p_flyable);
	}

	public		void closeTower() throws ExitException {
		ArrayList<Flyable> copy = new ArrayList<>(observers);
		for (Flyable aircraft : copy) {
			try {
				unregister(aircraft);
			} catch (ExitException e) {
				Utils.exit(1, "Error: Can't unregister all aircrafts");
			}
		}
	}

	public		ArrayList<Flyable> getObservers() { return observers; };

	protected	void conditionChanged() throws ExitException {
		for (Flyable aircraft : observers) {
			try {
				aircraft.updateConditions();
			} catch (ExitException e) {
				Utils.exit(1, "Error: Can't update weather for all aircrafts");
			}
		}
	};
}