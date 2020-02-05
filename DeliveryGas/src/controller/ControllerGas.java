package controller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.GasDao;
import model.entities.Gas;

public class ControllerGas {
	
	GasDao gasDao;
	
	public ControllerGas(){
		this.gasDao = DaoFactory.createGasDao();
	}
	
	public boolean insert(Gas obj){
		return this.gasDao.insert(obj);
	}
	
	public Gas findById(Integer id){
		return this.gasDao.findById(id);
	}
	
	public List<Gas> findAll(){
		return this.gasDao.findAll();
	}
	
	public boolean deleteById(Integer id){
		return this.gasDao.deleteById(id);
	}
	
	public boolean update(Gas obj){
		return this.gasDao.update(obj);
	}
}
