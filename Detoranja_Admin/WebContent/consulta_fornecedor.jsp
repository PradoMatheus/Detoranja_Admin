<%@page import="br.edu.fatec.detoranja.dominio.Fornecedor"%>
<%@page import="br.edu.fatec.detoranja.dominio.Compra"%>
<%@page import="br.edu.fatec.detoranja.dominio.Estoque_Movimentacao"%>
<%@page import="br.edu.fatec.detoranja.dominio.Estoque"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.edu.fatec.detoranja.dominio.Produto"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Administrador Fornecedores</title>
<%@ include file="complements/complements_css.jsp"%>
</head>
<body>

	<%@ include file="complements/navbar.jsp"%>

	<%
	List<Fornecedor> listaFornecedores = (List<Fornecedor>) request.getAttribute("listaFornecedor");
	%>

	<div class="container-fluid px-4">
		<h1 class="mt-4">Lista de Fornecedores</h1>
		<div class="card mb-4">
			<div class="card-header">
				<button type="button" id="btnNovo" class="btn btn-primary">Novo
					Fornecedor</button>
			</div>
			<div class="card-body">
				<table id="datatablesSimple">
					<thead>
						<tr>
							<th class="colDev mx-auto" style="width: 5%" scope="col">ID</th>
							<th scope="col" style="width: 42, 5%">Fantasia</th>
							<th scope="col" style="width: 42, 5%">Razão</th>
							<th class="colDev mx-auto" scope="col" style="width: 10%">Editar</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (listaFornecedores != null && listaFornecedores.size() > 0) {
							for (Fornecedor fornecedor : listaFornecedores) {
								out.print("<tr>");
								out.print("<th style='width: 5%' scope='row'>" + fornecedor.getId() + "</th>");
								out.print("<th style='width: 42,5%' scope='row'>" + fornecedor.getFantasia() + "</th>");
								out.print("<th style='width: 42,5%' scope='row'>" + fornecedor.getRazao() + "</th>");
								out.print(
								"<td class='colDev mx-auto' style='width: 10%'><button type='button' class='btn btn-outline-danger editarFornecedor' value='"
										+ fornecedor.getId() + "'>Editar</button></td>");
								out.print("</tr>");
							}
						} else {
							out.print("<tr>");
							out.print("<td style='text-align: center' colspan='5'>Sem Fornecedores Cadastrados!</td>");
							out.print("</tr>");
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Modal de cadastro do Fornecedor-->
	<div class="modal fade" id="modalFornecedor" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form action="" id="formFornecedor">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="labelFornecedor"></h5>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="row">
								<div class="col-md-2 mb-3">
									<label for="txtId" class="col-form-label">ID</label> <input
										type="text" class="form-control" id="txtId" name="txtId"
										readonly>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 mb-3">
									<label for="txtId" class="col-form-label">Razão Social</label>
									<input type="text" class="form-control" id="txtRazao"
										name="txtRazao" required="required">
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 mb-3">
									<label for="txtId" class="col-form-label">Fantasia</label> <input
										type="text" class="form-control" id="txtFantasia"
										name="txtFantasia" required="required">
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="txtId" class="col-form-label">CNPJ</label> <input
										type="text" class="form-control" id="txtCNPJ" name="txtCNPJ"
										required="required">
								</div>
								<div class="col-md-6 mb-3">
									<label for="txtId" class="col-form-label">Inscrição
										Estadual</label> <input type="text" class="form-control" id="txtie"
										name="txtie" required="required">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" id="btnCancelar">Cancelar</button>
						<button type="submit" class="btn btn-primary" id="btnSalvar">Salvar</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- JS -->
	<%@ include file="complements/complements_js.jsp"%>

	<script type="text/javascript">
	
		// CLICAR PARA EDITAR FORNECEDOR
		$(".editarFornecedor").click(function(){
			limpar();
			$('#labelFornecedor').html('Alterar Fornecedor')
			$.get("fornecedor?operacao=Buscar&id=" + $(this).val(),function(retorno) {
				$('#txtId').val(retorno.id)
				$('#txtCNPJ').val(retorno.cnpj);
				$('#txtie').val(retorno.inscricao);
				$('#txtFantasia').val(retorno.fantasia);
				$('#txtRazao').val(retorno.razao);
			});
			$('#modalFornecedor').modal('show')
		});
		
		// CHAMAR O FORM PARA CADASTRAR NOVO FORNECEDOR
		$("#btnNovo").click(function() {
			limpar();
			$('#labelFornecedor').html('Novo Fornecedor')
			$('#txtId').val(0)
			$('#modalFornecedor').modal('show')
		});

		// LIMPAR O MODAL DE FORNECEDOR
		function limpar() {
			$('#txtCNPJ').val('');
			$('#txtie').val('');
			$('#txtFantasia').val('');
			$('#txtRazao').val('');
		}

		// FECHAR O MODAL AO CLICAR NO BOTÃO CANCELAR
		$("#btnCancelar").click(function() {
			$('#modalFornecedor').modal('hide');
		});

		// SALVAR FORNECEDOR
		$("#formFornecedor").submit(function(event) {
			event.preventDefault();

			if ($("#txtCNPJ").val().length == 18) {
				if ($("#txtie").val().length == 15) {
					$.ajax({
						type : "POST",
						url : "fornecedor?operacao=Salvar",
						contentType : "application/json", // NOT dataType!
						data : JSON.stringify({
							id : $("#txtId").val(),
							razao : $("#txtRazao").val(),
							fantasia : $("#txtFantasia").val(),
							cnpj : $("#txtCNPJ").val(),
							inscricao : $("#txtie").val(),
						}),
						success : function(retorno) {
							console.log(retorno)
							if(retorno == true){
								toastr.success('Fornecedor salvo/atualizado com Sucesso!!')
								setTimeout(function() {
									location.reload(true);
								}, 500);
							} else {
								toastr.error('Erro ao salvar Fornecedor !!')
							}
						}
					});
				} else {
					toastr.warning('Digite uma Inscrição Estadual valida!!')
				}
			} else {
				toastr.warning('Digite um CNPJ valido!!')
			}
		});

		$(document)
				.ready(
						function() {
							// MASCARA DA MODAL DE FORNECEDOR
							$('#txtCNPJ').mask("00.000.000/0000-00");
							$('#txtie').mask("000.000.000.000");

							// CONFIGURA A TABELA DE PEDIDOS
							$('#datatablesSimple')
									.DataTable(
											{
												"language" : {
													"url" : "//cdn.datatables.net/plug-ins/1.11.1/i18n/pt_br.json"
												}
											});
						});
	</script>
</body>
</html>