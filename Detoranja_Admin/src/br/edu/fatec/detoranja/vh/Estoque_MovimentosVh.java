package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.Estoque_Movimentacao;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public class Estoque_MovimentosVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Estoque_Movimentacao movimentacao = new Estoque_Movimentacao();

		if (operacao != null && operacao.equals("Lista")) {

		} else if (operacao != null && operacao.equals("Buscar")) {
			movimentacao.setId(Integer.parseInt(req.getParameter("id")));
		}

		return movimentacao;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Lista")) {
			List<Estoque_Movimentacao> listaMovimentacoes = new ArrayList<Estoque_Movimentacao>();
			for (IDominio d : resultado.getListDominio()) {
				listaMovimentacoes.add((Estoque_Movimentacao) d);
			}
			req.setAttribute("listaMovimentacoes", listaMovimentacoes);

			try {
				req.getRequestDispatcher("consulta_entradasaida.jsp").forward(req, resp);
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
		}
	}

}
