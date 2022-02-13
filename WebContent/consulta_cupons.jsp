<!DOCTYPE html>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="br.edu.fatec.detoranja.dominio.Cupom"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Detoranja Cupons</title>
<%@ include file="complements/complements_css.jsp"%>
</head>
<body class="sb-nav-fixed">
	<%@ include file="complements/navbar.jsp"%>
	<%
	List<Cupom> listaCupons = (List<Cupom>) request.getAttribute("listaCupom");
	DecimalFormat df = new DecimalFormat();
	df.applyPattern("#,##0.00");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	%>
	<div id="layoutSidenav_content">
		<!--  -->
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Lista de Cupons</h1>
				<div class="card mb-4">
					<div class="card-header">
						<button type="button" id="btnNovo" class="btn btn-primary">Adicionar
							Novo Cupom</button>
					</div>
					<div class="card-body">
						<table id="datatablesSimple" class="table table-bordered table-sm">
							<thead>
								<tr>
									<th style="width: 5%; text-align: center;border:1px solid black;">#</th>
									<th style="width: 25%; text-align: left;border:1px solid black;">Cupom</th>
									<th style="width: 20%; text-align: left;border:1px solid black;">Cliente</th>
									<th style="width: 5%; text-align: center;border:1px solid black;">Tipo</th>
									<th style="width: 5%; text-align: center;border:1px solid black;">Desconto</th>
									<th style="width: 10%; text-align: center;border:1px solid black;">Valor minimo</th>
									<th style="width: 10%; text-align: center;border:1px solid black;">Validade</th>
									<th style="width: 10%; text-align: center;border:1px solid black;">Ativo</th>
									<th style="width: 10%; text-align: center;border:1px solid black;">Editar</th>
								</tr>
							</thead>
							<tbody>
							<% for(Cupom cupom : listaCupons) {%>
								<tr>
									<td style="width: 5%; text-align: center;"><% out.print(cupom.getId()); %></td>
									<td style="width: 25%; text-align: left;"><% out.print(cupom.getDesc_cupom()); %></td>
									<%if(cupom.getCliente().getId() != 0) { %>
										<td style="width: 20%; text-align: left;"><% out.print(cupom.getCliente().getId() + " - " + cupom.getCliente().getNome()); %></td>
									<%} else { %>
										<td style="width: 20%; text-align: left;">SEM CLIENTE VINCULADO !!</td>
									<% } %>
									<td style="width: 5%; text-align: center;"><% out.print(cupom.getTipo().getDescricao().toUpperCase()); %></td>
									<td style="width: 5%; text-align: center;">R$<% out.print(" " + df.format(cupom.getDesconto())); %></td>
									<td style="width: 10%; text-align: center;">R$<% out.print(" " + df.format(cupom.getValor_minimo())); %></td>
									<%if(cupom.getValidade() != null) { %>
										<td style="width: 10%; text-align: center;"><% out.print(cupom.getValidade().format(formatter)); %></td>
									<%} else {%>
										<td style="width: 10%; text-align: center;"><i class="far fa-times-circle fa-2x"></i></td>
									<% } %>
									<% if(cupom.getAtivo()) {%>
										<td style="width: 10%; text-align: center;"><i class='fa fa-check-circle fa-2x' ></i></td>
									<%} else {%>
										<td style="width: 10%; text-align: center;"><i class='fa fa-times-circle fa-2x' ></i></td>
									<% } %>
									<th style="width: 10%; text-align: center;"><button type="button" class="btn btn-success btnEditar" id="btnEditar" value="<% out.print(cupom.getId()); %>">Editar</button></th>
								</tr>
								<%} %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
		<!-- Modal de Inclusão e Edicao de um endereço-->
		<div class="modal fade" id="modalCupom" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-md"
				role="document">
				<form action="" id="formCupom">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="labelCupom">Novo Cupom</h5>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<div class="row">
									<div class="col-md-2 mb-3">
										<label for="txtId" class="col-form-label">ID</label> <input
											type="text" class="form-control" id="txtId" name="txtId"
											readonly>
									</div>
									<div class="col-md-10 mb-3">
										<label for="txtCodCupom" class="col-form-label">Cupom
										</label> <input type="text" class="form-control"
											id="txtCodCupom" name="txtCodCupom"
											required="required">
									</div>
								</div>
								<div class="row">
									<div class="col-md-4 mb-3">
										<label for="txtCodCupom" class="col-form-label">Troca
										</label> <input type="text" class="form-control" disabled="disabled"
											id="txtTroca" name="txtTroca">
									</div>
								</div>
								<div class="row">
									<div class="col-md-3 mb-3">
										<label for="txtTipo" class="col-form-label">Tipo Cupom
										</label> <select class="form-select" id="txtTipo" required="required">
										  <option selected disabled>Selecione</option>
										  <option value="1">Cliente</option>
										  <option value="2">Geral</option>
										</select>
									</div>
									<div class="col-md-2 mb-3">
										<label for="txtCliente" class="col-form-label">Cliente
										</label> <input type="text" min="1" class="form-control"
											id="txtCliente" name="txtCliente" readonly="readonly">
									</div>
									<div class="col-md-7 mb-3">
										<label for="txtNomeCliente" class="col-form-label">Nome Cliente
										</label> <input type="text" class="form-control"
											id="txtNomeCliente" name="txtNomeCliente" readonly="readonly">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 mb-3">
										<label for="txtDesconto" class="col-form-label">Desconto</label>
										<input type="text" class="form-control" id="txtDesconto"
											name="txtDesconto" required="required">
									</div>
									<div class="col-md-6 mb-3">
										<label for="txtValorMinimo" class="col-form-label">Valor Minimo</label>
										<input type="text" class="form-control" id="txtValorMinimo"
											name="txtValorMinimo">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 mb-3">
										<label for="txtValidade" class="col-form-label">Data de Validade</label>
										<input type="date" class="form-control" id="txtValidade"
											name="txtValidade">
									</div>
									<div class="col-md-6 mb-3">
										<label for="txtAtivo" class="col-form-label">Ativo</label>
										<div class="checkbox">
	  										<input type="checkbox" value="" id="txtAtivo">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" id="btnCancelar" class="btn btn-secondary"
								data-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-primary" name="operacao"
								value="Salvar" id="btnSalvar">Salvar</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--  -->
	</div>
	<%@ include file="complements/complements_js.jsp"%>

	<script type="text/javascript">
	
		// FECHAR O MODAL AO CLICAR NO BOTÃO CANCELAR
		$("#btnCancelar").click(function() {
			$('#modalCupom').modal('hide');
		});
		
		$(document).ready(function() {			
			$('#txtDesconto').mask("#.##0,00", {reverse: true});
			$('#txtValorMinimo').mask("#.##0,00", {reverse: true});
			$('#txtCliente').mask('###',{reverse: true});
			
			$('#datatablesSimple').DataTable({
				"language" : {
					"url" : "//cdn.datatables.net/plug-ins/1.11.1/i18n/pt_br.json"
					}
			});
			
			$("#txtTipo").change(function(){
				if($(this).val() == 1){
					$("#txtCliente").prop('readonly', false);
					$("#txtCliente").prop('required', true);
				} else {
					$("#txtCliente").prop('readonly', true);
					$("#txtCliente").prop('required', false);
					txtCliente : $("#txtCliente").val('')
				}
			});
			
			$("#txtCliente").keyup(function(){
				if($(this).val().trim().length > 0 && $(this).val().trim() != '')
					$.get("cliente?operacao=Buscar&id=" + $(this).val(),function(retorno) {
						console.log(retorno)
						if(retorno.hasOwnProperty('nome'))
							$("#txtNomeCliente").val(retorno.nome)
						else
							$("#txtNomeCliente").val("Cliente não encontrado!")
					});
				else
					$("#txtNomeCliente").val('Digite um código de cliente valido !!')
			});
		});
		
		
		$("#btnNovo").click(function(){
			$("#formCupom").trigger("reset");
			$('#labelCupom').html('Novo Cupom')
			$('#txtAtivo').attr("checked", false)
			$('#txtId').val(0)
			$('#modalCupom').modal('show')
		});
		
		$(".btnEditar").click(function(){
			$("#formCupom").trigger("reset");
			
			$.get("cupom_admin?operacao=Buscar&id=" + $(this).val(),function(retorno) {
				console.log(retorno)
				$('#labelCupom').html('Alterar Cupom ' + retorno.id)
				$('#txtId').val(retorno.id)
				$('#txtDesconto').val(retorno.desconto)
				if(retorno.hasOwnProperty('validade'))
					$('#txtValidade').val(retorno.validade.year + '-' + ("0" + retorno.validade.month).slice(-2) + '-' + ("0" + retorno.validade.day).slice(-2))
				if(retorno.valor_minimo != 0.0)
					$('#txtValorMinimo').val(retorno.valor_minimo)
				$('#txtCodCupom').val(retorno.desc_cupom)
				if(retorno.cliente.id != 0){
					$('#txtCliente').val(retorno.cliente.id)
					$.get("cliente?operacao=Buscar&id=" + retorno.cliente.id,function(value) {
						$("#txtNomeCliente").val(value.nome)
					});
					$("#txtCliente").prop('readonly', false);
				} else {
					$("#txtCliente").prop('readonly', true);
				}
				$('#txtTipo').val(retorno.tipo.id)
				$('#txtTroca').val(retorno.troca_Admin.id)
				$('#txtAtivo').attr("checked", retorno.ativo)
			});
			
			$('#modalCupom').modal('show')
		});
		
		$("#formCupom").submit(function() {
			if($("#txtNomeCliente").val() === 'Cliente não encontrado!'){
				toastr.warning('Cliente não Encontrado !!')	
			} else {	
				$.ajax({
					type : "POST",
					url : "cupom_admin?operacao=Salvar",
					contentType : "application/json", // NOT dataType!
					data : JSON.stringify({
						id : $("#txtId").val(),
						txtDesconto : $("#txtDesconto").val(),
						txtValorMinimo : $("#txtValorMinimo").val(),
						txtValidade : $("#txtValidade").val(),
						txtCodCupom : $("#txtCodCupom").val(),
						txtTipo : $("#txtTipo").val(),
						txtAtivo : document.getElementById("txtAtivo").checked,
						txtCliente : $("#txtCliente").val()
					}),
					success : function(retorno) {
						console.log(retorno)
						if(retorno == true){
							toastr.success('Cupom Salvo com Sucesso!!')
							setTimeout(function() {
								location.reload(true);
							}, 500);
						} else {
							toastr.error('Erro ao salvar Cupom !!')
						}
					}
				})
			}
			
			event.preventDefault();
		});
	</script>
</body>
</html>
