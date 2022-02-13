package br.edu.fatec.detoranja.vh;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Relatorio_Produtos;
import br.edu.fatec.detoranja.util.Resultado;

public class Relatorio_ProdutosVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Relatorio_Produtos relatorio = new Relatorio_Produtos();

		if (operacao != null && operacao.equals("Buscar")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			relatorio.setDatainicial(LocalDate.parse(req.getParameter("di"), formatter));
			relatorio.setDataFinal(LocalDate.parse(req.getParameter("df"), formatter));
			relatorio.setFiltro(Boolean.parseBoolean(req.getParameter("f")));
			relatorio.setOrdenar(req.getParameter("o"));
			relatorio.setQuantidade(Integer.parseInt(req.getParameter("q")));
		}

		return relatorio;
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
		}
	}

}
