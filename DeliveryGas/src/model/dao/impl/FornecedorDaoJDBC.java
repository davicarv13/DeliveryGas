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
import model.dao.FornecedorDao;
import model.entities.Fornecedor;
import model.entities.Endereco;
import model.entities.Fornecedor;

public class FornecedorDaoJDBC implements FornecedorDao{
	private Connection conn;
	
	public FornecedorDaoJDBC(Connection conn){
		this.conn = conn;
	}

	@Override
	public boolean insert(Fornecedor obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Fornecedor (Nome, CNPJ, Telefone, Email, CodEndereco) "
					+ "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getCNPJ());
			st.setString(3, obj.getTelefone());
			st.setString(4, obj.getEmail());
			st.setInt(5, obj.getEndereco().getCodEndereco());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setCodFornecedor(id);
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
	public boolean update(Fornecedor obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Fornecedor "
					+ "set Nome = ?, "
					+ "CNPJ = ?, "
					+ "Telefone = ?, "
					+ "Email = ?"
					+ "WHERE CodFornecedor = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getCNPJ());
			st.setString(3, obj.getTelefone());
			st.setString(4, obj.getEmail());
			st.setInt(5, obj.getCodFornecedor());
			
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
	
	public boolean updateByCNPJ(Fornecedor obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Fornecedor "
					+ "set Nome = ?, "
					+ "Telefone = ?, "
					+ "Email = ? "
					+ "WHERE CNPJ = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getTelefone());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getCNPJ());
			
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
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Fornecedor WHERE CodFornecedor = ?");
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
	public Fornecedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Fornecedor.*, Endereco.*"
					+ "FROM Fornecedor INNER JOIN Endereco "
					+ "ON Fornecedor.CodEndereco = Endereco.CodEndereco "
					+ "WHERE Fornecedor.CodFornecedor = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				Endereco obj1 = instantiateEndereco(rs);
				Fornecedor obj2 = instantiateFornecedor(rs, obj1);
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
	public Fornecedor findByCNPJ(String cnpj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Fornecedor.*, Endereco.*"
					+ "FROM Fornecedor INNER JOIN Endereco "
					+ "ON Fornecedor.CodEndereco = Endereco.CodEndereco "
					+ "WHERE Fornecedor.CNPJ = ?");
			
			st.setString(1, cnpj);
			rs = st.executeQuery();
			
			if(rs.next()){
				Endereco obj1 = instantiateEndereco(rs);
				Fornecedor obj2 = instantiateFornecedor(rs, obj1);
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
	public List<Fornecedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Fornecedor.*, Endereco.* "
					+ "FROM Fornecedor INNER JOIN Endereco "
					+ "ON Fornecedor.CodEndereco = Endereco.CodEndereco");
			rs = st.executeQuery();
			
			List<Fornecedor> list = new ArrayList<Fornecedor>();
			Map<Integer, Endereco> map = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			while(rs.next()){
				
				Endereco obj = map.get(rs.getInt("CodEndereco")); //Se o departamento nao existir retorna null
				
				if(obj == null){
					obj = instantiateEndereco(rs);
					map.put(rs.getInt("CodEndereco"), obj);
				}
				
				Fornecedor Fornecedor = instantiateFornecedor(rs, obj);
				list.add(Fornecedor);
				
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
	
	public Fornecedor instantiateFornecedor(ResultSet rs, Endereco endereco) throws SQLException{
		Fornecedor obj = new Fornecedor();
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		obj.setCodFornecedor(rs.getInt("CodFornecedor"));
		obj.setNome(rs.getString("Nome"));
		obj.setCNPJ(rs.getString("CNPJ"));
		obj.setTelefone(rs.getString("Telefone"));
		obj.setEmail(rs.getString("Email"));
		obj.setEndereco(endereco);
		return obj;
	}
}
