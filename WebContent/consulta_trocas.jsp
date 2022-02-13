<!DOCTYPE html>
<%@page import="br.edu.fatec.detoranja.dominio.Troca_Admin"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="br.edu.fatec.detoranja.dominio.Pedido"%>
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
<title>Detoranja Trocas</title>
<%@ include file="complements/complements_css.jsp"%>
</head>
<body class="sb-nav-fixed">
	<%@ include file="complements/navbar.jsp"%>
	<%
	List<Troca_Admin> listaTrocas = (List<Troca_Admin>) request.getAttribute("listaTrocas");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat();
	df.applyPattern("#,##0.00");
	%>
	<div id="layoutSidenav_content">
		<!--  -->
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Lista de Trocas</h1>
				<div class="card mb-4">
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th style='vertical-align: middle; text-align: center'>Troca</th>
									<th style='vertical-align: middle; text-align: center'>Data</th>
									<th style='vertical-align: middle; text-align: center'>Valor</th>
									<th style='vertical-align: middle; text-align: center'>Status</th>
									<th style='vertical-align: middle; text-align: center'>Alterar
										Status</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (Troca_Admin troca : listaTrocas) {
								%>
								<tr>
									<td style='vertical-align: middle; text-align: center'>
										<%
										out.print(troca.getId());
										%>
									</td>
									<td style='vertical-align: middle; text-align: center'>
										<%
										out.print(troca.getData().format(formatter));
										%>
									</td>
									<td style='vertical-align: middle; text-align: center'>R$<%
									out.print(df.format(troca.getTotal()));
									%></td>
									<td style='vertical-align: middle; text-align: center'>
										<%
										out.print(troca.getStatus().getDescricao());
										%>
									</td>
									<th style='vertical-align: middle; text-align: center'><input
										type="hidden" id="descStatus"
										value="<%out.print(troca.getStatus().getDescricao());%>"><input
										type="hidden" class="idStatus"
										value="<%out.print(troca.getStatus().getId());%>">
									<button type="button" class="btn btn-success btnEditar"
											id="btnEditar" value="<%out.print(troca.getId());%>">
											<i class="fa fa-exchange-alt" style="margin-right: 5px"></i>Alterar
										</button></th>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- MODAL DE ALTERAÇÂO DE STATUS DO PEDIDO-->
			<div class="modal fade" id="modalAlteracao" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">
								Alterar status da Troca Solicitada <strong id="labelAlteracao"></strong>
								<a style="left: 92%; position: absolute;color: red" href="#"><i class="fas fa-ban" id="btnCancelarTroca"></i></a>
							</h5>
						</div>
						<div class="modal-body">
							<table id="tabela_itens_troca" class="table table-striped">
					      		<thead id="troca_head">
					      		</thead>
					      		<tbody id="corpo_head">
					      		</tbody>
					      	</table>
							<label for="txtStatusAtual" class="col-form-label">Status
								atual:</label><br> <input type="text" id="txtStatusAtual"
								disabled="disabled"> <br> <label for="txtTipo"
								id="atualizaPara" class="col-form-label">Atualizar para:
							</label> <select class="form-select" id="txtStatus" required="required">
								<option selected disabled>Selecione</option>
							</select>
							<div id="aviso"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" id="btnCancelar"
								data-dismiss="modal">Fechar</button>
							<button type="button" class="btn btn-primary" id="btnAtualizar">Atualizar</button>
						</div>
					</div>
				</div>
			</div>
		</main>
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
	<%@ include file="complements/complements_js.jsp"%>

	<script type="text/javascript">
	
		// FECHAR O MODAL AO CLICAR NO BOTÃO CANCELAR
		$("#btnCancelar").click(function() {
			$('#modalAlteracao').modal('hide');
		});
		
		$(".btnEditar")
				.click(
						function() {
							$('#modalCarregamento').modal('show')
							
							$("#aviso div").remove()
							document.getElementById('atualizaPara').style.display = 'block';
							document.getElementById('txtStatus').style.display = 'block';
							$('#troca_head th').remove();
							$('#corpo_head td').remove();
							$('#btnAtualizar').prop('disabled', false);
							$('#labelAlteracao').text($(this).val())

							//LIMPA O LISTA DE STATUS
							$("#txtStatus option").remove()
							$("#txtStatusAtual").val(
									$(this).parent().find("#descStatus").val())	
							//BUSCA AS INFORMAÇÕES DA TROCA
							$.get("troca_admin?operacao=Buscar&id="+ $(this).val(), function(retorno) {
								$('#txtStatus').val(retorno.status.id)
								console.log(retorno)
								var j = 0;
								
								$('#troca_head').append(
										'<th style="width: 5%; text-align: center;">#</th>' + 
										'<th style="width: 5%";>Cod</th>' + 
										'<th style="width: 35%";>Livro</th>' + 
										'<th style="width: 5%; text-align: center;">Valor</th>' + 
										'<th style="width: 15%; text-align: center;">Qtd Disp</th> ' + 
										'<th style="width: 15%; text-align: center;">Qtd Troca</th> ')
										
								retorno.listTrocaItens.forEach(function(item){
									console.log(item)
									
									var corpo = '<tr>' +
									'<td style="width: 5%; text-align: center; "class="id_seq">'+ j + '</td>' +
									'<td style="width: 5%; text-align: center; "class="id_produto">' + item.produto.id + '</td>' +
									'<td style="width: 40%;">' + item.produto.nome + '</td>' +
									'<td style="width: 5%; text-align: center;" class="valor">' + item.valor + '</td>' +
									'<td style="width: 15%; text-align: center;" class="id_qtde_disponivel">' + item.quantidade + '</td>';
									if(retorno.status.id == 3){
										corpo += '<td style="width: 15%; text-align: center;"><input class="qtde" style="border:solid 1px black;border-radius: 5px;text-align: center;" type="text"></td>';
									} else {
										corpo += '<td style="width: 15%; text-align: center;"><input class="qtde" style="border:solid 1px black;border-radius: 5px;text-align: center;" type="text" disabled></td>';
									}
									corpo += '</tr>';
									$('#corpo_head').append(
										corpo
											);
								})
								$("#corpo_head").append("<script>$('.qtde').mask('###',{reverse: true});</scr"+"ipt>");
							});
							//BUSCA OS STATUS DISPONIVEIS
							$.get("troca_statusadmin?operacao=Lista&status="
									+ $(this).parent().find(".idStatus")
											.val(), function(retorno) {
								$.each(retorno, function(key, item) {
									$("#txtStatus").append(
											"<option value=" + item.id + ">"
													+ item.descricao
													+ "</option>");
								});
							});

							setTimeout(function() {
								$('#modalCarregamento').modal('hide')
								$('#modalAlteracao').modal('show');
							}, 1000);
						});

		$("#btnAtualizar")
				.click(
						function() {
							$
									.ajax({
										type : "POST",
										url : "troca_admin?operacao=Salvar&status="
												+ $('#txtStatus').val()
												+ "&id="
												+ parseInt($('#labelAlteracao')
														.text()),
										contentType : "application/json", // NOT dataType!
										success : function(retorno) {
											console.log(retorno)
											if (retorno == true) {
												toastr
														.success('Status Salvo com Sucesso!!')
												setTimeout(function() {
													location.reload(true);
												}, 500);
											} else {
												toastr
														.error('Erro ao salvar atualização do Status !!')
											}
										}
									})
						});

		$("#btnCancelarTroca")
		.click(
				function() {
					$
							.ajax({
								type : "POST",
								url : "troca_admin?operacao=Salvar&status=5"
										+ "&id="
										+ parseInt($('#labelAlteracao')
												.text()),
								contentType : "application/json", // NOT dataType!
								success : function(retorno) {
									console.log(retorno)
									if (retorno == true) {
										toastr
												.success('Status Salvo com Sucesso!!')
										setTimeout(function() {
											location.reload(true);
										}, 500);
									} else {
										toastr
												.error('Erro ao salvar atualização do Status !!')
									}
								}
							})
				});

		$(document)
				.ready(
						function() {

							// CONFIGURA A TABELA DE TROCAS
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
