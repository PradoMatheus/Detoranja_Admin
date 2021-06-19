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

</head>
<body>

	<%@ include file="complements/navbar.jsp"%>
	
	<% List<Produto_Desenvolvedor> listaProduto_Desenvolvedor = (List<Produto_Desenvolvedor>) request.getAttribute("listaProduto_Desenvolvedor"); %>

	<div class="container">
		<div class="py-5 text-center">
			<h2>Lista de Desenvolvedores</h2>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Descrição</th>
					<th scope="col">Editar</th>
					<th scope="col">Excluir</th>
				</tr>
			</thead>
			<tbody>
			<%
				if(listaProduto_Desenvolvedor != null){
					for (Produto_Desenvolvedor produto_desenvolvedor : listaProduto_Desenvolvedor){
						out.print("<tr>");
						out.print("<th scope='row'>" + produto_desenvolvedor.getId() + "</th>");
						out.print("<td>" + produto_desenvolvedor.getDescricao() + "</td>");
						out.print("<td><button type='button' class='btn btn-outline-danger'>Editar</button></td>");
						out.print("<td><button type='button' class='btn btn-outline-danger'>Excluir</button></td>");
						out.print("</tr>");
					}
				} else{
					out.print("<tr>");
					out.print("<td style='text-align: center' colspan='5'>Sem Produtos Cadastrados!</td>");
					out.print("</tr>");
				}
			%>
			</tbody>
		</table>
	</div>

	<!-- JS -->
	<script src="./Bootstrap/js/jquery.min.js"></script>
	<script src="./Bootstrap/js/popper.min.js"></script>
	<script src="./Bootstrap/js/bootstrap.min.js"></script>
</body>

<footer style="position: absolute; bottom: 0; width: 100%;">
	<div style="float: right; padding: 4em">
		<a href='cadastro_produtos.jsp'> <img src="./icons/add.svg"
			alt="..." style="width: 40px; color: white;">
		</a>
	</div>
</footer>
</html>