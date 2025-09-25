package com.eslamber.src.singletons;

import com.eslamber.src.ExitException;
import com.eslamber.src.utils.Utils;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
			FileWriter openedFile = new FileWriter(fileName, false);
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

	public			void deleteFile() {
		Path path = Paths.get("simulation.txt");
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
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