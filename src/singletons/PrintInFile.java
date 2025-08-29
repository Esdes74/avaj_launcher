package src.singletons;

import src.ExitException;
import src.utils.Utils;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class PrintInFile {
	private			BufferedWriter file;
	private static	PrintInFile instance = null;
	
	private			PrintInFile() throws ExitException { openFile("simulation.txt"); }

	public static	PrintInFile getInstance() throws ExitException {
		if (instance == null)
			instance = new PrintInFile();
		return instance;
	}

	private			void openFile(String fileName) throws ExitException {
		try {
			FileWriter openedFile = new FileWriter(fileName, true);
			file = new BufferedWriter(openedFile);
		} catch (IOException expt) {
			closeFile();
			Utils.exit(1, "Simulation.txt file error: " + expt.getMessage());
		}
	}

	public			void print(String contenu) throws ExitException {
		if (file != null) {
			try {
				file.write(contenu);
				file.newLine();
				file.flush();
			} catch (IOException expt) {
				closeFile();
				Utils.exit(1, "Simulation.txt file error: " + expt.getMessage());
			}
		}
	}

	public			void closeFile() throws ExitException {
		if (file != null) {
			try {
				file.close();
			} catch (IOException expt) {
				Utils.exit(1, "Simulation.txt file error: " + expt.getMessage());
			}
		}
	}
}