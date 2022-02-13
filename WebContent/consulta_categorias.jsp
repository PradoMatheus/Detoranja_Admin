<%@page import="br.edu.fatec.detoranja.dominio.Produto_Categoria"%>
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
	List<Produto_Categoria> listaProduto_Categoria = (List<Produto_Categoria>) request
			.getAttribute("listaProduto_Categorias");
	%>

	<div class="container" style="height: 85%">
		<div class="py-5 text-center">
			<h2>Lista de Categorias</h2>
		</div>
		<div class="form-row" style="position: relative;">
		  <div class="form-group col-md-3">
		  		<button class="btn btn-primary novoCategoria" type="submit"><img src="./icons/add.svg" height="20px" style="margin-right: 5px">Adicionar Categoria</button>
		  </div>
		  <div class="form-group col-md-9" style="align-items: center">
	  		<div class="input-group">
				<input type="text" class="form-control filtroCategoria" aria-describedby="pesquisar" placeholder="Pesquisar" id="txtPesquisa" name="txtPesquisa">
				<div class="input-group-prepend">
					<span class="input-group-text" id="txtPesquisa"><img src="./icons/search.svg" height="20px"></span>
				</div>
			</div>
		  </div>
		</div>
		<table class="table table-bordered table-sm" id="tabela_categoria">
			<thead>
				<tr>
					<th class="colDev" style="width: 5%" scope="col">ID</th>
					<th scope="col" style="width: 85%">Descrição</th>
					<th class="colDev" scope="col" style="width: 15%">Editar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (listaProduto_Categoria != null) {
					for (Produto_Categoria produto_categoria : listaProduto_Categoria) {
						out.print("<tr id='linha_categoria_" + produto_categoria.getId() + "'>");
						out.print("<th class='colDev' style='width: 5%' scope='row'>" + produto_categoria.getId() + "</th>");
						out.print("<td style='width: 85%' id='categoria_" + produto_categoria.getId() + "'>" + produto_categoria.getDescricao() + "</td>");
						out.print("<td class='colDev' style='width: 10%'><button type='button' class='btn btn-outline-danger editarCategoria' value='"
						+ produto_categoria.getId() + "'>Editar</button></td>");
						out.print("</tr>");
					}
				} else {
					out.print("<tr>");
					out.print("<td style='text-align: center' colspan='5'>Sem Categorias Cadastrados!</td>");
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
	<div class="modal fade" id="modalCategoria" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form action="produto_categoria" method="post">
				<div class="modal-header">
					<h5 class="modal-title" id="labelCategoriar">Novo
						Desenvolvedor</h5>
				</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="txtId" class="col-form-label">ID</label>
							<input type="text" class="form-control col-md-4" id="txtId" name="txtId" readonly>
							<label for="txtCategoria" class="col-form-label">Categoria</label>
							<input type="text" class="form-control" id="txtCategoria" name="txtCategoria"
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
					$(".editarCategoria").click(function() {
						$('#labelCategoriar').html('Editar Categoria')
						$("#btnExcluir").attr("disabled", false);
						$("#txtCategoria").val($("#categoria_" + $(this).val()).text());
						$("#txtId").val($(this).val())
						$("#btnSalvar").html('Atualizar')
						$('#modalCategoria').modal('show')
					});
					$(".novoCategoria").click(function() {
						$('#labelCategoriar').html('Nova Categoria')
						$("#btnExcluir").attr("disabled", true);
						$("#txtId").val(0)
						$("#btnSalvar").html('Salvar')
						$("#txtCategoria").val('');
						$('#modalCategoria').modal('show')
					});
					$(".filtroCategoria").keyup(function(){
						// Busca o valor digitado pelo usuario no input
						var filtro_digitado = $(this).val().toUpperCase();
						
						// Recupera a tabela de deseja filtrar
						var tabela = document.getElementById("tabela_categoria");
						// Percorre todas as colunas da tabela
						for (var i = 1, row; row = tabela.rows[i]; i++) {
							// Recupera o id do fornecedor que esta identificado na linha <td> e filtra apenas para exibir o numero
							var id_desenvolvedor = row.id.split("").filter(n => (Number(n) || n == 0)).join("")
							// Recupera a descricão da celula
							var descricao = $("#categoria_" + id_desenvolvedor).text().toUpperCase()
							
							// Compara os textos e caso exista, é exibida na View 
							if(descricao.includes(filtro_digitado, 0)){		
								$("#linha_categoria_" + id_desenvolvedor).show();
							} else {
								$("#linha_categoria_" + id_desenvolvedor).hide();
							}
						}
					});
				});
	</script>
</body>

</html>