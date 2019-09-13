package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import main.java.bibtex.BibEntries;
import main.java.bibtex.BibFinder;
import main.java.bibtex.BibParser;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		String texFile = "res/Kurkowski-org4.tex";
		String bibFile = "res/Kurkowski.bib";
		String newFile = "res/Kurkowski-org4-PU.tex";
		
		

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
		
		texLines = bibFinder.findBibReferences(texLines);



		for (String s : texLines) {
			System.out.println(s);
		}
		


		saver.rewriteFile(newFile, texLines);
		
		
		
		for (BibEntries entry : bibEntries) {
			System.out.println(entry);
		}
		
		String dupa = "mloda Dupa";
		System.out.println(dupa.substring(firstUpperCase(dupa)));
		
		

	}
	
	
	public static int firstUpperCase(String str) {        
	    for(int i=0; i<=str.length()-1; i++) {
	        if(Character.isUpperCase(str.charAt(i))) {
	            return i;
	        }
	    }
	    return 0;
	}

}
