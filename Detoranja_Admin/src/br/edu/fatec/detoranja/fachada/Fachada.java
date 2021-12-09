package br.edu.fatec.detoranja.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.detoranja.dao.AdministradorDAO;
import br.edu.fatec.detoranja.dao.ClienteDAO;
import br.edu.fatec.detoranja.dao.CompraDAO;
import br.edu.fatec.detoranja.dao.CupomDAO;
import br.edu.fatec.detoranja.dao.Estoque_MovimentosDAO;
import br.edu.fatec.detoranja.dao.FornecedorDAO;
import br.edu.fatec.detoranja.dao.IDAO;
import br.edu.fatec.detoranja.dao.PedidoDAO;
import br.edu.fatec.detoranja.dao.Pedido_StatusDAO;
import br.edu.fatec.detoranja.dao.ProdutoDAO;
import br.edu.fatec.detoranja.dao.Produto_CategoriaDAO;
import br.edu.fatec.detoranja.dao.Produto_DesenvolvedorDAO;
import br.edu.fatec.detoranja.dao.Produto_DistribuidorDAO;
import br.edu.fatec.detoranja.dao.Produto_PlataformaDao;
import br.edu.fatec.detoranja.dao.Relatorio_ProdutosDAO;
import br.edu.fatec.detoranja.dao.TrocaAdminDAO;
import br.edu.fatec.detoranja.dao.Troca_StatusAdminDAO;
import br.edu.fatec.detoranja.dominio.Administrador;
import br.edu.fatec.detoranja.dominio.Cliente;
import br.edu.fatec.detoranja.dominio.Compra;
import br.edu.fatec.detoranja.dominio.Cupom;
import br.edu.fatec.detoranja.dominio.Estoque_Movimentacao;
import br.edu.fatec.detoranja.dominio.Fornecedor;
import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.dominio.Pedido;
import br.edu.fatec.detoranja.dominio.Pedido_Status;
import br.edu.fatec.detoranja.dominio.Produto;
import br.edu.fatec.detoranja.dominio.Produto_Categoria;
import br.edu.fatec.detoranja.dominio.Produto_Desenvolvedor;
import br.edu.fatec.detoranja.dominio.Produto_Distribuidor;
import br.edu.fatec.detoranja.dominio.Produto_Plataforma;
import br.edu.fatec.detoranja.dominio.Relatorio_Produtos;
import br.edu.fatec.detoranja.dominio.Troca_Admin;
import br.edu.fatec.detoranja.dominio.Troca_StatusAdmin;
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
		Produto_PlataformaDao produto_plataformaDAO = new Produto_PlataformaDao();
		ClienteDAO clienteDAO = new ClienteDAO();
		CupomDAO cupomDAO = new CupomDAO();
		PedidoDAO pedidoDAO = new PedidoDAO();
		Pedido_StatusDAO pedido_StatusDAO = new Pedido_StatusDAO();
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		CompraDAO compraDAO = new CompraDAO();
		Estoque_MovimentosDAO estoque_MovimentosDAO = new Estoque_MovimentosDAO();
		TrocaAdminDAO trocaAdminDAO = new TrocaAdminDAO();
		Troca_StatusAdminDAO statusAdminDAO = new Troca_StatusAdminDAO();
		Relatorio_ProdutosDAO relatorio_ProdutosDAO = new Relatorio_ProdutosDAO();

		// VINCULANDO AS DAOS AS SUAS CLASSES
		daos.put(Administrador.class.getName(), admDao);
		daos.put(Produto.class.getName(), produtoDAO);
		daos.put(Produto_Categoria.class.getName(), produto_categoriaDAO);
		daos.put(Produto_Desenvolvedor.class.getName(), produto_desenvolvedorDAO);
		daos.put(Produto_Distribuidor.class.getName(), produto_distribuidorDAO);
		daos.put(Produto_Plataforma.class.getName(), produto_plataformaDAO);
		daos.put(Cliente.class.getName(), clienteDAO);
		daos.put(Cupom.class.getName(), cupomDAO);
		daos.put(Pedido.class.getName(), pedidoDAO);
		daos.put(Pedido_Status.class.getName(), pedido_StatusDAO);
		daos.put(Fornecedor.class.getName(), fornecedorDAO);
		daos.put(Compra.class.getName(), compraDAO);
		daos.put(Estoque_Movimentacao.class.getName(), estoque_MovimentosDAO);
		daos.put(Troca_Admin.class.getName(), trocaAdminDAO);
		daos.put(Troca_StatusAdmin.class.getName(), statusAdminDAO);
		daos.put(Relatorio_Produtos.class.getName(), relatorio_ProdutosDAO);

		// LISTA DE REGRA DE NEGOCIO
		// // ESTOQUE MOVIMENTAÇÃO
		List<IStrategy> rnSalvarMovimentacao = new ArrayList<IStrategy>();
		List<IStrategy> rnBuscarMovimentacao = new ArrayList<IStrategy>();
		List<IStrategy> rnListaMovimentacao = new ArrayList<IStrategy>();
		List<IStrategy> rnExcluirMovimentacao = new ArrayList<IStrategy>();
		// // COMPRA
		List<IStrategy> rnSalvarCompra = new ArrayList<IStrategy>();
		List<IStrategy> rnBuscarCompra = new ArrayList<IStrategy>();
		List<IStrategy> rnListaCompra = new ArrayList<IStrategy>();
		List<IStrategy> rnExcluirCompra = new ArrayList<IStrategy>();
		// // FORNECEDOR
		List<IStrategy> rnSalvarFornecedor = new ArrayList<IStrategy>();
		List<IStrategy> rnBuscarFornecedor = new ArrayList<IStrategy>();
		List<IStrategy> rnListaFornecedor = new ArrayList<IStrategy>();
		List<IStrategy> rnExcluirFornecedor = new ArrayList<IStrategy>();
		// // TROCA
		List<IStrategy> rnSalvarTroca = new ArrayList<IStrategy>();
		List<IStrategy> rnBuscarTroca = new ArrayList<IStrategy>();
		List<IStrategy> rnListaTroca = new ArrayList<IStrategy>();
		List<IStrategy> rnExcluirTroca = new ArrayList<IStrategy>();
		// // TROCA STATUS
		List<IStrategy> rnSalvarTrocaStatus = new ArrayList<IStrategy>();
		List<IStrategy> rnBuscarTrocaStatus = new ArrayList<IStrategy>();
		List<IStrategy> rnListaTrocaStatus = new ArrayList<IStrategy>();
		List<IStrategy> rnExcluirTrocaStatus = new ArrayList<IStrategy>();
		// // RELATORIO PRODUTOS
		List<IStrategy> rnSalvarRelatorioProdutos = new ArrayList<IStrategy>();
		List<IStrategy> rnBuscarRelatorioProdutos = new ArrayList<IStrategy>();
		List<IStrategy> rnListaRelatorioProdutos = new ArrayList<IStrategy>();
		List<IStrategy> rnExcluirRelatorioProdutos = new ArrayList<IStrategy>();
		// // FUNCIONARIO
		List<IStrategy> salvarfun = new ArrayList<IStrategy>();
		List<IStrategy> editarfun = new ArrayList<IStrategy>();

		// LISTA REGRA DE NEGOCIO DE CADA CLASSE
		// // ESTOQUE MOVIMENTACAO
		Map<String, List<IStrategy>> rnsmovimentacao = new HashMap<String, List<IStrategy>>();
		rnsmovimentacao.put("Salvar", rnSalvarMovimentacao);
		rnsmovimentacao.put("Buscar", rnBuscarMovimentacao);
		rnsmovimentacao.put("Lista", rnListaMovimentacao);
		rnsmovimentacao.put("Excluir", rnExcluirMovimentacao);
		// // COMPRA
		Map<String, List<IStrategy>> rnscompra = new HashMap<String, List<IStrategy>>();
		rnscompra.put("Salvar", rnSalvarCompra);
		rnscompra.put("Buscar", rnBuscarCompra);
		rnscompra.put("Lista", rnListaCompra);
		rnscompra.put("Excluir", rnExcluirCompra);
		// // FORNECEDOR
		Map<String, List<IStrategy>> rnsfornecedor = new HashMap<String, List<IStrategy>>();
		rnsfornecedor.put("Salvar", rnSalvarFornecedor);
		rnsfornecedor.put("Buscar", rnBuscarFornecedor);
		rnsfornecedor.put("Lista", rnListaFornecedor);
		rnsfornecedor.put("Excluir", rnExcluirFornecedor);
		// // TROCA
		Map<String, List<IStrategy>> rnstroca = new HashMap<String, List<IStrategy>>();
		rnstroca.put("Salvar", rnSalvarTroca);
		rnstroca.put("Buscar", rnBuscarTroca);
		rnstroca.put("Lista", rnListaTroca);
		rnstroca.put("Excluir", rnExcluirTroca);
		// // TROCA STATUS
		Map<String, List<IStrategy>> rnstrocastatus = new HashMap<String, List<IStrategy>>();
		rnstrocastatus.put("Salvar", rnSalvarTrocaStatus);
		rnstrocastatus.put("Buscar", rnBuscarTrocaStatus);
		rnstrocastatus.put("Lista", rnListaTrocaStatus);
		rnstrocastatus.put("Excluir", rnExcluirTrocaStatus);
		// // TROCA STATUS
		Map<String, List<IStrategy>> rnsrelatorioprodutos = new HashMap<String, List<IStrategy>>();
		rnsrelatorioprodutos.put("Salvar", rnSalvarRelatorioProdutos);
		rnsrelatorioprodutos.put("Buscar", rnBuscarRelatorioProdutos);
		rnsrelatorioprodutos.put("Lista", rnListaRelatorioProdutos);
		rnsrelatorioprodutos.put("Excluir", rnExcluirRelatorioProdutos);
		// // FUNCIONARIO
		Map<String, List<IStrategy>> rnfuncionario = new HashMap<String, List<IStrategy>>();
		rnfuncionario.put("Salvar", salvarfun);
		rnfuncionario.put("Editar", editarfun);

		// ADICINADO AS LISTA DE REGRAS DE NEGOCIOS
		rns.put(Compra.class.getName(), rnscompra);
		rns.put(Fornecedor.class.getName(), rnsfornecedor);
		rns.put(Administrador.class.getName(), rnfuncionario);
		rns.put(Estoque_Movimentacao.class.getName(), rnsmovimentacao);
		rns.put(Troca_Admin.class.getName(), rnstroca);
		rns.put(Troca_StatusAdmin.class.getName(), rnstrocastatus);
		rns.put(Relatorio_Produtos.class.getName(), rnsrelatorioprodutos);
	}

	@Override
	public Resultado salvar(IDominio dominio) {
		IDAO idao = daos.get(dominio.getClass().getName());

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
	public Resultado excluir(IDominio dominio) {
		IDAO idao = daos.get(dominio.getClass().getName());

		if (resultado.getMsg() == null) {
			idao.excluir(dominio);
		}

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

	@Override
	public Resultado sair(IDominio dominio) {
		return resultado;
	}

}
