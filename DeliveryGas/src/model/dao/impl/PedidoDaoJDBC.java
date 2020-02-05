package model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.PedidoDao;
import model.entities.Cliente;
import model.entities.Endereco;
import model.entities.Entregador;
import model.entities.Fornecedor;
import model.entities.Gas;
import model.entities.Pedido;

public class PedidoDaoJDBC implements PedidoDao{

	private Connection conn;
	
	public PedidoDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public boolean insert(Pedido obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO Pedido (Data_Expedicao, Data_Entrega, Estado, Valor_Total, CodEntregador, CodCliente, CodEnderecoEntrega) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			Calendar cal;
			st.setString(1, obj.getData_Expedicao());
			st.setString(2, obj.getData_Entrega());
			st.setString(3, obj.getEstado());
			st.setDouble(4, obj.getValor_Total());
			st.setInt(5, obj.getEntregador().getCodEntregador());
			st.setInt(6, obj.getCliente().getCodCliente());
			st.setInt(7, obj.getEnderecoEntrega().getCodEndereco());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setCodPedido(id);
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
	public boolean update(Pedido obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Pedido "
					+ "set Valor_Total = ?, "
					+ "CodEntregador = ?,"
					+ "CodCliente = ?, "
					+ "CodEnderecoEntrega = ? "
					+ "WHERE CodPedido = ?");
			
			st.setDouble(1, obj.getValor_Total());
			st.setInt(2, obj.getEntregador().getCodEntregador());
			st.setInt(3, obj.getCliente().getCodCliente());
			st.setInt(4, obj.getEnderecoEntrega().getCodEndereco());
			st.setInt(5, obj.getCodPedido());
			
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
	
	public boolean entregarPedido(Pedido obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Pedido "
					+ "set Estado = 'ENTREGUE',"
					+ "Data_Entrega = ? "
					+ "WHERE CodPedido = ?");
			
			st.setString(1, obj.getData_Entrega());
			st.setInt(2, obj.getCodPedido());
			
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

	public boolean cancelarPedido(Pedido obj) {
		PreparedStatement st = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"UPDATE Pedido "
					+ "set Estado = 'CANCELADO' "
					+ "WHERE CodPedido = ?");
			
			st.setInt(1, obj.getCodPedido());
			
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
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Pedido WHERE CodPedido = ?");
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
	public Pedido findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Pedido.*, Endereco.CodEndereco, Endereco.Rua, Endereco.Rua, Endereco.Cep, Endereco.Cidade, Endereco.Numero, Endereco.Bairro, "
					+ "Cliente.Nome, Cliente.CPF, "
					+ "Entregador.Nome, Entregador.CPF "
					+ "FROM Pedido INNER JOIN Endereco "
					+ "ON Pedido.CodEnderecoEntrega = Endereco.CodEndereco "
					+ "INNER JOIN Cliente "
					+ "ON Pedido.CodCliente = Cliente.CodCliente "
					+ "INNER JOIN Entregador "
					+ "ON Pedido.CodEntregador = Entregador.CodEntregador "
					+ "WHERE CodPedido = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			List<Pedido> list = new ArrayList<Pedido>();
			Map<Integer, Endereco> mapEndereco = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			Map<Integer, Cliente> mapCliente = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			Map<Integer, Entregador> mapEntregador = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			if(rs.next()){
	
				Endereco endereco = mapEndereco .get(rs.getInt("CodEnderecoEntrega"));
				Cliente cliente = mapCliente.get(rs.getInt("CodCliente")); 
				Entregador entregador = mapEntregador.get(rs.getInt("CodEntregador"));
				
				if(endereco == null){
					endereco = instantiateEndereco(rs);
					mapEndereco.put(rs.getInt("CodEnderecoEntrega"), endereco);
				}
				if(cliente == null){
					cliente = instantiateCliente(rs);
					mapCliente.put(rs.getInt("CodCliente"), cliente);
				}
				
				if(entregador == null){
					entregador = instantiateEntregador(rs);
					mapEntregador.put(rs.getInt("CodEntregador"), entregador);
				}
				
				Pedido pedido = instantiatePedido(rs, endereco, cliente, entregador);
				return pedido;
			}
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Pedido> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Pedido.*, Endereco.CodEndereco, Endereco.Rua, Endereco.Rua, Endereco.Cep, Endereco.Cidade, Endereco.Numero, Endereco.Bairro, "
					+ "Cliente.Nome, Cliente.CPF, "
					+ "Entregador.Nome, Entregador.CPF "
					+ "FROM Pedido INNER JOIN Endereco "
					+ "ON Pedido.CodEnderecoEntrega = Endereco.CodEndereco "
					+ "INNER JOIN Cliente "
					+ "ON Pedido.CodCliente = Cliente.CodCliente "
					+ "INNER JOIN Entregador "
					+ "ON Pedido.CodEntregador = Entregador.CodEntregador ");
			rs = st.executeQuery();
			
			List<Pedido> list = new ArrayList<Pedido>();
			Map<Integer, Endereco> mapEndereco = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			Map<Integer, Cliente> mapCliente = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			Map<Integer, Entregador> mapEntregador = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			while(rs.next()){
	
				Endereco endereco = mapEndereco .get(rs.getInt("CodEnderecoEntrega"));
				Cliente cliente = mapCliente.get(rs.getInt("CodCliente")); 
				Entregador entregador = mapEntregador.get(rs.getInt("CodEntregador"));
				
				if(endereco == null){
					endereco = instantiateEndereco(rs);
					mapEndereco.put(rs.getInt("CodEnderecoEntrega"), endereco);
				}
				if(cliente == null){
					cliente = instantiateCliente(rs);
					mapCliente.put(rs.getInt("CodCliente"), cliente);
				}
				
				if(entregador == null){
					entregador = instantiateEntregador(rs);
					mapEntregador.put(rs.getInt("CodEntregador"), entregador);
				}
				
				Pedido pedido = instantiatePedido(rs, endereco, cliente, entregador);
				list.add(pedido);
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	public List<Pedido> findAllPedidosPendentes() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = (PreparedStatement) conn.prepareStatement(
					"SELECT Pedido.*, Endereco.CodEndereco, Endereco.Rua, Endereco.Rua, Endereco.Cep, Endereco.Cidade, Endereco.Numero, Endereco.Bairro, "
					+ "Cliente.Nome, Cliente.CPF, "
					+ "Entregador.Nome, Entregador.CPF "
					+ "FROM Pedido INNER JOIN Endereco "
					+ "ON Pedido.CodEnderecoEntrega = Endereco.CodEndereco "
					+ "INNER JOIN Cliente "
					+ "ON Pedido.CodCliente = Cliente.CodCliente "
					+ "INNER JOIN Entregador "
					+ "ON Pedido.CodEntregador = Entregador.CodEntregador "
					+ "WHERE Pedido.Estado = 'PENDENTE'");
			rs = st.executeQuery();
			
			List<Pedido> list = new ArrayList<Pedido>();
			Map<Integer, Endereco> mapEndereco = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			Map<Integer, Cliente> mapCliente = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			Map<Integer, Entregador> mapEntregador = new HashMap<>(); //Mapeamento. Evita a criação de objetos duplicados
			
			while(rs.next()){
	
				Endereco endereco = mapEndereco .get(rs.getInt("CodEnderecoEntrega"));
				Cliente cliente = mapCliente.get(rs.getInt("CodCliente")); 
				Entregador entregador = mapEntregador.get(rs.getInt("CodEntregador"));
				
				if(endereco == null){
					endereco = instantiateEndereco(rs);
					mapEndereco.put(rs.getInt("CodEnderecoEntrega"), endereco);
				}
				if(cliente == null){
					cliente = instantiateCliente(rs);
					mapCliente.put(rs.getInt("CodCliente"), cliente);
				}
				
				if(entregador == null){
					entregador = instantiateEntregador(rs);
					mapEntregador.put(rs.getInt("CodEntregador"), entregador);
				}
				
				Pedido pedido = instantiatePedido(rs, endereco, cliente, entregador);
				list.add(pedido);
			}
			return list;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}
	
	
	
	public Endereco instantiateEndereco(ResultSet rs) throws SQLException{
		Endereco obj = new Endereco(rs.getInt("CodEnderecoEntrega"), rs.getString("Rua"), rs.getString("Cep"), 
				rs.getString("Cidade"), rs.getInt("Numero"), rs.getString("Bairro"));
		
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		return obj;
	}
	
	public Cliente instantiateCliente(ResultSet rs) throws SQLException{
		Cliente obj = new Cliente(rs.getInt("CodCliente"), rs.getString("CPF"), rs.getString("nome"), null, null, null);
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		return obj;
	}
	
	public Entregador instantiateEntregador(ResultSet rs) throws SQLException{
		Entregador obj = new Entregador(rs.getInt("CodEntregador"), rs.getString("CPF"), rs.getString("Nome"));
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		return obj;
	}
	
	public Pedido instantiatePedido(ResultSet rs, Endereco enderecoEntrega, Cliente cliente, Entregador entregador) throws SQLException{
		Pedido obj = new Pedido(rs.getInt("CodPedido"), rs.getString("Data_Expedicao"), rs.getString("Data_Entrega"), 
				rs.getString("Estado"), rs.getDouble("Valor_Total"), entregador, cliente, enderecoEntrega);
		//Excessao não será tratada aqui, porque já está sendo tratada no metodo que chama a funcao
		return obj;
	}

}
