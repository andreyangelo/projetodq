package model.dao;

import db.DB;
import model.dao.impl.ReagenteDaoJDBC;

public class DaoFactory {
	
	public static ReagenteDao createReagenteDao() {
		return new ReagenteDaoJDBC(DB.getConnection());
	}

}
