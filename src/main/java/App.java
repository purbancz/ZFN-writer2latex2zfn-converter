package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "res/Lobinski-org3.tex";
		String newFile = "res/Lobinski-org3-PU.tex";

		TexParser parser = new TexParser();
		SaveFile saver = new SaveFile();
		PreambleCleaner preambleCleaner = new PreambleCleaner();

		ArrayList<String> texLines = parser.parseTexFile(fileName);

		texLines = preambleCleaner.cleanPreamble(texLines);

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
			s = s.replace(" }", "} ");
			s = s.replace("{ ", " {");
			s = s.replace(" – ", " -- ");
			s = s.replaceAll("(\\s)([a-z])\\s", "$1$2~");
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

		for (String s : texLines) {
			System.out.println(s);
		}

		saver.rewriteFile(newFile, texLines);

	}

}
