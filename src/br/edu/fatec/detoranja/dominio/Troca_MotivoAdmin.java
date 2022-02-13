package br.edu.fatec.detoranja.dominio;

public class Troca_MotivoAdmin implements IDominio {

	private int id;					// Id do motivo
	private String descricao;		// Descrição do motivo
	
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
