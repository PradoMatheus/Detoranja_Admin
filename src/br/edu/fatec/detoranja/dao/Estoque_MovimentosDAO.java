package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Estoque_Movimentacao;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto;
import br.edu.fatec.detoranja.dominio.Troca_Admin;

public class Estoque_MovimentosDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		return false;
	}

	@Override
	public boolean excluir(IDominio obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IDominio buscar(IDominio obj) {
		Estoque_Movimentacao movimento = (Estoque_Movimentacao) obj;

		Connection conn = null;

		String sql = "SELECT MOV.id, MOV.id_produto, MOV.id_tipo, MOV.quantidade, MOV.observacao, COM_PROD.id_compra, MOV.id_pedido, MOV.data, MOV.id_troca "
				+ "FROM \"BD_ESTOQUE_MOVIMENTACOES\" MOV "
				+ "LEFT JOIN \"BD_COMPRA_PRODUTOS\" COM_PROD ON MOV.id_itenscompra = COM_PROD.id "
				+ "LEFT JOIN \"BD_TROCA\" TROCA ON TROCA.id = MOV.id_troca "
				+ "WHERE MOV.id = " + movimento.getId() + ";";
		
		System.out.println(sql);

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				movimento = new Estoque_Movimentacao();

				movimento.setId(rs.getInt("id"));
				movimento.setTipo(rs.getBoolean("id_tipo"));
				movimento.setQuantidade(rs.getInt("quantidade"));
				movimento.setObservaocao(rs.getString("observacao"));
				movimento.setData_movimentacao(rs.getObject("data", LocalDateTime.class));
				
				if (rs.getObject("id_pedido") != null)
					movimento.setPedido(rs.getInt("id_pedido"));
				if (rs.getObject("id_compra") != null)
					movimento.setCompra(rs.getInt("id_compra"));
				Troca_Admin troca = new Troca_Admin();
				if (rs.getObject("id_troca") != null)
					troca.setId(rs.getInt("id_troca"));
				movimento.setTroca(troca);
				
				Produto produto = new Produto();
				produto.setId(rs.getInt("id_produto"));
				movimento.setProduto((Produto) new ProdutoDAO().buscar(produto));
			}

			return movimento;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Estoque_Movimentacao movimento = (Estoque_Movimentacao) obj;

		Connection conn = null;

		String sql = "SELECT MOV.id, MOV.id_produto, MOV.id_tipo, MOV.quantidade, MOV.observacao, COM_PROD.id_compra, MOV.id_pedido, MOV.data "
				+ "FROM \"BD_ESTOQUE_MOVIMENTACOES\" MOV "
				+ "LEFT JOIN \"BD_COMPRA_PRODUTOS\" COM_PROD ON MOV.id_itenscompra = COM_PROD.id " + "ORDER BY MOV.id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaMovimentos = new ArrayList<IDominio>();
			while (rs.next()) {
				movimento = new Estoque_Movimentacao();

				movimento.setId(rs.getInt("id"));
				movimento.setTipo(rs.getBoolean("id_tipo"));
				movimento.setQuantidade(rs.getInt("quantidade"));
				movimento.setObservaocao(rs.getString("observacao"));
				movimento.setData_movimentacao(rs.getObject("data", LocalDateTime.class));
				if (rs.getObject("id_pedido") != null)
					movimento.setPedido(rs.getInt("id_pedido"));
				if (rs.getObject("id_compra") != null)
					movimento.setCompra(rs.getInt("id_compra"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("id_produto"));
				movimento.setProduto((Produto) new ProdutoDAO().buscar(produto));

				listaMovimentos.add(movimento);
			}

			return listaMovimentos;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
