package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class TexParser {


	public ArrayList<String> parseTexFile(String fileName) {
		File texFile = new File(fileName);
		ArrayList<String> texLines = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(texFile.toPath())) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				texLines.add(line);
			}
		} catch (IOException x) {
			System.err.format("IOException: %s", x);
		}
		return texLines;
	}

}
