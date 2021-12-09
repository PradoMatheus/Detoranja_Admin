<%@page import="br.edu.fatec.detoranja.dominio.Estoque_Movimentacao"%>
<%@page import="br.edu.fatec.detoranja.dominio.Estoque"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.edu.fatec.detoranja.dominio.Produto"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Administrador Entradas e Saídas</title>
<meta charset="UTF-8">
<%@ include file="complements/complements_css.jsp"%>
</head>
<body>

	<%@ include file="complements/navbar.jsp"%>

	<%
	List<Estoque_Movimentacao> listaMovimentacoes = (List<Estoque_Movimentacao>) request.getAttribute("listaMovimentacoes");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	%>

	<div class="container-fluid px-4">
		<h1 class="mt-4">Lista de Movimentações</h1>
		<div class="card mb-4">
			<div class="card-header">
				<button type="button" id="btnNovo" class="btn btn-primary">Adicionar Movimentação</button>
			</div>
			<div class="card-body">
				<table id="datatablesSimple">
					<thead>
						<tr>
							<th class="colDev mx-auto" style="width: 5%" scope="col">ID</th>
							<th scope="col" style="width: 40%">Produto</th>
							<th scope="col" style="width: 10%;vertical-align: middle; text-align: center">Quantidade</th>
							<th scope="col" style="width: 10%;vertical-align: middle; text-align: center">Tipo</th>
							<th scope="col" style="width: 25%;vertical-align: middle; text-align: center">Data</th>
							<th class="colDev mx-auto" scope="col" style="width: 10%">Consultar</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (listaMovimentacoes != null && listaMovimentacoes.size() > 0) {
							for (Estoque_Movimentacao movimentacao : listaMovimentacoes) {
								out.print("<th style='vertical-align: middle; text-align: center'class='colDev mx-auto' style='width: 5%' scope='row'>" + movimentacao.getId() + "</th>");
								out.print("<td class='colDev' style='width: 40%' scope='row'>" + movimentacao.getProduto().getNome() + "</td>");
								out.print("<td style='width: 10%;vertical-align: middle; text-align: center'>" + movimentacao.getQuantidade() + "</td>");
								if(!movimentacao.isTipo())
									out.print("<td style='width: 10%;vertical-align: middle; text-align: center'><i class='fas fa-plus-square fa-2x'></i></td>");
								else
									out.print("<td style='width: 10%;vertical-align: middle; text-align: center'><i class='fas fa-minus-square fa-2x'></i></td>");
								out.print("<td style='width: 25%;vertical-align: middle; text-align: center'>" + movimentacao.getData_movimentacao().format(formatter) + "</td>");
								out.print(
								"<td class='colDev mx-auto' style='width: 10%'><button type='button' class='btn btn-outline-danger editarMovimentacao' value='"
										+ movimentacao.getId() + "'>Consultar</button></td>");
								out.print("</tr>");
							}
						} else {
							out.print("<tr>");
							out.print("<td style='text-align: center' colspan='5'>Sem Movimentações Realizadas!</td>");
							out.print("</tr>");
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<!-- MODAL DE MOVIMENTO -->
	<div class="modal fade" id="movimentosModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered"
			role="document">
			<div class="modal-content">
				<form action="" id="formCompra">
				<div class="modal-header">
					<h5 class="modal-title">
						<strong id="labelAlteracao"></strong>
					</h5>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="txtId" class="col-form-label">ID</label> <input
									type="text" class="form-control" id="txtId" name="txtId"
									readonly>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 mb-3">
								<label for="txtProduto" class="col-form-label">ID</label> <input
									type="text" class="form-control" id="txtProduto"
									name="txtProduto" required="required" value="0">
							</div>
							<div class="col-md-10 mb-3">
								<label for="txtProdutoDesc" class="col-form-label">Produto</label>
								<input type="text" class="form-control" id="txtProdutoDesc"
									name="txtProdutoDesc" value="Sem Produto Adicionado !!" disabled="disabled">
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 mb-3">
								<label for="txtQuantidade" class="col-form-label">Qtde</label> <input
									type="text" class="form-control" id="txtQuantidade"
									name="txtQuantidade" required="required" value="0">
							</div>
							<div class="col-md-4 mb-3">
								<label for="txtTipo" class="col-form-label">Tipo do Mov.</label>
								<select class="form-select form-select" id="txtTipo" name="txtTipo">
									<option value='' disabled>Selecione...</option>
									<option value=0>Entrada</option>
									<option value=1>Saida</option>
								</select>
							</div>
							<div class="col-md-6 mb-3">
								<label for="txtData" class="col-form-label">Data do Movimento</label>
								<input type="datetime-local" class="form-control" id="txtData"
									name="txtData">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 mb-3">
								<label for="txtObs" class="col-form-label">Observação</label> <input
									type="text" class="form-control" id="txtObs"
									name="txtObs">
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 mb-3">
								<label for="txtCompra" class="col-form-label">ID Compra</label> <input
									type="text" class="form-control" id="txtCompra"
									name="txtCompra" disabled="disabled">
							</div>
							<div class="col-md-4 mb-3">
								<label for="txtPedido" class="col-form-label">ID Pedido</label> <input
									type="text" class="form-control" id="txtPedido"
									name="txtPedido" disabled="disabled">
							</div>
							<div class="col-md-4 mb-3">
								<label for="txtPedido" class="col-form-label">ID Troca</label> <input
									type="text" class="form-control" id="txtTroca"
									name="txtTroca" disabled="disabled">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal" id="btnCancelar">Fechar</button>
						<button type="submit" class="btn btn-primary" id="btnSalvar">Salvar</button>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- MODAL DE CARREGAMENTO -->
	<div class="modal fade" id="modalCarregamento" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header" style="text-align: center;">
	      <strong style="margin-left: 40%">Carregando...</strong>
	      </div>
	      <div class="modal-body">
			<div class="d-flex justify-content-center">
			  <div class="spinner-border" role="status">
			    <span class="sr-only">Loading...</span>
			  </div>
			</div>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- JS -->
	<%@ include file="complements/complements_js.jsp"%>

	<script type="text/javascript">
	
	// FECHAR O MODAL AO CLICAR NO BOTÃO CANCELAR
	$("#btnCancelar").click(function() {
		$('#movimentosModal').modal('hide');
	});
	
	// CHAMAR O FORM PARA VIZUALIZAR UMA MOVIMENTACAO
	$(".editarMovimentacao").click(function() {
		$('#labelAlteracao').html('Movimentação')
		$.get("movimentacao?operacao=Buscar&id=" + $(this).val(),function(retorno) {
			console.log(retorno)
			$('#txtId').val(retorno.id)
			$("#txtProduto").val(retorno.produto.id)
			$("#txtProdutoDesc").val(retorno.produto.nome)
			$('#txtQuantidade').val(retorno.quantidade)
			
			if (retorno.tipo === true)
				$('#txtTipo').val(1)
			else
				$('#txtTipo').val(0)
				
			$('#txtData').val(retorno.data_movimentacao.date.year + '-' + ('00' + retorno.data_movimentacao.date.month).slice(-2) + '-' + ('00' + retorno.data_movimentacao.date.day).slice(-2) + 'T' + ('00' + retorno.data_movimentacao.time.hour).slice(-2) + ':' + ('00' + retorno.data_movimentacao.time.minute).slice(-2))
			$('#txtObs').val(retorno.observaocao)
			
			if(retorno.pedido == 0)
				$('#txtPedido').val('Sem Pedido vinculado')
			else
				$('#txtPedido').val(retorno.pedido)
			if(retorno.compra == 0)
				$('#txtCompra').val('Sem Compra vinculado')
			else
				$('#txtCompra').val(retorno.compra)	
			if(retorno.troca.id == 0)
				$('#txtTroca').val('Sem Troca vinculado')
			else
				$('#txtTroca').val(retorno.troca.id)
				
			$('#txtProduto').prop("disabled", true);
			$('#txtQuantidade').prop("disabled", true);
			$('#txtTipo').prop("disabled", true);
			$('#txtData').prop("disabled", true);
			$('#txtObs').prop("disabled", true);
			$('#btnSalvar').prop("disabled", true);
			
			$('#movimentosModal').modal('show')
		});
	});
	
	function limpar(){
		$('#txtId').val(0)
		$("#txtProduto").val(0)
		$("#txtProdutoDesc").val('Sem Produto Adicionado !!')
		$('#txtQuantidade').val(0)
		$('#txtTipo').val('')
		$('#txtData').val('')
		$('#txtObs').val('')
		$('#txtPedido').val('Sem Pedido vinculado')
		$('#txtCompra').val('Sem Compra vinculado')
		$('#txtTroca').val('Sem Troca vinculado')
		
		$('#txtProduto').prop("disabled", false);
		$('#txtQuantidade').prop("disabled", false);
		$('#txtTipo').prop("disabled", false);
		$('#txtData').prop("disabled", false);
		$('#txtObs').prop("disabled", false);
		$('#btnSalvar').prop("disabled", false);
	}
	
	// NOVA MOVIMENTAÇÃO
	$("#btnNovo").click(function(){
		limpar()
		$('#movimentosModal').modal('show')
	});
	
	$(document).ready(function() {	
		// CONFIGURA A TABELA DE PEDIDOS
		$('#datatablesSimple').DataTable({
			"language" : {
				"url" : "//cdn.datatables.net/plug-ins/1.11.1/i18n/pt_br.json"
				}
		});			
	});
	</script>
</body>
</html>