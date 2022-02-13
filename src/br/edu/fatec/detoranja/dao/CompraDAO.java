package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Compra;
import br.edu.fatec.detoranja.dominio.Compra_Itens;
import br.edu.fatec.detoranja.dominio.Fornecedor;
import br.edu.fatec.detoranja.dominio.IDominio;

public class CompraDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Compra compra = (Compra) obj;

		String sql = "";
		if (compra.getId() == 0) {
			sql = "INSERT INTO public.\"BD_COMPRA\" (quantidade_total, valor_total, fornecedor, nota_fiscal, data) VALUES (?, ?, ?, ?, ?) RETURNING id;;";
		} else {
			sql = "UPDATE \"BD_CUPONS\" SET cod_cupom=?, desconto=?, valor_minimo=?, validade=?, ativo=?, tipo=?, cliente=? WHERE id = "
					+ compra.getId() + ";";
		}
		ResultSet ultimoID = null;
		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, compra.getQuantidade_total());
			pstm.setDouble(2, compra.getValor_total());
			pstm.setInt(3, compra.getFornecedor().getId());
			pstm.setString(4, compra.getNota_fiscal());
			pstm.setTimestamp(5, Timestamp.valueOf(compra.getData_compra()));

			ultimoID = pstm.executeQuery();
			while (ultimoID.next())
				compra.setId(ultimoID.getInt(1));

			new Compra_ItensDAO().salvar(compra);

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
		Compra compra = (Compra) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_COMPRA\" WHERE id = " + compra.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				compra = new Compra();

				compra.setId(rs.getInt("id"));
				compra.setQuantidade_total(rs.getInt("quantidade_total"));
				compra.setValor_total(rs.getDouble("valor_total"));

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("fornecedor"));
				
				compra.setFornecedor((Fornecedor) new FornecedorDAO().buscar(fornecedor));

				compra.setNota_fiscal(rs.getString("nota_fiscal"));
				compra.setData_compra(rs.getObject("data", LocalDateTime.class));
				
				List<Compra_Itens> itens = new ArrayList<Compra_Itens>();
				for(IDominio d : new Compra_ItensDAO().lista(compra)) {
					itens.add((Compra_Itens) d);
				}
				compra.setMovimentacoes(itens);
			}

			return compra;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Compra compra = (Compra) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_COMPRA\" ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaCompra = new ArrayList<IDominio>();
			while (rs.next()) {
				compra = new Compra();

				compra.setId(rs.getInt("id"));
				compra.setQuantidade_total(rs.getInt("quantidade_total"));
				compra.setValor_total(rs.getDouble("valor_total"));

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("fornecedor"));
				
				compra.setFornecedor((Fornecedor) new FornecedorDAO().buscar(fornecedor));

				compra.setNota_fiscal(rs.getString("nota_fiscal"));
				compra.setData_compra(rs.getObject("data", LocalDateTime.class));

				listaCompra.add(compra);
			}

			return listaCompra;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
