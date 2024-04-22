package persistence;

import persistence.dao.KenKenDAO;
import persistence.dto.KenKenDTO;

import java.io.IOException;

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
}
