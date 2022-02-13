package br.edu.fatec.detoranja.dominio;

public class Pedido_Itens implements IDominio{
	
	private int id,					// ID UNICO DOS ITENS DO PEDIDO
		quantidade;			// QUANTIDADE DE ITENS NO CARRINHO
	private Produto produto;		// PRODUTO NO CARRINHO
	private Pedido pedido;		// PEDIDO VINCULADO A ESSES ITENS
	private double valor;	// VALOR DO PRODUTO COMPRADO
	
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
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
