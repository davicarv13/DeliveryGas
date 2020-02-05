package model.entities;

import java.io.Serializable;

public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int CodEndereco;
	private String Rua;
	private String Cep;
	private String Cidade;
	private int Numero;
	private String Bairro;
	
	
	public Endereco(){
		
	}
	
	public Endereco(int codEndereco, String rua, String cep, String cidade, int numero, String bairro) {
		CodEndereco = codEndereco;
		Rua = rua;
		Cep = cep;
		Cidade = cidade;
		Numero = numero;
		Bairro = bairro;
	}
	public int getCodEndereco() {
		return CodEndereco;
	}
	public void setCodEndereco(int codEndereco) {
		CodEndereco = codEndereco;
	}
	public String getRua() {
		return Rua;
	}
	public void setRua(String rua) {
		Rua = rua;
	}
	public String getCep() {
		return Cep;
	}
	public void setCep(String cep) {
		Cep = cep;
	}
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CodEndereco;
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
		Endereco other = (Endereco) obj;
		if (CodEndereco != other.CodEndereco)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Endereco [CodEndereco=" + CodEndereco + ", Rua=" + Rua + ", Cep=" + Cep + ", Cidade=" + Cidade
				+ ", Numero=" + Numero + ", Bairro=" + Bairro + "]";
	}
}
