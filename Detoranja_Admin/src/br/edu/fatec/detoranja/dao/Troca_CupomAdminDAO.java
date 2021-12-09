package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import br.edu.fatec.detoranja.dominio.Cliente;
import br.edu.fatec.detoranja.dominio.Cupom;
import br.edu.fatec.detoranja.dominio.Cupom_Tipo;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Troca_Admin;

public class Troca_CupomAdminDAO implements IDAO {

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
		Troca_Admin troca = (Troca_Admin) obj;
		Cupom cupom;

		Connection conn = null;

		String sql = "SELECT * FROM \"BD_CUPONS\" WHERE troca = '" + troca.getId() + "';";

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				cupom = new Cupom();

				cupom.setId(rs.getInt("id"));
				cupom.setAtivo(rs.getBoolean("ativo"));
				cupom.setDesconto(rs.getDouble("desconto"));
				cupom.setValor_minimo(rs.getDouble("valor_minimo"));
				cupom.setValidade(rs.getObject("validade", LocalDate.class));
				cupom.setDesc_cupom(rs.getString("cod_cupom"));

				Cliente cliente = new Cliente();
				if (rs.getObject("cliente") != null || rs.getInt("cliente") != 0)
					cliente.setId(rs.getInt("cliente"));
				cupom.setCliente(cliente);

				Cupom_Tipo tipo = new Cupom_Tipo();
				if (rs.getObject("tipo") != null || rs.getInt("tipo") != 0)
					tipo.setId(rs.getInt("tipo"));
				cupom.setTipo(tipo);

				return cupom;
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
		return null;
	}

}
