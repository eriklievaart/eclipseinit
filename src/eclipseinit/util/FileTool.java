package eclipseinit.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTool {

	public static List<String> readLinesTrimmed(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				lines.add(line.trim());
				line = br.readLine();
			}
		}
		return lines;
	}
}
