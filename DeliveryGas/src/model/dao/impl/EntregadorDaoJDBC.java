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
import model.dao.EntregadorDao;
import model.entities.Entregador;
import model.entities.Entregador;
import model.entities.Entregador;
import model.entities.Endereco;
import model.entities.Entregador;

public class EntregadorDaoJDBC implements EntregadorDao{

	private Connection conn;
	
	public EntregadorDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public boolean insert(Entregador obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Entregador (Cpf, Nome, Telefone, Email, Salario, CodEndereco) "
					+ "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getCpf());
			st.setString(2, obj.getNome());
			st.setString(3, obj.getTelefone());
			st.setString(4, obj.getEmail());
			st.setDouble(5, obj.getSalario());
			st.setInt(6, obj.getEndereco().getCodEndereco());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setCodEntregador(id);
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
	public boolean update(Entregador obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateByCPF(Entregador obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Entregador "
					+ "set Nome = ?, "
					+ "Telefone = ?, "
					+ "Email = ?, "
					+ "Salario = ? "
					+ "WHERE CPF = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getTelefone());
			st.setString(3, obj.getEmail());
			st.setDouble(4, obj.getSalario());
			st.setString(5, obj.getCpf());
			
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
	public boolean deleteByCpf(String cpf) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Entregador WHERE CPF = ?");
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

	@Override
	public Entregador findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entregador findByCpf(String cpf) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Entregador.*, Endereco.*"
					+ "FROM Entregador INNER JOIN Endereco "
					+ "ON Entregador.CodEndereco = Endereco.CodEndereco "
					+ "WHERE Entregador.CPF = ?");
			
			st.setString(1, cpf);
			rs = st.executeQuery();
			
			if(rs.next()){
				Endereco obj1 = instantiateEndereco(rs);
				Entregador obj2 = instantiateEntregador(rs, obj1);
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
	public List<Entregador> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Entregador.*, Endereco.* "
					+ "FROM Entregador INNER JOIN Endereco "
					+ "ON Entregador.CodEndereco = Endereco.CodEndereco");
			rs = st.executeQuery();
			
			List<Entregador> list = new ArrayList<Entregador>();
			Map<Integer, Endereco> map = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			while(rs.next()){
				
				Endereco obj = map.get(rs.getInt("CodEndereco")); 
				
				if(obj == null){
					obj = instantiateEndereco(rs);
					map.put(rs.getInt("CodEndereco"), obj);
				}
				
				Entregador Entregador = instantiateEntregador(rs, obj);
				list.add(Entregador);
				
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
	
	public Entregador instantiateEntregador(ResultSet rs, Endereco endereco) throws SQLException{
		Entregador obj = new Entregador(rs.getInt("CodEntregador"), rs.getString("CPF"), rs.getString("Nome"),
				rs.getString("Telefone"), rs.getString("Email"), rs.getDouble("Salario"), endereco);
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		return obj;
	}
}
