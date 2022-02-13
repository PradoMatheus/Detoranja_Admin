package br.edu.fatec.detoranja.command;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public class SairCommand extends AbstractCommand {

	@Override
	public Resultado execute(IDominio dominio) {
		return fachada.salvar(dominio);
	}

}
