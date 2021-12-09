package br.edu.fatec.detoranja.dominio;

import java.time.LocalDateTime;
import java.util.List;

public class Troca_Admin implements IDominio {

	private int id;								// ID da Troca
	private Pedido pedido;						// pedido vinculado a troca
	private Troca_MotivoAdmin motivo; 				// Motivo da Troca
	private String observacao;					// Observaocao da Troca
	private LocalDateTime data;					// Data da solicitação da troca
	private Troca_StatusAdmin status;				// Status da Troca
	private List<Troca_ItensAdmin> listTrocaItens;	// Lista de Itens da Troca
	private Cliente cliente;					// Cliente vinculado a troca
	private Cupom cupom;						// Cupom gerado no processo de troca
	private List<Troca_LogAdmin> listLogs;			// Lista de Logs da Troca
	private double total;						// Total da solicitação de troca
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Troca_MotivoAdmin getMotivo() {
		return motivo;
	}
	public void setMotivo(Troca_MotivoAdmin motivo) {
		this.motivo = motivo;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Troca_StatusAdmin getStatus() {
		return status;
	}
	public void setStatus(Troca_StatusAdmin status) {
		this.status = status;
	}
	public List<Troca_ItensAdmin> getListTrocaItens() {
		return listTrocaItens;
	}
	public void setListTrocaItens(List<Troca_ItensAdmin> listTrocaItens) {
		this.listTrocaItens = listTrocaItens;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cupom getCupom() {
		return cupom;
	}
	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	public List<Troca_LogAdmin> getListLogs() {
		return listLogs;
	}
	public void setListLogs(List<Troca_LogAdmin> listLogs) {
		this.listLogs = listLogs;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}	
}
