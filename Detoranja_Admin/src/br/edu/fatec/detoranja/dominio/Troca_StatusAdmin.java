package br.edu.fatec.detoranja.dominio;

public class Troca_StatusAdmin implements IDominio {

	private int id;					// Id do Status
	private String descricao;		// Descrição do status
	
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
