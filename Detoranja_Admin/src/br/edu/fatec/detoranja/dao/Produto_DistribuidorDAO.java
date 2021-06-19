package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto_Distribuidor;

public class Produto_DistribuidorDAO implements IDAO {

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
		Produto_Distribuidor produto_distribuidor = (Produto_Distribuidor) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PRODUTOS_DISTRIBUIDOR\" ORDER BY descricao;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaProdutosDistribuidor = new ArrayList<IDominio>();
			while (rs.next()) {
				produto_distribuidor = new Produto_Distribuidor();
				
				produto_distribuidor.setId(rs.getInt("id"));
				produto_distribuidor.setDescricao(rs.getString("descricao"));
				
				listaProdutosDistribuidor.add(produto_distribuidor);
			}

			return listaProdutosDistribuidor;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}
}
