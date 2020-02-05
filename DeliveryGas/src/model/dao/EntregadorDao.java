package model.dao;

import java.util.List;

import model.entities.Entregador;

public interface EntregadorDao {
	boolean insert(Entregador obj);
	boolean update(Entregador obj);
	boolean updateByCPF(Entregador obj);
	boolean deleteById(Integer id);
	boolean deleteByCpf(String cpf);
	Entregador findById(Integer id);
	Entregador findByCpf(String cpf);
	List<Entregador> findAll();
}
