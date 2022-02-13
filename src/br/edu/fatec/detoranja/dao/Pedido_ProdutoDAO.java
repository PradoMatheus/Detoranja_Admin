package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido;
import br.edu.fatec.detoranja.dominio.Pedido_Itens;
import br.edu.fatec.detoranja.dominio.Produto;

public class Pedido_ProdutoDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Pedido pedido = (Pedido) obj;

		String sql = "INSERT INTO \"BD_PEDIDO_PRODUTOS\" (id_pedido, id_produto, quantidade, valor) "
				+ "VALUES (?, ?, ?, ?);";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);

			for (Pedido_Itens item : pedido.getListprodutos()) {
				pstm.setInt(1, pedido.getId());
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
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Pedido pedido = (Pedido) obj;
		Pedido_Itens item;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PEDIDO_PRODUTOS\" WHERE id_pedido = " + pedido.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaItens = new ArrayList<IDominio>();
			while (rs.next()) {
				item = new Pedido_Itens();

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
