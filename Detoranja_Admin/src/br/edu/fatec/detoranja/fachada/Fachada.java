package br.edu.fatec.detoranja.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.detoranja.dao.AdministradorDAO;
import br.edu.fatec.detoranja.dao.IDAO;
import br.edu.fatec.detoranja.dao.ProdutoDAO;
import br.edu.fatec.detoranja.dao.Produto_CategoriaDAO;
import br.edu.fatec.detoranja.dao.Produto_DesenvolvedorDAO;
import br.edu.fatec.detoranja.dao.Produto_DistribuidorDAO;
import br.edu.fatec.detoranja.dominio.Administrador;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Produto;
import br.edu.fatec.detoranja.dominio.Produto_Categoria;
import br.edu.fatec.detoranja.dominio.Produto_Desenvolvedor;
import br.edu.fatec.detoranja.dominio.Produto_Distribuidor;
import br.edu.fatec.detoranja.strategy.IStrategy;
import br.edu.fatec.detoranja.util.Resultado;

public class Fachada implements IFachada {

	private Map<String, IDAO> daos;

	private Map<String, Map<String, List<IStrategy>>> rns;

	private Resultado resultado;

	public Fachada() {
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		resultado = new Resultado();

		AdministradorDAO admDao = new AdministradorDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto_CategoriaDAO produto_categoriaDAO = new Produto_CategoriaDAO();
		Produto_DesenvolvedorDAO produto_desenvolvedorDAO = new Produto_DesenvolvedorDAO();
		Produto_DistribuidorDAO produto_distribuidorDAO = new Produto_DistribuidorDAO();

		// VINCULANDO AS DAOS AS SUAS CLASSES
		daos.put(Administrador.class.getName(), admDao);
		daos.put(Produto.class.getName(), produtoDAO);
		daos.put(Produto_Categoria.class.getName(), produto_categoriaDAO);
		daos.put(Produto_Desenvolvedor.class.getName(), produto_desenvolvedorDAO);
		daos.put(Produto_Distribuidor.class.getName(), produto_distribuidorDAO);

		// --------Regra de Negocio

		// -- Regra para salvar funcionario
		List<IStrategy> salvarfun = new ArrayList<IStrategy>();
		// -- Regra para editar funcionario
		List<IStrategy> editarfun = new ArrayList<IStrategy>();

		// Contem todas as regras de negocio de funcionario
		Map<String, List<IStrategy>> rnfuncionario = new HashMap<String, List<IStrategy>>();

		rnfuncionario.put("Salvar", salvarfun);
		rnfuncionario.put("Editar", editarfun);
	}

	@Override
	public Resultado salvar(IDominio dominio) {
		IDAO idao = daos.get(dominio.getClass().getName());
		// executarStrategys(dominio,
		// rns.get(dominio.getClass().getName()).get("Salvar"));
		if (resultado.getMsg() == null) {
			idao.salvar(dominio);
		}
		return resultado;
	}

	public Resultado buscar(IDominio dominio) {
		IDAO idao = daos.get(dominio.getClass().getName());

		resultado.setDominio(idao.buscar(dominio));

		return resultado;
	}

	@Override
	public Resultado lista(IDominio dominio) {
		IDAO idao = daos.get(dominio.getClass().getName());

		resultado.setListDominio(idao.lista(dominio));

		return resultado;
	}

	private void executarStrategys(IDominio entidade, List<IStrategy> strategys) {
		for (IStrategy str : strategys) {
			String mensagem = str.processar(entidade);
			if (mensagem != null) {
				System.out.println(mensagem);
				resultado.setMsg(mensagem);
				return;
			}
		}
		return;
	}
}
