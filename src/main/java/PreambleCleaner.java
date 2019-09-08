package main.java;

import java.util.ArrayList;

public class PreambleCleaner {

	public ArrayList<String> cleanPreamble(ArrayList<String> texLines) {
		ArrayList<String> newTexLines = new ArrayList<>();
		boolean egg;

		for (int i = 0; i < texLines.size(); i++) {
			egg = true;
			String s = texLines.get(i);
			if (s.startsWith("\\documentclass") || s.startsWith("\\usepackage") || s.startsWith("\\newcommand")
					|| s.startsWith("\\renewcommand") || s.startsWith("\\setlength") || s.startsWith("\\hypersetup")
					|| s.startsWith("%")) {
				egg = false;
			}

			if (egg) {
				newTexLines.add(s);
			}

			// adding lines after subsections (not in preamble)
			if (s.startsWith("\\sub")) {
				newTexLines.add("");
			}

		}
		return newTexLines;
	}

}
