package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import br.edu.fatec.detoranja.dominio.Fornecedor;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public class FornecedorVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Fornecedor fornecedor = new Fornecedor();

		if (operacao != null && operacao.equals("Salvar")) {
			try {
				JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);

				fornecedor.setId(data.get("id").getAsInt());
				fornecedor.setRazao(data.get("razao").getAsString());
				fornecedor.setFantasia(data.get("fantasia").getAsString());
				Long cnpj = Long
						.parseLong(data.get("cnpj").getAsString().replace(".", "").replace("/", "").replace("-", ""));
				fornecedor.setCnpj(cnpj);
				fornecedor.setInscricao(Long.parseLong(data.get("inscricao").getAsString().replace(".", "")));

			} catch (JsonSyntaxException | JsonIOException | IOException e) {
				e.printStackTrace();
			}
		} else if (operacao != null && operacao.equals("Lista")) {
		} else if (operacao != null && operacao.equals("Buscar")) {
			fornecedor.setId(Integer.parseInt(req.getParameter("id")));
		}

		return fornecedor;
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
			List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
			for (IDominio d : resultado.getListDominio()) {
				listaFornecedor.add((Fornecedor) d);
			}
			req.setAttribute("listaFornecedor", listaFornecedor);

			try {
				req.getRequestDispatcher("consulta_fornecedor.jsp").forward(req, resp);
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
