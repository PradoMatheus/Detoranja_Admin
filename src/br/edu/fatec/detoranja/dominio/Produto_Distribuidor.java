package br.edu.fatec.detoranja.dominio;

public class Produto_Distribuidor implements IDominio {

	private int id;					// ID do Desenvolvedor
	private String descricao;		// Descrição do Desenvolvedor
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
