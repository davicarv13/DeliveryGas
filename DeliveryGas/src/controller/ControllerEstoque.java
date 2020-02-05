package controller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EstoqueDao;
import model.entities.Estoque;

public class ControllerEstoque {
	private EstoqueDao estoqueDao;
	
	public ControllerEstoque(){
		this.estoqueDao = DaoFactory.createEstoqueDao();
	}
	
	public boolean insert(Estoque obj){
		return this.estoqueDao.insert(obj);
	}
	
	public boolean deleteById(Integer id){
		return this.estoqueDao.deleteById(id);
	}
	
	public List<Estoque> findAll(){
		return this.estoqueDao.findAll();
	}
	
	public Estoque findById(Integer id){
		return this.estoqueDao.findById(id);
	}
	
	public boolean update(Estoque obj){
		return this.estoqueDao.update(obj);
	}
	
	public boolean updateById(Integer id){
		return this.estoqueDao.updateById(id);
	}
}
