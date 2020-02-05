package model.entities;

import java.io.Serializable;

public class Fornecedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int CodFornecedor;
	private String Nome;
	private String CNPJ;
	private String telefone;
	private String email;
	private Endereco endereco;
	
	public Fornecedor() {
		
	}
	
	public Fornecedor(String nome, String CNPJ, String telefone, String email) {
		super();
		Nome = nome;
		this.CNPJ = CNPJ;
		this.telefone = telefone;
		this.email = email;
	}



	public Fornecedor(int codFornecedor, String nome, String cNPJ, String telefone, String email, Endereco endereco) {
		super();
		CodFornecedor = codFornecedor;
		Nome = nome;
		CNPJ = cNPJ;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public int getCodFornecedor() {
		return CodFornecedor;
	}

	public void setCodFornecedor(int codFornecedor) {
		CodFornecedor = codFornecedor;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		result = prime * result + CodFornecedor;
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
		Fornecedor other = (Fornecedor) obj;
		if (CodFornecedor != other.CodFornecedor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fornecedor [CodFornecedor=" + CodFornecedor + ", Nome=" + Nome + ", CNPJ=" + CNPJ + ", telefone="
				+ telefone + ", email=" + email + ", endereco=" + endereco + "]";
	}
}
