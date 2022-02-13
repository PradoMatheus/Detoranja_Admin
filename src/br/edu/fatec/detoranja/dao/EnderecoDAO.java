package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Endereco;
import br.edu.fatec.detoranja.dominio.Endereco_Tipo_Logradouro;
import br.edu.fatec.detoranja.dominio.IDominio;

public class EnderecoDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		return false;
	}

	@Override
	public boolean excluir(IDominio obj) {
		return false;
	}

	@Override
	public IDominio buscar(IDominio obj) {
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {

		Endereco endereco = (Endereco) obj;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_CLIENTES_ENDERECO\" WHERE id_cliente=" + endereco.getId_cliente()
				+ " ORDER BY id;";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaEndereco = new ArrayList<IDominio>();
			while (rs.next()) {
				endereco = new Endereco();

				endereco.setId(rs.getInt("id"));
				endereco.setId_cliente(rs.getInt("id_cliente"));
				endereco.setCep(rs.getString("cep"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setPais(rs.getString("pais"));

				Endereco_Tipo_Logradouro endereco_Tipo_Logradouro = new Endereco_Tipo_Logradouro();
				endereco_Tipo_Logradouro.setId(rs.getInt("id_tipo_logradouro"));
				Endereco_Tipo_LogradouroDAO endereco_tipo_logradouroDAO = new Endereco_Tipo_LogradouroDAO();
				endereco.setTipo_logradouro(
						(Endereco_Tipo_Logradouro) endereco_tipo_logradouroDAO.buscar(endereco_Tipo_Logradouro));

				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setDescricao(rs.getString("nome_endereco"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setReferencia(rs.getString("referencia"));

				listaEndereco.add(endereco);
			}

			return listaEndereco;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

}
