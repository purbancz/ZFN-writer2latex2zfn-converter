package main.java;

import java.util.ArrayList;

public class DummyCharactersCleaner {

	public void clearDummyCharacters(ArrayList<String> texLines) {
		for (int i = 0; i < texLines.size(); i++) {

			String s = texLines.get(i).trim().replaceAll(" +", " ");

			s = s.replace("\\ ", "");

			s = s.replace("„", ",,");
			s = s.replace("”", "'',");
			s = s.replace("’", "',");
			s = s.replace(" s. ", " s.~");
			s = s.replace(" ss. ", " ss.~");
			s = s.replace(" t. ", " t.~");
			s = s.replace(" r. ", "~r.");
			s = s.replace(" – ", " -- ");
			s = s.replace("\\par", "");
			
			s = s.replaceAll("(\\s)([a-zA-Z])\\s", "$1$2~");
			s = s.replaceAll("^([a-zA-Z])\\s", "$1~");
			s = s.replaceAll("(\\\\[sub]{0,}section)(\\[.{1,}\\])\\{(\\s?\\d\\.\\s)?(.{1,})\\}", "$1\\{$4\\}");
			
			s = s.replaceAll("(\\s)(\\})", "$2$1");
			s = s.replace("{ ", " {");

//			s = s.replaceAll("(\\\\[sub]{0,}section)(\\[.{1,}\\])(\\{.{1,}\\})", "$1$3");
//			s = s.replaceAll("([\\r\\n]+)([A-Z])\\s", "$1$2~");
//			s = s.replaceAll("\\{\\\\[^\\{]*", "Dupa");

//			s = s.replaceAll("\\\\usepackage(\\[\\S+|,)?\\{\\S+\\}", "Dupa");

//			String s = texLines.get(i).replaceAll("^ +| +$|( )+", "$1");

//			while (!s.isEmpty() && s.startsWith(" ")) {
//				s = s.substring(1);
//			}

//			while (!s.isEmpty() && s.startsWith("\\ ")) {
//				s = s.substring(2);
//			}

			if (texLines.get(i) != s) {
				texLines.set(i, s);
			}
		}

	}

}
