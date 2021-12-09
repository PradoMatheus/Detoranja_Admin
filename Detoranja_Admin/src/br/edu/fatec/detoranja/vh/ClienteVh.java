package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.Cliente;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public class ClienteVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Cliente cliente = new Cliente();

		if (operacao != null && operacao.equals("Buscar")) {
			cliente.setId(Integer.parseInt(req.getParameter("id")));

		} else if (operacao != null && operacao.equals("Lista")) {

		} else if (operacao != null && operacao.equals("Salvar")) {
			cliente.setId(Integer.parseInt(req.getParameter("id")));
			cliente.setAtivo(Boolean.parseBoolean(req.getParameter("ativo")));
		}

		return cliente;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {

		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Buscar")) {
			String json = new Gson().toJson(resultado.getDominio());

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			try {
				resp.getWriter().write(json);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else if (operacao != null && operacao.equals("Lista")) {
			List<Cliente> clientes = new ArrayList<Cliente>();
			for (IDominio d : resultado.getListDominio()) {
				clientes.add((Cliente) d);
			}

			try {
				req.setAttribute("ListaClientes", clientes);
				req.getRequestDispatcher("consulta_clientes.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (operacao != null && operacao.equals("Salvar")) {
			
		} else if (operacao != null && operacao.equals("Sair")) {
			
		}

	}

}
