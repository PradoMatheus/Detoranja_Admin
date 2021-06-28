<%@page import="br.edu.fatec.detoranja.dominio.Produto_Plataforma"%>
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
	List<Produto_Plataforma> listaProduto_Plataforma = (List<Produto_Plataforma>) request
			.getAttribute("listaProduto_Plataforma");
	%>

	<div class="container" style="height: 85%">
		<div class="py-5 text-center">
			<h2>Lista de Plataformas</h2>
		</div>
		<div class="form-row" style="position: relative;">
		  <div class="form-group col-md-3">
		  		<button class="btn btn-primary novaPlataforma" type="submit"><img src="./icons/add.svg" height="20px" style="margin-right: 5px">Adicionar Plataforma</button>
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
		<table class="table table-bordered table-sm" id="tabela_plataforma">
			<thead>
				<tr>
					<th class="colDev" style="width: 5%" scope="col">ID</th>
					<th scope="col" style="width: 85%">Descri��o</th>
					<th class="colDev" scope="col" style="width: 15%">Editar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (listaProduto_Plataforma != null && listaProduto_Plataforma.size() > 0) {
					for (Produto_Plataforma produto_plataforma : listaProduto_Plataforma) {
						out.print("<tr id='linha_plataforma_" + produto_plataforma.getId() + "'>");
						out.print("<th class='colDev' style='width: 5%' scope='row'>" + produto_plataforma.getId() + "</th>");
						out.print("<td style='width: 85%' id='plataforma_" + produto_plataforma.getId() + "'>" + produto_plataforma.getDescricao() + "</td>");
						out.print("<td class='colDev' style='width: 10%'><button type='button' class='btn btn-outline-danger editarPlataforma' value='"
						+ produto_plataforma.getId() + "'>Editar</button></td>");
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
		<nav aria-label="Navega��o de p�gina exemplo">
		  <ul class="pagination justify-content-center">
		    <li class="page-item"><a class="page-link" href="#" style="pointer-events: none;">1</a></li>
		    <li class="page-item"><a class="page-link" href="#">2</a></li>
		    <li class="page-item"><a class="page-link" href="#">3</a></li>
		    <li class="page-item"><a class="page-link" href="#">Pr�ximo</a></li>
		  </ul>
		</nav>
	</div>
	
	<!-- Modal de Edi��o e cadatro de um Desenvolvedor-->
	<div class="modal fade" id="modalPlataforma" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form action="produto_plataforma" method="post" enctype="application/x-www-form-urlencoded">
					<div class="modal-header">
						<h5 class="modal-title" id="labelPlataforma">Nova Plataforma</h5>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="txtId" class="col-form-label">ID</label>
							<input type="text" class="form-control col-md-4" id="txtId" name="txtId" readonly>
							<label for="txtPlataforma" class="col-form-label">Plataforma</label>
							<input type="text" class="form-control" id="txtPlataforma" name="txtPlataforma" required="required">
							<label for="txtUploadImage" class="col-form-label">Icone de Exibi��o</label>
							<div class="custom-file col-form-label">
							   <input type="file" class="custom-file-input selectedImage" id="txtUploadImage" name="txtUploadImage" aria-describedby="inputGroupFileAddon01" accept="image/*">
							   <label class="custom-file-label" for="txtUploadImage">Escolher uma Imagem</label>
							</div>
							<div class="form-control" style="height: 150px;">
								<img src="./icons/sem_foto.png" alt="..." style="height: 50px;margin-top: 10%" class="img-thumbnail rounded mx-auto d-block icon_plataforma">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-danger excluir_plataforma" name="operacao" value="Excluir" id="btnExcluir">Excluir</button>
						<button type="submit" class="btn btn-primary salvar_plataforma" name="operacao" value="Salvar" id="btnSalvar">Salvar</button>
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
					$(".editarPlataforma").click(function() {
						$('#labelPlataforma').html('Editar Plataforma')
						$("#btnExcluir").attr("disabled", false);
						$("#txtPlataforma").val($("#desenvolvedor_" + $(this).val()).text());
						$("#txtId").val($(this).val())
						$("#btnSalvar").html('Atualizar')
						$('#modalPlataforma').modal('show')
					});
					$(".novaPlataforma").click(function() {
						$('#labelPlataforma').html('Nova Plataformar')
						$("#btnExcluir").attr("disabled", true);
						$("#txtId").val(0)
						$("#btnSalvar").html('Salvar')
						$("#txtPlataforma").val('');
						$('#modalPlataforma').modal('show')
					});
					// Fun��o chamada quando o input � alterado
					$(".selectedImage").change(function(){
						var file = this.files[0];
						
						if(file != undefined){
							var reader = new FileReader();
							reader.onload = function(e){
								$(".icon_plataforma").attr('src', e.target.result);
								$(".icon_plataforma").css({'height': '130px', 'margin-top': '0%'});
							}
							reader.readAsDataURL(file);
						}
					})
					// Fun��o chamada quando a Modal � fechada
					$("#modalPlataforma").on('hidden.bs.modal',function(){
						$(".icon_plataforma").attr('src', './icons/sem_foto.png');
						$(".icon_plataforma").css({'height': '50px', 'margin-top': '10%'});
						document.getElementById("txtUploadImage").value = '';
					});
					// Fun��o para alterar a Imagem
					function readURL(file){
						if(file != undefined){
							var reader = new FileReader();
							reader.onload = function(e){
								$(".icon_plataforma").attr('src', e.target.result);
								$(".icon_plataforma").css({'height': '130px', 'margin-top': '0%'});
							}
							reader.readAsDataURL(file);
						}
					}
					//Fun��o chamada quando � digitado alguma letra no campo de pesquisa
					$(".filtroDesenvolvedor").keyup(function(){
						// Busca o valor digitado pelo usuario no input
						var filtro_digitado = $(this).val().toUpperCase();
						
						// Recupera a tabela de deseja filtrar
						var tabela = document.getElementById("tabela_plataforma");
						// Percorre todas as colunas da tabela
						for (var i = 1, row; row = tabela.rows[i]; i++) {
							// Recupera o id do fornecedor que esta identificado na linha <td> e filtra apenas para exibir o numero
							var id_plataforma = row.id.split("").filter(n => (Number(n) || n == 0)).join("")
							// Recupera a descric�o da celula
							var descricao = $("#plataforma_" + id_plataforma).text().toUpperCase()
							
							// Compara os textos e caso exista, � exibida na View 
							if(descricao.includes(filtro_digitado, 0)){		
								$("#linha_plataforma_" + id_plataforma).show();
							} else {
								$("#linha_plataforma_" + id_plataforma).hide();
							}
						}
					});
				});
	</script>
</body>

</html>