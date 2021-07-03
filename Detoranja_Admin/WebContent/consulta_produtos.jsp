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
	
	</head>
	<body>

	<%@ include file="complements/navbar.jsp"%>

	<%
	List<Produto> listaProduto = (List<Produto>) request
			.getAttribute("listaProduto");
	%>

	<div class="container" style="height: 85%">
		<div class="py-5 text-center">
			<h2>Lista de Produtos</h2>
		</div>
		<div class="form-row">
		  <div class="form-group col-md-3">
		  		<button class="btn btn-primary novaProduto" type="submit"><img src="./icons/add.svg" height="20px" style="margin-right: 5px">Adicionar Produto</button>
		  </div>
		  <div class="form-group col-md-9" style="align-items: center">
	  		<div class="input-group">
				<input type="text" class="form-control filtroProduto" aria-describedby="pesquisar" placeholder="Pesquisar" id="txtPesquisa" name="txtPesquisa">
				<div class="input-group-prepend">
					<span class="input-group-text" id="txtPesquisa"><img src="./icons/search.svg" height="20px"></span>
				</div>
			</div>
		  </div>
		</div>
		<table class="table table-bordered table-sm" id="tabela_produto">
			<thead>
				<tr>
					<th class="colDev mx-auto" style="width: 5%" scope="col">ID</th>
					<th scope="col" style="width: 60%">Descrição</th>
					<th scope="col" style="width: 25%">Data Alteração</th>
					<th class="colDev mx-auto" scope="col" style="width: 10%">Editar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (listaProduto != null && listaProduto.size() > 0) {
					for (Produto produto : listaProduto) {
						out.print("<tr id='linha_produto_" + produto.getId() + "'>");
						out.print("<th class='colDev mx-auto' style='width: 5%' scope='row'>" + produto.getId() + "</th>");
						out.print("<th class='colDev' id='produto_" + produto.getId() + "' style='width: 60%' scope='row'>" + produto.getNome() + "</th>");
						out.print("<td style='width: 25%'>" + produto.getData_alteracao() + "</td>");
						out.print("<td class='colDev mx-auto' style='width: 10%'><button type='button' class='btn btn-outline-danger editarPlataforma' value='"
						+ produto.getId() + "'>Editar</button></td>");
						out.print("</tr>");
					}
				} else {
					out.print("<tr>");
					out.print("<td style='text-align: center' colspan='5'>Sem Plataformas Cadastradas!</td>");
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

	<!-- JS -->
	<script src="./Bootstrap/js/jquery.min.js"></script>
	<script src="./Bootstrap/js/popper.min.js"></script>
	<script src="./Bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(document).ready(
				function() {
					// Clicando no novo produto é direcionado para a aba de cadastro
					$('.novaProduto').click(function(){
						window.location.href = 'cadastro_produtos.jsp'
					});
					// Função chamada quando é digitado alguma letra no campo de pesquisa
					$(".filtroProduto").keyup(function(){
						// Busca o valor digitado pelo usuario no input
						var filtro_digitado = $(this).val().toUpperCase();
						
						// Recupera a tabela de deseja filtrar
						var tabela = document.getElementById("tabela_produto");
						// Percorre todas as colunas da tabela
						for (var i = 1, row; row = tabela.rows[i]; i++) {
							// Recupera o id do fornecedor que esta identificado na linha <td> e filtra apenas para exibir o numero
							var id_plataforma = row.id.split("").filter(n => (Number(n) || n == 0)).join("")
							// Recupera a descricão da celula
							var descricao = $("#produto_" + id_plataforma).text().toUpperCase()
							
							// Compara os textos e caso exista, é exibida na View 
							if(descricao.includes(filtro_digitado, 0)){		
								$("#linha_produto_" + id_plataforma).show();
							} else {
								$("#linha_produto_" + id_plataforma).hide();
							}
						}
					});
				});
	</script>
</body>
</html>