package br.edu.fatec.detoranja.vh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Troca_StatusAdmin;
import br.edu.fatec.detoranja.util.Resultado;

public class Troca_Status_AdminVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Troca_StatusAdmin status = new Troca_StatusAdmin();

		if (operacao != null && operacao.equals("Lista")) {

			status.setId(Integer.parseInt(req.getParameter("status")));
		}

		return status;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Buscar")) {

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
		} else if (operacao != null && operacao.equals("Lista")) {
			String json = new Gson().toJson(resultado.getListDominio());

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
