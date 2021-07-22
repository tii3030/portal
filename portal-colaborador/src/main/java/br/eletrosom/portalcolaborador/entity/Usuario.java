package br.eletrosom.portalcolaborador.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * ENTIDADE QUE REPESENTA OS DADOS DE LOGIN DO USUARIO
 */
@Table(name = "sea01", schema = "public")
@Entity
public class Usuario {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2021022211049L;

	@Id
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "senha_web")
	private String senha;

	@Column(name = "chave_pc")
	private String chaveColaborador;

	@Column(name = "nmusu")
	private String nome;

	@Column(name = "profile_id")
	private Integer perfil;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empren", referencedColumnName = "ndemp")
	//@Column(name = "empren")
	private Empresa filial;

	@Column(name = "car_fun")
	private String cargo;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "admis")
	private Date dataAdmissao;

	@Column(name = "sr_deleted")
	private String deleted;

	@Transient 
	private Boolean ativo;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String codigo, String senha, String deleted) {
		super();
		this.codigo = codigo;
		this.senha = senha;
		this.deleted = deleted;
		this.setAtivo(deleted.equals("") ? true : false);
	}

	public Usuario(String codigo, String senha, String chaveColaborador, String deleted) {
		super();
		this.codigo = codigo;
		this.senha = senha;
		this.chaveColaborador = chaveColaborador;
		this.deleted = deleted;
		this.setAtivo(deleted.equals("") ? true : false);
	}


	public Usuario(String codigo, String senha, String chaveColaborador, String nome, Integer perfil,
			Empresa filial, String cargo, Date dataAdmissao, String deleted) {
		super();
		this.codigo = codigo;
		this.senha = senha;
		this.chaveColaborador = chaveColaborador;
		this.nome = nome;
		this.perfil = perfil;
		this.filial = filial;
		this.cargo = cargo;
		this.dataAdmissao = dataAdmissao;
		this.deleted = deleted;
		this.setAtivo(deleted.equals("") ? true : false);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getChaveColaborador() {
		return chaveColaborador;
	}

	public void setChaveColaborador(String chaveColaborador) {
		this.chaveColaborador = chaveColaborador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public Empresa getFilial() {
		return filial;
	}

	public void setFilial(Empresa filial) {
		this.filial = filial;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((chaveColaborador == null) ? 0 : chaveColaborador.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (chaveColaborador == null) {
			if (other.chaveColaborador != null)
				return false;
		} else if (!chaveColaborador.equals(other.chaveColaborador))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataAdmissao == null) {
			if (other.dataAdmissao != null)
				return false;
		} else if (!dataAdmissao.equals(other.dataAdmissao))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
