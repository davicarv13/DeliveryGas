package model.dao;

import java.util.List;

import model.entities.Gas;

public interface GasDao {
	boolean insert(Gas obj);
	boolean update(Gas obj);
	boolean deleteById(Integer id);
	Gas findById(Integer id);
	List<Gas> findAll();
}

