package br.edu.fatec.detoranja.util;

import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;

public class Resultado {
	private IDominio dominio;
	private List<IDominio> list_dominio;
	private String msg;
	
	public List<IDominio> getListDominio() {
		return list_dominio;
	}
	public void setListDominio(List<IDominio> list_dominio) {
		this.list_dominio = list_dominio;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public IDominio getDominio() {
		return dominio;
	}
	public void setDominio(IDominio dominio) {
		this.dominio = dominio;
	}
}
