package com.example.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.cursomc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "preenchimento obrigatorio.")
	@Length(min = 4, max = 120, message = "Minimo de 4 e o maximo de 120 caracteres.")
	private String nome;
	
	@NotEmpty(message = "preenchimento obrigatorio.")
	@Email(message = "Email invalido.")
	private String email;
	
	@NotEmpty(message = "preenchimento obrigatorio.")
	private String cpfOuCnpj;
	
	@NotNull(message = "preenchimento obrigatorio.")
	private Integer tipo;
	
	@NotEmpty(message = "preenchimento obrigatorio.")
	private String logradouro;
	
	@NotEmpty(message = "preenchimento obrigatorio.")
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	@NotEmpty(message = "preenchimento obrigatorio.")
	private String cep;
	
	@NotEmpty(message = "preenchimento obrigatorio.")
	private String telefone1;
	
	private String telefone2;
	
	private String telefone3;
	
	@NotNull(message = "preenchimento obrigatorio.")
	private Integer cidadeId;
	
	public ClienteNewDTO() {
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	
	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
}
