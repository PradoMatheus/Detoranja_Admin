<!DOCTYPE html>
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
<title>Detoranja Pedidos</title>
<%@ include file="complements/complements_css.jsp"%>
</head>
<body class="sb-nav-fixed">
	<%@ include file="complements/navbar.jsp"%>
	<%
	List<Pedido> listaPedidos = (List<Pedido>) request.getAttribute("listaPedido");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat();
	df.applyPattern("#,##0.00");
	%>
	<div id="layoutSidenav_content">
		<!--  -->
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Lista de Pedidos</h1>
				<div class="card mb-4">
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th style='vertical-align: middle; text-align: center'>Pedido</th>
									<th style='vertical-align: middle; text-align: center'>Data Compra</th>
									<th>Cliente</th>
									<th style='vertical-align: middle; text-align: center'>Valor</th>
									<th style='vertical-align: middle; text-align: center'>Status</th>
									<th style='vertical-align: middle; text-align: center'>Alterar Status</th>
								</tr>
							</thead>	
							<tbody>
							<% for(Pedido pedido : listaPedidos) {%>
								<tr>
									<td style='vertical-align: middle; text-align: center'><% out.print(pedido.getId()); %></td>
									<td style='vertical-align: middle; text-align: center'><% out.print(pedido.getData_pedido().format(formatter)); %></td>
									<td><% out.print(pedido.getCliente().getId() + " - " + pedido.getCliente().getNome()); %></td>
									<td style='vertical-align: middle; text-align: center'>R$<% out.print(df.format(pedido.getValorTotal())); %></td>
									<td style='vertical-align: middle; text-align: center'><% out.print(pedido.getStatus().getDescricao()); %></td>
									<th style='vertical-align: middle; text-align: center'><input type="hidden" id="descStatus" value="<% out.print(pedido.getStatus().getDescricao()); %>"><input type="hidden" class="idStatus" value="<%out.print(pedido.getStatus().getId());%>"><button type="button" class="btn btn-success btnEditar" id="btnEditar" value="<%out.print(pedido.getId());%>"><i class="fa fa-exchange-alt" style="margin-right: 5px"></i>Alterar</button></th>
								</tr>
								<%} %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		  	<!-- MODAL DE ALTERAÇÂO DE STATUS DO PEDIDO-->
		  	<div class="modal fade" id="modalAlteracao" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Alterar status do pedido <strong id="labelAlteracao"></strong></h5>
			        <a style="left: 92%; position: absolute;color: red" href="#"><i class="fas fa-ban" id="btnCancelarPedido"></i></a>
			      </div>
			      <div class="modal-body">
			      	<label for="txtStatusAtual" class="col-form-label">Status atual:</label><br>
						<input type="text" id="txtStatusAtual" disabled="disabled">
					<br>
			      	<label for="txtTipo" id="atualizaPara" class="col-form-label">Atualizar para:
					</label> <select class="form-select" id="txtStatus" required="required">
					  <option selected disabled>Selecione</option>
					</select>
					<div id="aviso"></div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btnCancelar">Fechar</button>
			        <button type="button" class="btn btn-primary" id="btnAtualizar">Atualizar</button>
			      </div>
			    </div>
			  </div>
			</div>
		</main>
	</div>
	<%@ include file="complements/complements_js.jsp"%>

	<script type="text/javascript">
	
		// FECHAR O MODAL AO CLICAR NO BOTÃO CANCELAR
		$("#btnCancelar").click(function() {
			$('#modalAlteracao').modal('hide');
		});
		
		$(".btnEditar").click(function(){
			$("#aviso div").remove()
			document.getElementById('atualizaPara').style.display = 'block';
			document.getElementById('txtStatus').style.display = 'block';
			$('#btnAtualizar').prop('disabled', false);
			
			$('#labelAlteracao').text($(this).val())
			$('#txtStatus').val($(this).parent().find(".idStatus").val())
			
			//LIMPA O LISTA DE STATUS
			$("#txtStatus option").remove()
			$("#txtStatusAtual").val($(this).parent().find("#descStatus").val())
			//BUSCA OS STATUS DISPONIVEIS
			if($(this).parent().find(".idStatus").val() != 5 && $(this).parent().find(".idStatus").val() != 3) {
				$.get("status_pedido?operacao=Lista&status=" + $(this).parent().find(".idStatus").val(),function(retorno) {
					$.each(retorno, function(key, item) {
						$("#txtStatus").append("<option value=" + item.id + ">"+ item.descricao + "</option>");
					});
				});
			}	
			else if($(this).parent().find(".idStatus").val() == 3) { 
				document.getElementById('txtStatus').style.display = 'none';
				document.getElementById('atualizaPara').style.display = 'none';
				$("#aviso").append('<div class="alert alert-warning d-flex justify-content-center" style="margin: 15px" id="pedidoFinalizado" role="alert">Pedido rejeitado  pela administradora do Cartão !!</div>');
				$('#btnAtualizar').prop('disabled', true);
			} else {
				document.getElementById('txtStatus').style.display = 'none';
				document.getElementById('atualizaPara').style.display = 'none';
				$("#aviso").append('<div class="alert alert-success d-flex justify-content-center" style="margin: 15px" id="pedidoFinalizado" role="alert">Pedido Finalizado !!</div>');
				$('#btnAtualizar').prop('disabled', true);
			}
				
			$('#modalAlteracao').modal('show');
		});
		
		$("#btnAtualizar").click(function(){
			$.ajax({
				type : "POST",
				url : "pedido?operacao=Salvar&status=" + $('#txtStatus').val() +"&id=" + parseInt($('#labelAlteracao').text()),
				contentType : "application/json", // NOT dataType!
				success : function(retorno) {
					console.log(retorno)
					if(retorno == true){
						toastr.success('Status Salvo com Sucesso!!')
						setTimeout(function() {
							location.reload(true);
						}, 500);
					} else {
						toastr.error('Erro ao salvar atualização do Status !!')
					}
				}
			})
		});
		$("#btnCancelarPedido").click(function(){
			$.ajax({
				type : "POST",
				url : "pedido?operacao=Salvar&status=10&id=" + parseInt($('#labelAlteracao').text()),
				contentType : "application/json", // NOT dataType!
				success : function(retorno) {
					console.log(retorno)
					if(retorno == true){
						toastr.success('Pedido cancelado com Sucesso!!')
						setTimeout(function() {
							location.reload(true);
						}, 500);
					} else {
						toastr.error('Erro ao salvar atualização do Status !!')
					}
				}
			})
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
