package exporter;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Exporter {
	public static void export(Object o, String dir) {
		ObjectMapper mapper = new ObjectMapper();
		File aFile = new File(dir);
		try {
			mapper.writeValue(aFile, o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
