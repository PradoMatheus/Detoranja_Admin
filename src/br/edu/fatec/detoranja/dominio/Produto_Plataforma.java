package br.edu.fatec.detoranja.dominio;

public class Produto_Plataforma implements IDominio{
	
	private int id;						// ID DA PLATAFORMA
	private String descricao,			// DESCRIÇÃO DA PLATAFORMA
		imagem;							// ICONE DA PLATAFORMA
	
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
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
