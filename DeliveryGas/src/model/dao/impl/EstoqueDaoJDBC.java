package model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.EstoqueDao;
import model.entities.Armazem;
import model.entities.Estoque;
import model.entities.Gas;

public class EstoqueDaoJDBC implements EstoqueDao{

	private Connection conn;
	
	public EstoqueDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public boolean insert(Estoque obj) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Estoque (CodArmazem, CodGas, Qtde) "
					+ "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getArmazem().getCodArmazem());
			st.setInt(2, obj.getGas().getCodGas());
			st.setInt(3, obj.getQtde());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setCodEstoque(id);
					return true;
				}
				
				DB.closeResultSet(rs);
			}
			else{
				throw new DbException("Unexpected Erro! No rows affected");
			}
			return false;
			
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
		finally{
			DB.closeStatement(st);
		}
	}

	@Override
	public boolean update(Estoque obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Estoque "
					+ "set Qtde = ? "
					+ "WHERE CodEstoque = ?");
			
			st.setInt(1, obj.getQtde());
			st.setInt(2, obj.getCodEstoque());
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected != 0){
				return true;
			}
			else{
				return false;
			}
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
		finally{
			DB.closeStatement(st);
		}
	}

	@Override
	public boolean deleteById(Integer id) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Estoque WHERE CodEstoque = ?");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			if(rowsAffected != 0){
				return true;
			}
			else{
				return false;
			}
			
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
		finally{
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Estoque> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Estoque.*, Gas.Tipo, Gas.Valor, Armazem.Nome "
					+ "FROM Estoque INNER JOIN Gas "
					+ "ON Estoque.CodGas = Gas.CodGas "
					+ "INNER JOIN Armazem "
					+ "ON Estoque.CodArmazem = Armazem.CodArmazem");
			rs = st.executeQuery();
			
			List<Estoque> list = new ArrayList<Estoque>();
			Map<Integer, Armazem> mapArmazem = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			Map<Integer, Gas> mapGas = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			while(rs.next()){
	
				Gas gas = mapGas .get(rs.getInt("CodGas"));
				Armazem armazem = mapArmazem.get(rs.getInt("CodArmazem")); 
				
				if(gas == null){
					gas = instantiateGas(rs);
					mapGas.put(rs.getInt("CodGas"), gas);
				}
				if(armazem == null){
					armazem = instantiateArmazem(rs);
					mapArmazem.put(rs.getInt("CodArmazem"), armazem);
				}
				Estoque estoque = instantiateEstoque(rs, gas, armazem);
				list.add(estoque);
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public Estoque findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Estoque.*, Gas.Tipo, Gas.Valor, Armazem.Nome "
							+ "FROM Estoque INNER JOIN Gas "
							+ "ON Estoque.CodGas = Gas.CodGas "
							+ "INNER JOIN Armazem "
							+ "ON Estoque.CodArmazem = Armazem.CodArmazem "
							+ "WHERE Estoque.CodEstoque = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				Gas gas = instantiateGas(rs);
				Armazem armazem = instantiateArmazem(rs);
				Estoque estoque = instantiateEstoque(rs, gas, armazem);
				return estoque;
			}
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	private Estoque instantiateEstoque(ResultSet rs, Gas gas, Armazem armazem) throws SQLException{
		Estoque obj = new Estoque();
		
		obj.setCodEstoque(rs.getInt("CodEstoque"));
		obj.setQtde(rs.getInt("Qtde"));
		obj.setArmazem(armazem);
		obj.setGas(gas);
		return obj;
	}
	
	private Gas instantiateGas(ResultSet rs) throws SQLException{
		Gas obj = new Gas();
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		obj.setCodGas(rs.getInt("CodGas"));
		obj.setTipo(rs.getString("Tipo"));
		obj.setValor(rs.getDouble("Valor"));
		return obj;
	}
	
	public Armazem instantiateArmazem(ResultSet rs) throws SQLException{
		Armazem obj = new Armazem();
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		obj.setCodArmazem(rs.getInt("CodArmazem"));
		obj.setNome(rs.getString("Nome"));
		return obj;
	}

	@Override
	public boolean updateById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
