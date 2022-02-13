package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.edu.fatec.detoranja.dominio.Cupom;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido;
import br.edu.fatec.detoranja.dominio.Troca_Admin;
import br.edu.fatec.detoranja.dominio.Troca_ItensAdmin;
import br.edu.fatec.detoranja.dominio.Troca_LogAdmin;
import br.edu.fatec.detoranja.dominio.Troca_MotivoAdmin;
import br.edu.fatec.detoranja.dominio.Troca_StatusAdmin;

public class TrocaAdminDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Troca_Admin troca = (Troca_Admin) obj;

		String sql = "UPDATE \"BD_TROCA\" SET id_status = ? WHERE id = " + troca.getId() + ";";

		if (troca.getStatus().getId() == 4) {
			sql += "INSERT INTO \"BD_ESTOQUE_MOVIMENTACOES\" "
					+ "(id_produto, id_tipo, quantidade, observacao, id_itenscompra, id_pedido, data, id_troca) "
					+ "SELECT id_produto, false, quantidade , NULL , NULL ,NULL, current_timestamp, id_troca "
					+ "FROM \"BD_TROCA_PRODUTOS\" WHERE id_troca = " + troca.getId() + ";";

			UUID uuid = UUID.randomUUID();
			String codCupom = uuid.toString().substring(0, 6);
			sql += "INSERT INTO \"BD_CUPONS\" "
					+ "(cod_cupom, desconto, valor_minimo, validade, ativo, tipo, cliente, troca) " + "VALUES ('"
					+ codCupom
					+ "', (SELECT SUM(\"BD_TROCA_PRODUTOS\".quantidade * \"BD_TROCA_PRODUTOS\".valor) FROM \"BD_TROCA_PRODUTOS\" JOIN \"BD_TROCA\" ON \"BD_TROCA\".id = \"BD_TROCA_PRODUTOS\".id_troca WHERE \"BD_TROCA_PRODUTOS\".id_troca = "
					+ troca.getId()
					+ "), NULL, NULL, true, 1, (SELECT \"BD_PEDIDO\".id_cliente FROM \"BD_TROCA\" JOIN \"BD_PEDIDO\" ON \"BD_PEDIDO\".id = \"BD_TROCA\".id_pedido WHERE \"BD_TROCA\".id = "
					+ troca.getId() + "), " + troca.getId() + ");";
		}

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, troca.getStatus().getId());

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
		Troca_Admin troca = (Troca_Admin) obj;

		Connection conn = null;

		String sql = "SELECT TROCA.id, TROCA.id_motivo, MOTIVO.descricao motivo, TROCA.id_pedido, TROCA.observacao, TROCA.\"data\" data, TROCA.id_status, STATUS.descricao status, SUM(TROCA_PRODUTO.valor) total "
				+ "FROM \"BD_TROCA\" TROCA " + "JOIN \"BD_PEDIDO\" PEDIDO ON PEDIDO.id = TROCA.id_pedido "
				+ "JOIN \"BD_TROCA_MOTIVO\" MOTIVO ON MOTIVO.id = TROCA.id_motivo "
				+ "JOIN \"BD_TROCA_STATUS\" STATUS ON STATUS.id = TROCA.id_status "
				+ "JOIN \"BD_TROCA_PRODUTOS\" TROCA_PRODUTO ON TROCA_PRODUTO.id_troca = TROCA.id " + "WHERE TROCA.id = "
				+ troca.getId() + " " + "GROUP BY TROCA.id, TROCA.observacao, STATUS.descricao, MOTIVO.descricao;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				troca = new Troca_Admin();

				troca.setId(rs.getInt("id"));
				troca.setObservacao(rs.getString("observacao"));
				troca.setData(rs.getObject("data", LocalDateTime.class));
				troca.setTotal(rs.getDouble("total"));

				Troca_MotivoAdmin motivo = new Troca_MotivoAdmin();
				motivo.setDescricao(rs.getString("motivo"));
				motivo.setId(rs.getInt("id_motivo"));
				troca.setMotivo(motivo);

				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("id_pedido"));
				troca.setPedido(pedido);

				Troca_StatusAdmin status = new Troca_StatusAdmin();
				status.setDescricao(rs.getString("status"));
				status.setId(rs.getInt("id_status"));
				troca.setStatus(status);

				List<Troca_ItensAdmin> listItens = new ArrayList<Troca_ItensAdmin>();
				for (IDominio d : new Troca_ItensAdminDAO().lista(troca))
					listItens.add((Troca_ItensAdmin) d);
				troca.setListTrocaItens(listItens);

				troca.setCupom((Cupom) new Troca_CupomAdminDAO().buscar(troca));

				List<Troca_LogAdmin> listaLogs = new ArrayList<Troca_LogAdmin>();
				for (IDominio d : new Troca_LogAdminDAO().lista(troca))
					listaLogs.add((Troca_LogAdmin) d);
				troca.setListLogs(listaLogs);

			}

			return troca;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Troca_Admin troca = (Troca_Admin) obj;

		Connection conn = null;

		String sql = "SELECT TROCA.id, TROCA.id_motivo, MOTIVO.descricao motivo, TROCA.id_pedido, TROCA.observacao, TROCA.\"data\" data, TROCA.id_status, STATUS.descricao status, SUM(TROCA_PRODUTO.valor) total "
				+ "FROM \"BD_TROCA\" TROCA " + "JOIN \"BD_PEDIDO\" PEDIDO ON PEDIDO.id = TROCA.id_pedido "
				+ "JOIN \"BD_TROCA_MOTIVO\" MOTIVO ON MOTIVO.id = TROCA.id_motivo "
				+ "JOIN \"BD_TROCA_STATUS\" STATUS ON STATUS.id = TROCA.id_status "
				+ "JOIN \"BD_TROCA_PRODUTOS\" TROCA_PRODUTO ON TROCA_PRODUTO.id_troca = TROCA.id "
				+ "GROUP BY TROCA.id, TROCA.observacao, STATUS.descricao, MOTIVO.descricao;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			List<IDominio> listTrocas = new ArrayList<IDominio>();

			while (rs.next()) {
				troca = new Troca_Admin();

				troca.setId(rs.getInt("id"));
				troca.setObservacao(rs.getString("observacao"));
				troca.setData(rs.getObject("data", LocalDateTime.class));
				troca.setTotal(rs.getDouble("total"));

				Troca_MotivoAdmin motivo = new Troca_MotivoAdmin();
				motivo.setDescricao(rs.getString("motivo"));
				motivo.setId(rs.getInt("id_motivo"));
				troca.setMotivo(motivo);

				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("id_pedido"));
				troca.setPedido(pedido);

				Troca_StatusAdmin status = new Troca_StatusAdmin();
				status.setDescricao(rs.getString("status"));
				status.setId(rs.getInt("id_status"));
				troca.setStatus(status);

				listTrocas.add(troca);
			}

			return listTrocas;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
