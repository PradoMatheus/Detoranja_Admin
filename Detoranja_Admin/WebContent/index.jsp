<head>
<title>Administrador Detoranja</title>
<meta charset="UTF-8">
<link href="./Bootstrap/css/bootstrap.css" rel="stylesheet"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>
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
	<footer> </footer>

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

	<script type="text/javascript">
		$("#btn_login").submit(function() {
			alert("Hello! I am an alert box!!");
		});
	</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script src="./Bootstrap/js/bootstrap.min.js"></script>

</body>
