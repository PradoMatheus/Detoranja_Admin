package br.edu.fatec.detoranja.dominio;

import java.time.LocalDateTime;

public class Fornecedor implements IDominio {
	private int id; // ID DO FORNECEDOR
	private String razao, // RAZAO DO FORNECEDOR
			fantasia; // FANTASIA DO FORNECEDOR
	private Long cnpj, // CNPJ DO FORNECEDOR
			inscricao; // INSCRICAO ESTADUAL DO FORNECEDOR
	private LocalDateTime data_cadastro; // DATA DO CADASTRO DO FORNCEDOR

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public long getInscricao() {
		return inscricao;
	}

	public void setInscricao(long inscricao) {
		this.inscricao = inscricao;
	}

	public LocalDateTime getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(LocalDateTime data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
}
