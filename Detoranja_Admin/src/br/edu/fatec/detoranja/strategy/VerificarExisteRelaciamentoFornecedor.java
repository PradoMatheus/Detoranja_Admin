package br.edu.fatec.detoranja.strategy;

import java.util.Map;

import br.edu.fatec.detoranja.dominio.IDominio;
import br.edu.fatec.detoranja.vh.AdministradorVh;
import br.edu.fatec.detoranja.vh.ClienteVh;
import br.edu.fatec.detoranja.vh.CompraVh;
import br.edu.fatec.detoranja.vh.CupomVh;
import br.edu.fatec.detoranja.vh.Estoque_MovimentosVh;
import br.edu.fatec.detoranja.vh.FornecedorVh;
import br.edu.fatec.detoranja.vh.IViewHelper;
import br.edu.fatec.detoranja.vh.PedidoVh;
import br.edu.fatec.detoranja.vh.Pedido_StatusVh;
import br.edu.fatec.detoranja.vh.ProdutoVh;
import br.edu.fatec.detoranja.vh.Produto_CategoriaVh;
import br.edu.fatec.detoranja.vh.Produto_DesenvolvedorVh;
import br.edu.fatec.detoranja.vh.Produto_DistribuidorVh;
import br.edu.fatec.detoranja.vh.Produto_PlataformaVh;
import br.edu.fatec.detoranja.vh.Relatorio_ProdutosVh;
import br.edu.fatec.detoranja.vh.Troca_AdminVh;
import br.edu.fatec.detoranja.vh.Troca_Status_AdminVh;

public class VerificarExisteRelaciamentoFornecedor implements IStrategy {
	
	private Map<String, IViewHelper> mapavh;

	@Override
	public String processar(IDominio dominio) {
		mapavh.put("/Detoranja_Admin/administrador", new AdministradorVh());
		mapavh.put("/Detoranja_Admin/produto_categoria", new Produto_CategoriaVh());
		mapavh.put("/Detoranja_Admin/produto_desenvolvedor", new Produto_DesenvolvedorVh());
		mapavh.put("/Detoranja_Admin/produto_distribuidor", new Produto_DistribuidorVh());
		mapavh.put("/Detoranja_Admin/produto_plataforma", new Produto_PlataformaVh());
		mapavh.put("/Detoranja_Admin/produto", new ProdutoVh());
		mapavh.put("/Detoranja_Admin/cliente", new ClienteVh());
		mapavh.put("/Detoranja_Admin/cupom_admin", new CupomVh());
		mapavh.put("/Detoranja_Admin/pedido", new PedidoVh());
		mapavh.put("/Detoranja_Admin/status_pedido", new Pedido_StatusVh());
		mapavh.put("/Detoranja_Admin/fornecedor", new FornecedorVh());
		mapavh.put("/Detoranja_Admin/compra", new CompraVh());
		mapavh.put("/Detoranja_Admin/movimentacao", new Estoque_MovimentosVh());
		mapavh.put("/Detoranja_Admin/troca_admin", new Troca_AdminVh());
		mapavh.put("/Detoranja_Admin/troca_statusadmin", new Troca_Status_AdminVh());
		mapavh.put("/Detoranja_Admin/relatorios_produto", new Relatorio_ProdutosVh());
		
		return null;
	}

}
