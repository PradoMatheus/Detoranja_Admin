package br.edu.fatec.detoranja.vh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public interface IViewHelper {
	
	public IDominio getDominio(HttpServletRequest req);
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado);
	
}
