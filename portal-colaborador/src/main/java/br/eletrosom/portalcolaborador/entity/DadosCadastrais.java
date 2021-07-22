package br.eletrosom.portalcolaborador.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * ENTIDADE JSON QUE IMPORTA OS DADOS CADASTRAIS RECEBIDOS VIA WS
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosCadastrais {

	private String codigo;
	private String nmusu;
	private String empre;
	private String rzsoc;
	private String cnpj;
	private String cod_dep;
	private String des_dep;
	private String car_fun;
	// private String car_fun;
	private String admis;
	private String sexo;
	// private String estadoCivil;
	private String dat_nas;
	private String cod_nac;
	private String des_nac;
	private String nmmae;
	private String nmpai;
	private String dep_relac;
	private String dep_nome;
	private String end_fun;
	private String numer;
	private String complem;
	private String bai_fun;
	private String cid_fun;
	private String est_fun;
	private String cpf_fun;
	private String dtcpf;
	private String rgnum;
	private String rgorg;
	private String rgdt;
	private String tit_elei;
	private String zona_elei;
	private String secao_elei;
	private String munic_elei;
	private String emis_elei;
	private String cart_trab;
	private String ser_trab;
	private String uf_trab;
	private String dt_trab;
	private String pis;
	private String cod_grau;
	private String desc_grau;
	private String tel_fun;
	private String tel_fun2;
	private String email;
	private String nat_rend;
	private String desc_nat;
	private String gestor;
	private String codges;
	private String end_cep;
	private String erro;

	public DadosCadastrais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DadosCadastrais(String codigo, String nmusu, String empre, String rzsoc, String cnpj, String cod_dep,
			String des_dep, String car_fun, String admis, String sexo, String dat_nas, String cod_nac, String des_nac,
			String nmmae, String nmpai, String dep_relac, String dep_nome, String end_fun, String numer, String complem,
			String bai_fun, String cid_fun, String est_fun, String cpf_fun, String dtcpf, String rgnum, String rgorg,
			String rgdt, String tit_elei, String zona_elei, String secao_elei, String munic_elei, String emis_elei,
			String cart_trab, String ser_trab, String uf_trab, String dt_trab, String pis, String cod_grau,
			String desc_grau, String tel_fun, String tel_fun2, String email, String nat_rend, String desc_nat,
			String gestor, String codges, String end_cep, String erro) {
		super();
		this.codigo = codigo;
		this.nmusu = nmusu;
		this.empre = empre;
		this.rzsoc = rzsoc;
		this.cnpj = cnpj;
		this.cod_dep = cod_dep;
		this.des_dep = des_dep;
		this.car_fun = car_fun;
		this.admis = admis;
		this.sexo = sexo;
		this.dat_nas = dat_nas;
		this.cod_nac = cod_nac;
		this.des_nac = des_nac;
		this.nmmae = nmmae;
		this.nmpai = nmpai;
		this.dep_relac = dep_relac;
		this.dep_nome = dep_nome;
		this.end_fun = end_fun;
		this.numer = numer;
		this.complem = complem;
		this.bai_fun = bai_fun;
		this.cid_fun = cid_fun;
		this.est_fun = est_fun;
		this.cpf_fun = cpf_fun;
		this.dtcpf = dtcpf;
		this.rgnum = rgnum;
		this.rgorg = rgorg;
		this.rgdt = rgdt;
		this.tit_elei = tit_elei;
		this.zona_elei = zona_elei;
		this.secao_elei = secao_elei;
		this.munic_elei = munic_elei;
		this.emis_elei = emis_elei;
		this.cart_trab = cart_trab;
		this.ser_trab = ser_trab;
		this.uf_trab = uf_trab;
		this.dt_trab = dt_trab;
		this.pis = pis;
		this.cod_grau = cod_grau;
		this.desc_grau = desc_grau;
		this.tel_fun = tel_fun;
		this.tel_fun2 = tel_fun2;
		this.email = email;
		this.nat_rend = nat_rend;
		this.desc_nat = desc_nat;
		this.gestor = gestor;
		this.codges = codges;
		this.end_cep = end_cep;
		this.erro = erro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNmusu() {
		return nmusu;
	}

	public void setNmusu(String nmusu) {
		this.nmusu = nmusu;
	}

	public String getEmpre() {
		return empre;
	}

	public void setEmpre(String empre) {
		this.empre = empre;
	}

	public String getRzsoc() {
		return rzsoc;
	}

	public void setRzsoc(String rzsoc) {
		this.rzsoc = rzsoc;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCod_dep() {
		return cod_dep;
	}

	public void setCod_dep(String cod_dep) {
		this.cod_dep = cod_dep;
	}

	public String getDes_dep() {
		return des_dep;
	}

	public void setDes_dep(String des_dep) {
		this.des_dep = des_dep;
	}

	public String getCar_fun() {
		return car_fun;
	}

	public void setCar_fun(String car_fun) {
		this.car_fun = car_fun;
	}

	public String getAdmis() {
		return admis;
	}

	public void setAdmis(String admis) {
		this.admis = admis;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDat_nas() {
		return dat_nas;
	}

	public void setDat_nas(String dat_nas) {
		this.dat_nas = dat_nas;
	}

	public String getCod_nac() {
		return cod_nac;
	}

	public void setCod_nac(String cod_nac) {
		this.cod_nac = cod_nac;
	}

	public String getDes_nac() {
		return des_nac;
	}

	public void setDes_nac(String des_nac) {
		this.des_nac = des_nac;
	}

	public String getNmmae() {
		return nmmae;
	}

	public void setNmmae(String nmmae) {
		this.nmmae = nmmae;
	}

	public String getNmpai() {
		return nmpai;
	}

	public void setNmpai(String nmpai) {
		this.nmpai = nmpai;
	}

	public String getDep_relac() {
		return dep_relac;
	}

	public void setDep_relac(String dep_relac) {
		this.dep_relac = dep_relac;
	}

	public String getDep_nome() {
		return dep_nome;
	}

	public void setDep_nome(String dep_nome) {
		this.dep_nome = dep_nome;
	}

	public String getEnd_fun() {
		return end_fun;
	}

	public void setEnd_fun(String end_fun) {
		this.end_fun = end_fun;
	}

	public String getNumer() {
		return numer;
	}

	public void setNumer(String numer) {
		this.numer = numer;
	}

	public String getBai_fun() {
		return bai_fun;
	}

	public void setBai_fun(String bai_fun) {
		this.bai_fun = bai_fun;
	}

	public String getCid_fun() {
		return cid_fun;
	}

	public void setCid_fun(String cid_fun) {
		this.cid_fun = cid_fun;
	}

	public String getEst_fun() {
		return est_fun;
	}

	public void setEst_fun(String est_fun) {
		this.est_fun = est_fun;
	}

	public String getCpf_fun() {
		return cpf_fun;
	}

	public void setCpf_fun(String cpf_fun) {
		this.cpf_fun = cpf_fun;
	}

	public String getDtcpf() {
		return dtcpf;
	}

	public void setDtcpf(String dtcpf) {
		this.dtcpf = dtcpf;
	}

	public String getRgnum() {
		return rgnum;
	}

	public void setRgnum(String rgnum) {
		this.rgnum = rgnum;
	}

	public String getRgorg() {
		return rgorg;
	}

	public void setRgorg(String rgorg) {
		this.rgorg = rgorg;
	}

	public String getRgdt() {
		return rgdt;
	}

	public void setRgdt(String rgdt) {
		this.rgdt = rgdt;
	}

	public String getTit_elei() {
		return tit_elei;
	}

	public void setTit_elei(String tit_elei) {
		this.tit_elei = tit_elei;
	}

	public String getZona_elei() {
		return zona_elei;
	}

	public void setZona_elei(String zona_elei) {
		this.zona_elei = zona_elei;
	}

	public String getSecao_elei() {
		return secao_elei;
	}

	public void setSecao_elei(String secao_elei) {
		this.secao_elei = secao_elei;
	}

	public String getMunic_elei() {
		return munic_elei;
	}

	public void setMunic_elei(String munic_elei) {
		this.munic_elei = munic_elei;
	}

	public String getEmis_elei() {
		return emis_elei;
	}

	public void setEmis_elei(String emis_elei) {
		this.emis_elei = emis_elei;
	}

	public String getCart_trab() {
		return cart_trab;
	}

	public void setCart_trab(String cart_trab) {
		this.cart_trab = cart_trab;
	}

	public String getSer_trab() {
		return ser_trab;
	}

	public void setSer_trab(String ser_trab) {
		this.ser_trab = ser_trab;
	}

	public String getUf_trab() {
		return uf_trab;
	}

	public void setUf_trab(String uf_trab) {
		this.uf_trab = uf_trab;
	}

	public String getDt_trab() {
		return dt_trab;
	}

	public void setDt_trab(String dt_trab) {
		this.dt_trab = dt_trab;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getCod_grau() {
		return cod_grau;
	}

	public void setCod_grau(String cod_grau) {
		this.cod_grau = cod_grau;
	}

	public String getDesc_grau() {
		return desc_grau;
	}

	public void setDesc_grau(String desc_grau) {
		this.desc_grau = desc_grau;
	}

	public String getTel_fun() {
		return tel_fun;
	}

	public void setTel_fun(String tel_fun) {
		this.tel_fun = tel_fun;
	}

	public String getTel_fun2() {
		return tel_fun2;
	}

	public void setTel_fun2(String tel_fun2) {
		this.tel_fun2 = tel_fun2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNat_rend() {
		return nat_rend;
	}

	public void setNat_rend(String nat_rend) {
		this.nat_rend = nat_rend;
	}

	public String getDesc_nat() {
		return desc_nat;
	}

	public void setDesc_nat(String desc_nat) {
		this.desc_nat = desc_nat;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getCodges() {
		return codges;
	}

	public void setCodges(String codges) {
		this.codges = codges;
	}

	public String getComplem() {
		return complem;
	}

	public void setComplem(String complem) {
		this.complem = complem;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getEnd_cep() {
		return end_cep;
	}

	public void setEnd_cep(String end_cep) {
		this.end_cep = end_cep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admis == null) ? 0 : admis.hashCode());
		result = prime * result + ((bai_fun == null) ? 0 : bai_fun.hashCode());
		result = prime * result + ((car_fun == null) ? 0 : car_fun.hashCode());
		result = prime * result + ((cart_trab == null) ? 0 : cart_trab.hashCode());
		result = prime * result + ((cid_fun == null) ? 0 : cid_fun.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((cod_dep == null) ? 0 : cod_dep.hashCode());
		result = prime * result + ((cod_grau == null) ? 0 : cod_grau.hashCode());
		result = prime * result + ((cod_nac == null) ? 0 : cod_nac.hashCode());
		result = prime * result + ((codges == null) ? 0 : codges.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((complem == null) ? 0 : complem.hashCode());
		result = prime * result + ((cpf_fun == null) ? 0 : cpf_fun.hashCode());
		result = prime * result + ((dat_nas == null) ? 0 : dat_nas.hashCode());
		result = prime * result + ((dep_nome == null) ? 0 : dep_nome.hashCode());
		result = prime * result + ((dep_relac == null) ? 0 : dep_relac.hashCode());
		result = prime * result + ((des_dep == null) ? 0 : des_dep.hashCode());
		result = prime * result + ((des_nac == null) ? 0 : des_nac.hashCode());
		result = prime * result + ((desc_grau == null) ? 0 : desc_grau.hashCode());
		result = prime * result + ((desc_nat == null) ? 0 : desc_nat.hashCode());
		result = prime * result + ((dt_trab == null) ? 0 : dt_trab.hashCode());
		result = prime * result + ((dtcpf == null) ? 0 : dtcpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emis_elei == null) ? 0 : emis_elei.hashCode());
		result = prime * result + ((empre == null) ? 0 : empre.hashCode());
		result = prime * result + ((end_cep == null) ? 0 : end_cep.hashCode());
		result = prime * result + ((end_fun == null) ? 0 : end_fun.hashCode());
		result = prime * result + ((erro == null) ? 0 : erro.hashCode());
		result = prime * result + ((est_fun == null) ? 0 : est_fun.hashCode());
		result = prime * result + ((gestor == null) ? 0 : gestor.hashCode());
		result = prime * result + ((munic_elei == null) ? 0 : munic_elei.hashCode());
		result = prime * result + ((nat_rend == null) ? 0 : nat_rend.hashCode());
		result = prime * result + ((nmmae == null) ? 0 : nmmae.hashCode());
		result = prime * result + ((nmpai == null) ? 0 : nmpai.hashCode());
		result = prime * result + ((nmusu == null) ? 0 : nmusu.hashCode());
		result = prime * result + ((numer == null) ? 0 : numer.hashCode());
		result = prime * result + ((pis == null) ? 0 : pis.hashCode());
		result = prime * result + ((rgdt == null) ? 0 : rgdt.hashCode());
		result = prime * result + ((rgnum == null) ? 0 : rgnum.hashCode());
		result = prime * result + ((rgorg == null) ? 0 : rgorg.hashCode());
		result = prime * result + ((rzsoc == null) ? 0 : rzsoc.hashCode());
		result = prime * result + ((secao_elei == null) ? 0 : secao_elei.hashCode());
		result = prime * result + ((ser_trab == null) ? 0 : ser_trab.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((tel_fun == null) ? 0 : tel_fun.hashCode());
		result = prime * result + ((tel_fun2 == null) ? 0 : tel_fun2.hashCode());
		result = prime * result + ((tit_elei == null) ? 0 : tit_elei.hashCode());
		result = prime * result + ((uf_trab == null) ? 0 : uf_trab.hashCode());
		result = prime * result + ((zona_elei == null) ? 0 : zona_elei.hashCode());
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
		DadosCadastrais other = (DadosCadastrais) obj;
		if (admis == null) {
			if (other.admis != null)
				return false;
		} else if (!admis.equals(other.admis))
			return false;
		if (bai_fun == null) {
			if (other.bai_fun != null)
				return false;
		} else if (!bai_fun.equals(other.bai_fun))
			return false;
		if (car_fun == null) {
			if (other.car_fun != null)
				return false;
		} else if (!car_fun.equals(other.car_fun))
			return false;
		if (cart_trab == null) {
			if (other.cart_trab != null)
				return false;
		} else if (!cart_trab.equals(other.cart_trab))
			return false;
		if (cid_fun == null) {
			if (other.cid_fun != null)
				return false;
		} else if (!cid_fun.equals(other.cid_fun))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (cod_dep == null) {
			if (other.cod_dep != null)
				return false;
		} else if (!cod_dep.equals(other.cod_dep))
			return false;
		if (cod_grau == null) {
			if (other.cod_grau != null)
				return false;
		} else if (!cod_grau.equals(other.cod_grau))
			return false;
		if (cod_nac == null) {
			if (other.cod_nac != null)
				return false;
		} else if (!cod_nac.equals(other.cod_nac))
			return false;
		if (codges == null) {
			if (other.codges != null)
				return false;
		} else if (!codges.equals(other.codges))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (complem == null) {
			if (other.complem != null)
				return false;
		} else if (!complem.equals(other.complem))
			return false;
		if (cpf_fun == null) {
			if (other.cpf_fun != null)
				return false;
		} else if (!cpf_fun.equals(other.cpf_fun))
			return false;
		if (dat_nas == null) {
			if (other.dat_nas != null)
				return false;
		} else if (!dat_nas.equals(other.dat_nas))
			return false;
		if (dep_nome == null) {
			if (other.dep_nome != null)
				return false;
		} else if (!dep_nome.equals(other.dep_nome))
			return false;
		if (dep_relac == null) {
			if (other.dep_relac != null)
				return false;
		} else if (!dep_relac.equals(other.dep_relac))
			return false;
		if (des_dep == null) {
			if (other.des_dep != null)
				return false;
		} else if (!des_dep.equals(other.des_dep))
			return false;
		if (des_nac == null) {
			if (other.des_nac != null)
				return false;
		} else if (!des_nac.equals(other.des_nac))
			return false;
		if (desc_grau == null) {
			if (other.desc_grau != null)
				return false;
		} else if (!desc_grau.equals(other.desc_grau))
			return false;
		if (desc_nat == null) {
			if (other.desc_nat != null)
				return false;
		} else if (!desc_nat.equals(other.desc_nat))
			return false;
		if (dt_trab == null) {
			if (other.dt_trab != null)
				return false;
		} else if (!dt_trab.equals(other.dt_trab))
			return false;
		if (dtcpf == null) {
			if (other.dtcpf != null)
				return false;
		} else if (!dtcpf.equals(other.dtcpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emis_elei == null) {
			if (other.emis_elei != null)
				return false;
		} else if (!emis_elei.equals(other.emis_elei))
			return false;
		if (empre == null) {
			if (other.empre != null)
				return false;
		} else if (!empre.equals(other.empre))
			return false;
		if (end_cep == null) {
			if (other.end_cep != null)
				return false;
		} else if (!end_cep.equals(other.end_cep))
			return false;
		if (end_fun == null) {
			if (other.end_fun != null)
				return false;
		} else if (!end_fun.equals(other.end_fun))
			return false;
		if (erro == null) {
			if (other.erro != null)
				return false;
		} else if (!erro.equals(other.erro))
			return false;
		if (est_fun == null) {
			if (other.est_fun != null)
				return false;
		} else if (!est_fun.equals(other.est_fun))
			return false;
		if (gestor == null) {
			if (other.gestor != null)
				return false;
		} else if (!gestor.equals(other.gestor))
			return false;
		if (munic_elei == null) {
			if (other.munic_elei != null)
				return false;
		} else if (!munic_elei.equals(other.munic_elei))
			return false;
		if (nat_rend == null) {
			if (other.nat_rend != null)
				return false;
		} else if (!nat_rend.equals(other.nat_rend))
			return false;
		if (nmmae == null) {
			if (other.nmmae != null)
				return false;
		} else if (!nmmae.equals(other.nmmae))
			return false;
		if (nmpai == null) {
			if (other.nmpai != null)
				return false;
		} else if (!nmpai.equals(other.nmpai))
			return false;
		if (nmusu == null) {
			if (other.nmusu != null)
				return false;
		} else if (!nmusu.equals(other.nmusu))
			return false;
		if (numer == null) {
			if (other.numer != null)
				return false;
		} else if (!numer.equals(other.numer))
			return false;
		if (pis == null) {
			if (other.pis != null)
				return false;
		} else if (!pis.equals(other.pis))
			return false;
		if (rgdt == null) {
			if (other.rgdt != null)
				return false;
		} else if (!rgdt.equals(other.rgdt))
			return false;
		if (rgnum == null) {
			if (other.rgnum != null)
				return false;
		} else if (!rgnum.equals(other.rgnum))
			return false;
		if (rgorg == null) {
			if (other.rgorg != null)
				return false;
		} else if (!rgorg.equals(other.rgorg))
			return false;
		if (rzsoc == null) {
			if (other.rzsoc != null)
				return false;
		} else if (!rzsoc.equals(other.rzsoc))
			return false;
		if (secao_elei == null) {
			if (other.secao_elei != null)
				return false;
		} else if (!secao_elei.equals(other.secao_elei))
			return false;
		if (ser_trab == null) {
			if (other.ser_trab != null)
				return false;
		} else if (!ser_trab.equals(other.ser_trab))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (tel_fun == null) {
			if (other.tel_fun != null)
				return false;
		} else if (!tel_fun.equals(other.tel_fun))
			return false;
		if (tel_fun2 == null) {
			if (other.tel_fun2 != null)
				return false;
		} else if (!tel_fun2.equals(other.tel_fun2))
			return false;
		if (tit_elei == null) {
			if (other.tit_elei != null)
				return false;
		} else if (!tit_elei.equals(other.tit_elei))
			return false;
		if (uf_trab == null) {
			if (other.uf_trab != null)
				return false;
		} else if (!uf_trab.equals(other.uf_trab))
			return false;
		if (zona_elei == null) {
			if (other.zona_elei != null)
				return false;
		} else if (!zona_elei.equals(other.zona_elei))
			return false;
		return true;
	}

}
