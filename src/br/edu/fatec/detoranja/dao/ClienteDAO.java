package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Cliente;
import br.edu.fatec.detoranja.dominio.Cliente_Genero;
import br.edu.fatec.detoranja.dominio.Endereco;
import br.edu.fatec.detoranja.dominio.Forma_Pagamento;
import br.edu.fatec.detoranja.dominio.IDominio;

public class ClienteDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		Cliente cliente = (Cliente) obj;

		String sql = "UPDATE \"BD_CLIENTES\" " + "SET ativo = ? " + "WHERE id = " + cliente.getId() + ";";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setBoolean(1, cliente.getAtivo());
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
		Cliente cliente = (Cliente) obj;

		String sql = "SELECT *, (SELECT descricao FROM \"BD_CLIENTES_GENERO\" WHERE id = \"BD_CLIENTES\".id_genero) AS genero FROM \"BD_CLIENTES\" WHERE id = " + cliente.getId() + ";";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				cliente.setId(rs.getInt("id"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setData_nascimento(rs.getObject("data_nascimento", LocalDate.class));
				cliente.setEmail(rs.getString("email"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setAtivo(rs.getBoolean("ativo"));
				cliente.setTelefone(rs.getLong("telefone"));
				
				Cliente_Genero genero = new Cliente_Genero();
				genero.setDescricao(rs.getString("genero"));
				cliente.setGenero(genero);

				EnderecoDAO enderecoDAO = new EnderecoDAO();
				Endereco endereco = new Endereco();
				endereco.setId_cliente(cliente.getId());
				List<Endereco> listaEnderecos = new ArrayList<Endereco>();
				for (IDominio d : enderecoDAO.lista(endereco)) {
					listaEnderecos.add((Endereco) d);
				}
				cliente.setEnderecos(listaEnderecos);

				Forma_PagamentoDAO formaDAO = new Forma_PagamentoDAO();
				Forma_Pagamento forma = new Forma_Pagamento();
				forma.setCliente(cliente);
				List<Forma_Pagamento> listaForma = new ArrayList<Forma_Pagamento>();
				for (IDominio d : formaDAO.lista(forma)) {
					listaForma.add((Forma_Pagamento) d);
				}
				cliente.setFormas(listaForma);

			}

			return cliente;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}

		return null;
	}

	@Override
	public List<IDominio> lista(IDominio obj) {
		Cliente cliente = (Cliente) obj;

		String sql = "SELECT * FROM \"BD_CLIENTES\" ORDER BY id;";

		Connection conn = null;

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<IDominio> listaClientes = new ArrayList<IDominio>();
			while (rs.next()) {
				cliente = new Cliente();

				cliente.setId(rs.getInt("id"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setData_nascimento(rs.getObject("data_nascimento", LocalDate.class));
				cliente.setEmail(rs.getString("email"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setAtivo(rs.getBoolean("ativo"));
				cliente.setTelefone(rs.getLong("telefone"));

				listaClientes.add(cliente);
			}

			return listaClientes;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			Conexao.fechar(conn);
		}

		return null;
	}

}
