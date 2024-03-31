package persistence.dao;

import persistence.dto.KenKenDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KenKenDAO {
	public static KenKenDTO load(String path) throws IOException {
		List<String> content = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		while ((line = reader.readLine()) != null)
			content.add(line);
		return KenKenDTO.fromLines(content.toArray(new String[0]));
	}
}
