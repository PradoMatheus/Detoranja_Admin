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
<title>Administrador Compra</title>
<meta charset="UTF-8">
<%@ include file="complements/complements_css.jsp"%>
</head>
<body>

	<%@ include file="complements/navbar.jsp"%>

	<%
	List<Compra> listaCompras = (List<Compra>) request.getAttribute("listaCompras");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	%>

	<div class="container-fluid px-4">
		<h1 class="mt-4">Lista de Compras</h1>
		<div class="card mb-4">
			<div class="card-header">
				<button type="button" id="btnNovo" class="btn btn-primary">Nova
					Compra</button>
			</div>
			<div class="card-body">
				<table id="datatablesSimple">
					<thead>
						<tr>
							<th class="colDev mx-auto" style="width: 5%" scope="col">ID</th>
							<th scope="col" style="width: 40%">Fornecedor</th>
							<th scope="col" style="width: 10%">Qtde</th>
							<th scope="col" style="width: 10%">Valor</th>
							<th scope="col" style="width: 25%">Data</th>
							<th class="colDev mx-auto" scope="col" style="width: 10%">Consultar</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (listaCompras != null && listaCompras.size() > 0) {
							for (Compra compra : listaCompras) {
								out.print("<th class='colDev mx-auto' style='width: 5%' scope='row'>" + compra.getId() + "</th>");
								out.print("<th class='colDev' style='width: 40%' scope='row'>" + compra.getFornecedor().getFantasia() + "</th>");
								out.print("<td style='width: 10%'>" + compra.getQuantidade_total() + "</td>");
								out.print("<td style='width: 10%'>" + compra.getValor_total() + "</td>");
								out.print("<td style='width: 25%'>" + compra.getData_compra().format(formatter) + "</td>");
								out.print(
								"<td class='colDev mx-auto' style='width: 10%'><button type='button' class='btn btn-outline-danger ConsultarCompra' value='"
										+ compra.getId() + "'>Consultar</button></td>");
								out.print("</tr>");
							}
						} else {
							out.print("<tr>");
							out.print("<td style='text-align: center' colspan='5'>Sem Compras Realizadas!</td>");
							out.print("</tr>");
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- MODAL DE COMPRAS -->
	<div class="modal fade" id="modalCompras" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-xl"
			role="document">
			<div class="modal-content">
				<form action="" id="formCompra">
				<div class="modal-header">
					<h5 class="modal-title">
						Compra <strong id="labelAlteracao"></strong>
					</h5>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<div class="col-md-1 mb-3">
								<label for="txtId" class="col-form-label">ID</label> <input
									type="text" class="form-control" id="txtId" name="txtId"
									readonly>
							</div>
						</div>
						<div class="row">
							<div class="col-md-1 mb-3">
								<label for="txtFornecedor" class="col-form-label">ID</label> <input
									type="text" class="form-control" id="txtFornecedor"
									name="txtFornecedor" required="required" value="0">
							</div>
							<div class="col-md-6 mb-3">
								<label for="txtNameFornecedor" class="col-form-label">Fornecedor</label>
								<input type="text" class="form-control" id="txtNameFornecedor"
									name="txtNameFornecedor" value="Sem fornecedor Adicionado !!" readonly="readonly">
							</div>
							<div class="col-md-2 mb-3">
								<label for="txtNota" class="col-form-label">Nota Fiscal</label>
								<input type="text" class="form-control" id="txtNota"
									name="txtNota">
							</div>
							<div class="col-md-2 mb-3">
								<label for="dt_compra" class="col-form-label">Data de
									Compra</label> <input type="datetime-local"  id="dt_compra" required="required">
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 mb-3">
								<label for="txtQtdeTotal" class="col-form-label">Quantidade
									de Itens</label> <input type="text" value="0" class="form-control"
									id="txtQtdeTotal" name="txtQtdeTotal" readonly="readonly">
							</div>
							<div class="col-md-2 mb-3">
								<label for="txtValorTotal" class="col-form-label">Valor
									Total do Pedido</label> <input type="text" value="0" class="form-control"
									id="txtValorTotal" name="txtValorTotal" readonly="readonly">
							</div>
						</div>
						<div class="container">
							<button type="button" class="btn btn-primary"
								style="margin-bottom: 5px" id="btnAdicionarProduto">Adicionar
								Produto</button>
							<table class="table table-bordered" id="tableProdutos">
								<thead>
									<tr>
										<th scope="col" style='width: 3%'>#</th>
										<th scope="col" style='width: 3%'>Código</th>
										<th scope="col" style='width: 25%'>Produto</th>
										<th scope="col" style='width: 5%'>Quantidade</th>
										<th scope="col" style='width: 10%'>Valor Unit.</th>
										<th scope="col" style='width: 10%'>Valor Total</th>
										<th scope="col" style='width: 34%'>Observação</th>
										<th scope="col" style='width: 10%'>Remover</th>
									</tr>
								</thead>
								<tbody id="tableProdutosCompras">
									<tr>
										<th scope="row" style='width: 3%'>1</th>
										<td
											style='width: 3%; vertical-align: middle; text-align: center'><input
											type="text" name="txtProduto" size="3%"
											style="text-align: center;"></td>
										<td
											style='width: 25%; vertical-align: middle; text-align: center'><input
											type="text" name="txtDescProduto" readonly="readonly"></td>
										<td
											style='width: 5%; vertical-align: middle; text-align: center'><input
											type="text" name="txtQuantidade" size="5%"
											style="text-align: center;"></td>
										<td
											style='width: 10%; vertical-align: middle; text-align: center'><input
											type="text" name="txtValorUnitario" size="5%"
											style="text-align: center;"></td>
										<td
											style='width: 10%; vertical-align: middle; text-align: center'><input
											type="text" name="txtValorTotal" readonly="readonly"
											style="text-align: center;"></td>
										<td
											style='width: 34%; vertical-align: middle; text-align: center'><input
											type="text" name="txtObservacao"></td>
										<td
											style='width: 10%; vertical-align: middle; text-align: center'><button
												type="button" class="btn btn-danger">
												<i class="fas fa-trash-alt"></i>
											</button></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal" id="btnCancelar">Fechar</button>
						<button type="submit" class="btn btn-primary" id="btnAtualizar">Salvar</button>
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
	
		//CONSULTAR COMPRA
		$(".ConsultarCompra").click(function() {
			$('#modalCarregamento').modal('show')
			$('#labelFornecedor').html('Novo Fornecedor')
			limpar()
			$.get("compra?operacao=Buscar&id=" + $(this).val(),function(retorno) {
				console.log(retorno);
				$('#txtId').val(retorno.id)
				$('#txtFornecedor').val(retorno.fornecedor.id);
				$('#txtFornecedor').prop("disabled", true);
				$.get("fornecedor?operacao=Buscar&id=" + retorno.fornecedor.id,function(retorno) {
					$('#txtNameFornecedor').val(retorno.fantasia);
				});
				$('#txtNota').val(retorno.nota_fiscal)
				$('#txtNota').prop("disabled", true)
				$('#btnAtualizar').prop("disabled", true)
				$('#btnAdicionarProduto').prop("disabled", true)
				$('#txtQtdeTotal').val(retorno.quantidade_total)
				$('#txtValorTotal').val(retorno.valor_total)
				$('#dt_compra').val(retorno.data_compra.date.year + '-' + ('00' + retorno.data_compra.date.month).slice(-2) + '-' + ('00' + retorno.data_compra.date.day).slice(-2) + 'T' + ('00' + retorno.data_compra.time.hour).slice(-2) + ':' + ('00' + retorno.data_compra.time.minute).slice(-2))
				$('#dt_compra').prop("disabled", true)
				
				retorno.listaProdutos.forEach(function(item){
					var sequencia = document.getElementById("tableProdutosCompras").rows.length + 1;
					$("#tableProdutosCompras").append(
							"<tr>" +
								"<th scope='row' style='width: 3%'>" + sequencia + "</th>" +
								"<td style='width: 3%;vertical-align: middle; text-align: center'><input type='text' value='" + item.produto.id + "' disabled size='3%' style='text-align: center;'></td>" +
								"<td style='width: 25%;vertical-align: middle; text-align: center'><input type='text' value='" + item.produto.nome + "' disabled></td>" +
								"<td style='width: 5%;vertical-align: middle; text-align: center'><input type='text' value='" + item.quantidade + "' disabled size='5%' style='text-align: center;'></td>" +
								"<td style='width: 10%;vertical-align: middle; text-align: center'><input type='text' value='" + item.valor_compra + "' disabled size='5%' style='text-align: center;'></td>" +
								"<td style='width: 10%;vertical-align: middle; text-align: center'><input type='text' value='" + parseFloat(item.valor_compra) * parseInt(item.quantidade)+ "' disabled style='text-align: center;'></td>" +
								"<td style='width: 34%;vertical-align: middle; text-align: center'><input type='text' value='" + item.observaocao + "'disabled></td>" +
								"<td style='width: 10%;vertical-align: middle; text-align: center'><button type='button' class='btn btn-danger' disabled><i class='fas fa-trash-alt'></i></button></td>" +
							"</tr>"
					);
				})
				setTimeout(function() {
					$('#modalCarregamento').modal('hide')
					$('#modalCompras').modal('show')
				}, 1000);
				//$('#modalCarregamento').modal('hide')
			});
		});
	
		// SUBMIT DA COMPRA
		$("#formCompra").submit(function(){
			event.preventDefault();
			
			if($('#txtNameFornecedor').val() === "Fornecedor não encontrado !!" || $('#txtNameFornecedor').val() === "Sem fornecedor Adicionado !!"){
				toastr.error('Selecione um Fornecedor !!')
				return false;
			} else if(document.getElementById("tableProdutosCompras").rows.length < 1){
				toastr.error('Adicione pelo menos um item a sua compra !!')
				return false;
			} else {
				var erro = true;
				$('#tableProdutos > tbody  > tr > td > .txtDescProduto').each(function(index, tr) {
					if(tr.value === "Produto não encontrado !!" || tr.value === "Sem Produto Adicionado !!" || tr.value.trim() == ""){
						toastr.error('Adicione um produto valido aos itens da compra !!')
						erro = false;
					} 
				});
				$('#tableProdutos > tbody  > tr > td > .txtQuantidade').each(function(index, tr) {
					if(tr.value < 1 || tr.value.trim() == ""){
						toastr.error('Adicione um quantidade valida !!')
						erro = false;
					}
				});
				$('#tableProdutos > tbody  > tr > td > .txtValorUnitario').each(function(index, tr) {
					if(tr.value < 0.01 || tr.value.trim() == ""){
						toastr.error('Adicione um valor unitario valido !!')
						erro = false;
					}
				});
				if(erro){
					var compra = {};
					
					compra.id = $("#txtId").val();
					compra.idFornecedor = $("#txtFornecedor").val();
					compra.ValorTotal = $("#txtValorTotal").val();
					compra.quantidadeTotal = $("#txtQtdeTotal").val();
					compra.dataCompra = $("#dt_compra").val();
					compra.nota = $("#txtNota").val()
					
					compra.ItensCompra = [];
					$('#tableProdutos > tbody  > tr > td > .txtProduto').each(function(index, tr) {
						compra.ItensCompra.push({"produto":tr.value})
					});
					$('#tableProdutos > tbody  > tr > td > .txtQuantidade').each(function(index, tr) {
						compra.ItensCompra[index].quantidade = tr.value
					});
					$('#tableProdutos > tbody  > tr > td > .txtValorUnitario').each(function(index, tr) {
						compra.ItensCompra[index].valor = tr.value
					});
					$('#tableProdutos > tbody  > tr > td > .txtObs').each(function(index, tr) {
						compra.ItensCompra[index].obs = tr.value
					});
					
					console.log(compra)
					$.ajax({
						type : "POST",
						url : "compra?operacao=Salvar",
						contentType : "application/json", // NOT dataType!
						data : JSON.stringify(compra),
						success : function(retorno) {
							if(retorno == true){
								toastr.success('Compra realizada com sucesso !!')
								setTimeout(function() {
									location.reload(true);
								}, 500);
							} else {
								toastr.error('Erro ao salvar a compra !!')
							}
						}
					});
				}
			}			
		});
		
		// BUSCAR FORNECEDOR
		$("#txtFornecedor").keyup(function(){
			if($(this).val().trim() != ""){
				$.get("fornecedor?operacao=Buscar&id=" + $(this).val(),function(retorno) {
					if(retorno === null){
						$('#txtNameFornecedor').val("Fornecedor não encontrado !!");
					} else {
						$('#txtNameFornecedor').val(retorno.fantasia);
					}
				});
			} else {
				$('#txtNameFornecedor').val("Sem fornecedor Adicionado !!");
			}
		});
	
		// REMOVER UM PRODUTO DA COMPRA
		$(".btnExcluirProduto").click(function(){
			alert($(this).val())
		});
	
		//ADICIONAR NOVO ITEM A COMPRA
		$("#btnAdicionarProduto").click(function(){
			var sequencia = document.getElementById("tableProdutosCompras").rows.length + 1;
			$("#tableProdutosCompras").append(
					"<tr>" +
						"<th scope='row' style='width: 3%'>" + sequencia + "</th>" +
						"<td style='width: 3%;vertical-align: middle; text-align: center'><input type='text' class='txtProduto' id='txtProduto_" + sequencia + "' size='3%' style='text-align: center;'></td>" +
						"<td style='width: 25%;vertical-align: middle; text-align: center'><input type='text' class='txtDescProduto' id='txtDescProduto_" + sequencia + "' readonly='readonly'></td>" +
						"<td style='width: 5%;vertical-align: middle; text-align: center'><input type='text' value='0' class='txtQuantidade' id='txtQuantidade_" + sequencia + "' size='5%' style='text-align: center;'></td>" +
						"<td style='width: 10%;vertical-align: middle; text-align: center'><input type='text' value='0' class='txtValorUnitario' id='txtValorUnitario_" + sequencia + "'  size='5%' style='text-align: center;'></td>" +
						"<td style='width: 10%;vertical-align: middle; text-align: center'><input type='text' value='0' class='txtValorTotal' id='txtValorTotal_" + sequencia + "' readonly='readonly' style='text-align: center;'></td>" +
						"<td style='width: 34%;vertical-align: middle; text-align: center'><input type='text' class='txtObs' name='txtObservacao'></td>" +
						"<td style='width: 10%;vertical-align: middle; text-align: center'><button type='button' class='btn btn-danger btnExcluirProduto' value='" + sequencia + "'><i class='fas fa-trash-alt'></i></button></td>" +
					"</tr>"
			);
			$("#tableProdutosCompras").append(
					"<script>" +
						"$('.btnExcluirProduto').click(function(){" + 
							"document.getElementById('tableProdutosCompras').rows[$(this).val() - 1].remove();" +
							"validar();" +
						"});" +
						"$('#txtValorUnitario_" + sequencia + "').keyup(function(){" + 
							"$('#txtValorTotal_" + sequencia + "').val($(this).val() * $('#txtQuantidade_" + sequencia + "').val());" +
							"validar();" +
						"});" +
						"$('#txtQuantidade_" + sequencia + "').keyup(function(){" +
							"$('#txtValorTotal_" + sequencia + "').val($(this).val() * $('#txtValorUnitario_" + sequencia + "').val());" + 
							"validar();" +
						"});" +
						"$('#txtProduto_" + sequencia + "').keyup(function(){" +
							"console.log('ola');" +
							"if($(this).val().trim() != ''){" +
								"$.get('produto?operacao=Buscar&id=' + $(this).val(),function(retorno) {" +
									"if(retorno === null){" +
										"$('#txtDescProduto_" + sequencia + "').val('Produto não encontrado !!');" +
									"} else {" +
										"$('#txtDescProduto_" + sequencia + "').val(retorno.nome);" +
									"}" +
								"});" +
							"} else {" +
								"$('#txtDescProduto_" + sequencia + "').val('Sem Produto Adicionado !!');" +
							"}" +
						"});" +
						"$(document).ready(function(){" +
							"$('#txtQuantidade_" + sequencia + "').mask('0000');" +
					      	"$('#txtValorUnitario_" + sequencia + "').mask('###0.00', {reverse: true});" +
						"});" +
					"</scr"+"ipt>"
			);
		});
		
		// FECHAR O MODAL AO CLICAR NO BOTÃO CANCELAR
		$("#btnCancelar").click(function() {
			$('#modalCompras').modal('hide');
		});
		
		// VALIDAR O VALOR TOTAL E A QUANTIDADE TOTAL
		function validar(){
			var somador = 0;
			var quantidade = 0;
			$('#tableProdutos > tbody  > tr > td > .txtValorTotal').each(function(index, tr) {
				if(tr.value.trim() != ""){
					somador += parseFloat(tr.value);
				} else {
					somador += parseInt(0);
				}
			});
			$('#tableProdutos > tbody  > tr > td > .txtQuantidade').each(function(index, tr) {
				if(tr.value.trim() != ""){
					quantidade += parseInt(tr.value);
				} else {
					quantidade += parseInt(0);
				}
			});
			$("#txtQtdeTotal").val(parseInt(quantidade));
			$("#txtValorTotal").val(parseFloat(somador));
		}
		
		// LIMPAR MODAL
		function limpar(){
			$("#tableProdutosCompras").children('tr').remove()
			$('#txtFornecedor').val(0);
			$('#txtNameFornecedor').val('Sem fornecedor Adicionado !!');
			$('#txtNota').val('')
			$('#txtQtdeTotal').val('0')
			$('#txtValorTotal').val('0')
			$('#dt_compra').val('')
		}

		// CHAMAR O FORM PARA CADASTRAR UMA COMPRA
		$("#btnNovo").click(function() {
			$('#labelFornecedor').html('Novo Fornecedor')
			limpar()
			$('#txtId').val(0)
			$('#txtFornecedor').prop("disabled", false);
			$('#txtNota').prop("disabled", false)
			$('#btnAtualizar').prop("disabled", false)
			$('#btnAdicionarProduto').prop("disabled", false)
			$('#dt_compra').prop("disabled", false)
			$('#modalCompras').modal('show')
		});

		$(document)
				.ready(
						function() {
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