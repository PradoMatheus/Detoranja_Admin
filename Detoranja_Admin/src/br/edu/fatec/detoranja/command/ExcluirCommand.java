package br.edu.fatec.detoranja.command;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Resultado execute(IDominio dominio) {
		return fachada.excluir(dominio);
	}

}
