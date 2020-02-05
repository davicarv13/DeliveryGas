package model.entities;

import java.io.Serializable;

public class Armazem implements Serializable{
	private static final long serialVersionUID = 1L;
	private int codArmazem;
	private String nome;
	private Endereco endereco;
	
	public Armazem(){
		
	}
	
	public Armazem(int codArmazem, String nome, Endereco endereco) {
		this.codArmazem = codArmazem;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public int getCodArmazem() {
		return codArmazem;
	}
	public void setCodArmazem(int codArmazem) {
		this.codArmazem = codArmazem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
		result = prime * result + codArmazem;
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
		Armazem other = (Armazem) obj;
		if (codArmazem != other.codArmazem)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Armazem [codArmazem=" + codArmazem + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
	
	
}
