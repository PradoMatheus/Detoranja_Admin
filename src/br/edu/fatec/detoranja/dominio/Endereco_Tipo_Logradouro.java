package br.edu.fatec.detoranja.dominio;

public class Endereco_Tipo_Logradouro implements IDominio{

	int id;					// Id do tipo de Logradouro
	String descricao;		// Descrição do Tipo de Logradouro
	
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
