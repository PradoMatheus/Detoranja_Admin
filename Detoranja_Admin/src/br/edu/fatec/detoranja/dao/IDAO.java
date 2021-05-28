package br.edu.fatec.detoranja.dao;

import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;

public interface IDAO {
	
	public boolean cadastrar(IDominio obj);
	public boolean remover(IDominio obj);
	public boolean alterar(IDominio obj);
	public IDominio buscar(IDominio obj);
	public List<IDominio> listar();
	
}
