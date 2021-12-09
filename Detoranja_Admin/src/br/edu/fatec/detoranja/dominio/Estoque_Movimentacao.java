package br.edu.fatec.detoranja.dominio;

import java.time.LocalDateTime;

public class Estoque_Movimentacao implements IDominio {
	private int id, // ID DA MOVIMENTA플O
			quantidade; // QUANTIDADE REALIZADA NA MOVIMENTA플O
	private boolean tipo; // TIPO DA MOVIMENTA플O
	private Produto produto; // PRODUTO EM ESTOQUE
	private LocalDateTime data_movimentacao; // DATA DE CADASTRO DO JOGO
	private String observaocao; // OBSERVA플O DA MOVIMETA플O
	private int pedido;	// PEDIDO QUE FOI REALIZADO NO MOVIMENTO
	private int compra; // COMPRA QUE FOI REALIZADO NO MOVIMENTO
	private Troca_Admin troca; // TROCA VINCULADA AO MOVIMENTO

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

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public LocalDateTime getData_movimentacao() {
		return data_movimentacao;
	}

	public void setData_movimentacao(LocalDateTime data_movimentacao) {
		this.data_movimentacao = data_movimentacao;
	}

	public String getObservaocao() {
		return observaocao;
	}

	public void setObservaocao(String observaocao) {
		this.observaocao = observaocao;
	}

	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	public int getCompra() {
		return compra;
	}

	public void setCompra(int compra) {
		this.compra = compra;
	}

	public Troca_Admin getTroca() {
		return troca;
	}

	public void setTroca(Troca_Admin troca) {
		this.troca = troca;
	}
}
