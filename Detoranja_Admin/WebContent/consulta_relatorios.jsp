<!DOCTYPE html>
<%@page import="java.time.LocalDateTime"%>
<%@page import="br.edu.fatec.detoranja.dominio.Troca_Admin"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="br.edu.fatec.detoranja.dominio.Pedido"%>
<%@page import="br.edu.fatec.detoranja.dominio.Cupom"%>
<%@page import="java.util.List"%>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<% 	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	LocalDateTime agora = LocalDateTime.now();%>
<title>Relatório</title>
<%@ include file="complements/complements_css.jsp"%>
</head>
<body class="sb-nav-fixed">
	<%@ include file="complements/navbar.jsp"%>
	<div id="layoutSidenav_content">
		<!--  -->
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Relatório de Livros Vendidos
				</h1>
				<form id="formPesquisa">
				<div class="card mb-4">
					<div class="row" style="margin-top: 5px">
							<div class="col-md-2 mb-3" style="margin-left: 20px">
								<label for="txtDataInicial" class="txtDataInicial">Data Inicial
								</label> <input type="date" class="form-control" value="<% out.print(agora.format(formatter)); %>"
									id="txtDataInicial" name="txtDataInicial">
							</div>
							<div class="col-md-2 mb-3">
								<label for="txtDataFinal" class="txtDataFinal">Data Final
								</label> <input type="date" class="form-control" value="<% out.print(agora.format(formatter)); %>"
									id="txtDataFinal" name="txtDataFinal">
							</div>
							<div class="col-md-2 mb-3">
								<label for="txtFiltro" class="txtFiltro">Agrupar
								</label> <select id="txtFiltro" class="form-select" aria-label="Filtro de Opções" style="pointer-events: none">
								  <option selected value = true >Por Livro</option>
								  <option value = false >Por Categoria</option>
								</select>
							</div>
							<div class="col-md-2 mb-3">
								<label for="txtOrdenacao" class="txtOrdenacao">Ordernação
								</label> <select id="txtOrdenacao" class="form-select" aria-label="Filtro de Ordenação" style="pointer-events: none">
								  <option selected value = "DESC" >Mais Vendidos</option>
								  <option value = "ASC" >Menos Vendidos</option>
								</select>
							</div>
							<div class="col-md-1 mb-3">
								<label for="txtQtde" class="txtQtde">Quantidade
								</label> <select id="txtQtde" class="form-select" aria-label="Filtro de Quantidade" style="pointer-events: none">
								  <option selected value = 5 >5</option>
								  <option value = 10 >10</option>
								  <option value = 15 >15</option>
								  <option value = 20 >20</option>
								</select>
							</div>
							<div class="col-md-1 mb-3">
								<label for="txtPesquisar" class="txtPesquisar">Pesquisar
								</label> <input type="submit" class=" form-control btn btn-primary" value="Pesquisar"
									id="txtPesquisar" name="txtPesquisar">
							</div>
					</div>
					<div class="card-body" id="id_canvas">
						<canvas id="myChart" width="400" height="150"></canvas>
					</div>
				</div>
				</form>
			</div>
		</main>
	</div>
	<%@ include file="complements/complements_js.jsp"%>
	
	<script>
	
		$("#formPesquisa").submit(function(event){
			event.preventDefault();
			
			$.get("relatorios_produto?operacao=Buscar&di=" + $("#txtDataInicial").val() + "&df=" + $("#txtDataFinal").val() + "&f=" + $("#txtFiltro").val() + "&o=" + $("#txtOrdenacao").val() + "&q=" + $("#txtQtde").val(),function(retorno) {
				console.log(retorno)
				
				//var dataInicial = retorno.datainicial.day + "/" + retorno.datainicial.month + "/" + retorno.datainicial.year
				var dataFinal = retorno.dataFinal.day + "/" + retorno.dataFinal.month + "/" + retorno.dataFinal.year
				var dataset = []
				var data = {}
				var labels = []
				
				//for (var key in retorno.listaprodutos){
			      //	retorno.listaprodutos[key].forEach(function(lista){	
			      	//	if(!data.hasOwnProperty(lista.produto.id)){
			      		//	data[lista.produto.id] = {
			      			//	label: lista.produto.nome,
			      			//	fillColor: random_rgba(),
	                        //   strokeColor: random_rgba(),
	                        //  pointColor: random_rgba(),
	                        //    data: [lista.quantidade]
			      		//	}
			      	//		console.log('não existe')
			      	//	} else {
			      	//		data[lista.produto.id].data.push(lista.quantidade)
			      	//		console.log(data[lista.produto.id].data)
			      	//	}
					//})
					//labels.push(key)
				//}
				
				var meses = ["Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"];
				
				for (var ano = retorno.datainicial.year; ano <= retorno.dataFinal.year; ano++) {
					console.log(ano + '-' + retorno.dataFinal.year)
					if(ano == retorno.datainicial.year){
						if(ano == retorno.dataFinal.year){
							for (var mes = retorno.datainicial.month; mes <= retorno.dataFinal.month; mes++) {
								console.log(meses[(mes - 1)] + ' de ' + ano)
								labels.push(meses[(mes - 1)] + ' de ' + ano)
							}
						} else {
							for (var mes = retorno.datainicial.month; mes <= 12; mes++) {
								console.log(meses[(mes - 1)] + ' de ' + ano)
								labels.push(meses[(mes - 1)] + ' de ' + ano)
							}
						}
					} else if(ano == retorno.dataFinal.year){
						for (var mes = 1; mes <= retorno.dataFinal.month; mes++) {
							console.log(meses[(mes - 1)] + ' de ' + ano)
							labels.push(meses[(mes - 1)] + ' de ' + ano)
						}
					} else {
						for (var mes = 1; mes <= 12; mes++) {
							console.log(meses[(mes - 1)] + ' de ' + ano)
							labels.push(meses[(mes - 1)] + ' de ' + ano)
						}
					}
				}
				
				retorno.listaProdutos.forEach(function(lista){
					var label = Object.keys(lista)[0]
					dataset.push({
                            label: label,
                            fill:false,
                            fillColor: random_rgba(),
                            strokeColor: random_rgba(),
                            pointColor: random_rgba(),
                            borderColor: random_rgba(),
                   		    //backgroundColor: data[key].pointColor,
                            data: lista[label]
                    })
				})
				
				//for (var key in data){
				//	console.log(data[key])
				//	dataset.push({
                 //           label: data[key].label,
                  //          fill:false,
                   //         fillColor: data[key].fillColor,
                   //         strokeColor: data[key].strokeColor,
                   //         pointColor: data[key].pointColor,
                   //         borderColor: data[key].pointColor,
                            //backgroundColor: data[key].pointColor,
                   //         data: data[key].data
                   // })
				//}
				
				$("#myChart").remove();
				$("#id_canvas").append('<canvas id="myChart" width="400" height="150"></canvas>');
				
				var ctx = document.getElementById('myChart').getContext('2d');
				var myChart = new Chart(ctx, {
					type: 'line',
					data: {
					    labels: labels,
					    datasets: dataset
					}
					});
			});
		});
		
		// GERAR COR ALEATORIAMENTE
		function random_rgba() {
		    var o = Math.round, r = Math.random, s = 255;
		    return 'rgba(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + r().toFixed(1) + ')';
		}

	</script>
</body>
</html>
