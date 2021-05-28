package br.edu.fatec.detoranja.command;

import br.edu.fatec.detoranja.fachada.Fachada;
import br.edu.fatec.detoranja.fachada.IFachada;

public abstract class AbstractCommand implements ICommand{

	protected IFachada fachada = new Fachada();
}
