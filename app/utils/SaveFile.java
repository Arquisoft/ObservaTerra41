package utils;

import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;

public class SaveFile {

	public static void save(File file, File dest) throws IOException {

		Files.copy(file, dest);

	}
}
