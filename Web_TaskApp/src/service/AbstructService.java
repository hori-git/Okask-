package service;

import dao.DAOManager;
import dao.DefaultDAOManager;

//DefaultKibanServiceのシステム的な仕様
public abstract class AbstructService {

	//DAOManagerのインスタンスを作成し、渡す
	public DAOManager getDAOManager() {

		DAOManager daomanager = new DefaultDAOManager();
		return daomanager;

	}

}
