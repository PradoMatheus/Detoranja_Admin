package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
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
			
			produto_distribuidor.setDescricao(req.getParameter("txtDistribuidor"));
			produto_distribuidor.setId(Integer.parseInt(req.getParameter("txtId").toString()));

		} else if (operacao != null && operacao.equals("Excluir")) {
			
			produto_distribuidor.setDescricao(req.getParameter("txtDistribuidor"));
			produto_distribuidor.setId(Integer.parseInt(req.getParameter("txtId").toString()));
			
		} else if (operacao != null && operacao.equals("Lista")) {

		}

		return produto_distribuidor;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Salvar")) {
			
			try {
				req.getRequestDispatcher("/produto_distribuidor?operacao=Lista&tipo=object").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (operacao != null && operacao.equals("Excluir")) {
			
			try {
				req.getRequestDispatcher("/produto_distribuidor?operacao=Lista&tipo=object").forward(req, resp);
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
				List<Produto_Distribuidor> listaProduto_Distribuidor = new ArrayList<Produto_Distribuidor>();
				for (IDominio d : resultado.getListDominio()) {
					listaProduto_Distribuidor.add((Produto_Distribuidor) d);
				}
				req.setAttribute("listaProduto_Distribuidor", listaProduto_Distribuidor);
				
				try {
					req.getRequestDispatcher("consulta_distribuidor.jsp").forward(req, resp);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
