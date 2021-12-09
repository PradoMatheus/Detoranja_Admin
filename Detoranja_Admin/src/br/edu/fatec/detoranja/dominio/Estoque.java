package br.edu.fatec.detoranja.dominio;

import java.util.List;

public class Estoque implements IDominio {
	private int id, // ID DO ESTOQUE
			quantidade; // QUANTIDADE DO ITEM EM ESTOQUE
	private Produto produto; // PRODUTO EM ESTOQUE
	private List<Estoque_Movimentacao> movimentacoes;	// AS MOVIMENTAÇÕES DO ITEM

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

	public List<Estoque_Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Estoque_Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

}
