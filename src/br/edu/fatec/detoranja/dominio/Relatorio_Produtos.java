package br.edu.fatec.detoranja.dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Relatorio_Produtos implements IDominio {

	private int id,
		quantidade;
	List<Map<String, List<Integer>>> listaProdutos;
	private LocalDate datainicial,
		dataFinal;
	private String ordenar;
	private boolean filtro;
	
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
	public LocalDate getDatainicial() {
		return datainicial;
	}
	public void setDatainicial(LocalDate datainicial) {
		this.datainicial = datainicial;
	}
	public LocalDate getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getOrdenar() {
		return ordenar;
	}
	public void setOrdenar(String i) {
		this.ordenar = i;
	}
	public boolean isFiltro() {
		return filtro;
	}
	public void setFiltro(boolean filtro) {
		this.filtro = filtro;
	}
	public List<Map<String, List<Integer>>> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Map<String, List<Integer>>> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
}
