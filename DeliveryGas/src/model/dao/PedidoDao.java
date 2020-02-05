package model.dao;

import java.util.List;

import model.entities.Pedido;

public interface PedidoDao {
	boolean insert(Pedido obj);
	boolean update(Pedido obj);
	boolean deleteById(Integer id);
	boolean cancelarPedido(Pedido obj);
	boolean entregarPedido(Pedido obj);
	Pedido findById(Integer id);
	List<Pedido> findAll();
	List<Pedido> findAllPedidosPendentes();
}
