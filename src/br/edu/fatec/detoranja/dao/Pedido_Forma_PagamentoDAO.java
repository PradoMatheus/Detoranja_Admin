package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Cliente;
import br.edu.fatec.detoranja.dominio.Forma_Pagamento;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido;

public class Pedido_Forma_PagamentoDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {

		Pedido pedido = (Pedido) obj;

		String sql = "INSERT INTO \"BD_PEDIDO_CARTAO\" "
				+ "(numero_cartao, bandeira, cvv, id_cliente, nome, ano_validade, mes_validade, id_pedido) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);

			for (Forma_Pagamento forma : pedido.getListforma_Pagamentos()) {
				pstm.setLong(1, forma.getNumero_cartao());
				pstm.setString(2, forma.getBandeira());
				pstm.setInt(3, forma.getCvv());
				pstm.setInt(4, forma.getCliente().getId());
				pstm.setString(5, forma.getNome());
				pstm.setInt(6, forma.getAno_validade());
				pstm.setInt(7, forma.getMes_validade());
				pstm.setInt(8, pedido.getId());
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
		return false;
	}

	@Override
	public IDominio buscar(IDominio obj) {
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Pedido pedido = (Pedido) obj;
		Forma_Pagamento forma;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PEDIDO_CARTAO\" WHERE id_pedido=" + pedido.getId()
				+ " ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaForma = new ArrayList<IDominio>();
			while (rs.next()) {
				forma = new Forma_Pagamento();

				forma.setId(rs.getInt("id"));
				forma.setBandeira(rs.getString("bandeira"));

				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				forma.setCliente(cliente);

				forma.setNumero_cartao(rs.getLong("numero_cartao"));
				forma.setCvv(rs.getInt("cvv"));
				forma.setAno_validade(rs.getInt("ano_validade"));
				forma.setMes_validade(rs.getInt("mes_validade"));
				forma.setNome(rs.getString("nome"));

				listaForma.add(forma);
			}

			return listaForma;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
