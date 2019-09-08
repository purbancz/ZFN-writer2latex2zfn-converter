package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import main.java.bibtex.BibEntries;
import main.java.bibtex.BibParser;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "res/Kurkowski-org4.tex";
		String newFile = "res/Kurkowski-org4-PU.tex";
		String bibFile = "res/Kurkowski.bib";
		
		

		TexParser parser = new TexParser();
		SaveFile saver = new SaveFile();
		DummyLinesCleaner linesCleaner = new DummyLinesCleaner();
		DummyCharactersCleaner charCleaner = new DummyCharactersCleaner();
		BibParser bibParser = new BibParser();
		
		ArrayList<BibEntries> bibEntries = bibParser.parseBibFile(bibFile);

		ArrayList<String> texLines = parser.parseTexFile(fileName);

		texLines = linesCleaner.cleanDummyLines(texLines);
		
		charCleaner.clearDummyCharacters(texLines);



//		for (String s : texLines) {
//			System.out.println(s);
//		}
		


		saver.rewriteFile(newFile, texLines);
		
		
		
		for (BibEntries entry : bibEntries) {
			System.out.println(entry);
			
		}

	}

}
