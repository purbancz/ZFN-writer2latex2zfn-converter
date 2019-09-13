package main.java.bibtex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibFinder {
	//
	String bibFile = "res/Kurkowski.bib";
	BibParser bibParser = new BibParser();
	ArrayList<BibEntries> bibEntries = bibParser.parseBibFile(bibFile);
	//

	ArrayList<String> texBibLines = new ArrayList<>();
	

	String regex = "(\\\\label\\{ref:RND[a-zA-Z0-9]{10}\\})(\\()([^\\)]{4,})(\\))([.,:;?!])?";
	Pattern pattern = Pattern.compile(regex);

	public ArrayList<String> findBibReferences(ArrayList<String> texLines) {
		for (int i = 0; i < texLines.size(); i++) {

			String par = texLines.get(i);
			Matcher matcher = pattern.matcher(par);
			int temporaryMatcherEnd = 0;

			if (!matcher.find()) {
				texBibLines.add(par);
			} else {
				texBibLines.add(par.substring(0, matcher.start()));
				texBibLines.add("%" + matcher.group());
				texBibLines.add(createBibEntry(matcher.group(3)));
				texBibLines.add(matcher.group(3) + matcher.group(5) + "%");
				temporaryMatcherEnd = matcher.end();
				while (matcher.find()) {
					texBibLines.add(par.substring(temporaryMatcherEnd, matcher.start()));
					texBibLines.add("%" + matcher.group());
					texBibLines.add(createBibEntry(matcher.group(3)));
					texBibLines.add(matcher.group(3) + matcher.group(5) + "%");
					temporaryMatcherEnd = matcher.end();
				}
				texBibLines.add(par.substring(temporaryMatcherEnd, par.length()));
			}

		}
		return texBibLines;
	}

	public String createBibEntry(String bibEntryRaw) {
		
		ArrayList<BibReferences> bibRefs = new ArrayList<>();

		String[] bibEntriesText = bibEntryRaw.split("; ");
		String bibTexFormula = "";

		String author = "";
		String year = "";

		BibReferences bibRef = null;

		if (bibEntriesText.length == 1) {
			bibTexFormula = "\\parencite";
		} else {
			bibTexFormula = "\\parencites";
		}

		for (int i = 0; i < bibEntriesText.length; i++) {
			author = "";
			year = "";
			bibRef = new BibReferences("", "", "");
			String[] bibSingleTextEntry = bibEntriesText[i].split(", ");

			for (String subs : bibSingleTextEntry) {

				if (Character.isDigit(subs.charAt(0))) {
					year = subs.split(" ")[0];
				}
				if (Character.isLowerCase(subs.charAt(0))) {
					if (!year.isEmpty()) {
						bibRef.setSuffix(subs);
					} else {
						bibRef.setPrefix(bibRef.getPrefix() + subs.substring(0, firstUpperCase(subs) - 1));
						if (!author.isEmpty()) {
							author = author + ", ";
						}
						author = author + subs.substring(firstUpperCase(subs));
//								+ Normalizer.normalize(subs.substring(firstUpperCase(subs)), Normalizer.Form.NFKD)
//										.replaceAll("[\\p{M}]", "");
					}
				}
				if (Character.isUpperCase(subs.charAt(0))) {
					if (!author.isEmpty()) {
						author = author + ", ";
					}
					author = author + subs;
//							+ Normalizer.normalize(subs, Normalizer.Form.NFKD).replaceAll("[\\p{M}]", "");
				}
			}
			
			if (author.isEmpty()) {
				bibTexFormula = bibTexFormula + "*";
			}

			for (BibEntries bibEntry : bibEntries) {
				if (areAuthors(author.split(", "), bibEntry.getAuthor()) && bibEntry.getYear().equals(year)) {
					bibRef.setKey(bibEntry.getKey());
				}
			}
			bibRefs.add(bibRef);
		}

//		if (author.isEmpty()) {
//			bibTexFormula = bibTexFormula + "*";
//		}

		for (BibReferences bibFinalRef : bibRefs) {
			bibTexFormula = bibTexFormula + bibFinalRef.toString();
		}


		return bibTexFormula;
	}

	public int firstUpperCase(String str) {
		for (int i = 0; i <= str.length() - 1; i++) {
			if (Character.isUpperCase(str.charAt(i))) {
				return i;
			}
		}
		return 0;
	}

	public boolean areAuthors(String[] keywords, String word) {
		for (String k : keywords)
			if (!word.contains(k))
				return false;
		return true;
	}

}
