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
import model.dao.EnderecoDao;
import model.entities.Endereco;

public class EnderecoDaoJDBC implements EnderecoDao{
	
	private Connection conn;
	
	public EnderecoDaoJDBC(Connection conn){
		this.conn = conn;
	}

	@Override
	public boolean insert(Endereco obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Endereco (Rua, Cep, Cidade, Numero, Bairro) "
					+ "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getRua());
			st.setString(2, obj.getCep());
			st.setString(3, obj.getCidade());
			st.setInt(4, obj.getNumero());
			st.setString(5, obj.getBairro());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setCodEndereco(id);
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
	public boolean update(Endereco obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Endereco "
					+ "set Rua = ?, "
					+ "Cep = ?, "
					+ "Cidade = ?, "
					+ "Numero = ?, "
					+ "Bairro = ? "
					+ "WHERE CodEndereco = ?");
			
			st.setString(1, obj.getRua());
			st.setString(2, obj.getCep());
			st.setString(3, obj.getCidade());
			st.setInt(4, obj.getNumero());
			st.setString(5, obj.getBairro());
			st.setInt(6, obj.getCodEndereco());
			System.out.println(obj.getCodEndereco());
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
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Endereco WHERE CodEndereco = ?");
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
	public Endereco findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT * FROM Endereco where CodEndereco = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				Endereco endereco = instantiateEndereco(rs);
				return endereco;
			}
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Endereco> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT * FROM Endereco");
			rs = st.executeQuery();
			
			List<Endereco> list = new ArrayList<Endereco>();
			
			while(rs.next()){
				Endereco endereco = instantiateEndereco(rs);
				list.add(endereco);
				
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	public Endereco instantiateEndereco(ResultSet rs) throws SQLException{
		Endereco endereco = new Endereco();
		
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		endereco.setCodEndereco(rs.getInt("CodEndereco"));
		endereco.setRua(rs.getString("Rua"));
		endereco.setCep(rs.getString("Cep"));
		endereco.setCidade(rs.getString("Cidade"));
		endereco.setNumero(rs.getInt("Numero"));
		endereco.setBairro(rs.getString("Bairro"));
		
		return endereco;
	}
}
