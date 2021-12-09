<%@page import="br.edu.fatec.detoranja.dominio.Cliente"%>
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
<title>Administrador Cliente</title>
<%@ include file="complements/complements_css.jsp"%>
</head>
<body>

	<%@ include file="complements/navbar.jsp"%>

	<%
	List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("ListaClientes");
	%>

	<div class="container-fluid px-4">
		<h1 class="mt-4">Lista de Clientes</h1>
		<div class="card mb-4">
			<div class="card-body">
				<table id="datatablesSimple">
					<thead>
						<tr>
							<th style="width: 5%;text-align: center;border:1px solid black;" scope="col">ID</th>
							<th style="width: 25%;text-align: left;border:1px solid black;">Nome</th>
							<th style="width: 30%;text-align: left;border:1px solid black;">E-Mail</th>
							<th style="width: 20%;text-align: lefr;border:1px solid black;">CPF</th>
							<th style="width: 5%;text-align: center;border:1px solid black;">Ativo</th>
							<th style="width: 15%;text-align: center;border:1px solid black;">Consultar</th>
						</tr>
					</thead>
					<tbody>
					<%
					if (listaClientes != null) {
						for (Cliente cliente : listaClientes) {
							out.print("<tr>");
							out.print("<th style='width: 5%'>" + cliente.getId() + "</th>");
							out.print("<th style='width: 25%'>" + cliente.getNome() + "</th>");
							out.print("<th style='width: 30%'>" + cliente.getEmail() + "</th>");
							out.print("<th style='width: 20%'>" + cliente.getCpf() + "</th>");
							if(cliente.getAtivo())
								out.print("<th style='width: 5%; text-align: center;'><i class='fa fa-check-circle fa-2x' ></i></th>");
							else
								out.print("<th style='width: 5%; text-align: center;'><i class='fa fa-times-circle fa-2x' ></i></th>");
							out.print(
							"<td style='width: 15%; text-align: center;'><button type='button' class='btn btn-outline-danger consultarCliente' value='"
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
		</div>
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
					  <li class="nav-item" role="presentation">
					    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#geral" type="button" role="tab" aria-controls="Informações Gerais" aria-selected="true"><i class="fas fa-user-friends"></i> Informações Gerais</button>
					  </li>
					  <li class="nav-item" role="presentation">
					    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#enderecos" type="button" role="tab" aria-controls="enderecos" aria-selected="false"><i class="far fa-address-card"></i> Endereços</button>
					  </li>
					  <li class="nav-item" role="presentation">
					    <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#formas" type="button" role="tab" aria-controls="contact" aria-selected="false"><i class="far fa-credit-card"></i> Cartões</button>
					  </li>
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
								<div class="col-md-3 mb-3">
									<label for="txtGenero" class="col-form-label">Gênero</label> <input
										type="text" class="form-control" id="txtGenero" name="txtGenero"
										readonly>
								</div>
								<div class="col-md-3 mb-3">
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
								<div class="col-md-3 mb-3">
									<label for="txtCPF" class="col-form-label">CPF</label> <input
										type="text" class="form-control" id="txtCPF" name="txtCPF"
										readonly>
								</div>
								<div class="col-md-3 mb-3">
									<label for="txtTelefone" class="col-form-label">Telefone</label> <input
										type="text" class="form-control" id="txtTelefone" name="txtTelefone"
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
												<label for="txtNomeImpresso" class="col-form-label">Nome
													Impresso no cartão</label> <input type="text" class="form-control"
													id="txtNomeImpresso" name="txtNomeImpresso"
													required="required" readonly>
											</div>
										</div>
										<div class="row">
											<div class="col-md-8 mb-3">
												<label for="txtNumeroCartao" class="col-form-label">Número
													do Cartão</label> <input type="text" class="form-control"
													id="txtNumeroCartao" name="txtNumeroCartao"
													required="required" readonly>
											</div>
											<div class="col-md-4 mb-3">
												<label for="txtBandeira" class="col-form-label">Bandeira</label>
												<input type="text" class="form-control" id="txtBandeira"
													name="txtBandeira" required="required" readonly>
											</div>
										</div>
										<div class="row">
											<div class="col-md-8 mb-3">
												<label for="txtVencimento" class="col-form-label">Vencimento</label>
												<input type="text" class="form-control" id="txtVencimento"
													name="txtVencimento" required="required" readonly>
											</div>
											<div class="col-md-4 mb-3">
												<label for="txtCVV" class="col-form-label">CVV</label>
												<input type="password" class="form-control" id="txtCVV"
													name="txtCVV" required="required" readonly>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" id="btnCancelar"
							data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-primary" id="btnAtualizar">Atualizar</button>
					</div>
				</div>
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
			$('#modalConsultaCliente').modal('hide');
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
		$('.consultarCliente').click(function() {
			$('#modalCarregamento').modal('show')
				$.get("cliente?operacao=Buscar&id=" + $(this).val(),function(retorno) {
					
					$('#labelCliente').html('Cliente: ' + retorno.id + " - " + retorno.nome)
					$('#txtId').val(retorno.id)
					$('#txtNome').val(retorno.nome)
					$("#txtGenero").val(retorno.genero.descricao)
					$('#txtDataNascimento').val(retorno.data_nascimento.day + '/' + retorno.data_nascimento.month + '/' + retorno.data_nascimento.year)
					console.log(retorno.data_nascimento)
					$('#txtEmail').val(retorno.email)
					$('#txtCPF').val(retorno.cpf)
					$('#txtTelefone').val(retorno.telefone)
					$('#txtAtivo').attr("checked", retorno.ativo)
					
					retorno.enderecos.forEach(function(endereco){
						$('#ListaEndereco').append('<button id="idEndereco_' + endereco.id + '" value=' + JSON.stringify(endereco).replaceAll(' ','-') + ' type="submit" class="list-group-item list-group-item-action" onclick="exibirEndereco(' + endereco.id + ')" data-toggle="list" role="tab" aria-controls="home">' + endereco.id + ' - ' + endereco.descricao + '</button>')
					})
					
					retorno.formas.forEach(function(forma){
						$('#ListaFormas').append('<button id="idForma_' + forma.id + '" value=' + JSON.stringify(forma).replaceAll(' ','-') + ' type="submit" class="list-group-item list-group-item-action" onclick="exibiForma(' + forma.id + ')"  data-toggle="list" role="tab" aria-controls="home">' + forma.id + ' - ' + forma.numero_cartao + '</button>')
					})
					$('#btnAtualizar').val(retorno.id)
				});
			setTimeout(function() {
				$('#modalCarregamento').modal('hide')
				$('#modalConsultaCliente').modal('show')
			}, 1000);
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
			$('#txtNomeImpresso').val(forma.nome)
			$('#txtVencimento').val(forma.mes_validade + '/' + forma.ano_validade)
			$('#txtCVV').val(forma.cvv)
		}
		
		// Função chamada quando a Modal é fechada
		$("#modalConsultaCliente").on('hidden.bs.modal',function(){
			$('#ListaEndereco button').remove()
			$('#ListaFormas button').remove()
			
			$('#geral-tab').attr("active", true);
			$('#enderecos-tab').attr("active", false);
			$('#formas-tab-tab').attr("active", false);
			
			// LIMPAR CAMPO DO CARTAO
			$('#txtIdForma').val('')
			$('#txtNumeroCartao').val('')
			$('#txtBandeira').val('')
			$('#txtValidade').val('')
			$('#txtNomeImpresso').val('')
			$('#txtVencimento').val('')
			$('#txtCVV').val('')
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