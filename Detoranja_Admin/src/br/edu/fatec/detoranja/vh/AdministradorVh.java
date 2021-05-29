package br.edu.fatec.detoranja.vh;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.detoranja.dominio.Administrador;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.util.Resultado;

public class AdministradorVh implements IViewHelper {

	@Override
	public IDominio getDominio(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");

		Administrador adm = new Administrador();

		if (operacao != null && operacao.equals("Salvar")) {

		} else if (operacao != null && operacao.equals("Buscar")) {
			
			String email = req.getParameter("txtemail");
			String senha = req.getParameter("txtpassword");
			
			try {
				
				MessageDigest algorithm = MessageDigest.getInstance("MD5");
				byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
				
				adm.setSenha(messageDigest.toString());
						
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			adm.setEmail(email);

		}

		return adm;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");
		
		if (operacao != null && operacao.equals("Salvar")) {
			
		} else if (operacao != null && operacao.equals("Buscar")) {
			
		}
	}
}
