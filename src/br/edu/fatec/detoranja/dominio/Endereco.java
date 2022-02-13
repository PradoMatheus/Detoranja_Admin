package br.edu.fatec.detoranja.dominio;

public class Endereco implements IDominio{
	
	int id,											// Id do Endereço
		id_cliente;									// Id do cliente que é vinculado o endereço	
	String descricao,				 				// Descrição ou Apelido do Endereço
			bairro,									// Bairro do Endereço
			estado,									// Estado do Endereco
			cidade,									// Cidade do Endereço
			pais,									// Pais do Endereço
			cep,									// Cep do Endereço
			logradouro,								// Rua do Endereço
			numero,									// Numero do Endereço
			complemento,							// Complemento do Endereco
			referencia;								// Referenca do Endereço
	Endereco_Tipo_Logradouro tipo_logradouro;		// Tipo do Logradouro do Endereço
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Endereco_Tipo_Logradouro getTipo_logradouro() {
		return tipo_logradouro;
	}
	public void setTipo_logradouro(Endereco_Tipo_Logradouro tipo_logradouro) {
		this.tipo_logradouro = tipo_logradouro;
	}
}
