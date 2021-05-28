package br.edu.fatec.detoranja.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.detoranja.dao.IDAO;
import br.edu.fatec.detoranja.dominio.IDominio;
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
		executarStrategys(dominio, rns.get(dominio.getClass().getName()).get("Salvar"));
		if (resultado.getMsg() == null) {
			idao.cadastrar(dominio);
		}
		resultado.setListDominio(idao.listar());
		return resultado;
	}

	public Resultado buscar(IDominio dominio) {
		IDAO idao = daos.get(dominio.getClass().getName());
		resultado.setListDominio(idao.listar());

		if (((Integer) dominio.getId()) != null) {
			resultado.setDominio(idao.buscar(dominio));
		}
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
