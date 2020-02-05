package model.dao;

import java.util.List;

import model.entities.Armazem;

public interface ArmazemDao {
	boolean insert(Armazem obj);
	boolean update(Armazem obj);
	boolean deleteById(Integer id);
	Armazem findById(Integer id);
	List<Armazem> findAll();
}
