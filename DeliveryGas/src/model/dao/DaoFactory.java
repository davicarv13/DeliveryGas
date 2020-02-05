package model.dao;

import db.DB;
import model.dao.impl.AdminDaoJDBC;
import model.dao.impl.ArmazemDaoJDBC;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.EnderecoDaoJDBC;
import model.dao.impl.EntregadorDaoJDBC;
import model.dao.impl.EstoqueDaoJDBC;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.GasDaoJDBC;
import model.dao.impl.PedidoDaoJDBC;

public class DaoFactory {
	public static EnderecoDao createEnderecoDao(){
		return new EnderecoDaoJDBC(DB.getConnection());
	}
	
	public static AdminDao createAdminDao(){
		return new AdminDaoJDBC(DB.getConnection());
	}
	
	public static ArmazemDao createArmazemDao(){
		return new ArmazemDaoJDBC(DB.getConnection());
	}
	
	public static FornecedorDao createFornecedorDao(){
		return new FornecedorDaoJDBC(DB.getConnection());
	}
	
	public static GasDao createGasDao(){
		return new GasDaoJDBC(DB.getConnection());
	}
	
	public static ClienteDao createClienteDao(){
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
	public static EstoqueDao createEstoqueDao(){
		return new EstoqueDaoJDBC(DB.getConnection());
	}

	public static PedidoDao createPedidoDao(){
		return new PedidoDaoJDBC(DB.getConnection());
	}
	
	public static EntregadorDao createEntregadorDao(){
		return new EntregadorDaoJDBC(DB.getConnection());
	}
}
