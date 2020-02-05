package model.entities;

import java.io.Serializable;

public class Estoque implements Serializable{

	private static final long serialVersionUID = 1L;
	private int CodEstoque;
	private int Qtde;
	private Armazem armazem;
	private Gas gas;
	
	public int getCodEstoque() {
		return CodEstoque;
	}
	public void setCodEstoque(int codEstoque) {
		CodEstoque = codEstoque;
	}
	public int getQtde() {
		return Qtde;
	}
	public void setQtde(int qtde) {
		Qtde = qtde;
	}
	public Armazem getArmazem() {
		return armazem;
	}
	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}
	public Gas getGas() {
		return gas;
	}
	public void setGas(Gas gas) {
		this.gas = gas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Estoque() {

	}
	public Estoque(int codEstoque, int qtde, Armazem armazem, Gas gas) {
		CodEstoque = codEstoque;
		Qtde = qtde;
		this.armazem = armazem;
		this.gas = gas;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CodEstoque;
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
		Estoque other = (Estoque) obj;
		if (CodEstoque != other.CodEstoque)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Estoque [CodEstoque=" + CodEstoque + ", Qtde=" + Qtde + ", armazem=" + armazem + ", gas=" + gas + "]";
	}
}

