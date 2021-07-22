package br.eletrosom.portalcolaborador.model;

import java.util.ArrayList;
import java.util.List;

public class PerfilModel {

	private Integer id;

	private String nome;
	
	private List<String> acessosPerfil = new ArrayList<String>();

	public PerfilModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerfilModel(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	

	public PerfilModel(Integer id, String nome, List<String> acessosPerfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.acessosPerfil = acessosPerfil;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getAcessosPerfil() {
		return acessosPerfil;
	}

	public void setAcessosPerfil(List<String> acessosPerfil) {
		this.acessosPerfil = acessosPerfil;
	}

}
