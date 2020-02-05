package controller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.PedidoDao;
import model.entities.Pedido;

public class ControllerPedido {
	private PedidoDao pedidoDao;
	
	public ControllerPedido(){
		this.pedidoDao = DaoFactory.createPedidoDao();
	}
	
	public boolean insert(Pedido obj){
		return this.pedidoDao.insert(obj);
		
	}
	
	public boolean update(Pedido obj){
		return this.pedidoDao.update(obj);
		
	}
	
	public boolean entregarPedido(Pedido obj){
		return this.pedidoDao.entregarPedido(obj);
	}
	
	public boolean deleteById(Integer id){
		return this.pedidoDao.deleteById(id);
	}

	public boolean cancelarPedido(Pedido obj){
		return this.pedidoDao.cancelarPedido(obj);
	}
	
	public Pedido findById(Integer id){
		return this.pedidoDao.findById(id);
	}
	
	public List<Pedido> findAll(){
		return this.pedidoDao.findAll();
	}
	
	public List<Pedido> findAllPedidosPendentes(){
		return this.pedidoDao.findAllPedidosPendentes();
	}
}
