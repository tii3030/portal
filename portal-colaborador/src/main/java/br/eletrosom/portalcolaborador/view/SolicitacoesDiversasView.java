package br.eletrosom.portalcolaborador.view;

/*
 * VISÃO DE SOLICITAÇÕES DIVERSAS
 */
public class SolicitacoesDiversasView {

	private Long id;

	private String empresa;

	private String codigoUsuario;

//	private Date dataSolicitacao;

	private String dataSolicitacaoFormatada;

	// private Short tipo;

	private String tipoDescricao;

	private String obsa;

	private String obsb;

	private String obsc;

	private Integer quantidade;

	private Integer mesRef;

	private String upload;

	private String status;

	// private Date dataPrevisao;

	private String previsaoFormatado;

	// private Date dataEntrega;

	private String entregaFormatada;

	private Integer paramEntrega;

	// private String solicitacao;

	private String solicitacaoDescricao;

	public SolicitacoesDiversasView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SolicitacoesDiversasView(Long id, String empresa, String codigoUsuario, String dataSolicitacaoFormatada,
			String tipoDescricao, String obsa, String obsb, String obsc, Integer quantidade, Integer mesRef,
			String upload, String status, String previsaoFormatado, String entregaFormatada, Integer paramEntrega,
			String solicitacaoDescricao) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.codigoUsuario = codigoUsuario;
		this.dataSolicitacaoFormatada = dataSolicitacaoFormatada;
		this.tipoDescricao = tipoDescricao;
		this.obsa = obsa;
		this.obsb = obsb;
		this.obsc = obsc;
		this.quantidade = quantidade;
		this.mesRef = mesRef;
		this.upload = upload;
		this.status = status;
		this.previsaoFormatado = previsaoFormatado;
		this.entregaFormatada = entregaFormatada;
		this.paramEntrega = paramEntrega;
		this.solicitacaoDescricao = solicitacaoDescricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getDataSolicitacaoFormatada() {
		return dataSolicitacaoFormatada;
	}

	public void setDataSolicitacaoFormatada(String dataSolicitacaoFormatada) {
		this.dataSolicitacaoFormatada = dataSolicitacaoFormatada;
	}

	public String getTipoDescricao() {
		return tipoDescricao;
	}

	public void setTipoDescricao(String tipoDescricao) {
		this.tipoDescricao = tipoDescricao;
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

	public String getPrevisaoFormatado() {
		return previsaoFormatado;
	}

	public void setPrevisaoFormatado(String previsaoFormatado) {
		this.previsaoFormatado = previsaoFormatado;
	}

	public String getEntregaFormatada() {
		return entregaFormatada;
	}

	public void setEntregaFormatada(String entregaFormatada) {
		this.entregaFormatada = entregaFormatada;
	}

	public Integer getParamEntrega() {
		return paramEntrega;
	}

	public void setParamEntrega(Integer paramEntrega) {
		this.paramEntrega = paramEntrega;
	}

	public String getSolicitacaoDescricao() {
		return solicitacaoDescricao;
	}

	public void setSolicitacaoDescricao(String solicitacaoDescricao) {
		this.solicitacaoDescricao = solicitacaoDescricao;
	}

}
