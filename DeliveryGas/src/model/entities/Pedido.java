package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int CodPedido;
	private String Data_Expedicao;
	private String Data_Entrega;
	private String Estado;
	private double Valor_Total;
	private Entregador entregador;
	private Cliente cliente;
	private Endereco enderecoEntrega;
	
	public Pedido() {
	
	}

	public Pedido(int codPedido, String data_Expedicao, String data_Entrega, String estado, double valor_Total,
			Entregador entregador, Cliente cliente, Endereco enderecoEntrega) {
		CodPedido = codPedido;
		Data_Expedicao = data_Expedicao;
		Data_Entrega = data_Entrega;
		Estado = estado;
		Valor_Total = valor_Total;
		this.entregador = entregador;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public int getCodPedido() {
		return CodPedido;
	}

	public void setCodPedido(int codPedido) {
		CodPedido = codPedido;
	}

	public String getData_Expedicao() {
		return Data_Expedicao;
	}

	public void setData_Expedicao(String data_Expedicao) {
		Data_Expedicao = data_Expedicao;
	}

	public String getData_Entrega() {
		return Data_Entrega;
	}

	public void setData_Entrega(String data_Entrega) {
		Data_Entrega = data_Entrega;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public double getValor_Total() {
		return Valor_Total;
	}

	public void setValor_Total(double valor_Total) {
		Valor_Total = valor_Total;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CodPedido;
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
		Pedido other = (Pedido) obj;
		if (CodPedido != other.CodPedido)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [CodPedido=" + CodPedido + ", Data_Expedicao=" + Data_Expedicao + ", Data_Entrega="
				+ Data_Entrega + ", Estado=" + Estado + ", Valor_Total=" + Valor_Total + ", entregador=" + entregador
				+ ", cliente=" + cliente + ", enderecoEntrega=" + enderecoEntrega + "]";
	}
}
