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
import model.dao.ClienteDao;
import model.entities.Cliente;
import model.entities.Endereco;
import model.entities.Cliente;
import model.entities.Cliente;
import model.entities.Cliente;

public class ClienteDaoJDBC implements ClienteDao{
	
	private Connection conn;
	
	public ClienteDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public boolean insert(Cliente obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Cliente (Cpf, Nome, Telefone, Email, CodEndereco) "
					+ "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getCpf());
			st.setString(2, obj.getNome());
			st.setString(3, obj.getTelefone());
			st.setString(4, obj.getEmail());
			st.setInt(5, obj.getEndereco().getCodEndereco());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setCodCliente(id);
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
	public boolean update(Cliente obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateByCPF(Cliente obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Cliente "
					+ "set Nome = ?, "
					+ "Telefone = ?, "
					+ "Email = ? "
					+ "WHERE CPF = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getTelefone());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getCpf());
			
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente findByCpf(String cpf) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Cliente.*, Endereco.*"
					+ "FROM Cliente INNER JOIN Endereco "
					+ "ON Cliente.CodEndereco = Endereco.CodEndereco "
					+ "WHERE Cliente.CPF = ?");
			
			st.setString(1, cpf);
			rs = st.executeQuery();
			
			if(rs.next()){
				Endereco obj1 = instantiateEndereco(rs);
				Cliente obj2 = instantiateCliente(rs, obj1);
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
	public List<Cliente> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Cliente.*, Endereco.* "
					+ "FROM Cliente INNER JOIN Endereco "
					+ "ON Cliente.CodEndereco = Endereco.CodEndereco");
			rs = st.executeQuery();
			
			List<Cliente> list = new ArrayList<Cliente>();
			Map<Integer, Endereco> map = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			while(rs.next()){
				
				Endereco obj = map.get(rs.getInt("CodEndereco")); //Se o departamento nao existir retorna null
				
				if(obj == null){
					obj = instantiateEndereco(rs);
					map.put(rs.getInt("CodEndereco"), obj);
				}
				
				Cliente Cliente = instantiateCliente(rs, obj);
				list.add(Cliente);
				
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public boolean deleteByCpf(String cpf) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Cliente WHERE CPF = ?");
			st.setString(1, cpf);
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
	
	private Endereco instantiateEndereco(ResultSet rs) throws SQLException{
		Endereco obj = new Endereco(rs.getInt("CodEndereco"), rs.getString("Rua"), rs.getString("Cep"), 
				rs.getString("Cidade"), rs.getInt("Numero"), rs.getString("Bairro"));
		
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		return obj;
	}
	
	public Cliente instantiateCliente(ResultSet rs, Endereco endereco) throws SQLException{
		Cliente obj = new Cliente(rs.getInt("CodCliente"), rs.getString("CPF"), rs.getString("Nome"),
				rs.getString("Telefone"), rs.getString("Email"), endereco);
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		return obj;
	}

}
