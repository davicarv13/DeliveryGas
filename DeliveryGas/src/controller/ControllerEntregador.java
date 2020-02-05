package controller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EntregadorDao;
import model.entities.Entregador;

public class ControllerEntregador {
	EntregadorDao entregadorDao;
	
	public ControllerEntregador(){
		this.entregadorDao = DaoFactory.createEntregadorDao();
	}
	
	public boolean insert(Entregador obj){
		return this.entregadorDao.insert(obj);
		
	}
	
	public boolean deleteByID(Integer CodEntregador){
		return this.entregadorDao.deleteById(CodEntregador);
	}
	
	public boolean deleteByCpf(String cpf){
		return this.entregadorDao.deleteByCpf(cpf);
	}
	
	public boolean update(Entregador obj){
		return this.entregadorDao.update(obj);
	}
	
	public boolean updateByCPF(Entregador obj){
		return this.entregadorDao.updateByCPF(obj);
	}
	
	public List<Entregador> findAll(){
		return this.entregadorDao.findAll();
	}
	
	public Entregador findById(int id){
		return this.entregadorDao.findById(id);
	}
	
	public Entregador findByCPF(String cpf){
		return this.entregadorDao.findByCpf(cpf);
	}
}
