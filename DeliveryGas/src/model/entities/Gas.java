package model.entities;

import java.io.Serializable;

public class Gas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int CodGas;
	private String tipo;
	private double valor;
	private Fornecedor fornecedor;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setCodGas(int codGas) {
		CodGas = codGas;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public int getCodGas() {
		return CodGas;
	}

	public String getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public Gas() {
		
	}

	public Gas(int codGas, String tipo, double valor, Fornecedor fornecedor) {
		super();
		CodGas = codGas;
		this.tipo = tipo;
		this.valor = valor;
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CodGas;
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
		Gas other = (Gas) obj;
		if (CodGas != other.CodGas)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gas [CodGas=" + CodGas + ", tipo=" + tipo + ", valor=" + valor + ", fornecedor=" + fornecedor + "]";
	}
}

