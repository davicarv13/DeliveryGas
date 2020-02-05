package model.entities;

import java.io.Serializable;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int CodCliente;
	private String Cpf;
	private String Nome;
	private String Telefone;
	private String Email;
	private Endereco endereco;
	
	public Cliente() {
		
	}
	
	public Cliente(int codCliente, String cpf, String nome, String telefone, String email, Endereco endereco) {
		
		CodCliente = codCliente;
		Nome = nome;
		Cpf = cpf;
		Telefone = telefone;
		Email = email;
		this.endereco = endereco;
	}
	
	public void setCodCliente(int codCliente) {
		CodCliente = codCliente;
	}

	public int getCodCliente() {
		return CodCliente;
	}

	public String getNome() {
		return Nome;
	}

	public String getCpf() {
		return Cpf;
	}

	public String getTelefone() {
		return Telefone;
	}

	public String getEmail() {
		return Email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String toString() {
		return "Cliente [CodCliente=" + CodCliente + ", Nome=" + Nome + ", Cpf=" + Cpf + ", Telefone=" + Telefone
				+ ", Email=" + Email + ", endereco=" + endereco + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CodCliente;
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
		Cliente other = (Cliente) obj;
		if (CodCliente != other.CodCliente)
			return false;
		return true;
	}
	
	
}
