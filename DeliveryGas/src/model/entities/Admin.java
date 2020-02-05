package model.entities;

import java.io.Serializable;

public class Admin implements Serializable{
 	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String password;
	
	public Admin() {
	}
	
	public Admin(int id, String nome, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (id != other.id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Admin [id=" + id + ", nome=" + nome + ", password=" + password + "]";
	}
	
	
	
	
	
}
