package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto;
import br.edu.fatec.detoranja.dominio.Relatorio_Produtos;

public class Relatorio_ProdutosDAO implements IDAO {

	@Override
	public boolean salvar(IDominio obj) {
		return false;
	}

	@Override
	public boolean excluir(IDominio obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IDominio buscar(IDominio obj) {
		Relatorio_Produtos relatorio = (Relatorio_Produtos) obj;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Connection conn = null;

		String sql = "";

		if (relatorio.isFiltro()) {
			Calendar calendar = Calendar.getInstance();
			Calendar calendarFim = Calendar.getInstance();
			calendar.set(relatorio.getDatainicial().getYear(), relatorio.getDatainicial().getMonthValue(), 0);
			calendarFim.set(relatorio.getDatainicial().getYear(), relatorio.getDatainicial().getMonthValue(), 0);

			System.out.println((calendar.get(Calendar.MONTH) + 1) + " - " + relatorio.getDataFinal().getMonthValue()
					+ " - " + calendar.get(Calendar.YEAR) + " - " + relatorio.getDataFinal().getYear());
			sql = "SELECT PRODUTO.id, PRODUTO.titulo, ";
			while ((calendar.get(Calendar.MONTH) + 1) <= relatorio.getDataFinal().getMonthValue()
					&& calendar.get(Calendar.YEAR) == relatorio.getDataFinal().getYear() || calendar.get(Calendar.YEAR) < relatorio.getDataFinal().getYear()) {
				if ((calendar.get(Calendar.MONTH) + 1) == relatorio.getDatainicial().getMonthValue()
						&& calendar.get(Calendar.YEAR) == relatorio.getDatainicial().getYear()) {
					sql += "COALESCE((SELECT SUM(ITEM.quantidade) FROM  \"BD_PEDIDO\" PEDIDO LEFT JOIN \"BD_PEDIDO_PRODUTOS\" ITEM ON PEDIDO.id = ITEM.id_pedido WHERE PRODUTO.id = ITEM.id_produto AND PEDIDO.data_pedido >= '"
							+ relatorio.getDatainicial().format(formatter) + "' AND  PEDIDO.data_pedido <= '"
							+ new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()) + "'), 0) AS \""
							+ calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "\" ";
				} else if ((calendar.get(Calendar.MONTH) + 1) == relatorio.getDataFinal().getMonthValue()
						&& calendar.get(Calendar.YEAR) == relatorio.getDataFinal().getYear()) {
					sql += ", COALESCE((SELECT SUM(ITEM.quantidade) FROM  \"BD_PEDIDO\" PEDIDO LEFT JOIN \"BD_PEDIDO_PRODUTOS\" ITEM ON PEDIDO.id = ITEM.id_pedido WHERE PRODUTO.id = ITEM.id_produto AND PEDIDO.data_pedido >= '"
							+ new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())
							+ "' AND  PEDIDO.data_pedido <= '" + relatorio.getDataFinal().format(formatter)
							+ "'), 0) AS \"" + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "\" ";
				} else {
					sql += ", COALESCE((SELECT SUM(ITEM.quantidade) FROM  \"BD_PEDIDO\" PEDIDO LEFT JOIN \"BD_PEDIDO_PRODUTOS\" ITEM ON PEDIDO.id = ITEM.id_pedido WHERE PRODUTO.id = ITEM.id_produto AND PEDIDO.data_pedido >= '"
							+ new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())
							+ "' AND  PEDIDO.data_pedido <= '"
							+ new SimpleDateFormat("yyyy-MM-dd").format(calendarFim.getTime()) + "'), 0) AS \""
							+ calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "\" ";
				}
				calendar.add(Calendar.MONTH, 1);
				calendar.set(Calendar.DATE, 1);
				calendarFim.add(Calendar.MONTH, 1);
				calendarFim.set(Calendar.DATE, calendarFim.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
			sql += "FROM \"BD_PRODUTOS\" PRODUTO";
		}

		System.out.println(sql);

		try {
			conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<Map<String, List<Integer>>> listaProdutos = new ArrayList<Map<String, List<Integer>>>();

			while (rs.next()) {
				Calendar calendar = Calendar.getInstance();
				Calendar calendarFim = Calendar.getInstance();
				calendar.set(relatorio.getDatainicial().getYear(), relatorio.getDatainicial().getMonthValue(), 0);
				calendarFim.set(relatorio.getDatainicial().getYear(), relatorio.getDatainicial().getMonthValue(), 0);

				Map<String, List<Integer>> mapa = new HashMap<String, List<Integer>>();
				List<Integer> quantidades = new ArrayList<Integer>();

				Produto produto = new Produto();

				produto.setId(rs.getInt("id"));
				String titulo = rs.getString("titulo");

				while ((calendar.get(Calendar.MONTH) + 1) <= relatorio.getDataFinal().getMonthValue()
						&& calendar.get(Calendar.YEAR) == relatorio.getDataFinal().getYear() || calendar.get(Calendar.YEAR) < relatorio.getDataFinal().getYear()) {

					quantidades.add(rs.getInt(calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH)));

					calendar.add(Calendar.MONTH, 1);
					calendar.set(Calendar.DATE, 1);
					calendarFim.add(Calendar.MONTH, 1);
					calendarFim.set(Calendar.DATE, calendarFim.getActualMaximum(Calendar.DAY_OF_MONTH));
				}

				mapa.put(titulo, quantidades);
				listaProdutos.add(mapa);

			}

			relatorio.setListaProdutos(listaProdutos);

			return relatorio;
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
