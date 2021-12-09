package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Endereco_Tipo_Logradouro;
import br.edu.fatec.detoranja.dominio.IDominio;

public class Endereco_Tipo_LogradouroDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(IDominio obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IDominio buscar(IDominio obj) {
		Endereco_Tipo_Logradouro tipo_logradouro = (Endereco_Tipo_Logradouro) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_TIPO_LOGRADOURO\" WHERE id = " + tipo_logradouro.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				tipo_logradouro = new Endereco_Tipo_Logradouro();

				tipo_logradouro.setId(rs.getInt("id"));
				tipo_logradouro.setDescricao(rs.getString("descricao"));
			}

			return tipo_logradouro;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		return null;
	}

}
