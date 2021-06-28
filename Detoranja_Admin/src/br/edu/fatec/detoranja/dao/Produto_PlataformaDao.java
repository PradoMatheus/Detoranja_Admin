package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto_Plataforma;

public class Produto_PlataformaDao implements IDAO {

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
		Produto_Plataforma produto_plataforma = (Produto_Plataforma) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PRODUTOS_PLATAFORMA\" ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaPlataforma = new ArrayList<IDominio>();
			while (rs.next()) {
				produto_plataforma = new Produto_Plataforma();

				produto_plataforma.setId(rs.getInt("id"));
				produto_plataforma.setDescricao(rs.getString("descricao"));
				produto_plataforma.setImagem(rs.getString("Imagem"));

				listaPlataforma.add(produto_plataforma);
			}

			return listaPlataforma;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
