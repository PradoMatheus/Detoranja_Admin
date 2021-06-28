<nav class="navbar navbar-dark bg-secondary fixed-top navbar-expand-lg">

	<%
		// VALIDA SE O USUARIO ESTÁ LOGADO, CASO NÃO ESTEJA É DIRECIONA A TELA DE LOGIN
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
		aria-label="Alterna navegação">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse d-flex justify-content-end"
		id="conteudoNavbarSuportado">
		<ul class="navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle"  href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"	> 
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
				</div>
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
        		<!-- Botão para acionar modal -->
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSair">
				  Sair
				</button>
				
				<!-- Modal -->
				<div class="modal fade" id="modalSair" role="dialog"  aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" >Logout</h5>
				      </div>
				      <div class="modal-body">
				        Deseja realmente sair do Administrador Detoranja ?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
				        <button type="button" class="btn btn-primary">sair</button>
				      </div>
				    </div>
				  </div>
				</div>
      		</li>
		</ul>
	</div>
	
</nav>

<br><br>