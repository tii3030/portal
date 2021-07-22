package br.eletrosom.portalcolaborador.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSecurityModel extends User {

	private static final long serialVersionUID = 202103031121L;

	public UsuarioSecurityModel(String codigo, String senha, Boolean ativo,
			Collection<? extends GrantedAuthority> authorities) {
		super(codigo, senha, ativo, true, true, true, authorities);
	}

	/*public UsuarioSecurityModel(String codigo, String senha, String chave, Boolean ativo,
			Collection<? extends GrantedAuthority> authorities) {
		super(codigo, senha, ativo, true, true, true, authorities);
	}*/

}
