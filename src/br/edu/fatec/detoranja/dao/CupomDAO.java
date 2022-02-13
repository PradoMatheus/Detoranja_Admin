package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Cliente;
import br.edu.fatec.detoranja.dominio.Cupom;
import br.edu.fatec.detoranja.dominio.Cupom_Tipo;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Troca_Admin;

public class CupomDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Cupom cupom = (Cupom) obj;

		String sql = "";
		if (cupom.getId() == 0) {
			sql = "INSERT INTO \"BD_CUPONS\" (cod_cupom, desconto, valor_minimo, validade, ativo, tipo, cliente) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		} else {
			sql = "UPDATE \"BD_CUPONS\" SET cod_cupom=?, desconto=?, valor_minimo=?, validade=?, ativo=?, tipo=?, cliente=? WHERE id = "
					+ cupom.getId() + ";";
		}
		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, cupom.getDesc_cupom());
			pstm.setDouble(2, cupom.getDesconto());
			
			if (cupom.getValor_minimo() == 0)
				pstm.setNull(3, 0);
			else
				pstm.setDouble(3, cupom.getValor_minimo());
			
			if (cupom.getValidade() == null)
				pstm.setNull(4, 0);
			else
				pstm.setDate(4, Date.valueOf(cupom.getValidade()));
			
			pstm.setBoolean(5, cupom.getAtivo());
			pstm.setInt(6, cupom.getTipo().getId());
			if (cupom.getCliente().getId() == 0)
				pstm.setNull(7, 0);
			else
				pstm.setInt(7, cupom.getCliente().getId());
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
		Cupom cupom = (Cupom) obj;

		Connection conn = null;

		String sql = "SELECT *, (SELECT TIPO.descricao FROM \"BD_CUPONS_TIPO\" TIPO WHERE TIPO.id = CUPOM.tipo) tipo_descricao, (SELECT nome FROM \"BD_CLIENTES\" CLIENTE WHERE CLIENTE.id = CUPOM.cliente) nome "
				+ "FROM \"BD_CUPONS\" CUPOM "
				+ "WHERE id = " + cupom.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				cupom = new Cupom();

				cupom.setId(rs.getInt("id"));
				cupom.setAtivo(rs.getBoolean("ativo"));
				cupom.setDesconto(rs.getDouble("desconto"));
				cupom.setValor_minimo(rs.getDouble("valor_minimo"));
				cupom.setValidade(rs.getObject("validade", LocalDate.class));
				cupom.setDesc_cupom(rs.getString("cod_cupom"));

				Cliente cliente = new Cliente();
				if (rs.getObject("cliente") != null || rs.getInt("cliente") != 0) {
					cliente.setId(rs.getInt("cliente"));
					cliente.setNome(rs.getString("nome"));
				}
				cupom.setCliente(cliente);
				
				Troca_Admin troca_Admin = new Troca_Admin();
				if(rs.getObject("troca") != null)
					troca_Admin.setId(rs.getInt("troca"));
				cupom.setTroca_Admin(troca_Admin);

				Cupom_Tipo tipo = new Cupom_Tipo();
				if (rs.getObject("tipo") != null || rs.getInt("tipo") != 0) {
					tipo.setId(rs.getInt("tipo"));
					tipo.setDescricao(rs.getString("tipo_descricao"));
				}
				cupom.setTipo(tipo);

				return cupom;
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
		Cupom cupom = (Cupom) obj;

		Connection conn = null;

		String sql = "SELECT *, (SELECT TIPO.descricao FROM \"BD_CUPONS_TIPO\" TIPO WHERE TIPO.id = CUPOM.tipo) tipo_descricao, (SELECT nome FROM \"BD_CLIENTES\" CLIENTE WHERE CLIENTE.id = CUPOM.cliente) nome "
				+ "FROM \"BD_CUPONS\" CUPOM ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaProdutosCategorias = new ArrayList<IDominio>();
			while (rs.next()) {
				cupom = new Cupom();

				cupom.setId(rs.getInt("id"));
				cupom.setAtivo(rs.getBoolean("ativo"));
				cupom.setDesconto(rs.getDouble("desconto"));
				cupom.setValor_minimo(rs.getDouble("valor_minimo"));
				cupom.setValidade(rs.getObject("validade", LocalDate.class));
				cupom.setDesc_cupom(rs.getString("cod_cupom"));

				Cliente cliente = new Cliente();
				if (rs.getObject("cliente") != null || rs.getInt("cliente") != 0) {
					cliente.setId(rs.getInt("cliente"));
					cliente.setNome(rs.getString("nome"));
				}
				cupom.setCliente(cliente);

				Cupom_Tipo tipo = new Cupom_Tipo();
				if (rs.getObject("tipo") != null || rs.getInt("tipo") != 0) {
					tipo.setId(rs.getInt("tipo"));
					tipo.setDescricao(rs.getString("tipo_descricao"));
				}
				cupom.setTipo(tipo);

				listaProdutosCategorias.add(cupom);
			}

			return listaProdutosCategorias;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
