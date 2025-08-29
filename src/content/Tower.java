package src.content;

import src.abst.Flyable;
import java.util.ArrayList;
import java.util.ListIterator;
import src.ExitException;
import src.singletons.PrintInFile;
import src.ExitException;

public class Tower {
	private		ArrayList<Flyable> observers = new ArrayList<Flyable>();

	public		void register(Flyable p_flyable) throws ExitException {
		PrintInFile.getInstance().print("bonjour a tous"); // TODO: Mettre les vrais messages de sortie
		observers.add(p_flyable);
	}

	public		void unregister(Flyable p_flyable) {
		PrintInFile.getInstance().print("Au revoir"); // TODO: Mettre les vrais messages de sortie
		observers.remove(p_flyable);
	}

	public		ArrayList<Flyable> getObservers() { return observers; };

	protected	void conditionChanged() throws ExitException {
		ListIterator<Flyable>	iter = null;
		Flyable					currentAircraft = null;

		iter = observers.listIterator();

		while (iter.hasNext()) {
			currentAircraft = iter.next();
			currentAircraft.updateConditions();
		}
	};
}