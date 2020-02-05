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
import model.dao.GasDao;
import model.entities.Armazem;
import model.entities.Endereco;
import model.entities.Fornecedor;
import model.entities.Gas;

public class GasDaoJDBC implements GasDao{

	private Connection conn;
	
	public GasDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public boolean insert(Gas obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Gas (Tipo, Valor, CodFornecedor)"
					+ "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getTipo());
			st.setDouble(2, obj.getValor());
			st.setInt(3, obj.getFornecedor().getCodFornecedor());
		
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setCodGas(id);
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
	public boolean update(Gas obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Gas "
					+ "set Tipo = ?, "
					+ "Valor = ? "
					+ "WHERE CodGas = ?");
			
			st.setString(1, obj.getTipo());
			st.setDouble(2, obj.getValor());
			st.setInt(3, obj.getCodGas());
			
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
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Gas WHERE CodGas = ?");
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
	public Gas findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Gas.*, Fornecedor.*"
					+ "FROM Gas INNER JOIN Fornecedor "
					+ "ON Gas.CodFornecedor = Fornecedor.CodFornecedor "
					+ "WHERE Gas.CodGas = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				Fornecedor obj1 = instantiateFornecedor(rs);
				Gas obj2 = instantiateGas(rs, obj1);
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
	public List<Gas> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Gas.*, Fornecedor.Nome, Fornecedor.CNPJ, Fornecedor.Telefone, Fornecedor.Email "
					+ "FROM Gas INNER JOIN Fornecedor "
					+ "ON Gas.CodFornecedor = Fornecedor.CodFornecedor");
			
			rs = st.executeQuery();
			
			List<Gas> list = new ArrayList<Gas>();
			Map<Integer, Fornecedor> map = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			while(rs.next()){
				
				Fornecedor obj = map.get(rs.getInt("CodFornecedor"));
				
				if(obj == null){
					obj = instantiateFornecedor(rs);
					map.put(rs.getInt("CodFornecedor"), obj);
				}
				
				Gas obj2 = instantiateGas(rs, obj);
				list.add(obj2);
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	public Gas instantiateGas(ResultSet rs, Fornecedor fornecedor) throws SQLException{
		Gas obj = new Gas(rs.getInt("CodGas"), rs.getString("Tipo"), rs.getDouble("Valor"), fornecedor);
		return obj;
	}
	
	public Fornecedor instantiateFornecedor(ResultSet rs) throws SQLException{
		Fornecedor obj = new Fornecedor(rs.getString("Nome"), rs.getString("CNPJ"),
				rs.getString("Telefone"), rs.getString("Email"));
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		return obj;
	}

}
