package br.eletrosom.portalcolaborador.entity;

public class InformeRendimentos extends Documento {

	public InformeRendimentos(String usuario, String empresa, String anoBase, String dataEntrega, String mesref) {
		super();
		this.setUsuario(usuario);
		this.setEmpresa(empresa);
		this.setDataIni(anoBase);
		this.setDataFim(dataEntrega);
		this.setMesref(mesref);
	}

}
