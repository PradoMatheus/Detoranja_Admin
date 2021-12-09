package br.edu.fatec.detoranja.dominio;

import java.time.LocalDate;
import java.util.List;

public class Cliente implements IDominio {
	
	private int id;								// ID do Cliente
	private String nome,						// Nome do Cliente
		email,							// E-mail do Cliente
		senha,							// Senha do Cliente
		cpf;							// CPF do Cliente
	private LocalDate data_nascimento;			// Data de Nascimento do Cliente
	private Boolean ativo;						// indica se o usuario esta ativo
	private Cliente_Genero genero;				// Genero do Cliente
	private List<Endereco> enderecos;			// Lista de Endereços do Cliente
	private List<Forma_Pagamento> formas;		// Formas de Pagamentos do Cliente
	private Long telefone;						// telefone do cliente
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public List<Forma_Pagamento> getFormas() {
		return formas;
	}
	public void setFormas(List<Forma_Pagamento> formas) {
		this.formas = formas;
	}
	public Cliente_Genero getGenero() {
		return genero;
	}
	public void setGenero(Cliente_Genero genero) {
		this.genero = genero;
	}
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
}
