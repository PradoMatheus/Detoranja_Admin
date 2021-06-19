package br.edu.fatec.detoranja.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Produto implements IDominio{

	private int id;											// PK DO JOGO
	private String nome,									// NOME DO JOGO
		desenvolvedor,										// DESENVOLVEDORA DO JOGO
		distribuidora,										// DISTRIBUIDORA DO JOGO
		plataforma,											// PLATAFORMA DO JOGO
		descricao;											// DESCRICAO SOBRE O JOGO
	private double valor;									// VALOR DE VENDA DO JOGO
	private LocalDate data_lancamento;					// DATA DE LANÇAMENTO DO JOGO
	private LocalDateTime	data_cadastro,					// DATA DE CADASTRO DO JOGO
		data_alteracao;										// DATA DE ALTERAÇÃO DO JOGO
	private ArrayList<Produto_Categoria> produto_Categorias;// LISTA DE CATEGORIAS DO JOGO	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDesenvolvedor() {
		return desenvolvedor;
	}
	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}
	public String getDistribuidora() {
		return distribuidora;
	}
	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDate getData_lancamento() {
		return data_lancamento;
	}
	public void setData_lancamento(LocalDate data_lancamento) {
		this.data_lancamento = data_lancamento;
	}
	public LocalDateTime getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(LocalDateTime data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public LocalDateTime getData_alteracao() {
		return data_alteracao;
	}
	public void setData_alteracao(LocalDateTime data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ArrayList<Produto_Categoria> getProduto_Categorias() {
		return produto_Categorias;
	}
	public void setProduto_Categorias(ArrayList<Produto_Categoria> produto_Categorias) {
		this.produto_Categorias = produto_Categorias;
	}	
	
}
