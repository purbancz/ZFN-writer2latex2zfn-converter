package main.java;

import java.util.ArrayList;

public class DummyLinesCleaner {

	public ArrayList<String> cleanDummyLines(ArrayList<String> texLines) {
		ArrayList<String> newTexLines = new ArrayList<>();
		boolean egg;

		for (int i = 0; i < texLines.size(); i++) {
			egg = true;
			String s = texLines.get(i).trim().replaceAll(" +", " ");
			
			long leftBracker = s.chars().filter(ch -> ch == '{').count();
			 
			long rightBracket = s.chars().filter(ch -> ch == '}').count();
			
			if (s.startsWith("\\documentclass") || s.startsWith("\\usepackage") || s.startsWith("\\newcommand")
					|| s.startsWith("\\renewcommand") || s.startsWith("\\setlength") || s.startsWith("\\hypersetup")
					|| s.startsWith("%")) {
				egg = false;
			}
			
			if (s.startsWith("{") && leftBracker > rightBracket) {
				egg = false;
			}
		
			if (leftBracker < rightBracket && s.endsWith("}")) {
				s = s.substring(0, s.length()-1);
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
