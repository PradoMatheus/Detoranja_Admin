package br.edu.fatec.detoranja.vh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.detoranja.dominio.Administrador;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Criptografia_Hash;
import br.edu.fatec.detoranja.util.Resultado;

public class AdministradorVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Administrador adm = new Administrador();

		if (operacao != null && operacao.equals("Salvar")) {

		} else if (operacao != null && operacao.equals("Buscar")) {

			adm.setSenha(Criptografia_Hash.criptografiaHash(req.getParameter("txtpassword")));
			adm.setEmail(req.getParameter("txtemail"));

		}

		return adm;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");

		if (operacao != null && operacao.equals("Salvar")) {

		} else if (operacao != null && operacao.equals("Buscar")) {
			Administrador admin = (Administrador) resultado.getDominio();
			try {
				if (admin.getId() != 0) {
					req.getSession().setAttribute("AdminUser", admin.getId());
					req.getRequestDispatcher("principal.jsp").forward(req, resp);
				} else {
					req.getRequestDispatcher("index.jsp").forward(req, resp);
				}
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
