package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "res/Lobinski-org3.tex";
		String newFile = "res/Lobinski-org3-PU.tex";

		TexParser parser = new TexParser();
		SaveFile saver = new SaveFile();
		DummyLinesCleaner linesCleaner = new DummyLinesCleaner();
		DummyCharactersCleaner charCleaner = new DummyCharactersCleaner();

		ArrayList<String> texLines = parser.parseTexFile(fileName);

		texLines = linesCleaner.cleanDummyLines(texLines);
		
		charCleaner.clearDummyCharacters(texLines);



		for (String s : texLines) {
			System.out.println(s);
		}

		saver.rewriteFile(newFile, texLines);

	}

}
