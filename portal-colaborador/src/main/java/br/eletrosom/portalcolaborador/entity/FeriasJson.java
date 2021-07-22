package br.eletrosom.portalcolaborador.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeriasJson {

	private String inireq;
	private String fimreq;
	private String inifer;
	private String fimfer;
	private String dtpgto;
	private String diasfer;
	private String saldfer;
	private String dtlimi;
	private String mensagem;

	public FeriasJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeriasJson(String inireq, String fimreq, String inifer, String fimfer, String dtpgto, String diasfer,
			String saldfer, String dtlimi, String mensagem) {
		super();
		this.inireq = inireq;
		this.fimreq = fimreq;
		this.inifer = inifer;
		this.fimfer = fimfer;
		this.dtpgto = dtpgto;
		this.diasfer = diasfer;
		this.saldfer = saldfer;
		this.dtlimi = dtlimi;
		this.mensagem = mensagem;
	}

	public String getInireq() {
		return inireq;
	}

	public void setInireq(String inireq) {
		this.inireq = inireq;
	}

	public String getFimreq() {
		return fimreq;
	}

	public void setFimreq(String fimreq) {
		this.fimreq = fimreq;
	}

	public String getInifer() {
		return inifer;
	}

	public void setInifer(String inifer) {
		this.inifer = inifer;
	}

	public String getFimfer() {
		return fimfer;
	}

	public void setFimfer(String fimfer) {
		this.fimfer = fimfer;
	}

	public String getDtpgto() {
		return dtpgto;
	}

	public void setDtpgto(String dtpgto) {
		this.dtpgto = dtpgto;
	}

	public String getDiasfer() {
		return diasfer;
	}

	public void setDiasfer(String diasfer) {
		this.diasfer = diasfer;
	}

	public String getSaldfer() {
		return saldfer;
	}

	public void setSaldfer(String saldfer) {
		this.saldfer = saldfer;
	}

	public String getDtlimi() {
		return dtlimi;
	}

	public void setDtlimi(String dtlimi) {
		this.dtlimi = dtlimi;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
