package br.eletrosom.portalcolaborador.entityPk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

/*
 * CHAVE PRIMARIA DA ENTIDADE SOLICITACOESDIVERSAS
 */
@Embeddable
public class SolicitacoesPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoUsuario;

	private String solicitacao;

	private Date dataSolicitacao;

	public SolicitacoesPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SolicitacoesPK(String codigoUsuario, String solicitacao, Date dataSolicitacao) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.solicitacao = solicitacao;
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());
		result = prime * result + ((dataSolicitacao == null) ? 0 : dataSolicitacao.hashCode());
		result = prime * result + ((solicitacao == null) ? 0 : solicitacao.hashCode());
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
		SolicitacoesPK other = (SolicitacoesPK) obj;
		if (codigoUsuario == null) {
			if (other.codigoUsuario != null)
				return false;
		} else if (!codigoUsuario.equals(other.codigoUsuario))
			return false;
		if (dataSolicitacao == null) {
			if (other.dataSolicitacao != null)
				return false;
		} else if (!dataSolicitacao.equals(other.dataSolicitacao))
			return false;
		if (solicitacao == null) {
			if (other.solicitacao != null)
				return false;
		} else if (!solicitacao.equals(other.solicitacao))
			return false;
		return true;
	}

}
