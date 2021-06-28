package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto_Desenvolvedor;
import br.edu.fatec.detoranja.util.Resultado;

public class Produto_DesenvolvedorVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Produto_Desenvolvedor produto_desenvolvedor = new Produto_Desenvolvedor();

		if (operacao != null && operacao.equals("Salvar")) {
			
			produto_desenvolvedor.setDescricao(req.getParameter("txtDesenvolvedor"));
			produto_desenvolvedor.setId(Integer.parseInt(req.getParameter("txtId").toString()));
			

		} else if (operacao != null && operacao.equals("Excluir")) {
			
			produto_desenvolvedor.setDescricao(req.getParameter("txtDesenvolvedor"));
			produto_desenvolvedor.setId(Integer.parseInt(req.getParameter("txtId").toString()));
		
		} else if (operacao != null && operacao.equals("Lista")) {

		}

		return produto_desenvolvedor;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Salvar")) {
			
			try {
				req.getRequestDispatcher("/produto_desenvolvedor?operacao=Lista&tipo=object").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (operacao != null && operacao.equals("Excluir")) {
			
			try {
				req.getRequestDispatcher("/produto_desenvolvedor?operacao=Lista&tipo=object").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
				List<Produto_Desenvolvedor> listaProduto_Desenvolvedor = new ArrayList<Produto_Desenvolvedor>();
				for (IDominio d : resultado.getListDominio()) {
					listaProduto_Desenvolvedor.add((Produto_Desenvolvedor) d);
				}
				req.setAttribute("listaProduto_Desenvolvedor", listaProduto_Desenvolvedor);
				
				try {
					req.getRequestDispatcher("consulta_desenvolvedor.jsp").forward(req, resp);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
