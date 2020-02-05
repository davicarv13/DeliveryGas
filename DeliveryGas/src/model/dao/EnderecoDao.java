package model.dao;

import java.util.List;

import model.entities.Endereco;

public interface EnderecoDao {
	boolean insert(Endereco obj);
	boolean update(Endereco obj);
	boolean deleteById(Integer id);
	Endereco findById(Integer id);
	List<Endereco> findAll();
}
