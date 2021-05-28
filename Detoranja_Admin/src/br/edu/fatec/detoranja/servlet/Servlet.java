package br.edu.fatec.detoranja.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.detoranja.command.BuscarCommand;
import br.edu.fatec.detoranja.command.ICommand;
import br.edu.fatec.detoranja.command.SalvarCommand;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;
import br.edu.fatec.detoranja.vh.AdministradorVh;
import br.edu.fatec.detoranja.vh.IViewHelper;

@WebServlet(urlPatterns = { "/administrador" })
public class Servlet extends HttpServlet {
	private Map<String, IViewHelper> mapavh;
	private Map<String, ICommand> commands;
	
	public Servlet() {
		mapavh = new HashMap<String, IViewHelper>();
		mapavh.put("/Detoranja_Admin/administrador", new AdministradorVh());

		commands = new HashMap<String, ICommand>();
		commands.put("Salvar", new SalvarCommand());
		commands.put("Buscar", new BuscarCommand());
	}
       
	private void executar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		IViewHelper iViewHelper = mapavh.get(req.getRequestURI());
		System.out.println(req.getRequestURI());
		IDominio dominio = iViewHelper.getDominio(req);
		String operacao = req.getParameter("operacao");
		ICommand command = commands.get(operacao);
		Resultado resultado = command.execute(dominio);
		iViewHelper.setDominio(req, resp, resultado);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executar(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executar(req, resp);
	}

}
