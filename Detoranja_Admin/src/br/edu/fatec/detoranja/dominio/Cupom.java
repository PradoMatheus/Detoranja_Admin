package br.edu.fatec.detoranja.dominio;

import java.time.LocalDate;

public class Cupom implements IDominio {

	int id;
	String desc_cupom;
	double desconto, valor_minimo;
	LocalDate validade;
	Cupom_Tipo tipo;
	Cliente cliente;
	Boolean ativo;
	private Troca_Admin troca_Admin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc_cupom() {
		return desc_cupom;
	}
	public void setDesc_cupom(String desc_cupom) {
		this.desc_cupom = desc_cupom;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public double getValor_minimo() {
		return valor_minimo;
	}
	public void setValor_minimo(double valor_minimo) {
		this.valor_minimo = valor_minimo;
	}
	public LocalDate getValidade() {
		return validade;
	}
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
	public Cupom_Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Cupom_Tipo tipo) {
		this.tipo = tipo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Troca_Admin getTroca_Admin() {
		return troca_Admin;
	}
	public void setTroca_Admin(Troca_Admin troca_Admin) {
		this.troca_Admin = troca_Admin;
	}
}
