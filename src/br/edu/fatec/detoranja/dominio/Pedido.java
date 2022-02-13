package br.edu.fatec.detoranja.dominio;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido implements IDominio {

	private int id,
		quantidade;
	private double valorTotal;
	private LocalDateTime	data_pedido;					// DATA DA REALIZAÇÂO DO PEDIDO
	private Pedido_Status status;
	private Cliente cliente;
	private Endereco endereco;
	private List<Forma_Pagamento> listforma_Pagamentos;
	private List<Cupom> listcupoms;
	private List<Pedido_Itens> listprodutos;
	private Pedido_Frete frete;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDateTime getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(LocalDateTime data_pedido) {
		this.data_pedido = data_pedido;
	}
	public Pedido_Status getStatus() {
		return status;
	}
	public void setStatus(Pedido_Status status) {
		this.status = status;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Forma_Pagamento> getListforma_Pagamentos() {
		return listforma_Pagamentos;
	}
	public void setListforma_Pagamentos(List<Forma_Pagamento> listforma_Pagamentos) {
		this.listforma_Pagamentos = listforma_Pagamentos;
	}
	public List<Cupom> getListcupoms() {
		return listcupoms;
	}
	public void setListcupoms(List<Cupom> listcupoms) {
		this.listcupoms = listcupoms;
	}
	public List<Pedido_Itens> getListprodutos() {
		return listprodutos;
	}
	public void setListprodutos(List<Pedido_Itens> listprodutos) {
		this.listprodutos = listprodutos;
	}
	public Pedido_Frete getFrete() {
		return frete;
	}
	public void setFrete(Pedido_Frete frete) {
		this.frete = frete;
	}
}
