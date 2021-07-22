package br.eletrosom.portalcolaborador.entity;

public class FolhaPonto extends Documento {


	public FolhaPonto(String usuario, String empresa, String dataIni, String dataFim, String mesref) {
		this.setUsuario(usuario);
		this.setEmpresa(empresa);
		this.setDataIni(dataIni);
		this.setDataFim(dataFim);
		this.setMesref(mesref);

	}
}
