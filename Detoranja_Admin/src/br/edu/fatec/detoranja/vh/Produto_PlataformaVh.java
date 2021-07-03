package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto_Plataforma;
import br.edu.fatec.detoranja.util.Resultado;

public class Produto_PlataformaVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");
		
		Produto_Plataforma produto_plataforma = new Produto_Plataforma();
		
		if (operacao != null && operacao.equals("Salvar")) {
			
			produto_plataforma.setDescricao(req.getParameter("txtDescricao"));
			produto_plataforma.setId(Integer.parseInt(req.getParameter("txtId").toString()));
			produto_plataforma.setImagem(null);

		} else if (operacao != null && operacao.equals("Excluir")) {
			
		} else if (operacao != null && operacao.equals("Lista")) {
			// Não necessita de nenhum tratamento
		}
		
		return produto_plataforma;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");
		
		if (operacao != null && operacao.equals("Salvar")) {
			
			String json = new Gson().toJson("success");

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			try {
				resp.getWriter().write(json);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		} else if (operacao != null && operacao.equals("Excluir")) {

		} else if (operacao != null && operacao.equals("Lista")) {
			
			if (req.getParameter("tipo").equals("json")) {

			} else {
				List<Produto_Plataforma> listaProduto_Plataforma = new ArrayList<Produto_Plataforma>();
				for (IDominio d : resultado.getListDominio()) {
					listaProduto_Plataforma.add((Produto_Plataforma) d);
				}
				req.setAttribute("listaProduto_Plataforma", listaProduto_Plataforma);
				
				try {
					req.getRequestDispatcher("consulta_plataforma.jsp").forward(req, resp);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
