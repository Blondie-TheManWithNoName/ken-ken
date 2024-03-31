package persistence;

import persistence.dao.KenKenDAO;
import persistence.dto.KenKenDTO;

import java.io.IOException;

public class PersistenceController {
	public KenKenDTO loadKenKen(String path) throws IOException {
		return KenKenDAO.load(path);
	}
}
