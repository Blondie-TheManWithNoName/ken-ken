package persistence.dao;

import persistence.dto.KenKenDTO;

import java.io.*;
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

	public static void save(KenKenDTO kenKenDTO, String path) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		for (String line : kenKenDTO.toLines())
			writer.write(line + "\n");
		writer.close();
	}

	public static void saveGame(KenKenDTO kenKenDTO) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/example_path.kenken_game"));
		for (String line : kenKenDTO.toLines())
			writer.write(line + "\n");
		writer.close();
	}
}
