package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto;
import br.edu.fatec.detoranja.util.Resultado;

public class ProdutoVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Produto produto = new Produto();

		if (operacao != null && operacao.equals("Salvar")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			produto.setNome(req.getParameter("txtNome"));
			produto.setDistribuidora(req.getParameter("txtDistribuidora"));
			produto.setDesenvolvedor(req.getParameter("txtDesenvolvedor"));
			produto.setData_lancamento(LocalDate.parse(req.getParameter("txtDataLancamento"), formatter));
			produto.setData_cadastro(LocalDateTime.now());
			produto.setData_alteracao(LocalDateTime.now());
			produto.setValor(Double.parseDouble(req.getParameter("txtValor").replace(",", ".")));
			produto.setPlataforma(req.getParameter("txtPlataforma"));
			produto.setDescricao(req.getParameter("txtDescricao"));
		}
		if (operacao != null && operacao.equals("Lista")) {

		}

		return produto;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Salvar")) {
			try {
				req.getRequestDispatcher("./produto?operacao=Lista").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (operacao != null && operacao.equals("Lista")) {

			List<Produto> listaProduto = new ArrayList<Produto>();
			for (IDominio d : resultado.getListDominio()) {
				listaProduto.add((Produto) d);
			}
			req.setAttribute("listaProduto", listaProduto);
			
			try {
				req.getRequestDispatcher("consulta_produtos.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
