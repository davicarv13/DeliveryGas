package controller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ClienteDao;
import model.entities.Cliente;

public class ControllerCliente {
	ClienteDao ClienteDao;
	
	public ControllerCliente(){
		this.ClienteDao = DaoFactory.createClienteDao();
	}
	
	public boolean insert(Cliente obj){
		return this.ClienteDao.insert(obj);
		
	}
	
	public boolean deleteByID(Integer CodCliente){
		return this.ClienteDao.deleteById(CodCliente);
	}
	
	public boolean deleteByCpf(String cpf){
		return this.ClienteDao.deleteByCpf(cpf);
	}
	
	public boolean update(Cliente obj){
		return this.ClienteDao.update(obj);
	}
	
	public boolean updateByCPF(Cliente obj){
		return this.ClienteDao.updateByCPF(obj);
	}
	
	public List<Cliente> findAll(){
		return this.ClienteDao.findAll();
	}
	
	public Cliente findById(int id){
		return this.ClienteDao.findById(id);
	}
	
	public Cliente findByCPF(String cpf){
		return this.ClienteDao.findByCpf(cpf);
	}
}
