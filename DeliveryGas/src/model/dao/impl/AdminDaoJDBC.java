package model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.AdminDao;
import model.entities.Admin;

public class AdminDaoJDBC implements AdminDao{
	
	private Connection conn;
	
	public AdminDaoJDBC(Connection conn){
		this.conn = conn;
	}

	@Override
	public void insert(Admin obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Admin (nome, password) "
					+ "VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getPassword());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setId(id);
				}
				
				DB.closeResultSet(rs);
			}
			else{
				throw new DbException("Unexpected Erro! No rows affected");
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
	public void update(Admin obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Admin "
					+ "set nome = ?, "
					+ "senha = ? "
					+ "WHERE id = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getPassword());
			st.setInt(3, obj.getId());
			
			st.executeUpdate();
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
		finally{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Admin WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
		finally{
			DB.closeStatement(st);
		}
	}

	@Override
	public Admin findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT * FROM Admin where id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				Admin admin = instantiateAdmin(rs);
				return admin;
			}
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Admin> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT * FROM Admin");
			rs = st.executeQuery();
			
			List<Admin> list = new ArrayList<Admin>();
			
			while(rs.next()){
				Admin Admin = instantiateAdmin(rs);
				list.add(Admin);
				
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public Admin findByNomePassword(String nome, String password) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT * FROM Admin where nome = ? and password = ?");
			st.setString(1, nome);
			st.setString(2, password);
			rs = st.executeQuery();
			
			if(rs.next()){
				Admin admin = instantiateAdmin(rs);
				return admin;
			}
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	public Admin instantiateAdmin(ResultSet rs) throws SQLException{
		Admin admin = new Admin();
		
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		admin.setId(rs.getInt("id"));
		admin.setNome(rs.getString("nome"));
		admin.setPassword(rs.getString("password"));
		
		return admin;
	}

	
}
