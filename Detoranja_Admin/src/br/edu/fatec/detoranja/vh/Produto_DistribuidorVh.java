package br.edu.fatec.detoranja.vh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto_Distribuidor;
import br.edu.fatec.detoranja.util.Resultado;

public class Produto_DistribuidorVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Produto_Distribuidor produto_distribuidor = new Produto_Distribuidor();

		if (operacao != null && operacao.equals("Salvar")) {

		} else if (operacao != null && operacao.equals("Lista")) {

		}

		return produto_distribuidor;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Salvar")) {

		} else if (operacao != null && operacao.equals("Lista")) {
			
			if (req.getParameter("tipo").equals("json")) {
				String json = new Gson().toJson(resultado.getListDominio());

				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");

				try {
					resp.getWriter().write(json);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			} else {
				
			}
		}

	}

}
