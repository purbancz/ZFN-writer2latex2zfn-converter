package main.java.bibtex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibFinder {

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
				texBibLines.add(matcher.group(3) + matcher.group(5) + "%");
				temporaryMatcherEnd = matcher.end();
				while (matcher.find()) {
					texBibLines.add(par.substring(temporaryMatcherEnd, matcher.start()));
					texBibLines.add("%" + matcher.group());
					texBibLines.add(matcher.group(3) + matcher.group(5) + "%");
					temporaryMatcherEnd = matcher.end();
				}
				texBibLines.add(par.substring(temporaryMatcherEnd, par.length()));
			}

		}
		return texBibLines;
	}

}
