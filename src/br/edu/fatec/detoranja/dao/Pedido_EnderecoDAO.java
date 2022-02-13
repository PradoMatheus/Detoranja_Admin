package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Endereco;
import br.edu.fatec.detoranja.dominio.Endereco_Tipo_Logradouro;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido;

public class Pedido_EnderecoDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Pedido pedido = (Pedido) obj;

		String sql = "INSERT INTO \"BD_PEDIDO_ENDERECO\" "
				+ "(id_cliente, cep, estado, cidade, pais, id_tipo_logradouro, logradouro, numero, bairro, nome_endereco, complemento, referencia, \"id_Pedido\") "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pedido.getEndereco().getId_cliente());
			pstm.setInt(2, Integer.parseInt(pedido.getEndereco().getCep()));
			pstm.setString(3, pedido.getEndereco().getEstado());
			pstm.setString(4, pedido.getEndereco().getCidade());
			pstm.setString(5, pedido.getEndereco().getPais());
			pstm.setInt(6, pedido.getEndereco().getTipo_logradouro().getId());
			pstm.setString(7, pedido.getEndereco().getLogradouro());
			pstm.setString(8, pedido.getEndereco().getNumero());
			pstm.setString(9, pedido.getEndereco().getBairro());
			pstm.setString(10, pedido.getEndereco().getDescricao());
			pstm.setString(11, pedido.getEndereco().getComplemento());
			pstm.setString(12, pedido.getEndereco().getReferencia());
			pstm.setInt(13, pedido.getId());
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
		Endereco endereco = new Endereco();
		
		Connection conn = null;

		String sql = "SELECT * FROM \"BD_PEDIDO_ENDERECO\" WHERE \"id_Pedido\"=" + pedido.getId() + ";";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

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
				endereco.setTipo_logradouro(endereco_Tipo_Logradouro);
				
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setDescricao(rs.getString("nome_endereco"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setReferencia(rs.getString("referencia"));
			}

			return endereco;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}
		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
