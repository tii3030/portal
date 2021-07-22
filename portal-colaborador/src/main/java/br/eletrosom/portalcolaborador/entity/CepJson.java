package br.eletrosom.portalcolaborador.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CepJson {

	private String codcid;
	private String nomcid;
	private String estcid;
	private String codbai;
	private String nombai;
	private String cepbai;
	private String mensagem;

	public CepJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CepJson(String codcid, String nomcid, String estcid, String codbai, String nombai, String cepbai,
			String mensagem) {
		super();
		this.codcid = codcid;
		this.nomcid = nomcid;
		this.estcid = estcid;
		this.codbai = codbai;
		this.nombai = nombai;
		this.cepbai = cepbai;
		this.mensagem = mensagem;
	}

	public String getCodcid() {
		return codcid;
	}

	public void setCodcid(String codcid) {
		this.codcid = codcid;
	}

	public String getNomcid() {
		return nomcid;
	}

	public void setNomcid(String nomcid) {
		this.nomcid = nomcid;
	}

	public String getEstcid() {
		return estcid;
	}

	public void setEstcid(String estcid) {
		this.estcid = estcid;
	}

	public String getCodbai() {
		return codbai;
	}

	public void setCodbai(String codbai) {
		this.codbai = codbai;
	}

	public String getNombai() {
		return nombai;
	}

	public void setNombai(String nombai) {
		this.nombai = nombai;
	}

	public String getCepbai() {
		return cepbai;
	}

	public void setCepbai(String cepbai) {
		this.cepbai = cepbai;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
