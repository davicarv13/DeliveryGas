package model.entities;

import java.io.Serializable;

public class Entregador implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int CodEntregador;
	private String Cpf;
	private String Nome;
	private String Telefone;
	private String Email;
	private double Salario;
	private Endereco endereco;
	
	public Entregador() {
		
	}

	public Entregador(int codEntregador, String cpf, String nome, String telefone, String email, double salario,
			Endereco endereco) {
		super();
		CodEntregador = codEntregador;
		Cpf = cpf;
		Nome = nome;
		Telefone = telefone;
		Email = email;
		Salario = salario;
		this.endereco = endereco;
	}
	
	public Entregador(int codEntregador, String cpf, String nome) {
		CodEntregador = codEntregador;
		Cpf = cpf;
		Nome = nome;
	}

	public int getCodEntregador() {
		return CodEntregador;
	}

	public void setCodEntregador(int codEntregador) {
		CodEntregador = codEntregador;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public double getSalario() {
		return Salario;
	}

	public void setSalario(double salario) {
		Salario = salario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CodEntregador;
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
		Entregador other = (Entregador) obj;
		if (CodEntregador != other.CodEntregador)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entregador [CodEntregador=" + CodEntregador + ", Cpf=" + Cpf + ", Nome=" + Nome + ", Telefone=" + Telefone
				+ ", Email=" + Email + ", Salario=" + Salario + ", endereco=" + endereco + "]";
	}
}
