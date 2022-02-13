package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido_Status;
import br.edu.fatec.detoranja.dominio.Troca_MotivoAdmin;

public class Troca_MotivoAdminDAO implements IDAO {

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
		Troca_MotivoAdmin motivo = (Troca_MotivoAdmin) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_TROCA_MOTIVO\" ORDER BY id;";


		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaMotivos = new ArrayList<IDominio>();
			while (rs.next()) {
				motivo = new Troca_MotivoAdmin();

				motivo.setId(rs.getInt("id"));
				motivo.setDescricao(rs.getString("descricao"));

				listaMotivos.add(motivo);
			}

			return listaMotivos;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
