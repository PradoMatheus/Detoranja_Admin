package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Fornecedor;
import br.edu.fatec.detoranja.dominio.IDominio;

public class FornecedorDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Fornecedor fornecedor = (Fornecedor) obj;

		String sql = "";
		if (fornecedor.getId() == 0) {
			sql = "INSERT INTO \"BD_FORNECEDOR\"(razao_social, fantasia, cnpj, inscricao_estadual) VALUES (?, ?, ?, ?);";
		} else {
			sql = "UPDATE \"BD_FORNECEDOR\" SET razao_social=?, fantasia=?, cnpj=?, inscricao_estadual=? WHERE id = "
					+ fornecedor.getId() + ";";
		}
		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, fornecedor.getRazao());
			pstm.setString(2, fornecedor.getFantasia());
			pstm.setLong(3, fornecedor.getCnpj());
			pstm.setLong(4, fornecedor.getInscricao());
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
		Fornecedor fornecedor = (Fornecedor) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_FORNECEDOR\" WHERE id = " + fornecedor.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				fornecedor = new Fornecedor();

				fornecedor.setId(rs.getInt("id"));
				fornecedor.setFantasia(rs.getString("fantasia"));
				fornecedor.setRazao(rs.getString("razao_social"));
				fornecedor.setCnpj(rs.getLong("cnpj"));
				fornecedor.setInscricao(rs.getLong("inscricao_estadual"));

				return fornecedor;
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
		Fornecedor fornecedor = (Fornecedor) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_FORNECEDOR\" ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaFornecedor = new ArrayList<IDominio>();
			while (rs.next()) {
				fornecedor = new Fornecedor();

				fornecedor.setId(rs.getInt("id"));
				fornecedor.setRazao(rs.getString("razao_social"));
				fornecedor.setFantasia(rs.getString("fantasia"));
				fornecedor.setCnpj(rs.getLong("cnpj"));
				fornecedor.setInscricao(rs.getLong("inscricao_estadual"));

				listaFornecedor.add(fornecedor);
			}

			return listaFornecedor;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
