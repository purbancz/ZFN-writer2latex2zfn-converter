package main.java.bibtex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BibParser {

	private static BufferedReader inReader;

	public ArrayList<BibEntries> parseBibFile(String pathToFile) {

		ArrayList<BibEntries> listBibEntries = new ArrayList<>();

		ArrayList<String> bibLines = new ArrayList<>();
		int lastEntryStartLine = 0;
//		boolean newEntry = true;

		File in = new File(pathToFile);

		String line = "";
//		BibEntries entry = new BibEntries("notabene", "noauthor", "noyear");
		BibEntries entry = null;

		try {
			inReader = new BufferedReader(new FileReader(in));
			while ((line = inReader.readLine()) != null) {
				bibLines.add(line);
			}
		} catch (IOException x) {
			System.err.format("IOException: %s", x);
		}

		for (int i = 0; i < bibLines.size(); i++) {
			line = bibLines.get(i).trim();
//			line = Normalizer.normalize(bibLines.get(i).trim(), Normalizer.Form.NFKD).replaceAll("[\\p{M}]", "");
			if (line.startsWith("@")) {
				String[] keyLine = line.split("\\{");
				lastEntryStartLine = i;
				entry = new BibEntries("", "", "");
				entry.setKey(keyLine[1].substring(0, keyLine[1].length() - 1));
			}
			if (line.startsWith("author")) {
				String[] keyAuthor = line.split("\\{");
				entry.setAuthor(keyAuthor[1].substring(0, keyAuthor[1].length() - 2));
			}
			if (line.startsWith("year")) {
				String[] keyYear = line.split("\\{");
				entry.setYear(keyYear[1].substring(0, keyYear[1].length() - 2));
			}
			if (line.equals("}")) {
				if (entry.getAuthor() == null || entry.getAuthor() == "") {
//					entry.setAuthor("Dupa" + lastEntryStartLine);
					for (int y = i; y > lastEntryStartLine; y--) {
						line = bibLines.get(y).trim();
						if (line.startsWith("editor")) {
							String[] keyAuthor = line.split("\\{");
							entry.setAuthor(keyAuthor[1].substring(0, keyAuthor[1].length() - 2));
							break;
						}
					}
				}
				listBibEntries.add(entry);
			}
		}

		return listBibEntries;

	}

}
