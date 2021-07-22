package br.eletrosom.portalcolaborador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.eletrosom.portalcolaborador.details.UsuarioDetails;
import br.eletrosom.portalcolaborador.entity.Usuario;
import br.eletrosom.portalcolaborador.repository.UsuarioRepository;

/*
 * SERVIÇO DE AUTENTICAÇÃO DE USUARIO
 */
public class UsuarioDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repositorio;

	//CARREGA USUARIO POR CODIGO
	@Override
	public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
		
		Usuario usuario = repositorio.findByCodigo(codigo);
		
		if (usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
         
        return new UsuarioDetails(usuario);
		
	}

}
