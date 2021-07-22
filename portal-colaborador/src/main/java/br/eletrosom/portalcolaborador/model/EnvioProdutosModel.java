package br.eletrosom.portalcolaborador.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.eletrosom.portalcolaborador.entity.SolicitacoesDiversas;

public class EnvioProdutosModel {

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataEnvio;

	private boolean selected = false;

	private SolicitacoesDiversas solicitacaoDiversa;

	List<SolicitacoesDiversas> lista = new ArrayList<SolicitacoesDiversas>();

	public EnvioProdutosModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public SolicitacoesDiversas getSolicitacaoDiversa() {
		return solicitacaoDiversa;
	}

	public void setSolicitacaoDiversa(SolicitacoesDiversas solicitacaoDiversa) {
		this.solicitacaoDiversa = solicitacaoDiversa;
	}

	public EnvioProdutosModel(List<SolicitacoesDiversas> lista) {
		super();
		this.lista = lista;
	}

	public List<SolicitacoesDiversas> getLista() {
		return lista;
	}

	public void setLista(List<SolicitacoesDiversas> lista) {
		this.lista = lista;
	}

}
