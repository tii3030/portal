package br.eletrosom.portalcolaborador.model;

import java.time.LocalDate;

public class UsuarioModel {

	// @NotEmpty(message = "O Codigo é de preenchimento obrigatório.")
	private String codigo;

	// @NotEmpty(message = "O Nome é de preenchimento obrigatório.")
	// private String nome;

	// @NotEmpty(message = "A Senha é de preenchimento obrigatório.")
	private String senha;

	// @NotEmpty(message = "A chave é de preenchimento obrigatório.")
	private String chave;

	private Boolean ativo;

	// private Perfil perfil;

	public UsuarioModel() {

		System.out.println("Login em " + LocalDate.now());
	}

	public UsuarioModel(String codigo, String senha, Boolean ativo) {
		super();
		this.codigo = codigo;
		this.senha = senha;
		this.ativo = ativo;

	}

	public UsuarioModel(String codigo, String senha, String chave, Boolean ativo) {
		super();
		this.codigo = codigo;
		this.ativo = ativo;
		this.senha = senha;
		this.chave = chave;

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
