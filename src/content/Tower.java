package src.content;

import src.abst.Flyable;
import src.ExitException;
import src.singletons.PrintInFile;
import java.util.ArrayList;
import java.lang.String;

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
		ArrayList<Flyable> observersCopy = new ArrayList<>(observers);
		for (Flyable aircraft : observersCopy) {
			unregister(aircraft);
		}
	}

	public		ArrayList<Flyable> getObservers() { return observers; };
	public		String crashMessage(Flyable aircraftOne, Flyable aircraftTwo) throws ExitException {
		return "Tower say: " + aircraftOne.getName() + "(" + aircraftOne.getId() + ") do you copy me ? You going to hit " + aircraftTwo.getName() + "(" + aircraftTwo.getId() + ")";
	}

	protected	void conditionChanged() throws ExitException {
		ArrayList<Flyable> observersCopy = new ArrayList<>(observers);
		for (Flyable aircraft : observersCopy) {
			if (observers.contains(aircraft))
				aircraft.updateConditions();
		}
	};
}