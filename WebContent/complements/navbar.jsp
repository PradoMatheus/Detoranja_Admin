<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
	<%
		// VALIDA SE O USUARIO ESTÁ LOGADO, CASO NÃO ESTEJA É DIRECIONA A TELA DE LOGIN
		if (session.getAttribute("AdminUser") == null)
			response.sendRedirect("index.jsp");
	%>
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="principal.jsp">Detoranja</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Pesquisar" aria-label="Pesquisar" aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Configurações</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="./administrador?operacao=Sair">Sair</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
	        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Produtos</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseProduto" aria-expanded="false" aria-controls="collapseProduto">
                                <div class="sb-nav-link-icon"><i class="fas fa-book"></i></div>
                                Livros
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseProduto" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="#">Cadastro</a>
                                    <a class="nav-link" href="#">Autor</a>
                                    <a class="nav-link" href="#">Categoria</a>
                                    <a class="nav-link" href="#">Editora</a>
                                    <a class="nav-link" href="#">Idioma</a>
                                    <a class="nav-link" href="#">Precificação</a>
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">ESTOQUE</div>
                            <a class="nav-link" href="./compra?operacao=Lista">
                                <div class="sb-nav-link-icon"><i class="fas fa-dolly-flatbed"></i></div>
                                Compras
                            </a>
                            <a class="nav-link" href="./movimentacao?operacao=Lista">
                                <div class="sb-nav-link-icon"><i class="fas fa-compress-alt"></i></div>
                                Movimentações
                            </a>
                            <div class="sb-sidenav-menu-heading">CLIENTE</div>
                            <a class="nav-link" href="./cliente?operacao=Lista">
                                <div class="sb-nav-link-icon"><i class="fas fa-clipboard"></i></div>
                                Consulta
                            </a>
                            <div class="sb-sidenav-menu-heading">FORNECEDOR</div>
                            <a class="nav-link" href="./fornecedor?operacao=Lista">
                                <div class="sb-nav-link-icon"><i class="fas fa-clipboard"></i></div>
                                Cadastro
                            </a>
                            <div class="sb-sidenav-menu-heading">VENDAS</div>
                            <a class="nav-link" href="./pedido?operacao=Lista&id=0">
                                <div class="sb-nav-link-icon"><i class="fas fa-briefcase"></i></div>
                                Pedidos <span class="badge badge-primary" style="background-color: blue; margin-left: 5px">0</span>
                            </a>
                            <a class="nav-link" href="./troca_admin?operacao=Lista&id=1">
                                <div class="sb-nav-link-icon"><i class="fas fa-exchange-alt"></i></div>
                                Troca <span class="badge badge-primary" style="background-color: blue; margin-left: 5px">0</span>
                            </a>
                            <a class="nav-link" href="./cupom_admin?operacao=Lista">
                                <div class="sb-nav-link-icon"><i class="fas fa-ticket-alt"></i></div>
                                Cupons
                            </a>
                            <div class="sb-sidenav-menu-heading">Relatorios</div>
                            <a class="nav-link" href="consulta_relatorios.jsp">
                                <div class="sb-nav-link-icon"><i class="fas fa-clipboard"></i></div>
                                Livros Vendidos
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Usuario Logado:</div>
                       Matheus Prado de Melo
                    </div>
                </nav>
            </div>