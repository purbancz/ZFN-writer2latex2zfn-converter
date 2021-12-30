package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import main.java.bibtex.BibEntries;
import main.java.bibtex.BibFinder;
import main.java.bibtex.BibParser;
import main.java.cleaners.DummyCharactersCleaner;
import main.java.cleaners.DummyLinesCleaner;
import main.java.utility.SaveFile;
import main.java.utility.TexParser;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		String texFile = "res/krzanowski_exp.tex";
		String bibFile = "res/krzanowski.bib";
		String newFile = "res/krzanowski.tex";

		TexParser parser = new TexParser();
		SaveFile saver = new SaveFile();
		DummyLinesCleaner linesCleaner = new DummyLinesCleaner();
		DummyCharactersCleaner charCleaner = new DummyCharactersCleaner();
		BibParser bibParser = new BibParser();
		BibFinder bibFinder = new BibFinder();

		ArrayList<BibEntries> bibEntries = bibParser.parseBibFile(bibFile);

		ArrayList<String> texLines = parser.parseTexFile(texFile);

		texLines = linesCleaner.cleanDummyLines(texLines);

		charCleaner.clearDummyCharacters(texLines);

		texLines = bibFinder.findBibReferences(texLines, bibEntries);
//		texLines = bibFinder.findBibReferences(texLines);

		for (String s : texLines) {
			System.out.println(s);
		}

		saver.rewriteFile(newFile, texLines);
		
		

		for (BibEntries entry : bibEntries) {
			System.out.println(entry);
		}


		
		
	}

}
