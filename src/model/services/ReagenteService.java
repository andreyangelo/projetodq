package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ReagenteDao;
import model.entities.Reagente;

public class ReagenteService {
	
	private ReagenteDao dao = DaoFactory.createReagenteDao();
	
	public void register_reagente(Reagente reagente) {
		dao.register_reagent(reagente);
	}

	public void cadastro(Reagente reagente) {
		dao.cadastro(reagente);
	}
	
	public int insert_reagent(Reagente reagente) {
		return dao.insert_reagent(reagente);
	}
	
	public Reagente get_inserted(int id) {
		return dao.get_inserted(id);
	}
	
	public Reagente findReagente(Reagente reagente) {
		return dao.findReagente(reagente);
	}
	
	public void plus_stock_reagente(Reagente inserido) {
		dao.plus_stock_reagente(inserido);
	}
	
	public void minus_stock_reagente(Reagente reagente) {
		dao.minus_stock_reagente(reagente);
	}
	
	public List<Reagente> findAll() {
		return dao.findAll();
	}

}
