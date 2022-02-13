<%@page import="br.edu.fatec.detoranja.dominio.Produto_Desenvolvedor"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.edu.fatec.detoranja.dominio.Produto"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Administrador Detoranja</title>
<meta charset="UTF-8">

<!-- CSS -->
<link rel="stylesheet" href="./Bootstrap/css/bootstrap.min.css">
<link rel="icon" href="./complements/logo_browser.png">
<style type="text/css">
	.colDev{
		text-align: center; 
    	vertical-align: middle;
	}
</style>

</head>
<body>

	<%@ include file="complements/navbar.jsp"%>

	<%
	List<Produto_Desenvolvedor> listaProduto_Desenvolvedor = (List<Produto_Desenvolvedor>) request
			.getAttribute("listaProduto_Desenvolvedor");
	%>

	<div class="container" style="height: 85%">
		<div class="py-5 text-center">
			<h2>Lista de Desenvolvedores</h2>
		</div>
		<div class="form-row" style="position: relative;">
		  <div class="form-group col-md-3">
		  		<button class="btn btn-primary novoDesenvolvedor" type="submit"><img src="./icons/add.svg" height="20px" style="margin-right: 5px">Adicionar Desenvolvedor</button>
		  </div>
		  <div class="form-group col-md-9" style="align-items: center">
	  		<div class="input-group">
				<input type="text" class="form-control filtroDesenvolvedor" aria-describedby="pesquisar" placeholder="Pesquisar" id="txtPesquisa" name="txtPesquisa">
				<div class="input-group-prepend">
					<span class="input-group-text" id="txtPesquisa"><img src="./icons/search.svg" height="20px"></span>
				</div>
			</div>
		  </div>
		</div>
		<table class="table table-bordered table-sm" id="tabela_desenvolvedor">
			<thead>
				<tr>
					<th class="colDev" style="width: 5%" scope="col">ID</th>
					<th scope="col" style="width: 85%">Descrição</th>
					<th class="colDev" scope="col" style="width: 15%">Editar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (listaProduto_Desenvolvedor != null) {
					for (Produto_Desenvolvedor produto_desenvolvedor : listaProduto_Desenvolvedor) {
						out.print("<tr id='linha_desenvolvedor_" + produto_desenvolvedor.getId() + "'>");
						out.print("<th class='colDev' style='width: 5%' scope='row'>" + produto_desenvolvedor.getId() + "</th>");
						out.print("<td style='width: 85%' id='desenvolvedor_" + produto_desenvolvedor.getId() + "'>" + produto_desenvolvedor.getDescricao() + "</td>");
						out.print("<td class='colDev' style='width: 10%'><button type='button' class='btn btn-outline-danger editarDesenvolvedor' value='"
						+ produto_desenvolvedor.getId() + "'>Editar</button></td>");
						out.print("</tr>");
					}
				} else {
					out.print("<tr>");
					out.print("<td style='text-align: center' colspan='5'>Sem Produtos Cadastrados!</td>");
					out.print("</tr>");
				}
				%>
			</tbody>
		</table>
	</div>
	
	<div>		
		<nav aria-label="Navegação de página exemplo">
		  <ul class="pagination justify-content-center">
		    <li class="page-item"><a class="page-link" href="#" style="pointer-events: none;">1</a></li>
		    <li class="page-item"><a class="page-link" href="#">2</a></li>
		    <li class="page-item"><a class="page-link" href="#">3</a></li>
		    <li class="page-item"><a class="page-link" href="#">Próximo</a></li>
		  </ul>
		</nav>
	</div>
	
	<!-- Modal de Edição e cadatro de um Desenvolvedor-->
	<div class="modal fade" id="modalDesenvolvedor" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form action="produto_desenvolvedor" method="post">
				<div class="modal-header">
					<h5 class="modal-title" id="labelDesenvolvedor">Novo
						Desenvolvedor</h5>
				</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="txtId" class="col-form-label">ID</label>
							<input type="text" class="form-control col-md-4" id="txtId" name="txtId" readonly>
							<label for="txtDesenvolvedor" class="col-form-label">Desenvolvedor</label>
							<input type="text" class="form-control" id="txtDesenvolvedor" name="txtDesenvolvedor"
								required="required">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-danger" name="operacao" value="Excluir" id="btnExcluir">Excluir</button>
						<button type="submit" class="btn btn-primary" name="operacao" value="Salvar" id="btnSalvar">Salvar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- JS -->
	<script src="./Bootstrap/js/jquery.min.js"></script>
	<script src="./Bootstrap/js/popper.min.js"></script>
	<script src="./Bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(document).ready(
				function() {
					$(".editarDesenvolvedor").click(function() {
						$('#labelDesenvolvedor').html('Editar Desenvolvedor')
						$("#btnExcluir").attr("disabled", false);
						$("#txtDesenvolvedor").val($("#desenvolvedor_" + $(this).val()).text());
						$("#txtId").val($(this).val())
						$("#btnSalvar").html('Atualizar')
						$('#modalDesenvolvedor').modal('show')
					});
					$(".novoDesenvolvedor").click(function() {
						$('#labelDesenvolvedor').html('Novo Desenvolvedor')
						$("#btnExcluir").attr("disabled", true);
						$("#txtId").val(0)
						$("#btnSalvar").html('Salvar')
						$("#txtDesenvolvedor").val('');
						$('#modalDesenvolvedor').modal('show')
					});
					$(".filtroDesenvolvedor").keyup(function(){
						// Busca o valor digitado pelo usuario no input
						var filtro_digitado = $(this).val().toUpperCase();
						
						// Recupera a tabela de deseja filtrar
						var tabela = document.getElementById("tabela_desenvolvedor");
						// Percorre todas as colunas da tabela
						for (var i = 1, row; row = tabela.rows[i]; i++) {
							// Recupera o id do fornecedor que esta identificado na linha <td> e filtra apenas para exibir o numero
							var id_desenvolvedor = row.id.split("").filter(n => (Number(n) || n == 0)).join("")
							// Recupera a descricão da celula
							var descricao = $("#desenvolvedor_" + id_desenvolvedor).text().toUpperCase()
							
							// Compara os textos e caso exista, é exibida na View 
							if(descricao.includes(filtro_digitado, 0)){		
								$("#linha_desenvolvedor_" + id_desenvolvedor).show();
							} else {
								$("#linha_desenvolvedor_" + id_desenvolvedor).hide();
							}
						}
					});
				});
	</script>
</body>

</html>