package model.dao;

import java.util.List;

import model.entities.Cliente;

public interface ClienteDao {
	boolean insert(Cliente obj);
	boolean update(Cliente obj);
	boolean updateByCPF(Cliente obj);
	boolean deleteById(Integer id);
	boolean deleteByCpf(String cpf);
	Cliente findById(Integer id);
	Cliente findByCpf(String cpf);
	List<Cliente> findAll();
}
