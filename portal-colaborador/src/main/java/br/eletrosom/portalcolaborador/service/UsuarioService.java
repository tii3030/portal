package br.eletrosom.portalcolaborador.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eletrosom.portalcolaborador.entity.Acessos;
import br.eletrosom.portalcolaborador.entity.Perfil;
import br.eletrosom.portalcolaborador.entity.Usuario;
import br.eletrosom.portalcolaborador.model.UsuarioModel;
import br.eletrosom.portalcolaborador.model.UsuarioSecurityModel;
import br.eletrosom.portalcolaborador.repository.AcessosRepository;
import br.eletrosom.portalcolaborador.repository.PerfilRepository;
import br.eletrosom.portalcolaborador.repository.UsuarioRepository;
import br.eletrosom.portalcolaborador.view.UsuarioView;

/*
 * SERVIÇOS DE USUARIO
 */
@Service
@Transactional
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository grupoRepository;

	@Autowired
	private AcessosRepository permissaoRepository;

	//CARREGA USUARIO POR CODIGO
	@Override
	public UserDetails loadUserByUsername(String codigo) throws BadCredentialsException, DisabledException {

		Usuario usuarioEntity = usuarioRepository.findByCodigo(codigo);

		// usuarioEntity.setSenha(new
		// BCryptPasswordEncoder().encode(usuarioEntity.getSenha()));

		usuarioEntity.setAtivo(usuarioEntity.getDeleted().equals(" ") ? true : false);

		// Usuario usuarioEntity = usuarioRepository.findByCodigoAndSenha(codigo, senha)

		if (!usuarioEntity.isAtivo() || usuarioEntity == null)
			throw new DisabledException("Usuário não encontrado ou não está ativo no sistema!");
		
		UserDetails details = new UsuarioSecurityModel(usuarioEntity.getCodigo(), usuarioEntity.getSenha(), usuarioEntity.isAtivo(),
				this.buscarPermissoesDosGrupos(usuarioEntity));

		return details;
	}

	/***
	 * BUSCA AS PERMISSÕES DO PERFIL
	 */
	public List<GrantedAuthority> buscarPermissoesDosGrupos(Usuario usuarioEntity) {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

		List<Acessos> lista = permissaoRepository.findByAcessosPkProfileId(usuarioEntity.getPerfil());

		// List<Acessos> lista =
		// permissaoRepository.findByAcessosPkProfileIdPortal(usuarioEntity.getPerfil());

		if (usuarioEntity.isAtivo()) {
			auths.add(new SimpleGrantedAuthority("ACESSO_USUARIO"));
		}
		
		if(lista == null) {
			return auths;
		}

		for (Acessos permissao : lista) {
			String result = "";
			switch (permissao.getAcessosPK().getNode()) {
			case "portal_colaborador.gestor":
				result = "ACESSO_GESTOR";
				break;
			case "portal_colaborador.rh":
				result = "ACESSO_RH";
				break;
			}
			if (!result.equals("")) {
				auths.add(new SimpleGrantedAuthority(result));
			}

		}

		return auths;
	}
	
	/*public UserDetails updateUserByUsername(Usuario usuarioEntity) throws BadCredentialsException, DisabledException {

	//	Usuario usuarioEntity = usuarioRepository.findByCodigo(codigo);

	//	usuarioEntity.setAtivo(usuarioEntity.getDeleted().equals(" ") ? true : false);

		List<GrantedAuthority> auths = this.buscarPermissoesDosGrupos(usuarioEntity);
		
	//	UserDetails sec = this.loadUserByUsername(codigo);
		
		auths.add(new SimpleGrantedAuthority("ACESSO_USUARIO_SEG"));
		
		return new UsuarioSecurityModel(usuarioEntity.getCodigo(), usuarioEntity.getSenha(), usuarioEntity.isAtivo(),
				auths);
	}*/

	/***
	 * CONSULTA OS USUÁRIOS CADASTRADOS
	 * 
	 * @return
	 */
	public List<UsuarioModel> consultarUsuarios() {

		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();

		List<Usuario> usuariosEntity = this.usuarioRepository.findAll();

		// Perfil usuarioPerfil = this.grupoRepository.findById(profileId)

		usuariosEntity.forEach(usuarioEntity -> {

			usuariosModel.add(new UsuarioModel(usuarioEntity.getCodigo(), usuarioEntity.getSenha(), null));
		});

		return usuariosModel;
	}

	/***
	 * CONSULTA UM USUÁRIO PELO SEU CÓDIGO
	 * APRESENTA OS DADOS NO CABEÇALHO DA PAGINA
	 * @param codigoUsuario
	 * @return
	 */
	public UsuarioView buscarUsuarioView(UsuarioView view, String codigoUsuario) {

		Usuario usuarioEntity = this.usuarioRepository.findByCodigo(codigoUsuario);

		Perfil perfilU = this.grupoRepository.findById(usuarioEntity.getPerfil());
		
		view.setCodigo(usuarioEntity.getCodigo());
		view.setNome(usuarioEntity.getNome());
		view.setPerfil(perfilU != null ? perfilU.getNome() : "");
		view.setFilial(
				usuarioEntity.getFilial().getCodigo() + " - " + usuarioEntity.getFilial().getRazaoSocial());
		view.setCargo(usuarioEntity.getCargo());
		view.setDataAdmissao(usuarioEntity.getDataAdmissao());
		view.setAdmissao(usuarioEntity.getDataAdmissao());
		view.setIdentificacaoFormatado(usuarioEntity.getCodigo(), usuarioEntity.getNome());
		view.setFilialCodigo(usuarioEntity.getFilial().getCodigo());

		return view;

	}

	//RETORNA USUARIO POR CODIGO
	public Usuario getUsuario(String codigoUsuario) {

		Usuario usuarioEntity = this.usuarioRepository.findByCodigo(codigoUsuario);

		return usuarioEntity;

	}

	//SALVA CHAVE DE SEGURANÇA 
	public void alterarUsuario(UsuarioModel usuarioModel) {

		Usuario usuarioEntity = this.usuarioRepository.findByCodigo(usuarioModel.getCodigo());

		if (usuarioEntity == null) {
			throw new DisabledException("Usuário ou senha não encontrados!");
		}

		/* Chave de Segurança */
		usuarioEntity.setChaveColaborador(usuarioModel.getChave());

		this.usuarioRepository.save(usuarioEntity);

	}

}
