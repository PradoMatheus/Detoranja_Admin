package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido_Status;

public class Pedido_StatusDAO implements IDAO {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Pedido_Status status = (Pedido_Status) obj;

		Connection conn = null;

		String sql = "";
		if (status.getId() == 2)
			sql = "SELECT * FROM \"BD_PEDIDO_STATUS\" WHERE id = 4 ";
		else
			sql = "SELECT * FROM \"BD_PEDIDO_STATUS\" WHERE id > " + status.getId();

		if (status.getId() == 1)
			sql += "LIMIT 2;";
		else
			sql += "ORDER BY id LIMIT 1;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listastatus = new ArrayList<IDominio>();
			while (rs.next()) {
				status = new Pedido_Status();

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
