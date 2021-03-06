<nav class="navbar navbar-dark bg-secondary fixed-top navbar-expand-lg">

	<%
		// VALIDA SE O USUARIO EST? LOGADO, CASO N?O ESTEJA ? DIRECIONA A TELA DE LOGIN
		if (session.getAttribute("AdminUser") == null)
			response.sendRedirect("index.jsp");
	%>

	<a class="navbar-brand" href="principal.jsp">
		<img src="./complements/logo_site.png" width="30" height="30" alt="">
		Detoranja
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#conteudoNavbarSuportado"
		aria-controls="conteudoNavbarSuportado" aria-expanded="false"
		aria-label="Alterna navega??o">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse d-flex justify-content-end"
		id="conteudoNavbarSuportado">
		<ul class="navbar-nav">
			<li class="nav-item dropdown">
				<!-- <a class="nav-link dropdown-toggle"  href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"	> 
					<img src="./icons/game-controller.svg" alt="..." style="width: 25px; color: white;">
					Cadastros
				</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="./produto?operacao=Lista">Produtos</a> 
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="./produto_desenvolvedor?operacao=Lista&tipo=object">Desenvolvedores</a>
					<a class="dropdown-item" href="./produto_distribuidor?operacao=Lista&tipo=object">Distribuidoras</a>
					<a class="dropdown-item" href="./produto_categoria?operacao=Lista&tipo=object">Categorias</a>
					<a class="dropdown-item" href="./produto_plataforma?operacao=Lista&tipo=object">Plataformas</a>
				</div> -->
			</li>
			<li class="nav-item">
				<a class="nav-link" href="./cliente?operacao=Lista">
					<img src="./icons/people.svg" alt="..." style="width: 25px; color: white;">
					Clientes
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">
					<img src="./icons/briefcase.svg" alt="..." style="width: 25px; color: white;">
					Pedidos
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">
					<img src="./icons/swap-horizontal.svg" alt="..." style="width: 25px; color: white;">
					Cupons
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">
					<img src="./icons/swap-horizontal.svg" alt="..." style="width: 25px; color: white;">
					Trocas
				</a>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle"  href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"	> 
					<img src="./icons/document.svg" alt="..." style="width: 25px; color: white;">
					Relatorios
				</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="#">Vendas</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Trocas</a>
				</div>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">
					<img src="./icons/settings.svg" alt="..." style="width: 25px; color: white;">
					Minha Conta
				</a>
			</li>
			<li class="nav-item">
        		<a class="nav-link" href="./administrador?operacao=Sair">
					<img src="./icons/settings.svg" alt="..." style="width: 25px; color: white;">
					Sair
				</a>
      		</li>
		</ul>
	</div>
	
</nav>

<br><br>