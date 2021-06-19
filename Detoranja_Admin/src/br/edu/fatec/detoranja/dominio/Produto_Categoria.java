package br.edu.fatec.detoranja.dominio;

public class Produto_Categoria implements IDominio{
	
	private int id;						// ID DA CATEGORIA DE JOGO
	private String descricao;			// DESCRIÇÃO DA CATEGORIA DO JOGO
	
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
