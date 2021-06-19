package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto;

public class ProdutoDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Produto produto = (Produto) obj;

		String sql = "INSERT INTO \"BD_PRODUTOS\" "
				+ "(nome, valor, desenvolvedor, data_lancamento, distribuidora, data_cadastro, data_alteracao, plataforma, descricao) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, produto.getNome());
			pstm.setDouble(2, produto.getValor());
			pstm.setString(3, produto.getDesenvolvedor());
			pstm.setDate(4, Date.valueOf(produto.getData_lancamento()));
			pstm.setString(5, produto.getDistribuidora());
			pstm.setDate(6, Date.valueOf(produto.getData_cadastro().toLocalDate()));
			pstm.setDate(7, Date.valueOf(produto.getData_alteracao().toLocalDate()));
			pstm.setString(8, produto.getPlataforma());
			pstm.setString(9, produto.getDescricao());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Produto produto = (Produto) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PRODUTOS\";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaProdutos = new ArrayList<IDominio>();
			while (rs.next()) {
				produto = new Produto();
				
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setDesenvolvedor(rs.getString("desenvolvedor"));
				produto.setDistribuidora(rs.getString("distribuidora"));
				produto.setData_alteracao(rs.getObject("data_alteracao", LocalDateTime.class));
				produto.setData_cadastro(rs.getObject("data_cadastro", LocalDateTime.class));
				produto.setData_lancamento(rs.getObject("data_lancamento", LocalDate.class));
				produto.setPlataforma(rs.getString("Plataforma"));
				produto.setValor(rs.getDouble("valor"));
				
				listaProdutos.add(produto);
			}
			
			return listaProdutos;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally{
			Conexao.fechar(conn);
		}
		return null;
	}

}
