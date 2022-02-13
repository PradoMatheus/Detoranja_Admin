package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido;
import br.edu.fatec.detoranja.dominio.Pedido_Status;
import br.edu.fatec.detoranja.util.Resultado;

public class PedidoVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Pedido pedido = new Pedido();

		if (operacao != null && operacao.equals("Lista")) {
			pedido.setId(Integer.parseInt(req.getParameter("id")));
		} else if (operacao != null && operacao.equals("Salvar")) {
			pedido.setId(Integer.parseInt(req.getParameter("id")));

			Pedido_Status status = new Pedido_Status();
			status.setId(Integer.parseInt(req.getParameter("status")));
			pedido.setStatus(status);
		}

		return pedido;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Lista")) {
			List<Pedido> listaPedido = new ArrayList<Pedido>();
			for (IDominio d : resultado.getListDominio()) {
				listaPedido.add((Pedido) d);
			}
			req.setAttribute("listaPedido", listaPedido);

			try {
				if (Integer.parseInt(req.getParameter("id")) == 0)
					req.getRequestDispatcher("consulta_pedidos.jsp").forward(req, resp);
				else
					req.getRequestDispatcher("consulta_trocas.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
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
