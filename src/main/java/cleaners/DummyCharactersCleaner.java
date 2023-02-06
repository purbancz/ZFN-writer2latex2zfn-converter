package main.java.cleaners;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DummyCharactersCleaner {

	public void clearDummyCharacters(ArrayList<String> texLines) {
		for (int i = 0; i < texLines.size(); i++) {

			String s = texLines.get(i).trim().replaceAll(" +", " ");

			s = s.replace("\\ ", "");

			s = s.replace("„", ",,");
			s = s.replace("”", "''");
			s = s.replace("’", "'");
			s = s.replace("“", "``");
			s = s.replace(" s. ", " s.~");
			s = s.replace(" ss. ", " ss.~");
			s = s.replace(" t. ", " t.~");
			s = s.replace(" rozdz. ", " rozdz.~");
			s = s.replace(" r. ", "~r.");
			s = s.replace(" – ", " -- ");
			s = s.replace("—", "---");
			s = s.replace("\\par", "");

			s = s.trim().replaceAll(" +", " ");

			//line widows
			s = s.replaceAll("(\\s)([a-zA-Z])\\s", "$1$2~");
			s = s.replaceAll("(\\{)([a-zA-Z])\\s", "$1$2~");
			s = s.replaceAll("^([a-zA-Z])\\s", "$1~");

			//dummy styles
			s = s.replaceAll("\\\\[a-zA-Z]{1,}\\{\\s?\\}", "");
			//section name cleaning
			s = s.replaceAll("(\\\\[sub]{0,}section)(\\[.{1,}\\])?\\{(\\s?\\d\\.\\s)?(.{1,})\\}", "$1\\{$4\\}");
			
			//spaces before commas and periods
			s = s.replaceAll("(\\s)([\\.,?!;]{1}|'')(\\s)", "$2$3");
			s = s.replaceAll("(,,)(\\s)", "$1");

			s = clearleftBracket("textit", s);
			s = clearRightBracket("textit", s);
			s = clearleftBracket("textbf", s);
			s = clearRightBracket("textbf", s);

			s = s.trim().replaceAll(" +", " ");

			s = s.replace("\\textbf{ }", " ");
			s = s.replace("\\textit{ }", " ");
			s = s.replace("\\textbf{}", "");
			s = s.replace("\\textit{}", "");
			
			s = s.replace("\\footnote{ ", "\\footnote{");

			
			
			texLines.set(i, s);

		}

	}


	private String clearleftBracket(String enviroment, String text) {
		String regex = "(\\\\" + enviroment + "\\{)([^\\p{L}\\\\])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		text = text.replaceAll(regex, "$2$1");
		while (matcher.find()) {
			text = text.replaceAll(regex, "$2$1");
		}
		return text;
	}

	private String clearRightBracket(String enviroment, String text) {
		String regex = "(\\\\" + enviroment + "\\{.{0,}?)([^\\p{L}0-9\\.])(\\})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		text = text.replaceAll(regex, "$1$3$2");
		while (matcher.find()) {
			text = text.replaceAll(regex, "$1$3$2");
		}
		return text;
	}

}
