package br.edu.fatec.detoranja.dominio;

import java.time.LocalDateTime;

public class Troca_LogAdmin implements IDominio {

	int id;
	Pedido_Status status;
	LocalDateTime data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pedido_Status getStatus() {
		return status;
	}
	public void setStatus(Pedido_Status status) {
		this.status = status;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}	
}
