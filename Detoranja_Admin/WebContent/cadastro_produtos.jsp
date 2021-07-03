
<html>
<head>
<title>Administrador Detoranja</title>
<meta charset="UTF-8">

<!-- CSS -->
<link rel="stylesheet" href="./Bootstrap/css/bootstrap.min.css">
<link rel="icon" href="./complements/logo_browser.png">

<style type="text/css">
	.divpadrao {
		margin-top: 10px;
		height: 350px;
	}
</style>

</head>
<%@ include file="complements/navbar.jsp"%>
<body class="bg-light">

	<div class="container">
		<form class="needs-validation" action="produto" method="post">
			<div class="py-5 text-center">
				<h2>Cadastro de Produto</h2>
			</div>
			
			<ul class="nav nav-tabs" id="myTab" role="tablist">
			  <li class="nav-item">
			    <a class="nav-link active" id="geral-tab" data-toggle="tab" href="#geral" role="tab" aria-controls="Informações Gerais" aria-selected="true">Informações Gerais</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" id="estoque-tab" data-toggle="tab" href="#fotos" role="tab" aria-controls="Fotos" aria-selected="false">Fotos</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" id="estoque-tab" data-toggle="tab" href="#plataformas" role="tab" aria-controls="Plataformas" aria-selected="false">Plataformas</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" id="historico-tab" data-toggle="tab" href="#historico" role="tab" aria-controls="Historico de Alterações" aria-selected="false">Historico de Alterações</a>
			  </li>
			</ul>
			<div class="tab-content" id="myTabContent">
			  <div class="tab-pane fade show active divpadrao" id="geral" role="tabpanel" aria-labelledby="geral-tab">
			  	<!-- DIV QUE CONTEM AS INFORMAÇÕES GERAIS DO PRODUTO -->
			  	<div class="form-group">
					<label for="txtnome">Nome do Produto</label> <input type="text"
						class="form-control" id="txtNome" name="txtNome"
						placeholder="Nome do Produto" required>
				</div>
				<div class="form-row">
					<div class="form-group col-md-5">
						<label for="txtDesenvolvedor">Desenvolvedor</label> 
						<select class="form-control" id="txtDesenvolvedor" name="txtDesenvolvedor" required>
							<option value="" selected disabled>Selecione um Desenvolvedor</option>
						</select>
					</div>
					<div class="form-group col-md-5">
						<label for="txtDistribuidora">Distribuidora</label>
						<select class="form-control" id="txtDistribuidora" name="txtDistribuidora" required>
							<option value="" selected disabled>Selecione um Distribuidor</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<label for="txtDataLancamento">Data de Lançamento</label> <input
							type="text" class="form-control" placeholder="00/00/0000"
							id="txtDataLancamento" name="txtDataLancamento" required>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-5">
						<label for="txtCategoria">Categoria</label> 
							<select class="form-control" id="txtCategoria" name="txtCategoria" required>
							<option value="" selected disabled>Selecione uma Categoria</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<label for="txtValor">Valor</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="real_conc">R$</span>
							</div>
							<input type="text" class="form-control"
								aria-describedby="real_conc" placeholder="0,00" id="txtValor"
								name="txtValor" required>
						</div>
					</div>
					<div class="form-group col-md-3"></div>
					<div class="form-group col-md-2">
						<label for="desbilitadoProduto">Status do Produto</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">
							      <input type="checkbox" aria-label="Chebox para permitir input text">
							    </div>
							</div>
							<input type="text" class="form-control" aria-describedby="desabilitar" id="txtDesabilitar" name="txtDesabilitar" value="Inativo" disabled>
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="txtDescricao">Descricao</label>
						<textarea class="form-control" id="txtDescricao"
							name="txtDescricao" placeholder="Descreva o jogo !!"></textarea>
					</div>
				</div>
			  </div>
			  <div class="tab-pane fade divpadrao" id="fotos" role="tabpanel" aria-labelledby="profile-tab">
			  <!-- DIV QUE CONTEM AS FOTOS DO ESTOQUE -->
			  </div>
			  <div class="tab-pane fade divpadrao" id="plataformas" role="tabpanel" aria-labelledby="contact-tab">
			  <!-- DIV QUE CONTEM AS PLATAFORMAS DO PRODUTO -->
			  </div>
			  <div class="tab-pane fade divpadrao" id="historico" role="tabpanel" aria-labelledby="contact-tab">
			  <!-- DIV QUE CONTEM OS HISTORICOS DO PRODUTO -->
			  </div>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary btn-lg btn-block"
					name="operacao" value="Salvar" style="margin-top: 150px">Salvar
					Produto</button>
			</div>
		</form>
	</div>

	<!-- JS -->
	<script src="./Bootstrap/js/jquery.min.js"></script>
	<script src="./Bootstrap/js/jquery.mask.min.js"></script>
	<script src="./Bootstrap/js/jquery.mask.js"></script>
	<script src="./Bootstrap/js/popper.min.js"></script>
	<script src="./Bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(document).ready(
				function() {
					$('#txtDataLancamento').mask('00/00/0000')
					$('#txtValor').mask('###.##0,00', {
						reverse : true
					})
					
					// Função para buscar as opções de categorias cadastradas e disponibiliza-las ao usuario
					$.get("produto_categoria?operacao=Lista&tipo=json",function(retorno) {
						$.each(retorno, function(key, item) {
							$("#txtCategoria").append("<option id=" + item.id + ">"+ item.descricao + "</option>");
						});
					});
					
					// Função para buscar as opções de desenvolvedor cadastradas e disponibiliza-las ao usuario
					$.get("produto_desenvolvedor?operacao=Lista&tipo=json",function(retorno) {
						$.each(retorno, function(key, item) {
							$("#txtDesenvolvedor").append("<option id=" + item.id + ">"+ item.descricao + "</option>");
						});
					});
					
					// Função para buscar as opções de distribuidor cadastradas e disponibiliza-las ao usuario
					$.get("produto_distribuidor?operacao=Lista&tipo=json",function(retorno) {
						$.each(retorno, function(key, item) {
							$("#txtDistribuidora").append("<option id=" + item.id + ">"+ item.descricao + "</option>");
						});
					});
				})
	</script>
</body>
<footer> </footer>
</html>