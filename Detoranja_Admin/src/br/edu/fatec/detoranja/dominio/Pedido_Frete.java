package br.edu.fatec.detoranja.dominio;

public class Pedido_Frete implements IDominio {

	int id;
	double valor;
	Pedido_Frete_Tipo frete_Tipo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Pedido_Frete_Tipo getFrete_Tipo() {
		return frete_Tipo;
	}

	public void setFrete_Tipo(Pedido_Frete_Tipo frete_Tipo) {
		this.frete_Tipo = frete_Tipo;
	}
}
