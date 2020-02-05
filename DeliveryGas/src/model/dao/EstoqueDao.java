package model.dao;

import java.util.List;

import model.entities.Estoque;

public interface EstoqueDao {
	boolean insert(Estoque obj);
	boolean update(Estoque obj);
	boolean updateById(Integer id);
	boolean deleteById(Integer id);
	List<Estoque> findAll();
	Estoque findById(Integer id);
}
