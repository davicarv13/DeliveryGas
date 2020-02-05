package controller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.entities.Endereco;

public class ControllerEndereco {
	EnderecoDao enderecoDao;
	
	public ControllerEndereco(){
		this.enderecoDao = DaoFactory.createEnderecoDao();
	}
	
	public boolean insert(Endereco obj){
		return this.enderecoDao.insert(obj);
		
	}
	
	public boolean deleteByID(Integer CodEndereco){
		return this.enderecoDao.deleteById(CodEndereco);
	}
	
	public boolean update(Endereco obj){
		return this.enderecoDao.update(obj);
	}
	
	public List<Endereco> findAll(){
		return this.enderecoDao.findAll();
	}
	
	public Endereco findById(Integer id){
		return this.enderecoDao.findById(id);
	}
}
