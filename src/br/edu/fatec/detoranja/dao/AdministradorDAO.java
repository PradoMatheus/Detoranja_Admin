package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Administrador;
import br.edu.fatec.detoranja.dominio.IDominio;

public class AdministradorDAO implements IDAO{

	@Override
	public boolean salvar(IDominio obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(IDominio obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IDominio buscar(IDominio obj) {
		Connection conn = null;
		Administrador admin = (Administrador) obj;

		try {
			String sql = "SELECT * FROM \"BD_ADMINISTRADOR\" WHERE senha = '" + admin.getSenha()  + "' AND email = '" + admin.getEmail()  + "';";
			System.out.println(sql);
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {

				admin.setId(rs.getInt("id"));
				admin.setNome(rs.getString("nome"));
				
			}
			return admin;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
