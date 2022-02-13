package br.edu.fatec.detoranja.vh;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import br.edu.fatec.detoranja.dominio.Cliente;
import br.edu.fatec.detoranja.dominio.Cupom;
import br.edu.fatec.detoranja.dominio.Cupom_Tipo;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public class CupomVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Cupom cupom = new Cupom();

		if (operacao != null && operacao.equals("Salvar")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			try {
				JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);

				cupom.setId(data.get("id").getAsInt());
				cupom.setDesconto(Double.parseDouble(data.get("txtDesconto").getAsString().replace(",", ".")));
				if (data.get("txtValorMinimo").getAsString().length() > 0)
				cupom.setValor_minimo(Double.parseDouble(data.get("txtValorMinimo").getAsString().replace(",", ".")));

				if (data.get("txtValidade").getAsString().length() > 0)
					cupom.setValidade(LocalDate.parse(data.get("txtValidade").getAsString(), formatter));
				cupom.setDesc_cupom(data.get("txtCodCupom").getAsString());

				Cupom_Tipo tipo = new Cupom_Tipo();
				tipo.setId(Integer.parseInt(data.get("txtTipo").getAsString()));
				cupom.setTipo(tipo);

				cupom.setAtivo(Boolean.parseBoolean(data.get("txtAtivo").getAsString()));

				Cliente cliente = new Cliente();
				if (data.get("txtCliente").getAsString().length() > 0)
					cliente.setId(Integer.parseInt(data.get("txtCliente").getAsString()));
				cupom.setCliente(cliente);

			} catch (JsonSyntaxException | JsonIOException | IOException e) {
				e.printStackTrace();
			}
		} else if (operacao != null && operacao.equals("Lista")) {

		} else if (operacao != null && operacao.equals("Buscar")) {
			cupom.setId(Integer.parseInt(req.getParameter("id")));
		}
		return cupom;
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
			List<Cupom> listaCupom = new ArrayList<Cupom>();
			for (IDominio d : resultado.getListDominio()) {
				listaCupom.add((Cupom) d);
			}
			req.setAttribute("listaCupom", listaCupom);

			try {
				req.getRequestDispatcher("consulta_cupons.jsp").forward(req, resp);
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
