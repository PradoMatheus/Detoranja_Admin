package br.edu.fatec.detoranja.dominio;

import java.time.LocalDateTime;
import java.util.List;

public class Compra implements IDominio {
	private int id, // ID DA COMPRA
			quantidade_total; // QUANTIDADE DO ITENS COMPRADOS
	private double valor_total;	// VALOR TOTAL DA COMPRA
	private Fornecedor fornecedor;	// FORNECEDOR QUE REALIZOU A COMPRA
	private String nota_fiscal;	// NOTA DA COMPRA
	private List<Compra_Itens> listaProdutos;	// LISTA DE ITENS COMPRADOS
	private LocalDateTime data_compra;					// DATA DA COMPRA
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantidade_total() {
		return quantidade_total;
	}
	public void setQuantidade_total(int quantidade_total) {
		this.quantidade_total = quantidade_total;
	}
	public double getValor_total() {
		return valor_total;
	}
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getNota_fiscal() {
		return nota_fiscal;
	}
	public void setNota_fiscal(String nota_fiscal) {
		this.nota_fiscal = nota_fiscal;
	}
	public List<Compra_Itens> getistaProdutos() {
		return listaProdutos;
	}
	public void setMovimentacoes(List<Compra_Itens> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	public LocalDateTime getData_compra() {
		return data_compra;
	}
	public void setData_compra(LocalDateTime data_compra) {
		this.data_compra = data_compra;
	}
}
