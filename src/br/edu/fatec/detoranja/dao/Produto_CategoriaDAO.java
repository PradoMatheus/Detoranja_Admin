package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto_Categoria;

public class Produto_CategoriaDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Produto_Categoria produto_categoria = (Produto_Categoria) obj;

		Connection conn = null;
		String sql = null;
		
		if(produto_categoria.getId() < 1)
			sql = "INSERT INTO \"BD_CATEGORIAS\" (descricao) VALUES (?);";
		else
			sql = "UPDATE \"BD_CATEGORIAS\" SET descricao = ? WHERE id = " + produto_categoria.getId() + ";";
			
		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, produto_categoria.getDescricao());
			
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
		Produto_Categoria produto_categoria = (Produto_Categoria) obj;

		Connection conn = null;
		String sql = "DELETE FROM \"BD_CATEGORIAS\" WHERE id = ?;";

			
		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, produto_categoria.getId());
			
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

		Produto_Categoria produto_categoria = (Produto_Categoria) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_CATEGORIAS\" ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaProdutosCategorias = new ArrayList<IDominio>();
			while (rs.next()) {
				produto_categoria = new Produto_Categoria();
				
				produto_categoria.setId(rs.getInt("id"));
				produto_categoria.setDescricao(rs.getString("descricao"));
				
				listaProdutosCategorias.add(produto_categoria);
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
