package br.eletrosom.portalcolaborador.entity;

public abstract class Documento {
	private String usuario;
	private String empresa;
	private String dataIni;
	private String dataFim;
	private String mesref;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDataIni() {
		return dataIni;
	}

	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	
	public String getMesref() {
		return mesref;
	}

	public void setMesref(String mesref) {
		this.mesref = mesref;
	}

	public Documento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
