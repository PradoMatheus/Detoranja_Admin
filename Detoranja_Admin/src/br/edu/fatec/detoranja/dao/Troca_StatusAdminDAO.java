package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Troca_StatusAdmin;

public class Troca_StatusAdminDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		return false;
	}

	@Override
	public boolean excluir(IDominio obj) {
		return false;
	}

	@Override
	public IDominio buscar(IDominio obj) {
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Troca_StatusAdmin status = (Troca_StatusAdmin) obj;

		Connection conn = null;

		String sql = "";
		sql = "SELECT * FROM \"BD_TROCA_STATUS\" WHERE id > " + status.getId() + " AND id <> 5 LIMIT 1;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listastatus = new ArrayList<IDominio>();
			while (rs.next()) {
				status = new Troca_StatusAdmin();

				status.setId(rs.getInt("id"));
				status.setDescricao(rs.getString("descricao"));

				listastatus.add(status);
			}

			return listastatus;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
