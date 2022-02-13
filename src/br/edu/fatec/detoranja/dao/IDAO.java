package br.edu.fatec.detoranja.dao;

import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;

public interface IDAO {
	
	public boolean salvar(IDominio obj);
	public boolean excluir(IDominio obj);
	public IDominio buscar(IDominio obj);
	public List<IDominio> lista(IDominio obj);
	
}
