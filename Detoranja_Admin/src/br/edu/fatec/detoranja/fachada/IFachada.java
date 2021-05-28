package br.edu.fatec.detoranja.fachada;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public interface IFachada {
	public Resultado salvar(IDominio dominio);	
	public Resultado buscar(IDominio dominio);
}
