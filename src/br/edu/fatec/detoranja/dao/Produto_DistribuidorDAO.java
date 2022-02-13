package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto_Distribuidor;

public class Produto_DistribuidorDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Produto_Distribuidor produto_distribuidor = (Produto_Distribuidor) obj;

		Connection conn = null;
		String sql = null;
		
		if(produto_distribuidor.getId() < 1)
			sql = "INSERT INTO \"BD_PRODUTOS_DISTRIBUIDOR\" (descricao) VALUES (?);";
		else
			sql = "UPDATE \"BD_PRODUTOS_DISTRIBUIDOR\" SET descricao = ? WHERE id = " + produto_distribuidor.getId() + ";";
			
		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, produto_distribuidor.getDescricao());
			
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
		Produto_Distribuidor produto_distribuidor = (Produto_Distribuidor) obj;

		Connection conn = null;
		String sql = "DELETE FROM \"BD_PRODUTOS_DISTRIBUIDOR\" WHERE id = ?;";

			
		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, produto_distribuidor.getId());
			
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
		Produto_Distribuidor produto_distribuidor = (Produto_Distribuidor) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PRODUTOS_DISTRIBUIDOR\" ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaProdutosDistribuidor = new ArrayList<IDominio>();
			while (rs.next()) {
				produto_distribuidor = new Produto_Distribuidor();
				
				produto_distribuidor.setId(rs.getInt("id"));
				produto_distribuidor.setDescricao(rs.getString("descricao"));
				
				listaProdutosDistribuidor.add(produto_distribuidor);
			}

			return listaProdutosDistribuidor;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}
}
