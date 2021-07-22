package br.eletrosom.portalcolaborador.details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.eletrosom.portalcolaborador.entity.Usuario;

/*
 * DETALHES DO USUARIO LOGADO
 */
public class UsuarioDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public UsuarioDetails(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Set<Acessos> roles = user.getRoles();
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();


		auths.add(new SimpleGrantedAuthority("ACESSO_USUARIO"));



		return auths;
	}

	@Override
	public String getPassword() {
		return this.usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return this.usuario.getCodigo();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.usuario.isAtivo();
	}
	
	public String getNome() {
		return this.usuario.getNome();
	}
	
	public String getFilial() {
		return this.usuario.getFilial().getCodigo();
	}
	
	public Date getAdmissão() {
		return this.usuario.getDataAdmissao();
	}

}
