package br.eletrosom.portalcolaborador.view;

import java.util.Calendar;
import java.util.Date;

import br.eletrosom.portalcolaborador.utils.DateUtil;
/*
 * VISÃO DE USUARIO
 */
public class UsuarioView {

	private String codigo;

	private String senha;

	private String chaveColaborador;

	private String nome;

	private String perfil;

	private String filial;

	private String cargo;

	private Date dataAdmissao;

	private String identificacao;

	private String admissao;
	
	private String filialCodigo;

	public UsuarioView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioView(String codigo, String senha, String chaveColaborador, String nome, String perfil,
			String filial, String cargo, Date dataAdmissao,String filialCodigo) {
		super();
		this.codigo = codigo;
		this.senha = senha;
		this.chaveColaborador = chaveColaborador;
		this.nome = nome;
		this.perfil = perfil;
		this.filial = filial;
		this.cargo = cargo;
		this.dataAdmissao = dataAdmissao;
		this.filialCodigo = filialCodigo;
	}
	
	public UsuarioView(String codigo, String nome, String perfil,
			String filial, String cargo, String admissao, String identificacao) {
		this.codigo = codigo;
		this.nome = nome;
		this.perfil = perfil;
		this.filial = filial;
		this.cargo = cargo;
		this.admissao = admissao;
		this.identificacao = identificacao;
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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
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

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public void setIdentificacaoFormatado(String codigo, String nome) {
		this.setIdentificacao(codigo + " - " + nome);
	}

	public String getAdmissao() {
		return this.admissao;
	}

	public void setAdmissao(Date dataAdmissao) {

		this.admissao = new DateUtil().converteDateView(Calendar.getInstance().getTime());

	}

	public String getFilialCodigo() {
		return filialCodigo;
	}

	public void setFilialCodigo(String filialCodigo) {
		this.filialCodigo = filialCodigo;
	}
	

}
