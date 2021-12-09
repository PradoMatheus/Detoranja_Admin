package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Compra;
import br.edu.fatec.detoranja.dominio.Compra_Itens;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto;

public class Compra_ItensDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Compra compra = (Compra) obj;

		String sql = "INSERT INTO public.\"BD_COMPRA_PRODUTOS\" (id_compra, id_produto, quantidade, valor, observacao) VALUES (?, ?, ?, ?, ?);";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);

			for (Compra_Itens item : compra.getistaProdutos()) {
				pstm.setInt(1, compra.getId());
				pstm.setInt(2, item.getProduto().getId());
				pstm.setInt(3, item.getQuantidade());
				pstm.setDouble(4, item.getValor_compra());
				pstm.setString(5, item.getObservaocao());

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
		Compra compra = (Compra) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_COMPRA_PRODUTOS\" WHERE id_compra = " + compra.getId() + ";";
		
		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaItensCompra = new ArrayList<IDominio>();
			while (rs.next()) {
				Compra_Itens item = new Compra_Itens();

				item.setId(rs.getInt("id"));
				item.setQuantidade(rs.getInt("quantidade"));
				item.setValor_compra(rs.getDouble("valor"));
				item.setObservaocao(rs.getString("observacao"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("id_produto"));
				item.setProduto((Produto) new ProdutoDAO().buscar(produto));

				listaItensCompra.add(item);
			}

			return listaItensCompra;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
