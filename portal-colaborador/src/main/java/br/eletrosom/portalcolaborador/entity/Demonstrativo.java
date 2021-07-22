package br.eletrosom.portalcolaborador.entity;

public class Demonstrativo extends Documento{


	public Demonstrativo(String usuario, String empresa, String dataIni, String dataFim, String mesref) {
		super();
		this.setUsuario(usuario);
		this.setEmpresa(empresa);
		this.setDataIni(dataIni);
		this.setDataFim(dataFim);
		this.setMesref(mesref);
	}

}
