package persistence.dao;

import persistence.dto.ScoreDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {
	private final static String PATH = "data/scores.kenken_scores";

	public static void save(ScoreDTO score) throws IOException {
		List<ScoreDTO> scores = load();
		boolean added = false;
		for (int i = 0; i < scores.size(); i++) {
			if (scores.get(i).getScore() < score.getScore()) {
				scores.add(i, score);
				added = true;
				break;
			}
		}
		if (!added)
			scores.add(score);
		BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
		for (ScoreDTO s : scores)
			writer.write(s + "\n");
		writer.close();
	}

	public static List<ScoreDTO> load() throws IOException {
		List<ScoreDTO> scores = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(PATH));
		String line;
		while ((line = reader.readLine()) != null)
			scores.add(ScoreDTO.fromLine(line));
		return scores;
	}
}
