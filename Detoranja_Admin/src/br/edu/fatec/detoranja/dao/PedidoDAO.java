package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.edu.fatec.detoranja.dominio.Cliente;
import br.edu.fatec.detoranja.dominio.Endereco;
import br.edu.fatec.detoranja.dominio.Forma_Pagamento;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido;
import br.edu.fatec.detoranja.dominio.Pedido_Frete;
import br.edu.fatec.detoranja.dominio.Pedido_Itens;
import br.edu.fatec.detoranja.dominio.Pedido_Status;

public class PedidoDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Pedido pedido = (Pedido) obj;

		String sql = "UPDATE \"BD_PEDIDO\" SET id_status = ? WHERE id = " + pedido.getId() + ";";
		if (pedido.getStatus().getId() == 2)
			sql += "INSERT INTO \"BD_ESTOQUE_MOVIMENTACOES\" "
					+ "(id_produto, id_tipo, quantidade, observacao, id_itenscompra, id_pedido, data) "
					+ "SELECT id_produto, true, quantidade , NULL , NULL ,id_pedido, current_timestamp "
					+ "FROM public.\"BD_PEDIDO_PRODUTOS\" WHERE id_pedido = " + pedido.getId() + ";";
		else if (pedido.getStatus().getId() == 9) {
			sql += "INSERT INTO \"BD_ESTOQUE_MOVIMENTACOES\" "
					+ "(id_produto, id_tipo, quantidade, observacao, id_itenscompra, id_pedido, data) "
					+ "SELECT id_produto, false, quantidade , NULL , NULL ,id_pedido, current_timestamp "
					+ "FROM public.\"BD_PEDIDO_PRODUTOS\" WHERE id_pedido = " + pedido.getId() + ";";
			
			UUID uuid = UUID.randomUUID();
			String codCupom = uuid.toString().substring(0, 6);
			sql += "INSERT INTO \"BD_CUPONS\" " +
				"(cod_cupom, desconto, valor_minimo, validade, ativo, tipo, cliente) " +
				"VALUES ('" + codCupom + "', (SELECT valor FROM \"BD_PEDIDO\" WHERE id = " + pedido.getId() + "), NULL, NULL, true, 1, (SELECT id_cliente FROM \"BD_PEDIDO\" WHERE id = " + pedido.getId() + "));";
		}

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pedido.getStatus().getId());

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

		Connection conn = null;

		String sql = "SELECT PEDIDO.id, PEDIDO.id_cliente, PEDIDO.valor, PEDIDO.quantidade, PEDIDO.data_pedido, PEDIDO.id_status, STATUS.descricao FROM \"BD_PEDIDO\" PEDIDO "
				+ "JOIN \"BD_PEDIDO_STATUS\" STATUS ON STATUS.id = PEDIDO.id_status  " + "WHERE PEDIDO.id = "
				+ pedido.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				pedido.setId(rs.getInt("id"));

				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				pedido.setCliente(cliente);

				pedido.setValorTotal(rs.getDouble("valor"));
				pedido.setQuantidade(rs.getInt("quantidade"));
				pedido.setData_pedido(rs.getObject("data_pedido", LocalDateTime.class));

				Pedido_Status status = new Pedido_Status();
				status.setId(rs.getInt("id_status"));
				status.setDescricao(rs.getString("descricao"));
				pedido.setStatus(status);

				pedido.setEndereco((Endereco) new Pedido_EnderecoDAO().buscar(pedido));

				List<Pedido_Itens> listItens = new ArrayList<Pedido_Itens>();
				for (IDominio d : new Pedido_ProdutoDAO().lista(pedido))
					listItens.add((Pedido_Itens) d);
				pedido.setListprodutos(listItens);

				List<Forma_Pagamento> listForma = new ArrayList<Forma_Pagamento>();
				for (IDominio d : new Pedido_Forma_PagamentoDAO().lista(pedido))
					listForma.add((Forma_Pagamento) d);
				pedido.setListforma_Pagamentos(listForma);

				pedido.setFrete((Pedido_Frete) new Pedido_FreteDAO().buscar(pedido));
			}

			return pedido;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Pedido pedido = (Pedido) obj;

		String sql = "SELECT PEDIDO.id, PEDIDO.id_cliente, PEDIDO.valor, PEDIDO.quantidade, PEDIDO.data_pedido, PEDIDO.id_status, STATUS.descricao, CLIENTE.nome FROM \"BD_PEDIDO\" PEDIDO "
				+ "JOIN \"BD_PEDIDO_STATUS\" STATUS ON STATUS.id = PEDIDO.id_status JOIN \"BD_CLIENTES\" CLIENTE ON CLIENTE.id = PEDIDO.id_cliente ";

		if (pedido.getId() == 0)
			sql += "WHERE PEDIDO.id_status <= 5;";
		else
			sql += "WHERE PEDIDO.id_status > 5;";
		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaPedido = new ArrayList<IDominio>();
			while (rs.next()) {
				pedido = new Pedido();

				pedido.setId(rs.getInt("id"));

				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				pedido.setCliente(cliente);

				pedido.setValorTotal(rs.getDouble("valor"));
				pedido.setQuantidade(rs.getInt("quantidade"));
				pedido.setData_pedido(rs.getObject("data_pedido", LocalDateTime.class));

				Pedido_Status status = new Pedido_Status();
				status.setId(rs.getInt("id_status"));
				status.setDescricao(rs.getString("descricao"));
				pedido.setStatus(status);

				listaPedido.add(pedido);
			}

			return listaPedido;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}

		return null;
	}

}
