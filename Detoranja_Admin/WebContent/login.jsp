<head>
    <title>Administrador Detoranja</title>
    <meta charset="UTF-8">
    <link href="./Bootstrap/css/bootstrap.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
    <main>
    <form action="administrador" method="POST">
        <div style="padding: 5rem"></div>
        <div class="container" style="width: 18rem">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Login</h5>
                    <div class="form-group" >
                        <input type="text" class="form-control" id="txtemail" name="txtemail" placeholder="E-mail">
                    </div>
                    <div class="form-group" >
                        <input type="password" class="form-control" id="txtpassword" name="txtpassword" placeholder="Senha">
                    </div>
                    <button class="btn waves-effect waves-light" type="submit" name="operacao" value="Buscar">Login</button>
                    </div>
            </div>
        </div>
        </form>
    </main>
    <footer>

    </footer>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script src="./Bootstrap/js/bootstrap.min.js"></script>
        
</body>
