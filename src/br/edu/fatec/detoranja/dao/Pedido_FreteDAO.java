package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido;
import br.edu.fatec.detoranja.dominio.Pedido_Frete;

public class Pedido_FreteDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Pedido pedido = (Pedido) obj;

		String sql = "INSERT INTO \"BD_PEDIDO_FRETE\" (valor, id_pedido) VALUES (?, ?)";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setDouble(1, pedido.getFrete().getValor());
			pstm.setInt(2, pedido.getId());
			pstm.execute();

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
		Pedido pedido = (Pedido) obj;
		Pedido_Frete frete = new Pedido_Frete();

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PEDIDO_FRETE\" WHERE id_pedido = " + pedido.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				frete = new Pedido_Frete();

				frete.setId(rs.getInt("id"));
				frete.setValor(rs.getDouble("valor"));

				return frete;
			}
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
