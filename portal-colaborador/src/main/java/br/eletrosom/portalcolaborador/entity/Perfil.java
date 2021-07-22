package br.eletrosom.portalcolaborador.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * ENTIDADE QUE REPRESENTA O PERFIL DE USUARIO
 */
@Table(name = "perfil", schema = "public")
@Entity
public class Perfil {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 202103031049L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String nome;

	public Perfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Perfil(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
}
