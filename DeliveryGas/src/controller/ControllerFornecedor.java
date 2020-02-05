package controller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.FornecedorDao;
import model.entities.Fornecedor;

public class ControllerFornecedor {
	private FornecedorDao fornecedorDao;
	
	public ControllerFornecedor(){
		this.fornecedorDao = DaoFactory.createFornecedorDao();
	}
	
	public boolean insert(Fornecedor obj){
		return this.fornecedorDao.insert(obj);
		
	}
	
	public boolean deleteByID(Integer CodFornecedor){
		return this.fornecedorDao.deleteById(CodFornecedor);
	}
	
	public boolean update(Fornecedor obj){
		return this.fornecedorDao.update(obj);
	}
	
	public boolean updateByCNPJ(Fornecedor obj){
		return this.fornecedorDao.updateByCNPJ(obj);
	}
	
	public List<Fornecedor> findAll(){
		return this.fornecedorDao.findAll();
	}
	
	public Fornecedor findById(int id){
		return this.fornecedorDao.findById(id);
	}
	
	public Fornecedor findByCNPJ(String cnpj){
		return this.fornecedorDao.findByCNPJ(cnpj);
	}
}
