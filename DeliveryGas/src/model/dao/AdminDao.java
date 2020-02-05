package model.dao;

import java.util.List;

import model.entities.Admin;

public interface AdminDao {
	void update(Admin obj);
	void insert(Admin obj);
	void deleteById(Integer id);
	Admin findById(Integer id);
	Admin findByNomePassword(String nome, String password);
	List<Admin> findAll();
}
