package br.eletrosom.portalcolaborador.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.eletrosom.portalcolaborador.entityPk.AcessosPk;

/*
 * ENTIDADE DO CONTROLE DE ACESSO DOS PERFIS DE USUARIO
 */
@Table(name = "acessos", schema = "public")
@Entity
public class Acessos {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 202103031050L;

	@EmbeddedId
	private AcessosPk acessosPK;

	@Column(name = "action")
	private String action;

	public Acessos(AcessosPk acessosPK, String action) {
		super();
		this.acessosPK = acessosPK;
		this.action = action;
	}

	public Acessos() {
		super();
	}

	public AcessosPk getAcessosPK() {
		return acessosPK;
	}

	public void setAcessosPK(AcessosPk acessosPK) {
		this.acessosPK = acessosPK;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
