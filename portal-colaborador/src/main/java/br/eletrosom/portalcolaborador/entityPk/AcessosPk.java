package br.eletrosom.portalcolaborador.entityPk;

import java.io.Serializable;

import javax.persistence.Embeddable;
/*
 * CHAVE PRIMARIA DA ENTIDADE ACESSOS
 */
@Embeddable
public class AcessosPk implements Serializable {

	private static final long serialVersionUID = 1L;

	private String node;

	private Integer profile_id;

	public AcessosPk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AcessosPk(String node, Integer profile_id) {
		super();
		this.node = node;
		this.profile_id = profile_id;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public Integer getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(Integer profile_id) {
		this.profile_id = profile_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		result = prime * result + ((profile_id == null) ? 0 : profile_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcessosPk other = (AcessosPk) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		if (profile_id == null) {
			if (other.profile_id != null)
				return false;
		} else if (!profile_id.equals(other.profile_id))
			return false;
		return true;
	}

}
