package br.edu.fatec.detoranja.dominio;

public class Compra_Itens implements IDominio {
	private int id, // ID DA MOVIMENTA플O
			quantidade; // QUANTIDADE REALIZADA NA MOVIMENTA플O
	private Produto produto; // PRODUTO EM ESTOQUE
	private double valor_compra; // VALOR DA COMPRA DO PRODUTO
	private String observaocao; // OBSERVA플O DA MOVIMETA플O

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValor_compra() {
		return valor_compra;
	}

	public void setValor_compra(double valor_compra) {
		this.valor_compra = valor_compra;
	}

	public String getObservaocao() {
		return observaocao;
	}

	public void setObservaocao(String observaocao) {
		this.observaocao = observaocao;
	}
}
