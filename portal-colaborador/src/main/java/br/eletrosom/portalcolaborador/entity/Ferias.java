package br.eletrosom.portalcolaborador.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pcolab_ferias", schema = "public")
@Entity
public class Ferias {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "data_solicitacao")
	private Date dataSolicitacao;

	@Column(name = "per_aqu_ini")
	private Date periodoInicio;

	@Column(name = "per_aqu_fim")
	private Date periodoFim;

	@Column(name = "inicio")
	private Date inidio;

	@Column(name = "fim")
	private Date fim;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "status")
	private String status;

	@Column(name = "obs")
	private String obs;

	@Column(name = "saldo")
	private Integer saldo;

	@Column(name = "codges")
	private String codges;

	@Column(name = "nome")
	private String nome;

	public Ferias() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ferias(Long id, String usuario, Date dataSolicitacao, Date periodoInicio, Date periodoFim, Date inidio,
			Date fim, Integer quantidade, String status, String obs, Integer saldo, String codges, String nome) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.dataSolicitacao = dataSolicitacao;
		this.periodoInicio = periodoInicio;
		this.periodoFim = periodoFim;
		this.inidio = inidio;
		this.fim = fim;
		this.quantidade = quantidade;
		this.status = status;
		this.obs = obs;
		this.saldo = saldo;
		this.codges = codges;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Date getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public Date getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(Date periodoFim) {
		this.periodoFim = periodoFim;
	}

	public Date getInidio() {
		return inidio;
	}

	public void setInidio(Date inidio) {
		this.inidio = inidio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public String getCodges() {
		return codges;
	}

	public void setCodges(String codges) {
		this.codges = codges;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
