<html>
	<head>
		<title>Administrador Detoranja</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./Bootstrap/css/bootstrap.min.css">
		<link rel="icon" href="./complements/logo_browser.png">
	</head>
	<body>
		
		<%
		// VALIDA SE O USUARIO ESTÁ LOGADO, CASO NÃO ESTEJA É DIRECIONA A TELA DE LOGIN
		if (session.getAttribute("AdminUser") != null)
			response.sendRedirect("principal.jsp");
		%>
		<main>
			<form id="form_login" action="administrador" method="POST">
				<div style="padding: 5rem"></div>
				<div class="container" style="width: 18rem">
					<div class="card text-center">
						<div class="card-body">
							<h5 class="card-title">Login</h5>
							<div class="form-group">
								<input type="text" class="form-control" id="txtemail"
									name="txtemail" placeholder="E-mail">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="txtpassword"
									name="txtpassword" placeholder="Senha">
							</div>
							<button class="btn waves-effect waves-light" type="submit"
								name="operacao" value="Buscar">Login</button>
						</div>
					</div>
				</div>
			</form>
		</main>

		<script src="./Bootstrap/js/jquery-3.3.1.slim.min.js"></script>
		<script src="./Bootstrap/js/jquery.mask.min.js"></script>
		<script src="./Bootstrap/js/jquery.mask.js"></script>
		<script src="./Bootstrap/js/popper.min.js"></script>
		<script src="./Bootstrap/js/bootstrap.min.js"></script>
	
		<script type="text/javascript">
			$("#form_login").submit(function(event) {
				var email = document.getElementById("txtemail").value;
				if (email === "" || !email.includes("@")) {
					alert("Coloque um email valido!")
					event.preventDefault()
					return;
				}
				var senha = document.getElementById("txtpassword").value;
				if (senha === "") {
					alert("Coloque uma senha valida!")
					event.preventDefault()
				}
			})
		</script>

	</body>
	
	<footer> 
	</footer>
</html>
