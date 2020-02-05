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
import model.dao.ArmazemDao;
import model.entities.Armazem;
import model.entities.Endereco;

public class ArmazemDaoJDBC implements ArmazemDao{

	private Connection conn;
	
	public ArmazemDaoJDBC(Connection conn){
		this.conn = conn;
	}

	@Override
	public boolean insert(Armazem obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Armazem (Nome, CodEndereco) "
					+ "VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getEndereco().getCodEndereco());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setCodArmazem(id);
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
	public boolean update(Armazem obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Armazem "
					+ "set Nome = ? "
					+ "WHERE CodArmazem = ?");
			
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCodArmazem());
			System.out.println(obj.getCodArmazem());
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
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Armazem WHERE CodArmazem = ?");
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
	public Armazem findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Armazem.*, Endereco.*"
					+ "FROM Armazem INNER JOIN Endereco "
					+ "ON Armazem.CodEndereco = Endereco.CodEndereco "
					+ "WHERE Armazem.CodArmazem = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				Endereco obj1 = instantiateEndereco(rs);
				Armazem obj2 = instantiateArmazem(rs, obj1);
				return obj2;
			}
			
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Armazem> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Armazem.*, Endereco.* "
					+ "FROM Armazem INNER JOIN Endereco "
					+ "ON Armazem.CodEndereco = Endereco.CodEndereco");
			rs = st.executeQuery();
			
			List<Armazem> list = new ArrayList<Armazem>();
			Map<Integer, Endereco> map = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			while(rs.next()){
				
				Endereco obj = map.get(rs.getInt("CodEndereco")); //Se o departamento nao existir retorna null
				
				if(obj == null){
					obj = instantiateEndereco(rs);
					map.put(rs.getInt("CodEndereco"), obj);
				}
				
				Armazem armazem = instantiateArmazem(rs, obj);
				list.add(armazem);
				
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	private Endereco instantiateEndereco(ResultSet rs) throws SQLException{
		Endereco obj = new Endereco();
		
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		obj.setCodEndereco(rs.getInt("CodEndereco"));
		obj.setRua(rs.getString("Rua"));
		obj.setCep(rs.getString("Cep"));
		obj.setCidade(rs.getString("Cidade"));
		obj.setNumero(rs.getInt("Numero"));
		obj.setBairro(rs.getString("Bairro"));
		return obj;
	}
	
	public Armazem instantiateArmazem(ResultSet rs, Endereco endereco) throws SQLException{
		Armazem Armazem = new Armazem();
		
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		Armazem.setCodArmazem(rs.getInt("CodArmazem"));
		Armazem.setNome(rs.getString("Nome"));
		Armazem.setEndereco(endereco);
		
		return Armazem;
	}
}
