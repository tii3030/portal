package br.eletrosom.portalcolaborador.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pcolab_log", schema = "public")
@Entity
public class Log {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "tipo_operacao")
	private String tipo;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "data")
	private Date data;

	@Column(name = "time")
	private String time;

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(Long id, String tipo, String usuario, Date data, String time) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.usuario = usuario;
		this.data = data;
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
