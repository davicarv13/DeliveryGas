package model.dao;

import java.util.List;

import model.entities.Fornecedor;

public interface FornecedorDao {
	boolean insert(Fornecedor obj);
	boolean update(Fornecedor obj);
	boolean updateByCNPJ(Fornecedor obj);
	boolean deleteById(Integer id);
	Fornecedor findById(Integer id);
	Fornecedor findByCNPJ(String cnpj);
	List<Fornecedor> findAll();
}
