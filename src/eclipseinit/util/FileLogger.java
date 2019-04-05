package eclipseinit.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class FileLogger implements Closeable {

	private FileWriter os;

	private FileLogger(File file) {
		try {
			os = new FileWriter(file, true);

		} catch (Exception e) {
			// then don't log
			e.printStackTrace();
		}
	}

	public void log(String message) {
		try {
			System.out.println(message);
			if (os != null) {
				os.write(message + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void with(File file, Consumer<FileLogger> consumer) {
		file.getParentFile().mkdirs();
		try (FileLogger log = new FileLogger(file)) {
			consumer.accept(log);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		if (os != null) {
			os.close();
		}
	}
}
