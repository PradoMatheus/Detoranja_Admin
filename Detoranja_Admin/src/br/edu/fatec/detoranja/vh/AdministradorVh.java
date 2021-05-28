package br.edu.fatec.detoranja.vh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.detoranja.dao.Conexao;
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
			
			Connection conn = null;
			
			try {
				String sql = "SELECT * FROM `sql10414616`.`BD_ADMINISTRADOR`;";
				conn = Conexao.getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {

					System.out.println(rs.getString("nome"));

				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally {
				Conexao.fechar(conn);
			}
			
		}

		return adm;
	}

	@Override
	public void setDominio(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) {
		String operacao = req.getParameter("operacao");
		if (operacao != null && operacao.equals("Salvar")) {
		} 
	}
}
