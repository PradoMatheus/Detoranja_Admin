package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Troca_Admin;
import br.edu.fatec.detoranja.dominio.Troca_StatusAdmin;
import br.edu.fatec.detoranja.util.Resultado;

public class Troca_AdminVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Troca_Admin troca = new Troca_Admin();
		
		if (operacao != null && operacao.equals("Salvar")) {
			troca.setId(Integer.parseInt(req.getParameter("id")));

			Troca_StatusAdmin status = new Troca_StatusAdmin();
			status.setId(Integer.parseInt(req.getParameter("status")));
			troca.setStatus(status);
		} else if (operacao != null && operacao.equals("Lista")) {

		} else if (operacao != null && operacao.equals("Buscar")) {
			troca.setId(Integer.parseInt(req.getParameter("id")));
		}

		return troca;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Lista")) {
			List<Troca_Admin> listaTroca = new ArrayList<Troca_Admin>();
			for (IDominio d : resultado.getListDominio()) {
				listaTroca.add((Troca_Admin) d);
			}
			req.setAttribute("listaTrocas", listaTroca);

			try {
				req.getRequestDispatcher("consulta_trocas.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (operacao != null && operacao.equals("Buscar")) {
			String json = new Gson().toJson(resultado.getDominio());

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			try {
				resp.getWriter().write(json);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else if (operacao != null && operacao.equals("Salvar")) {

			String json = "";
			if (resultado.getMsg() == null) {
				json = new Gson().toJson(true);
			} else {
				json = new Gson().toJson(false);
			}

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			try {
				resp.getWriter().write(json);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
