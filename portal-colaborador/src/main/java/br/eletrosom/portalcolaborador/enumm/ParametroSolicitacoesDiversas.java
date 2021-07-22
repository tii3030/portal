package br.eletrosom.portalcolaborador.enumm;

public enum ParametroSolicitacoesDiversas {

	CRA("Crachá"), UNI("Uniforme"), ALI("Vale Alimentação"), TRA("Vale Transporte"), SAU("Plano Saúde"),
	ODO("Plano Odontológico"), DRO("Convênio Drogasil");

	private String descricao;

	private ParametroSolicitacoesDiversas(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
