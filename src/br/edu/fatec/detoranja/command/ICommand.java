package br.edu.fatec.detoranja.command;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public interface ICommand {
	
	public Resultado execute(IDominio dominio);

}
