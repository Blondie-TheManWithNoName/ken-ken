package persistence;

import persistence.dao.KenKenDAO;
import persistence.dao.ScoreDAO;
import persistence.dto.KenKenDTO;
import persistence.dto.ScoreDTO;

import java.io.IOException;
import java.util.List;

public class PersistenceController {
	public KenKenDTO loadKenKen(String path) throws IOException {
		return KenKenDAO.load(path);
	}

	public void saveGame(KenKenDTO kenKenDTO) throws IOException {
		KenKenDAO.save(kenKenDTO);
	}

	public KenKenDTO loadSavedGame(String path) throws IOException {
		return KenKenDAO.loadSavedGame(path);
	}

	public void saveScore(ScoreDTO scoreDTO) throws IOException {
		ScoreDAO.save(scoreDTO);
	}

	public List<ScoreDTO> loadScores() throws IOException {
		return ScoreDAO.load();
	}
}
