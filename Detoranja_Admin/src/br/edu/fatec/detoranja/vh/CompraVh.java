package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import br.edu.fatec.detoranja.dominio.Compra;
import br.edu.fatec.detoranja.dominio.Compra_Itens;
import br.edu.fatec.detoranja.dominio.Fornecedor;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto;
import br.edu.fatec.detoranja.util.Resultado;

public class CompraVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Compra compra = new Compra();

		if (operacao != null && operacao.equals("Salvar")) {

			try {
				JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);

				compra.setId(data.get("id").getAsInt());
				compra.setData_compra(LocalDateTime.parse(data.get("dataCompra").getAsString()));
				compra.setNota_fiscal(data.get("nota").getAsString());
				compra.setQuantidade_total(data.get("quantidadeTotal").getAsInt());
				compra.setValor_total(data.get("ValorTotal").getAsDouble());

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(data.get("idFornecedor").getAsInt());
				compra.setFornecedor(fornecedor);

				// BUSCA E PREENCHE A LISTA DE ITENS DA COMPRA
				List<Compra_Itens> listaItens = new ArrayList<Compra_Itens>();
				JsonArray jItens = data.get("ItensCompra").getAsJsonArray();
				for (int i = 0; i < jItens.size(); i++) {
					Compra_Itens item = new Compra_Itens();
					JsonObject obj = jItens.get(i).getAsJsonObject();

					item.setId(0);
					item.setObservaocao(obj.get("obs").getAsString());
					item.setQuantidade(obj.get("quantidade").getAsInt());
					item.setValor_compra(obj.get("valor").getAsDouble());

					Produto produto = new Produto();
					produto.setId(obj.get("produto").getAsInt());
					item.setProduto(produto);

					listaItens.add(item);
				}
				compra.setMovimentacoes(listaItens);

			} catch (JsonSyntaxException | JsonIOException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operacao != null && operacao.equals("Lista")) {

		} else if (operacao != null && operacao.equals("Buscar")) {
			compra.setId(Integer.parseInt(req.getParameter("id")));
		}

		return compra;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Salvar")) {

			String json = null;

			if (resultado.getMsg() != null)
				json = new Gson().toJson(false);
			else
				json = new Gson().toJson(true);

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			try {
				resp.getWriter().write(json);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else if (operacao != null && operacao.equals("Lista")) {
			List<Compra> listaCompras = new ArrayList<Compra>();
			for (IDominio d : resultado.getListDominio()) {
				listaCompras.add((Compra) d);
			}
			req.setAttribute("listaCompras", listaCompras);

			try {
				req.getRequestDispatcher("consulta_compras.jsp").forward(req, resp);
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
