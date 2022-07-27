package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.DB;
import db.DbException;
import model.dao.ReagenteDao;
import model.entities.Reagente;


public class ReagenteDaoJDBC implements ReagenteDao{
	
	private Connection conn;
	
	public ReagenteDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void cadastro(Reagente reagente) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		int inseridos;
		
		try{
			
			st = conn.prepareStatement("INSERT INTO cadastro " 
					+ "(Codigo, Reagente, Classificacao, Formula, Risco) "
					+ "VALUES "
					+ "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, reagente.getCodigo());
			st.setString(2, reagente.getReagente());
			st.setString(3, reagente.getClassificacao());
			st.setString(4, reagente.getFormula());
			st.setString(5, reagente.getRisco());
			
			inseridos = st.executeUpdate();
			if(inseridos > 0) {
				rs = st.getGeneratedKeys();
				rs.first();
				reagente.setId(rs.getInt(1));
			}
			else {
				throw new DbException("Erro Inesperado");
			}
			
			DB.closeStatement(st);
			
			st = conn.prepareStatement("INSERT INTO fabricante "
					+ " (Fabricante, cadastroID) "
					+ "VALUES "
					+ "(?,?)");
			
			st.setString(1, reagente.getFabricante());
			st.setInt(2, reagente.getId());
			st.executeUpdate();
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

	@Override //faz o cadastro do reagente
	public void register_reagent(Reagente reagente) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		int inseridos;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO cadastro_reagente "
					+ "(Responsavel, Codigo, Reagente, Classificacao, Formula, Risco, "
					+ "Fabricante, Unidade, Quantidade_Minima, Local, Data) "
					+ "VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			//st.setString(1, reagente.getResponsavel());
			st.setString(1, "PAULO");
			st.setString(2, reagente.getCodigo());
			st.setString(3, reagente.getReagente());
			st.setString(4, reagente.getClassificacao());
			st.setString(5, reagente.getFormula());
			st.setString(6, reagente.getRisco());
			st.setString(7, reagente.getFabricante());
			st.setString(8, reagente.getUnidade());
			st.setInt(9, reagente.getQuantidadeMinima());
			st.setString(10, reagente.getLocalizacao());
			st.setDate(11, new java.sql.Date(Date.valueOf(reagente.getDataCadastro()).getTime()));
			
			inseridos = st.executeUpdate();
			if(inseridos > 0) {
				rs = st.getGeneratedKeys();
				rs.first();
				reagente.setId(rs.getInt(1));
			}
			else {
				throw new DbException("Erro Inesperado");
			}
				
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

	@Override //faz a entrada de reagente
	public int insert_reagent(Reagente reagente) {
		int id;
		PreparedStatement st = null;
		ResultSet rs = null;
		int inseridos;
		
		try {
			st = conn.prepareStatement("INSERT INTO entrada_reagente "
					+ "(Codigo, Reagente, Fabricante, Unidade, Quantidade, Local, Data, Usuario) "
					+ "VALUES "
					+ "(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, reagente.getCodigo());
			st.setString(2, reagente.getReagente());
			st.setString(3, reagente.getFabricante());
			st.setString(4, reagente.getUnidade());
			st.setInt(5, reagente.getQuantidade());
			st.setString(6, reagente.getLocalizacao());
			st.setDate(7, new java.sql.Date(Date.valueOf(reagente.getDataEntrada()).getTime()));
			st.setString(8, "PAULO");
			
			inseridos = st.executeUpdate();
			
			if(inseridos > 0) {
				rs = st.getGeneratedKeys();
				rs.first();
				reagente.setId(rs.getInt(1));
				id=rs.getInt(1);
			}
			else {
				throw new DbException("Erro Inesperado");
			}
			return id;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	@Override
	public Reagente get_inserted(int id) {
		
		Reagente obj = new Reagente();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT *FROM entrada_reagente "
					+ "WHERE Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				obj.setId(rs.getInt("Id"));
				obj.setCodigo(rs.getString("Codigo"));
				obj.setReagente(rs.getString("Reagente"));
				obj.setFabricante(rs.getString("Fabricante"));
				obj.setUnidade(rs.getString("Unidade"));
				obj.setQuantidade(rs.getInt("Quantidade"));
				obj.setLocalizacao(rs.getString("Local"));
				obj.setDataEntrada(rs.getDate("Data").toLocalDate());
				obj.setResponsavel(rs.getString("Usuario"));
				
			}
			
			return obj;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public void plus_stock_reagente(Reagente inserido) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE estoque_reagente "
					+"SET Quantidade = estoque_reagente.Quantidade + ? "
					+"WHERE Codigo = ? AND Reagente = ? AND Fabricante = ? AND Unidade = ? AND Local = ?");
			
			st.setInt(1, inserido.getQuantidade());
			st.setString(2, inserido.getCodigo());
			st.setString(3, inserido.getReagente());
			st.setString(4, inserido.getFabricante());
			st.setString(5, inserido.getUnidade());
			st.setString(6, inserido.getLocalizacao());
			
			st.executeUpdate();
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}


	@Override
	public void minus_stock_reagente(Reagente retirado) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE estoque_reagente "
					+ "SET Quantidade = estoque_reagente.Quantidade - ? "
					+ "WHERE Codigo = ? AND Reagente = ? AND Fabricante = ? AND Unidade = ? AND Local = ?");
			
			st.setInt(1, retirado.getQuantidade());
			st.setString(2, retirado.getCodigo());
			st.setString(3, retirado.getReagente());
			st.setString(4, retirado.getFabricante());
			st.setString(5, retirado.getUnidade());
			st.setString(6, retirado.getLocalizacao());
			
			st.executeUpdate();
		}
		
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}
	
	@Override
	public Reagente findReagente(Reagente obj) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT *FROM estoque_reagente WHERE "
					+ "Codigo = ? AND Fabricante = ? AND Unidade = ? AND Local = ?");
			
			st.setString(1, obj.getCodigo());
			st.setString(2, obj.getFabricante());
			st.setString(3, obj.getUnidade());
			st.setString(4, obj.getLocalizacao());
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Reagente reagente = new Reagente(rs.getInt("Id"),
						rs.getString("Codigo"),rs.getString("Reagente"),
						rs.getString("Fabricante"),rs.getString("Unidade"),
						rs.getInt("Quantidade"),rs.getString("Local"),
						rs.getDate("DATA").toLocalDate(), rs.getString("Usuario"));
				return reagente;
			}
			
			else {
				return null;
			}
		
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	@Override
	public List<Reagente> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			//st = conn.prepareStatement("SELECT *FROM cadastro_reagente");
			st = conn.prepareStatement("SELECT *FROM estoque_reagente");
			rs = st.executeQuery();
			
			List<Reagente> list = new ArrayList<Reagente>();
			
			/*while(rs.next()) {
				list.add(new Reagente(rs.getInt("Id"), rs.getString("Responsavel"), 
						rs.getString("Codigo"), rs.getString("Reagente"),
						rs.getString("Classificacao"), rs.getString("Formula"), 
						rs.getString("Risco"), rs.getString("Fabricante"), 
						rs.getString("Unidade"), rs.getInt("Quantidade_Minima"),
						rs.getString("Local"), rs.getDate("Data").toLocalDate()));
			}*/
			
			while(rs.next()) {
				list.add(new Reagente(rs.getInt("Id"), rs.getString("Codigo"), 
						rs.getString("Reagente"),rs.getString("Fabricante"),
						rs.getString("Unidade"), rs.getInt("Quantidade"),
						rs.getString("Local"), rs.getDate("DATA").toLocalDate(), 
						rs.getString("Usuario")));
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}
}