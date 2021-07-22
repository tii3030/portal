package br.eletrosom.portalcolaborador.model;

import java.util.List;

import br.eletrosom.portalcolaborador.entity.Ferias;

public class ListaFerias {

	private String gestor;

	private String departamento;

	private List<Ferias> lista;

	public ListaFerias() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListaFerias(String gestor, String departamento, List<Ferias> lista) {
		super();
		this.gestor = gestor;
		this.departamento = departamento;
		this.lista = lista;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Ferias> getLista() {
		return lista;
	}

	public void setLista(List<Ferias> lista) {
		this.lista = lista;
	}

}
