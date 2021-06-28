package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto_Desenvolvedor;

public class Produto_DesenvolvedorDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Produto_Desenvolvedor produto_desenvolvedor = (Produto_Desenvolvedor) obj;

		Connection conn = null;
		String sql = null;
		
		if(produto_desenvolvedor.getId() < 1)
			sql = "INSERT INTO \"BD_PRODUTOS_DESENVOLVEDOR\" (descricao) VALUES (?);";
		else
			sql = "UPDATE \"BD_PRODUTOS_DESENVOLVEDOR\" SET descricao = ? WHERE id = " + produto_desenvolvedor.getId() + ";";
			
		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, produto_desenvolvedor.getDescricao());
			
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
		Produto_Desenvolvedor produto_desenvolvedor = (Produto_Desenvolvedor) obj;

		Connection conn = null;
		String sql = "DELETE FROM \"BD_PRODUTOS_DESENVOLVEDOR\" WHERE id = ?;";

			
		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, produto_desenvolvedor.getId());
			
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
	public IDominio buscar(IDominio obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Produto_Desenvolvedor produto_desenvolvedor = (Produto_Desenvolvedor) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PRODUTOS_DESENVOLVEDOR\" ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaProdutosDesenvolvedor = new ArrayList<IDominio>();
			while (rs.next()) {
				produto_desenvolvedor = new Produto_Desenvolvedor();

				produto_desenvolvedor.setId(rs.getInt("id"));
				produto_desenvolvedor.setDescricao(rs.getString("descricao"));

				listaProdutosDesenvolvedor.add(produto_desenvolvedor);
			}

			return listaProdutosDesenvolvedor;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}
}
