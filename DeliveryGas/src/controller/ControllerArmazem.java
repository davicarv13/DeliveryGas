package controller;

import java.util.List;

import model.dao.ArmazemDao;
import model.dao.DaoFactory;
import model.entities.Armazem;

public class ControllerArmazem {
	ArmazemDao armazemDao;
	
	public ControllerArmazem(){
		this.armazemDao = DaoFactory.createArmazemDao();
	}
	
	public boolean insert(Armazem obj){
		return this.armazemDao.insert(obj);
		
	}
	
	public boolean deleteByID(Integer CodArmazem){
		return this.armazemDao.deleteById(CodArmazem);
	}
	
	public boolean update(Armazem obj){
		return this.armazemDao.update(obj);
	}
	
	public List<Armazem> findAll(){
		return this.armazemDao.findAll();
	}
	
	public Armazem findById(int id){
		return this.armazemDao.findById(id);
	}
}
