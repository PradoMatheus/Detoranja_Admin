<%@page import="br.edu.fatec.detoranja.dominio.Cliente"%>
<%@page import="br.edu.fatec.detoranja.dominio.Produto_Categoria"%>
<%@page import="br.edu.fatec.detoranja.dominio.Produto_Desenvolvedor"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.edu.fatec.detoranja.dominio.Produto"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Administrador Detoranja - Clientes</title>
<meta charset="UTF-8">

<!-- CSS -->
<link rel="stylesheet" href="./Bootstrap/css/bootstrap.min.css">
<link rel="icon" href="./complements/logo_browser.png">
<style type="text/css">
.colDev {
	text-align: center;
	vertical-align: middle;
}

.divpadrao {
	margin-top: 10px;
	height: 450px;
}
</style>

</head>
<body>

	<%@ include file="complements/navbar.jsp"%>

	<%
	List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("ListaClientes");
	%>

	<div class="container" style="height: 85%">
		<div class="py-5 text-center">
			<h2>Lista de Clientes</h2>
		</div>
		<div class="form-row" style="position: relative;">
			<div class="form-group col-md-3">
				<button class="btn btn-primary filtro" type="submit">
					<img src="./icons/add.svg" height="20px" style="margin-right: 5px">Filtro
				</button>
			</div>
			<div class="form-group col-md-9" style="align-items: center">
				<div class="input-group">
					<input type="text" class="form-control filtroCategoria"
						aria-describedby="pesquisar" placeholder="Pesquisar"
						id="txtPesquisa" name="txtPesquisa">
					<div class="input-group-prepend">
						<span class="input-group-text" id="txtPesquisa"><img
							src="./icons/search.svg" height="20px"></span>
					</div>
				</div>
			</div>
		</div>
		<table class="table table-bordered table-sm" id="tabela_categoria">
			<thead>
				<tr>
					<th class="colDev" style="width: 5%" scope="col">ID</th>
					<th class='colDev' scope="col" style="width: 25%">Nome</th>
					<th class='colDev' scope="col" style="width: 30%">E-Mail</th>
					<th class='colDev' scope="col" style="width: 20%">CPF</th>
					<th class='colDev' scope="col" style="width: 5%">Ativo</th>
					<th class="colDev" scope="col" style="width: 15%">Consultar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (listaClientes != null) {
					for (Cliente cliente : listaClientes) {
						out.print("<tr>");
						out.print("<th class='colDev' style='width: 5%' scope='row'>" + cliente.getId() + "</th>");
						out.print("<th class='colDev' style='width: 25%' scope='row'>" + cliente.getNome() + "</th>");
						out.print("<th class='colDev' style='width: 30%' scope='row'>" + cliente.getEmail() + "</th>");
						out.print("<th class='colDev' style='width: 20%' scope='row'>" + cliente.getCpf() + "</th>");
						out.print("<th class='colDev' style='width: 5%' scope='row'>" + cliente.getAtivo() + "</th>");
						out.print(
						"<td class='colDev' style='width: 15%'><button type='button' class='btn btn-outline-danger consultarCliente' value='"
								+ cliente.getId() + "'>Consultar</button></td>");
						out.print("</tr>");
					}
				} else {
					out.print("<tr>");
					out.print("<td style='text-align: center' colspan='5'>Sem Clientes Cadastrados!</td>");
					out.print("</tr>");
				}
				%>
			</tbody>
		</table>
	</div>

	<!-- Modal de Informações do Cliente-->
	<div class="modal fade" id="modalConsultaCliente" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="labelCliente">Cliente</h5>
				</div>
				<div class="modal-body">
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							id="geral-tab" data-toggle="tab" href="#geral" role="tab"
							aria-controls="Informações Gerais" aria-selected="true">Informações
								Gerais</a></li>
						<li class="nav-item"><a class="nav-link" id="enderecos-tab"
							data-toggle="tab" href="#enderecos" role="tab"
							aria-controls="Enderecos" aria-selected="false">Endereços</a></li>
						<li class="nav-item"><a class="nav-link" id="formas-tab"
							data-toggle="tab" href="#formas" role="tab"
							aria-controls="Formas" aria-selected="false">Cartões de
								Credito</a></li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active divpadrao" id="geral"
							role="tabpanel" aria-labelledby="geral-tab">
							<div class="row">
								<div class="col-md-2 mb-3">
									<label for="txtId" class="col-form-label">ID</label> <input
										type="text" class="form-control" id="txtId" name="txtId"
										readonly>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="txtNome" class="col-form-label">Nome</label> <input
										type="text" class="form-control" id="txtNome" name="txtNome"
										readonly>
								</div>
								<div class="col-md-2 mb-3"></div>
								<div class="col-md-4 mb-3">
									<label for="txtDataNascimento" class="col-form-label">Data
										de Nascimento</label> <input type="text" class="form-control"
										id="txtDataNascimento" name="txtDataNascimento" readonly>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="txtEmail" class="col-form-label">E-Mail</label> <input
										type="text" class="form-control" id="txtEmail"
										name="txtEmail" readonly>
								</div>
								<div class="col-md-6 mb-3">
									<label for="txtCPF" class="col-form-label">CPF</label> <input
										type="text" class="form-control" id="txtCPF" name="txtCPF"
										readonly>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input"
											id="txtAtivo"> <label
											class="custom-control-label" for="txtAtivo">Status
											do Cliente (Ativo ou Inativo) </label>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane fade divpadrao" id="enderecos"
							role="tabpanel" aria-labelledby="geral-tab">
							<div class="row">
								<div class="col-4">
									<div class="list-group" id="ListaEndereco" role="tablist">
										<h5>Lista de Endereços</h5>
									</div>
								</div>
								<div class="col-8">
									<div class="tab-content" id="nav-tabContent">
										<div class="row">
											<div class="col-md-2 mb-3">
												<label for="txtIdEndereco" class="col-form-label">ID</label> <input
													type="text" class="form-control" id="txtIdEndereco" name="txtIdEndereco"
													readonly>
											</div>
										</div>
										<div class="row">
											<div class="col-md-8 mb-3">
												<label for="txtDescricao" class="col-form-label">Descrição</label>
												<input type="text" class="form-control" id="txtDescricao"
													name="txtDescricao" required="required" readonly>
											</div>
											<div class="col-md-4 mb-3">
												<label for="txtCEP" class="col-form-label">CEP</label> <input
													type="text" class="form-control" id="txtCEP" name="txtCEP"
													required="required" readonly>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 mb-3">
												<label for="txtBairro" class="col-form-label">Bairro</label>
												<input type="text" class="form-control" id="txtBairro"
													name="txtBairro" requ0ired="required" readonly>
											</div>
											<div class="col-md-3 mb-3">
												<label for="txtCidade" class="col-form-label">Cidade</label>
												<input type="text" class="form-control" id="txtCidade"
													name="txtCidade" required="required" readonly>
											</div>
											<div class="col-md-3 mb-3">
												<label for="txtEstado" class="col-form-label">Estado</label>
												<input type="text" class="form-control" id="txtEstado"
													name="txtEstado" required="required" readonly>
											</div>
											<div class="col-md-3 mb-3">
												<label for="txtPais" class="col-form-label">Pais</label> <input
													type="text" class="form-control" id="txtPais"
													name="txtPais" required="required" readonly>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 mb-3">
												<label for="txtTipoLogradouro" class="col-form-label">Tipo</label>
												<select class="form-control" id="txtTipoLogradouro"
													name="txtTipoLogradouro">
													<option value="" selected disabled>Selecione</option>
												</select>
											</div>
											<div class="col-md-7 mb-3">
												<label for="txtLogradouro" class="col-form-label">Logradouro</label>
												<input type="text" class="form-control" id="txtLogradouro"
													name="txtLogradouro" required="required" readonly>
											</div>
											<div class="col-md-2 mb-3">
												<label for="txtNumero" class="col-form-label">Número</label>
												<input type="text" class="form-control" id="txtNumero"
													name="txtNumero" required="required" readonly>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 mb-3">
												<label for="txtComplemento" class="col-form-label">Complemento</label>
												<input type="text" class="form-control" id="txtComplemento"
													name="txtComplemento" readonly>
											</div>
											<div class="col-md-6 mb-3">
												<label for="txtReferencia" class="col-form-label">Referencia</label>
												<input type="text" class="form-control" id="txtReferencia"
													name="txtComplemento" readonly>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane fade divpadrao" id="formas" role="tabpanel"
							aria-labelledby="geral-tab">
							<div class="row">
								<div class="col-4">
									<div class="list-group" id="ListaFormas" role="tablist">
										<h5>Lista de Cartões</h5>
									</div>
								</div>
								<div class="col-8">
									<div class="tab-content" id="nav-tabContent">
										<div class="row">
											<div class="col-md-2 mb-3">
												<label for="txtIdForma" class="col-form-label">ID</label> <input
													type="text" class="form-control" id="txtIdForma" name="txtId"
													readonly>
											</div>
										</div>
										<div class="row">
											<div class="col-md-8 mb-3">
												<label for="txtNumeroCartao" class="col-form-label">Número
													do Cartão</label> <input type="text" class="form-control"
													id="txtNumeroCartao" name="txtNumeroCartao"
													required="required" readonly>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 mb-3">
												<label for="txtBandeira" class="col-form-label">Bandeira</label>
												<input type="text" class="form-control" id="txtBandeira"
													name="txtBandeira" required="required" readonly>
											</div>
											<div class="col-md-1 mb-3"></div>
											<div class="col-md-5 mb-3">
												<label for="txtValidade" class="col-form-label">Validade</label>
												<input type="text" class="form-control" id="txtValidade"
													name="txtValidade" required="required" readonly>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-primary" id="btnAtualizar">Atualizar</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- JS -->
	<script src="./Bootstrap/js/jquery.min.js"></script>
	<script src="./Bootstrap/js/jquery.mask.min.js"></script>
	<script src="./Bootstrap/js/jquery.mask.js"></script>
	<!-- <script src="./Bootstrap/js/popper.min.js"></script> -->
	<script src="./Bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$('.consultarCliente').click(function() {
			
			$.get("cliente?operacao=Buscar&id=" + $(this).val(),function(retorno) {
				
				$('#labelCliente').html('Cliente: ' + retorno.id + " - " + retorno.nome)
				$('#txtId').val(retorno.id)
				$('#txtNome').val(retorno.nome)
				$('#txtDataNascimento').val(retorno.data_nascimento)
				$('#txtEmail').val(retorno.email)
				$('#txtCPF').val(retorno.cpf)
				$('#txtAtivo').attr("checked", retorno.ativo)
				
				retorno.enderecos.forEach(function(endereco){
					$('#ListaEndereco').append('<button id="idEndereco_' + endereco.id + '" value=' + JSON.stringify(endereco).replaceAll(' ','-') + ' type="submit" class="list-group-item list-group-item-action" onclick="exibirEndereco(' + endereco.id + ')" data-toggle="list" role="tab" aria-controls="home">' + endereco.id + ' - ' + endereco.descricao + '</button>')
				})
				
				retorno.formas.forEach(function(forma){
					$('#ListaFormas').append('<button id="idForma_' + forma.id + '" value=' + JSON.stringify(forma).replaceAll(' ','-') + ' type="submit" class="list-group-item list-group-item-action" onclick="exibiForma(' + forma.id + ')"  data-toggle="list" role="tab" aria-controls="home">' + forma.id + ' - ' + forma.numero_cartao + '</button>')
				})
				$('#btnAtualizar').val(retorno.id)
			});
			$('#modalConsultaCliente').modal('show')
		});
		
		function exibirEndereco(id){
			var endereco = JSON.parse($('#idEndereco_' + id).val().replaceAll('-',' '))
			$('#txtIdEndereco').val(endereco.id)
			$('#txtDescricao').val(endereco.descricao)
			$('#txtCEP').val(endereco.cep)
			$('#txtBairro').val(endereco.bairro)
			$('#txtCidade').val(endereco.cidade)
			$('#txtEstado').val(endereco.estado)
			$('#txtLogradouro').val(endereco.logradouro)
			$('#txtComplemento').val(endereco.complemento)
			$('#txtReferencia').val(endereco.referencia)
			$('#txtNumero').val(endereco.numero)
			$('#txtPais').val(endereco.pais)
			
			$('#txtTipoLogradouro').append('<option value="' + endereco.tipo_logradouro.id + '" selected >' + endereco.tipo_logradouro.descricao + '</option>')
		}
		
		function exibiForma(id){
			var forma = JSON.parse($('#idForma_' + id).val().replaceAll('-',' '))
			$('#txtIdForma').val(forma.id)
			$('#txtNumeroCartao').val(forma.numero_cartao)
			$('#txtBandeira').val(forma.bandeira)
			$('#txtValidade').val(forma.validade)
		}
		
		// Função chamada quando a Modal é fechada
		$("#modalConsultaCliente").on('hidden.bs.modal',function(){
			$('#ListaEndereco button').remove()
			$('#ListaFormas button').remove()
			
			$('#geral-tab').attr("active", true);
			$('#enderecos-tab').attr("active", false);
			$('#formas-tab-tab').attr("active", false);
		});
		
		$('#btnAtualizar').click(function(){
			var ativo = document.getElementById("txtAtivo").checked	
			$.post("cliente?operacao=Salvar&id=" + $(this).val() + "&ativo=" + ativo,function(retorno) {
				location.reload(true);
			});
		});
	</script>
</body>

</html>