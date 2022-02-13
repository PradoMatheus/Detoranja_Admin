package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto;
import br.edu.fatec.detoranja.dominio.Troca_Admin;
import br.edu.fatec.detoranja.dominio.Troca_ItensAdmin;

public class Troca_ItensAdminDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Troca_Admin troca = (Troca_Admin) obj;

		String sql = "INSERT INTO \"BD_TROCA_PRODUTOS\" (id_troca, id_produto, quantidade, valor) "
				+ "VALUES (?, ?, ?, ?);";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);

			for (Troca_ItensAdmin item : troca.getListTrocaItens()) {
				pstm.setInt(1, troca.getId());
				pstm.setInt(2, item.getProduto().getId());
				pstm.setInt(3, item.getQuantidade());
				pstm.setDouble(4, item.getValor());
				pstm.execute();
			}

			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}

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
		Troca_Admin troca = (Troca_Admin) obj;
		Troca_ItensAdmin item;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_TROCA_PRODUTOS\" WHERE id_troca = " + troca.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaItens = new ArrayList<IDominio>();
			while (rs.next()) {
				item = new Troca_ItensAdmin();

				item.setId(rs.getInt("id"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("id_produto"));
				item.setProduto((Produto) new ProdutoDAO().buscar(produto));

				item.setQuantidade(rs.getInt("quantidade"));
				item.setValor(rs.getDouble("valor"));

				listaItens.add(item);
			}

			return listaItens;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
