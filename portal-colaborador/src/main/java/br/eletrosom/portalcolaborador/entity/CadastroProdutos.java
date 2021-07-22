package br.eletrosom.portalcolaborador.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * ENTIDADE DE CADASTRO DE PRODUTOS
 */
@Table(name = "pcolab_cadprod", schema = "public")
@Entity
public class CadastroProdutos {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "tipo_solicitacao")
	private String tipo;

	@Column(name = "preco")
	private Double preco;

	@Column(name = "unidade")
	private String unidade;

	@Column(name = "prazo_entrega")
	private Integer prazoEntrega;

	@Column(name = "descricao")
	private String descricao;

	public CadastroProdutos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CadastroProdutos(Long id, String codigo, String tipo, Double preco, String unidade, Integer prazoEntrega,
			String descricao) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipo = tipo;
		this.preco = preco;
		this.unidade = unidade;
		this.prazoEntrega = prazoEntrega;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Integer getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(Integer prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prazoEntrega == null) ? 0 : prazoEntrega.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		CadastroProdutos other = (CadastroProdutos) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prazoEntrega == null) {
			if (other.prazoEntrega != null)
				return false;
		} else if (!prazoEntrega.equals(other.prazoEntrega))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

	

}
