package model.dao;

import java.util.List;

import model.entities.Reagente;

public interface ReagenteDao {
	void cadastro(Reagente obj);
	void register_reagent (Reagente obj);
	int insert_reagent(Reagente obj);
	Reagente get_inserted(int id);
	void plus_stock_reagente(Reagente obj);
	void minus_stock_reagente(Reagente obj); 
	Reagente findReagente(Reagente obj);
	List<Reagente> findAll();
}
