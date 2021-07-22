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

//ENTIDADE QUE REPRESENTA AS SOLICITAÇÕES SALVAS
@Table(name = "pcolab_solicitacoes", schema = "public")
@Entity
public class SolicitacoesDiversas {
	
	@Id
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "empresa", referencedColumnName = "ndemp")
	private Empresa empresa;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "usuario", referencedColumnName = "codigo")
	private Usuario usuario;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_solicitacao")
	private Date dataSolicitacao;

	// criar classe de rotulo dos tipos
	@Column(name = "tipo")
	private Short tipo;

	@Column(name = "obs_a")
	private String obsa;
	
	@Column(name = "obs_b")
	private String obsb;
	
	@Column(name = "obs_c")
	private String obsc;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "mesref")
	private Integer mesRef;

	@Column(name = "upload")
	private String upload;

	@Column(name = "status")
	private String status;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dt_prev")
	private Date dataPrevisao;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dt_entrega")
	private Date dataEntrega;

	@Column(name = "param_entrega")
	private Integer paramEntrega;
	
	@Column(name = "solicitacao")
	private String solicitacao;
	
	@Transient
	private boolean selected = false;

	public SolicitacoesDiversas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SolicitacoesDiversas(Long id, Empresa empresa, Usuario usuario, Date dataSolicitacao, Short tipo,
			String obsa, String obsb, String obsc, Integer quantidade, Integer mesRef, String upload, String status,
			Date dataPrevisao, Date dataEntrega, Integer paramEntrega, String solicitacao) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.usuario = usuario;
		this.dataSolicitacao = dataSolicitacao;
		this.tipo = tipo;
		this.obsa = obsa;
		this.obsb = obsb;
		this.obsc = obsc;
		this.quantidade = quantidade;
		this.mesRef = mesRef;
		this.upload = upload;
		this.status = status;
		this.dataPrevisao = dataPrevisao;
		this.dataEntrega = dataEntrega;
		this.paramEntrega = paramEntrega;
		this.solicitacao = solicitacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Short getTipo() {
		return tipo;
	}

	public void setTipo(Short tipo) {
		this.tipo = tipo;
	}

	public String getObsa() {
		return obsa;
	}

	public void setObsa(String obsa) {
		this.obsa = obsa;
	}

	public String getObsb() {
		return obsb;
	}

	public void setObsb(String obsb) {
		this.obsb = obsb;
	}

	public String getObsc() {
		return obsc;
	}

	public void setObsc(String obsc) {
		this.obsc = obsc;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getMesRef() {
		return mesRef;
	}

	public void setMesRef(Integer mesRef) {
		this.mesRef = mesRef;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(Date dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getParamEntrega() {
		return paramEntrega;
	}

	public void setParamEntrega(Integer paramEntrega) {
		this.paramEntrega = paramEntrega;
	}

	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataEntrega == null) ? 0 : dataEntrega.hashCode());
		result = prime * result + ((dataPrevisao == null) ? 0 : dataPrevisao.hashCode());
		result = prime * result + ((dataSolicitacao == null) ? 0 : dataSolicitacao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mesRef == null) ? 0 : mesRef.hashCode());
		result = prime * result + ((obsa == null) ? 0 : obsa.hashCode());
		result = prime * result + ((obsb == null) ? 0 : obsb.hashCode());
		result = prime * result + ((obsc == null) ? 0 : obsc.hashCode());
		result = prime * result + ((paramEntrega == null) ? 0 : paramEntrega.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((solicitacao == null) ? 0 : solicitacao.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((upload == null) ? 0 : upload.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		SolicitacoesDiversas other = (SolicitacoesDiversas) obj;
		if (dataEntrega == null) {
			if (other.dataEntrega != null)
				return false;
		} else if (!dataEntrega.equals(other.dataEntrega))
			return false;
		if (dataPrevisao == null) {
			if (other.dataPrevisao != null)
				return false;
		} else if (!dataPrevisao.equals(other.dataPrevisao))
			return false;
		if (dataSolicitacao == null) {
			if (other.dataSolicitacao != null)
				return false;
		} else if (!dataSolicitacao.equals(other.dataSolicitacao))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mesRef == null) {
			if (other.mesRef != null)
				return false;
		} else if (!mesRef.equals(other.mesRef))
			return false;
		if (obsa == null) {
			if (other.obsa != null)
				return false;
		} else if (!obsa.equals(other.obsa))
			return false;
		if (obsb == null) {
			if (other.obsb != null)
				return false;
		} else if (!obsb.equals(other.obsb))
			return false;
		if (obsc == null) {
			if (other.obsc != null)
				return false;
		} else if (!obsc.equals(other.obsc))
			return false;
		if (paramEntrega == null) {
			if (other.paramEntrega != null)
				return false;
		} else if (!paramEntrega.equals(other.paramEntrega))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (solicitacao == null) {
			if (other.solicitacao != null)
				return false;
		} else if (!solicitacao.equals(other.solicitacao))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (upload == null) {
			if (other.upload != null)
				return false;
		} else if (!upload.equals(other.upload))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	
}
